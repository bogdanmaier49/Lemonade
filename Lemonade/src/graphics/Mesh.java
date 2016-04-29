package graphics;


import static org.lwjgl.opengl.GL11.GL_COLOR_ARRAY;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTexCoordPointer;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import math.Vector2f;

public class Mesh{
	
	public Vector2f position = new Vector2f ();
	public Vector2f scale = new Vector2f (10,10);
	public float rotation = 0;
	
	public LImage image;
	
	private FloatBuffer vertexData;
	private FloatBuffer uvData;
	private int vboVertexHandle;
	private int vboUVHandle;
	
	public Mesh (LImage image){
		
		if (image == null)
		{		
			System.err.println("[Err]: Imagine nulla.");
			return;
		}
		
		this.image = image;
		Init();
	}
	
	public Mesh (float x, float y, float width, float height, LImage image){
		
		if (image == null)
		{
			System.err.println("[Err]: Imagine nulla.");
			return;
		}
		
		this.position.x = x;
		this.position.y = y;
		this.scale.x = width;
		this.scale.y = height;
		this.image = image;
		
		Init();
	}
	
	public Mesh (float x, float y, float width, float height, float angle, LImage image){
		
		if (image == null)
		{
			System.err.println("[Err]: Imagine nulla.");
			return;
		}
		
		this.position.x = x;
		this.position.y = y;
		this.scale.x = width;
		this.scale.y = height;
		this.image = image;
		Init();
	}
	
	private void Init() {
		// VertexData
		vertexData = BufferUtils.createFloatBuffer(8);
		
		vertexData.put(0).put(0);
		vertexData.put(1).put(0);
		vertexData.put(1).put(1);
		vertexData.put(0).put(1);


		vertexData.flip();
		
		uvData = BufferUtils.createFloatBuffer(8);
		
		uvData.put(0).put(0);
		uvData.put(1).put(0);
		uvData.put(1).put(1);
		uvData.put(0).put(1);
		
		uvData.flip();
		
		// VBO
		vboVertexHandle = glGenBuffers();
		vboUVHandle = glGenBuffers();
		
		image.bind();

		// vertices
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
		glVertexPointer(2, GL_FLOAT, 0, 0L);
		// texCoords
		glBindBuffer(GL_ARRAY_BUFFER, vboUVHandle);
		glBufferData(GL_ARRAY_BUFFER, uvData, GL_STATIC_DRAW);
		glTexCoordPointer(2, GL_FLOAT, 0, 0);
	}

	public void Render() {
		glPushMatrix ();
			glTranslatef(position.x, position.y, 0);
			glScalef (scale.x, scale.y, 1);
			glRotatef (rotation, 0, 0, 1);
					
			glBindBuffer(GL_ARRAY_BUFFER, 0);
			
			glEnableClientState(GL_VERTEX_ARRAY);
			glEnableClientState(GL_TEXTURE_COORD_ARRAY);

			glDrawArrays(GL_QUADS, 0, 4);

			glDisableClientState(GL_COLOR_ARRAY);
			glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		glPopMatrix();
	}


	public void Destroy() {
		glDeleteBuffers (vboVertexHandle);
	}
	
}
