package raytracer;

public class Ray {

	private Point origin;
	private Vector direction;

	public Ray(Point ori, Vector dir) {
		origin = ori;
		direction = Vector.normalizeVector(dir);
	}

	public Point getOrigin() {
		return origin;
	}

	public Vector getDirection() {
		return direction;
	}

}
