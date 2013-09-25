package lights;

import raytracer.Point;
import raytracer.Vector;


public class SpotLight implements Light {

	private Point position;
	private Vector direction;
	private double power, coneRadius;
	
	public SpotLight(Point pos, Vector dir, double pow, double radius) {
		position = pos;
		direction = dir;
		power = pow;
		coneRadius = radius;
	}
	
	public Vector surfaceToLight(Point P) {
		Vector result = new Vector(P,position);
		return result;
	}

	public double getPower(double distance) {
		double cR = Math.sin(Math.toRadians(coneRadius));
		double result = power / (distance * distance * cR * cR);
		return result;
	}

	public Point getPosition() {
		return position;
	}
	
	public boolean isWithinCone(Point P) {
		Vector lightToSurface = new Vector(position,P);
		double angle = Vector.angle(lightToSurface,direction);
		if(angle > Math.cos(Math.toRadians(coneRadius))) {
			return true;
		} else {
			return false;
		}
	}

}
