package testgame;

import core.GameContainer;
import core.GameObject;
import graphics.LGraphics;
import graphics.LImage;
import graphics.Mesh;
import math.Collider;
import math.Vector2f;

public class Block extends GameObject {

	public Block(String name, GameContainer gc) {
		super(name, gc);
	}

	Mesh mesh;
	LImage image;
	
	public Vector2f velocity = new Vector2f ();
	public float speed = 2;
	
	public Collider collider;
	
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
		
		this.collider = new Collider (this.position, this.scale);
	}

	@Override
	public void Update() {
	}

	@Override
	public void Render() {
		LGraphics.SetColor(0, 1, 0);
		mesh.Render();
		LGraphics.SetColor(1, 1, 1);
	}

	@Override
	public void Destroy() {
		mesh.Destroy();
	}

}
