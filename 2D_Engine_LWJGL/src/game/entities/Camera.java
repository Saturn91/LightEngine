package game.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch;	//High of Camera
	private float yaw;		//aiming left an d right
	private float speed = 0.25f;
	
	public Camera() {
		
	}
	
	public void move(){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.y +=speed;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.y -=speed;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x +=speed;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x -=speed;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			position.z +=speed;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			position.z -=speed;
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
