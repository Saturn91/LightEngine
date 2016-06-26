package EnginTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class MainGameLoop {

	public static void main(String[] args) {
		
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
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
		
		RawModel model = loader.loadToVAO(vertices, indices);

		//actual Gameloop
		while(!Display.isCloseRequested()){
			renderer.prepare();	
			renderer.render(model);
			DisplayManager.updateDisplay();
		}
		
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}