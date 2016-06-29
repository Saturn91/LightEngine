package renderEngine;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import entity.Entity;
import shaders.StaticShader;
import toolbox.Maths;

public class Renderer {
	
	private static final float FOV = 70;			//Field of view
	private static final float NEAR_PLANE = 0.1f;	//View nearest distance
	private static final float FAR_PLANE = 1000;	//View in distance
	
	private Matrix4f projectionMatrix;
	
public Renderer(StaticShader shader) {
	createProjectionMatrix();
	
	//didn't get why i need the shader here...
	shader.start();
	shader.loadProjectionMatrix(projectionMatrix);
	shader.stop();
}
	
	public void prepare(){
		GL11.glEnable(GL11.GL_DEPTH_TEST);			//fix rendering of different Triangels on top of each other
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0, 0, 0, 1); 				//red Background
	}
	
	public void render (Entity entity, StaticShader shader){
		TexturedModel model = entity.getModel();
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);		//Enable List 0 in VBO (positions)
		GL20.glEnableVertexAttribArray(1);		//Enable List 1 in VBO (texturecoords)
		Matrix4f transformationMatrix = Maths.createTransformation(entity.getPosition(), entity.getRotX(), //getPos and rot!
				entity.getRotY(), entity.getRotZ(), entity.getScale());	
		shader.loadTransformationMatrix(transformationMatrix);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);	//Activate Texture on Texture0 wich is the default of Texturesampler in fragmentshader!
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, rawModel.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);		
		GL20.glDisableVertexAttribArray(1);		
		GL30.glBindVertexArray(0); //unbind)
	}
	
	private void createProjectionMatrix(){
		float aspectRadio = (float) Display.getWidth()/(float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRadio);
		float x_scale = y_scale / aspectRadio;
		float frustum_lenght = FAR_PLANE - NEAR_PLANE;
		
		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = - ((FAR_PLANE + NEAR_PLANE) / frustum_lenght);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = - ((2 * NEAR_PLANE * FAR_PLANE) / frustum_lenght);
		projectionMatrix.m33 = 0;
	}
}
