package game.entities;

import game.entities.TexturedModel;

public class Entity {
	private TexturedModel model;
	
	public Entity(TexturedModel model){
		this.model = model;
	}

	public TexturedModel getModel() {
		return model;
	}	
}
