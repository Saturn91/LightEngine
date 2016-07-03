package game.level;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import toolbox.Constants;
import Textures.ModelTexture;
import display.renderer.Loader;
import game.entities.Entity;
import game.entities.GameObject;
import game.entities.RawModel;
import game.entities.TexturedModel;
import game.main.Game;

public class Map {
	
	public Map() {
		
		Loader loader = new Loader();
		RawModel model= loader.loadToVAO(Constants.QuadVerticies(1, 1), Constants.TextureCords(), Constants.QuadIndices());
		ModelTexture texture = new ModelTexture(loader.loadTexture("StandartTile"));
		TexturedModel staticModel = new TexturedModel(model, texture);
		Entity entity = new Entity(staticModel);
		GameObject.addEntity("Test", entity);
		
		Game.addEntity(new GameObject("Test", new Vector2f(0.0f, 0.0f), 1.0f));
		
		for(int x = 0; x < 100; x+=2){
			for(int y = 0; y < 100; y+=2){
				Game.addEntity(new GameObject("Test", new Vector2f(x, y), 1.0f));
			}
		}
	}
}
