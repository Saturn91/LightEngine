package game;

import game.main.Game;

import org.lwjgl.opengl.Display;

import display.DisplayManager;

public class GameMainLoop {
	public static final String GAME_TITLE = "GameEngine";
	public static final int WINDOW_WIDTH = 1024;
	public static final int WINDOW_HEIGHT = 720;
	public static final float fps = 120;
	
	private DisplayManager display;
	
	private Game game;
	
	public void start(){
		display = new DisplayManager(GAME_TITLE, WINDOW_WIDTH, WINDOW_HEIGHT);
		display.setDisplaySync((int)fps);
		game = new Game();
		GameLoop();
		display.closeDisplay();		
	}
	
	public void GameLoop(){
		while(!Display.isCloseRequested()){
			tick();
			game.render();
			display.updateDisplay();
		}
	}
	
	private void tick() {
		game.tick();
	}

	public void close(){
		
	}
}
