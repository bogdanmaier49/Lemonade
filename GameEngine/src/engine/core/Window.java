package engine.core;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {
	
	private int width;
	private int height;
	private String title;
	private boolean fullscreen;
	
	public Window (int Width, int Height, String Title, boolean fullscreen){
		width = Width;
		height = Height;
		title = Title;
		this.fullscreen = fullscreen;
		
		if (Width <= 0){
			width = 1024;
			this.fullscreen = false;
		}
		if (Height <= 0){
			height = 900;
			this.fullscreen = false;
		}
		
	}

	public void create (){
		DisplayMode mode = new DisplayMode(width, height);
		
		
		try {
			
			if (fullscreen == true)
			{
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				
				for (DisplayMode d : modes){
					if (d.getWidth() == mode.getWidth() && 
							d.getHeight() == mode.getHeight() &&
							mode.isFullscreenCapable())
					{
						Display.setFullscreen(fullscreen);
						mode = d;
						break;
					}
				}
			}
			
			Display.setDisplayMode(mode);			
			Display.create();
		
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		Display.setTitle(title);
		Display.setVSyncEnabled(true);
	}
	
	public boolean isCloseRequested (){
		return Display.isCloseRequested();
	}
	
	public void loop (){
		Display.sync(60);
		Display.update();
	}
	
	public void Destroy (){
		Display.destroy();
	}
	
	/*
	 *   GETTERS
	 */
	
	public int getWidth (){
		return width;
	}
	
	public int getHeight (){
		return height;
	}
	
	public String getTitlte (){
		return title;
	}
	
	public boolean isFullscreen (){
		return this.fullscreen;
	}
	
	/*
	 *	STTERS 
	 */
	
	public void setTitle (String title){
		this.title = title;
		Display.setTitle(title);
	}
	
}
