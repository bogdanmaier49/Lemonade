package engine.core;

public class Camera {
	
	private float x = 0;
	private float y = 0;
	
	public Camera (float x, float y){
		MoveTo(x, y);
	}
	
	public void MoveTo (float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public float getX (){
		return x;
	}
	
	public float getY (){
		return y;
	}
}
