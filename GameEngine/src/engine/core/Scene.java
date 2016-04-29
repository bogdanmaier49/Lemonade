package engine.core;

import engine.graphics.Renderer;

public abstract class Scene {
	
	public void _Init (Game game){
		Init(game);
		
	}
	public void _Update (Game game, double delta){
		Update(game, delta);
	}
	public void _Render (Game game, Renderer renderer){
		Render(game, renderer);
	}
	
	public abstract void Init (Game game);
	public abstract void Update (Game game, double delta);
	public abstract void Render (Game game, Renderer renderer);
	
	
}
