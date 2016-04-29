package math;

import graphics.LGraphics;

public class Collider {
	
	public Vector2f position;
	public Vector2f scale;
	
	public Collider (Vector2f pos, Vector2f scale){
		this.position = pos;
		this.scale = scale;
	}
	
	public void DebugDraw (){
		LGraphics.SetColor(0, 1, 0);
		LGraphics.DrawRect(position.x, position.y, scale.x, scale.y);
		LGraphics.SetColor(1, 1, 1);
	}
	
	public boolean Collides (Collider coll){
		
		if (this.position.x + this.scale.x < coll.position.x) return false;
		if (this.position.x > coll.position.x + coll.scale.x) return false;
		if (this.position.y + this.scale.y < coll.position.y) return false;
		if (this.position.y > coll.position.y + coll.scale.y) return false;

		return true;
	}
	
}
