package EnginTester;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;

import Textures.ModelTexture;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;

public class MainGameLoop {

	public static void main(String[] args) {
		
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		//Pseudo Data
		float[] vertices = {
			//Left bottom triangle
			-0.5f, 0.5f, 0f,
			-0.5f, -0.5f, 0f,
			0.5f, -0.5f, 0f,
			0.5f, 0.5f, 0f
		};
		
		int[] indices = {
			0,1,3,	//Top Left Triangle
			3,1,2	//Down Right Triangle
		};
		
		float[] textureCoords =  {
				0,0,	//V0
				0,1,	//V1
				1,1,	//V2
				1,0		//V3
		};
		
		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("image"));
		TexturedModel texturedModel = new TexturedModel(model, texture);

		//actual Gameloop
		while(!Display.isCloseRequested()){
			shader.start();
			renderer.prepare();	
			renderer.render(texturedModel);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		loader.cleanUp();
		shader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
