package lights;

import raytracer.Point;
import raytracer.Vector;

public interface Light {
	
	public Vector surfaceToLight(Point P);
	public double getPower(double distance);
	public Point getPosition();
	public boolean isWithinCone(Point P);
}
