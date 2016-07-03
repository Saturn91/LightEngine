package shaders;

import game.GameMainLoop;
import game.entities.Camera;
import game.entities.Light;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import toolbox.Maths;

public class StaticShader extends ShaderProgramm{
	
	private static final String VERTEX_FILE = "src/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/shaders/fragmentShader.txt";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private Light cameraLight = new Light(new Vector2f(0,0), new Vector3f(1, 1, 1));
	private Vector3f enviromentlight = new Vector3f(0, 0, 0); 

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");			//connect position variable to 0-Attribute of VB0
		super.bindAttribute(1, "textureCoords");	//connect texturecoords to 1-Attribute of VBO
	}
	
	public void configureCameraLight(Light light){
		this.cameraLight = light;
	}
	
	public void setEnviromentLight(Vector3f lightColor){
		this.enviromentlight = lightColor;
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
	}
	
	public void loadTransformationMatrix(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	
	public void loadProjectionMatrix(Matrix4f matrix){
		super.loadMatrix(location_projectionMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera){
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	
	public void update(){
		//update CameraLight
		super.setShaderVariable2f("lightPosition", cameraLight.getPosition());
		super.setShaderVariable3f("pointLightColor", cameraLight.getColor());
		super.setShaderVariablef("pointLightStrenght", cameraLight.getStrenght());
		super.setShaderVariablef("range", cameraLight.getRange());
		
		//update enviromentLight
		super.setShaderVariable3f("enviromentLight", enviromentlight);
	}
	
	
	

}
