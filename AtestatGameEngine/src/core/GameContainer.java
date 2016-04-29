package core;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.openal.SoundStore;

import graphics.LGraphics;

import static org.lwjgl.opengl.GL11.*;

public abstract class GameContainer {
	
	public LGraphics graphics = new LGraphics ();
	
	public static int WindowWidth;
	public static int WindowHeight;
	
	private boolean running = false;
	
	public GameObjectManager objectManager = new GameObjectManager ();
	
	public String ComponentsPackageName;
	
	/**
	 * @param width      = numar de pixeli pe orizontala
	 * @param height     = numar de pixeli pe verticala
	 * @param name		 = titlul ferestrei
	 * @param fullscreen = true daca fereastra este fullscreen, false in caz contrar
	 * 
	 * @descriere Functia creeaza fereastra jocului.
	 * 
	 */
	public void CreateWindow (int width, int height, String name, boolean fullscreen)
	{
		
		WindowWidth = width;
		WindowHeight = height;
		
		DisplayMode mode = new DisplayMode (width, height);
		
		
		try {
			
			if (fullscreen){
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				for (DisplayMode m : modes)
				{
					if (m.getWidth() == mode.getWidth() && m.getHeight() == mode.getHeight() && m.isFullscreenCapable())
					{
						mode = m;
					}
				}
			}
			
			if (mode.isFullscreenCapable())
				fullscreen = false;

			Display.setFullscreen(true);
			Display.setDisplayMode(mode);
			Display.setTitle(name);
			
			Display.setVSyncEnabled(true);
			
			Display.create();
			
			InitGraphics2D();
			
			running = true;
			
			run ();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @descriere Functia seteaza modalitatea de randare si modalitatea de vizualizare a graficii pentru un joc 2D
	 */
	private void InitGraphics2D (){
		glMatrixMode (GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,GameContainer.WindowWidth,GameContainer.WindowHeight,0,-1,1);
		glMatrixMode (GL_MODELVIEW);
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public void SetWindowTitle (String title){
		Display.setTitle(title);
	}
	
	
	/**
	 *  @descriere Functia contine loop-ul infinit al jocului.
	 *  	O executie a loopului este considerat un cadru (frame)
	 *  	In fiecare cardu(frame) se reandeaza pe ecran toate obiectele si se executa logia jocului.
	 */
	private void run (){
		
		Load ();
			
		while (running){
			this.graphics.Clear();
			
			Input.update();
			
			this.objectManager.Update();

			this.objectManager.Render();
			
			SoundStore.get().poll(0);
			
			Display.sync(60);
			Display.update();
			
			if (Display.isCloseRequested())
				running = false;
		}
		
		this.objectManager.Destroy();
		AL.destroy();
		System.exit(0);
	}
	
	public abstract void Load ();
	
}
