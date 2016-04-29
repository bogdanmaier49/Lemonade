package engine.core;

import java.util.ArrayList;

import engine.graphics.Renderer;
import static org.lwjgl.opengl.GL11.*;


public class Game {
	
	private ArrayList<Scene> scenes = new ArrayList <Scene>();
	public Window window = null;
	
	private int curentSceneIndex = 0;
	
	private Renderer renderer;
	
	public Camera view = new Camera (0,0);
	
	public void create (int width, int height, String title, boolean fullscreen){
		
		if (scenes.size() == 0)
		{
			System.err.println("[ERR]: Jocul nu poate porni deoarece nu exista nici o scena!");
			return;
		}
		
		if (this.curentSceneIndex >= scenes.size())
		{
			System.err.println("[ERR]: Nu exista scena cu numarul + " + this.curentSceneIndex);
			return;
		}
		
		window = new Window (width, height, title, fullscreen);	
		window.create();
		
		renderer = new Renderer ();
		renderer.initGraphics(width, height);
		
		onInit();
		
		long lastTime = System.nanoTime();
		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		while (! window.isCloseRequested()) {

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			glTranslatef (view.getX(), view.getY(), 0);
			onUpdate(delta);
			glTranslatef (-view.getX(), -view.getY(), 0);
			
			if (delta >= 1.0) {								
				updates++;
				delta--;
			}
			
			
			onRender(renderer);
			
			
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
			
			window.loop();
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		onClose();
	}
	
	public void addScene (Scene scene){
		this.scenes.add(scene);
	}
	
	public void playScene (int index){
		this.curentSceneIndex = index;
		this.scenes.get(index).Init(this);
	}
	
	public void onInit (){
		this.scenes.get(curentSceneIndex).Init(this);
	}
	
	public void onUpdate (double delta){
		this.scenes.get(curentSceneIndex).Update(this, delta);
	}
	
	public void onRender (Renderer renderer){
		glClear(GL_COLOR_BUFFER_BIT);
		this.scenes.get(curentSceneIndex).Render(this, renderer);
	}
	
	public void onClose (){
		window.Destroy();
		System.exit(0);
	}
	
}
