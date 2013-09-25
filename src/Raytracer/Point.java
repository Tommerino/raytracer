package raytracer;

public class Point {
	
	private double x, y, z;

	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point(Ray ray, double t) {
		x = ray.getOrigin().getX() + ray.getDirection().getX() * t;
		y = ray.getOrigin().getY() + ray.getDirection().getY() * t;
		z = ray.getOrigin().getZ() + ray.getDirection().getZ() * t;
	}
	
	public static Point rayPoint(Ray ray, double t) {
		double rX,rY,rZ;
		rX = ray.getOrigin().getX() + ray.getDirection().getX() * t;
		rY = ray.getOrigin().getY() + ray.getDirection().getY() * t;
		rZ = ray.getOrigin().getZ() + ray.getDirection().getZ() * t;
		Point result = new Point(rX,rY,rZ);
		return result;
	}
	
	public static double distance(Point from, Point to) {
		double x = to.getX() - from.getX();
		double y = to.getY() - from.getY();
		double z = to.getZ() - from.getZ();
		double result = Math.sqrt(x*x + y*y + z*z);
		return result;
	}
	
	public static Point subtract(Point p1, Point p2) {
		double x,y,z;
		x = p1.getX() - p2.getX();
		y = p1.getY() - p2.getY();
		z = p1.getZ() - p2.getZ();
		Point result = new Point(x,y,z);
		return result;
	}
	
	
	public static Point add(Point p1, Point p2) {
		double x,y,z;
		x = p1.getX() + p2.getX();
		y = p1.getY() + p2.getY();
		z = p1.getZ() + p2.getZ();
		Point result = new Point(x,y,z);
		return result;
	}
	
	public void move(Vector dir) {
		x = dir.getX();
		y = dir.getY();
		z = dir.getZ();
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
}