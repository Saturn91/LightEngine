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
		
		RawModel model2= loader.loadToVAO(Constants.QuadVerticies(1, 1), Constants.TextureCords(), Constants.QuadIndices());
		ModelTexture texture2 = new ModelTexture(loader.loadTexture("canvas_white"));
		TexturedModel staticModel2 = new TexturedModel(model2, texture2);
		Entity entity2 = new Entity(staticModel2);
		GameObject.addEntity("canvas", entity2);
		
		for(int x = 0; x < 25; x++){
			for(int y = 0; y < 25; y++){
				GameObject gameObject = new GameObject("canvas", new Vector2f(x, y), 1.0f, 0);
				Game.addEntity(gameObject);
			}
		}
	}
}
