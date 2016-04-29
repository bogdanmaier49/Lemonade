package core;

import math.Vector2f;

public abstract class GameObject {
	public String name;
	public String tag;
	
	public Vector2f position = new Vector2f ();
	public Vector2f scale = new Vector2f (10,10);
	public float angle = 0;
	
	public GameContainer container;
		
	public GameObject (String name, GameContainer gc){
		this.name = name;
		this.container = gc;
	}
	
	public abstract void Init ();
	
	public abstract void Update ();
	
	public abstract void Render ();
	
	public abstract void Destroy();

	
}
