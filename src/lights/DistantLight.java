package lights;

import raytracer.Point;
import raytracer.Vector;


public class DistantLight implements Light {

	private double power;
	private Vector direction;
	private Point position;
	
	public DistantLight(Vector dir, double pow) {
		direction = dir;
		power = pow;
		position = new Point(0,0,0);
	}

	public double getPower(double distance) {
		return power;
	}

	public Vector getDirection() {
		return direction;
	}
	
	public Vector surfaceToLight(Point P) {
		Vector result = direction.getReversed();
		return result;
	}

	public Point getPosition() {
		return position;
	}
	
	public boolean isWithinCone(Point P) {
		return true;
	}

}
