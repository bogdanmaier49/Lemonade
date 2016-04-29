package testgame;

import core.GameContainer;
import math.Vector2f;

public class Main extends GameContainer{

	public static void main(String[] args) {
		new Main().CreateWindow(1024, 900, "DADA", false);
	}
		
	@Override
	public void Load() {
		
		
		Block wall = new Block("Block", this);
		wall.position = new Vector2f (300,100);
		wall.scale = new Vector2f (100,100);
		this.objectManager.addGameObject(wall);
		
		Player player = new Player ("Player", this, wall);
		player.position = new Vector2f (100,100);
		player.scale = new Vector2f (100,100);
		this.objectManager.addGameObject(player);
	}

	

}