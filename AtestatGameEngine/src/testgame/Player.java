package testgame;

import core.GameContainer;
import core.GameObject;
import core.Input;
import graphics.LGraphics;
import graphics.LImage;
import graphics.Mesh;
import math.Ray;
import math.Vector2f;

public class Player extends GameObject{

	public Player(String name, GameContainer gc, Block b) {
		super(name, gc);
		this.b = b;
	}

	Mesh mesh;
	LImage image;
	
	public Vector2f velocity = new Vector2f ();
	public float speed = 12;
	
	Block b;
	
		
	@Override
	public void Init() {
		
		image = new LImage("res/test.png");
		
		mesh = new Mesh (
				this.position.x,
				this.position.y,
				this.scale.x,
				this.scale.y,
				this.angle,
				image
				);		
	}
		
	private Ray[] placeVerticalRays (float direction, int numberOfRays, float height){
		
		int dir = 1;
		if (direction < 0)
			dir = 0;
		
		Ray[] result = new Ray[numberOfRays];
		
		float gap = height/(numberOfRays - 1);
		
		for (int i=0; i<numberOfRays; i++)
		{
			result[i] = new Ray(
					new Vector2f (this.position.x + scale.x * dir, this.position.y + gap * i),
					new Vector2f ()
					);
		}
		
		return result;
	}
	
	private Ray[] placeHorizntalRays (float direction, int numberOfRays, float width){
		
		int dir = 1;
		if (direction < 0)
			dir = 0;
		
		Ray[] result = new Ray[numberOfRays];
		
		float gap = width/(numberOfRays - 1);
		
		for (int i=0; i<numberOfRays; i++)
		{
			result[i] = new Ray(
					new Vector2f (this.position.x +  gap * i, this.position.y + scale.y * dir),
					new Vector2f ()
					);
		}
		
		return result;
	}
	
	Ray[] vRays;
	Ray[] hRays;
	
	@Override
	public void Update() {
		
		if (Input.isKeyDown(Input.KEY_D))
		{
			velocity.x = speed;
		}
		
		if (Input.isKeyDown(Input.KEY_A))
		{
			velocity.x = -speed;
		}
		
		if (Input.isKeyDown(Input.KEY_S))
		{
			velocity.y = speed;
		}
		
		if (Input.isKeyDown(Input.KEY_W))
		{
			velocity.y = -speed;
		}
		
		vRays = this.placeVerticalRays(this.velocity.normalized().x, 5, this.scale.y);
		hRays = this.placeHorizntalRays(this.velocity.normalized().y, 5, this.scale.x);
		
		for (Ray r : vRays)
		{	
			r.endPosition.x = r.startPosition.x + velocity.x * this.velocity.normalized().x;
			r.endPosition.y = r.startPosition.y;
			
			Vector2f point = r.Collides(b.collider);
			if (point != null)
				velocity.x = r.getLenght();
		}
		
		for (Ray r : hRays)
		{	
			r.endPosition.x = r.startPosition.x;
			r.endPosition.y = r.startPosition.y + velocity.y * this.velocity.normalized().y;
			
			Vector2f point = r.Collides(b.collider);
			if (point != null)
				velocity.x = r.getLenght();
		}
		
		this.position.x += this.velocity.x;
		this.position.y += this.velocity.y;

		mesh.position = position;
		
		velocity.x = 0;
		velocity.y = 0;
		
	}

	@Override
	public void Render() {
		mesh.Render();
		
		for (Ray r : vRays)
			r.DebugDraw();
		
		for (Ray r : hRays)
			r.DebugDraw();
	}

	@Override
	public void Destroy() {
		mesh.Destroy();
	}

}
