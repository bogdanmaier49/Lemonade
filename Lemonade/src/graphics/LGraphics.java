package graphics;

import static org.lwjgl.opengl.GL11.*;


import math.Vector2f;

public class LGraphics {
	
	public static void DrawImage (LImage image, float x, float y, float w, float h){
		image.bind();
		
		glPushMatrix ();
		
		glTranslatef(x, y, 0);
		glScalef(w, h, 1);
		
		glBegin (GL_QUADS);
		
		glTexCoord2f (0,0);
		glVertex2f (-0.5f,-0.5f);
		glTexCoord2f (1,0);
		glVertex2f (0.5f,-0.5f);
		glTexCoord2f (1,1);
		glVertex2f (0.5f,0.5f);
		glTexCoord2f (0,1);		
		glVertex2f (-0.5f,0.5f);
		
		glEnd ();
		
		glPopMatrix ();
	}
	
	public static void SetColor (float r, float g, float b){
		glColor3f (r,g,b);
	}
	
	public static void DrawRect (float x, float y, float w, float h){
		
		glPushMatrix ();
		
		glTranslatef(x, y, 0);
		glScalef(w, h, 1);
		
		glBegin (GL_QUADS);
		
		glVertex2f (-0.5f,-0.5f);
		glVertex2f (0.5f,-0.5f);
		glVertex2f (0.5f,0.5f);
		glVertex2f (-0.5f,0.5f);
		
		glEnd ();
		
		glPopMatrix ();
		
	}
	
	
	
	public static void DrawLine (Vector2f end1, Vector2f end2){
		glBegin (GL_LINES);
		
		glVertex2f (end1.x, end1.y);
		glVertex2f (end2.x, end2.y);
		
		glEnd ();
	}
	
	
	
	public void Clear (){
		glClear (GL_COLOR_BUFFER_BIT);
	}
	
}
