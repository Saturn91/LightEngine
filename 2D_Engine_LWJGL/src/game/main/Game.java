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
	private Light light;
	
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
		shader.setLight(new Vector3f(0, 0, 0));
		shader.setPointLight(light);
		shader.loadViewMatrix(camera);
		for(GameObject g: gameObjects){
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
		camera = new Camera();
		renderer = new Renderer(shader);
		renderer.setZoom(10);
		map = new Map();
		light = new Light(new Vector2f(-5f,0f), new Vector3f(1f,1f,1f));
	}
}
