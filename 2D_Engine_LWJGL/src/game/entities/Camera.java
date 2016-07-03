package game.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import display.renderer.Renderer;

public class Camera {
	
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch;	//High of Camera
	private float yaw;		//aiming left an d right
	private float speed = 0.001f;
	
	public Camera() {
		
	}
	
	public void move(){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.y +=speed*Renderer.getZoom();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.y -=speed*Renderer.getZoom();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x +=speed*Renderer.getZoom();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x -=speed*Renderer.getZoom();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			position.z +=speed*Renderer.getZoom();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			position.z -=speed*Renderer.getZoom();
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setSpeed(float speed){
		this.speed = speed;
	}
}
