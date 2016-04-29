package engine.graphics;

import static org.lwjgl.opengl.GL11.*;

import engine.core.Camera;

public class Renderer {
	
	public void initGraphics (int width, int height){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public void FillRectangle (float x, float y, float w, float h){
		glBegin (GL_QUADS);
			glVertex2f (x, y);
			glVertex2f (x + w, y);
			glVertex2f (x + w, y + h);
			glVertex2f (x, y + h);
		glEnd ();
	}
	
	public void DrawImage (Image image, float x, float y, float w, float h){
		image.bind();
		glBegin (GL_QUADS);
			glTexCoord2f (0,0);
			glVertex2f (x, y);
			glTexCoord2f (1,0);
			glVertex2f (x + w, y);
			glTexCoord2f (1,1);
			glVertex2f (x + w, y + h);
			glTexCoord2f (0,1);
			glVertex2f (x, y + h);
		glEnd ();
	}
	
}
