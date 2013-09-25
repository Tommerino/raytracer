package lights;

import raytracer.Point;
import raytracer.Vector;


public class PointLight implements Light {

	private Point position;
	private double power, lightRadius;
	
	public PointLight(Point pos, double pow) {
		position = pos;
		power = pow;
		lightRadius = 1;
	}
	
	public PointLight(Point pos, double pow, double size) {
		position = pos;
		power = pow;
		this.lightRadius = size;
	}

	public Vector surfaceToLight(Point P) {
		Vector result = new Vector(P,position);
		return result;
	}

	public double getPower(double distance) {
		double result = power * lightRadius / (distance * distance);
		return result;
	}

	public Point getPosition() {
		return position;
	}
	
	public boolean isWithinCone(Point P) {
		return true;
	}

}
