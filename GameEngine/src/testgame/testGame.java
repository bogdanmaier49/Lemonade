package testgame;

import engine.core.Game;

public class testGame {

	public static void main(String[] args) {
		Game game = new Game ();
		gameplay gm = new gameplay();
		game.addScene(gm);
		game.create(1024, 900, "DADA", false);
	}

}
