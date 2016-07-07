package game.main;

import game.entities.Camera;
import game.entities.Entity;
import game.entities.GameObject;
import game.entities.Light;
import game.level.Map;

import java.util.ArrayList;

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
	
	private Light lights[] = {
		new Light(new Vector2f(2,0), new Vector3f(0f, 1f, 0f)),
		new Light(new Vector2f(1,1), new Vector3f(0f, 0f, 1f))
	};
	
	/**
	 * build Game
	 */
	public void init(){
		gameObjects = new ArrayList<>();
		shader = new StaticShader();
		shader.setEnviromentLight(new Vector3f(0.05f,0.05f,0.1f));
		Light light = new Light(new Vector2f(0,0), new Vector3f(1f, 0f, 0f));
		shader.configureCameraLight(light);
		shader.setPointLights(lights);
		camera = new Camera();
		renderer = new Renderer(shader);
		renderer.setZoom(10);
		map = new Map();
	}
}
