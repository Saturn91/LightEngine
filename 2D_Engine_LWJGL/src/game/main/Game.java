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
			renderer.render(g, shader);
		}
		
		shader.stop();
	}
	
	/**
	 * Change all states of Entities. 
	 */
	private float range = 10.0f;
	private float strenght = 3;
	private Light light = new Light(new Vector2f(0,0), new Vector3f(1, 1, 1));
	private boolean rising = false;
	public void tick(){
		if(range>=10 && rising){
			rising = false;
		}else{
			if(range <= 7.5f &! rising){
				rising = true;
			}
		}
		
		if(rising){
			range+= 0.15f;
			strenght+=0.01f;
		}else{
			range-= 0.17f;
			strenght-=0.01f;
		}
		light.setRange(range);
		light.setStrenght(strenght);
		shader.configureCameraLight(light);
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
		//shader.setEnviromentLight(new Vector3f(0.1f,0.1f,0.2f));
		//Light light = new Light(.....
		//shader.configureCameraLight(light);
		camera = new Camera();
		renderer = new Renderer(shader);
		renderer.setZoom(10);
		map = new Map();
	}
}
