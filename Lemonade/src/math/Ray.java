package math;

import graphics.LGraphics;

public class Ray {
	
	public Vector2f startPosition;
	public Vector2f endPosition;
	
	public Ray (Vector2f startPosition, Vector2f endPosition)
	{
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}
	
	public Vector2f IntersectsLine (Vector2f point1, Vector2f point2){
		return Physics.intersection(this.startPosition, this.endPosition, point1, point2);
	}
	
	public Vector2f Collides (Collider coll){
		return Physics.intersection(this.startPosition, this.endPosition, coll.position.x, coll.position.y, coll.scale.x, coll.scale.y);
	}
	
	public float getLenght (){
		return this.startPosition.distance(endPosition);
	}
	
	public void DebugDraw (){
		LGraphics.SetColor(1, 0, 0);
		LGraphics.DrawLine(this.startPosition, this.endPosition);
		LGraphics.SetColor(1, 1, 1);
	}
}
