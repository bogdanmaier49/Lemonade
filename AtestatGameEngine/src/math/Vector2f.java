package math;

public class Vector2f {
	
	public float x;
	public float y;
	
	public Vector2f (){
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2f (float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void normalize (){
		x /= Lenght();
		y /= Lenght();
	}
	
	public Vector2f normalized (){
		return new Vector2f (x/Lenght(), y/Lenght());
	}
	
	public float Lenght (){
		return (float)( Math.sqrt(x*x + y*y) );
	}
	
	public float distance (Vector2f point){
		return (float)Math.sqrt((x - point.x)*(x - point.x) + (y - point.y)*(y - point.y));
	}
	
	public Vector2f add (float vx, float vy){
		return new Vector2f (this.x + vx, this.y + vy);
	}
	
}
