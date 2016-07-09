package game.main;

import game.entities.Camera;
import game.entities.Entity;
import game.entities.GameObject;
import game.entities.Light;
import game.level.Map;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import shaders.StaticShader;
import display.renderer.Renderer;


public class Game {
	
	private static ArrayList<GameObject> gameObjects;
	private Renderer renderer;
	private StaticShader shader;
	private Map map;
	private Camera camera;
	
	public Game() {
		init();
	}
	
	/**
	 * get trough all entities and render them
	 */
	public void render(){
		//Camera
		camera.move();
		//Prepare Renderer
		renderer.prepare();
		
		//Start Shaderprogramm
		shader.start();
		
		shader.update();
		
		shader.loadViewMatrix(camera);
		
		
		for(GameObject g: gameObjects){
			//TODO check wich entities are on screen
			renderer.render(g, shader);
		}
		
		shader.stop();
	}
	
	/**
	 * Change all states of Entities. 
	 */
	
	public void tick(){
		
	}
	
	public static void addEntity(GameObject gameObject){
		gameObjects.add(gameObject);
	}
	
	/**
	 * build Game
	 */
	public void init(){
		gameObjects = new ArrayList<>();
		shader = new StaticShader();
		shader.setEnviromentLight(new Vector3f(0,0,0));
		Light light = new Light(new Vector2f(0,0), new Vector3f(0.8f, 0.6f, 0.8f));
		shader.configureCameraLight(light);
		shader.setPointLights(generateLights());
		camera = new Camera();
		renderer = new Renderer(shader);
		renderer.setZoom(10);
		map = new Map();
	}
	
	public Vector3f generateColor(){
		Random random = new Random();
		float x = random.nextFloat();
		float y = random.nextFloat();
		float z = random.nextFloat();
		return new Vector3f(x,y,z);
	}
	
	public Vector2f generatePosition(){
		Random random = new Random();
		float x = random.nextFloat()*24f + 1f;
		float y = random.nextFloat()*24f + 1f;
		return new Vector2f(x,y);
	}
	
	public Light[] generateLights(){
		Light light[] = new Light[3];
		for(int i = 0; i<3; i++){
			light[i] = new Light(generatePosition(), generateColor());
		}		
		return light;
	}
}
