package math;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Physics {
	 
	 public static Vector2f intersection(Vector2f point1, Vector2f point2, Vector2f point3, Vector2f point4)
	 {
		 Line2D.Float pLine1 = new Line2D.Float(new Point2D.Float(point1.x, point1.y), new Point2D.Float(point2.x, point2.y));
		 Line2D.Float pLine2 = new Line2D.Float(new Point2D.Float(point3.x, point3.y), new Point2D.Float(point4.x, point4.y));
		 
	     Vector2f result = null;

	     float
	         s1_x = pLine1.x2 - pLine1.x1,
	         s1_y = pLine1.y2 - pLine1.y1,

	         s2_x = pLine2.x2 - pLine2.x1,
	         s2_y = pLine2.y2 - pLine2.y1,

	         s = (-s1_y * (pLine1.x1 - pLine2.x1) + s1_x * (pLine1.y1 - pLine2.y1)) / (-s2_x * s1_y + s1_x * s2_y),
	         t = ( s2_x * (pLine1.y1 - pLine2.y1) - s2_y * (pLine1.x1 - pLine2.x1)) / (-s2_x * s1_y + s1_x * s2_y);

	     if (s >= 0 && s <= 1 && t >= 0 && t <= 1)
	     {
	         // Collision detected
	         result = new Vector2f(
	             (pLine1.x1 + (t * s1_x)),
	             (pLine1.y1 + (t * s1_y)));
	     }   // end if

	     return result;
	 }
	 
	 
	 public static Vector2f intersection (Vector2f point1, Vector2f point2, float x, float y, float w, float h){
		 
		 Vector2f result = null;
		 
		 Vector2f[] points = new Vector2f[4];
		 
		 points[0] = intersection(point1, point2, new Vector2f (x  , y	), 	new Vector2f (x+w, y	));
		 points[1] = intersection(point1, point2, new Vector2f (x+w, y	), 	new Vector2f (x+w, y+h	));
		 points[2] = intersection(point1, point2, new Vector2f (x+w, y+h), 	new Vector2f (x	 , y+h	));
		 points[3] = intersection(point1, point2, new Vector2f (x  , y+h), 	new Vector2f (x	 , y	));

		 float minDistance = 1000000.0f;
		 
		 for (Vector2f point : points)
		 {
			 if (point != null)
				 if (point1.distance(point) < minDistance)
				 {
					 minDistance = point1.distance(point);
					 result = point;
				 }
		 }
		 
		 return result;
	 }
	
	
}
