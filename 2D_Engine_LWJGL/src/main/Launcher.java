package main;

import game.GameMainLoop;

public class Launcher {
	private static GameMainLoop game;
	
	public static void main(String[] args) {
		game = new GameMainLoop();
		game.start();
	}

}
