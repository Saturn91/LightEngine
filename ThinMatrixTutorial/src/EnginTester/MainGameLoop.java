package EnginTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;

public class MainGameLoop {

	public static void main(String[] args) {
		
		
		DisplayManager.createDisplay();

		//actual Gameloop
		while(!Display.isCloseRequested()){
					
			DisplayManager.updateDisplay();
		}
		
		DisplayManager.closeDisplay();
	}

}
