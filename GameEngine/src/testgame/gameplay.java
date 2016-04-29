package testgame;

import org.lwjgl.input.Keyboard;

import engine.core.Game;
import engine.core.Scene;
import engine.graphics.Image;
import engine.graphics.Renderer;

public class gameplay extends Scene{
	
	private Image image;
	@Override
	public void Init(Game game) {
		image = new Image ("res/bob.png");
	}

	@Override
	public void Update(Game game, double delta) {
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
			game.view.MoveTo(game.view.getX() + 1, game.view.getY());
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
			game.view.MoveTo(game.view.getX() - 1, game.view.getY());
	}

	@Override
	public void Render(Game game, Renderer renderer) {
		renderer.DrawImage(image, 0, 0, 100, 100);
	}

}
