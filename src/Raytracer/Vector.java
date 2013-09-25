package raytracer;

public class Vector {

	private double x,y,z;

	/**
	 * Creates a vector using 3 delta values.
	 */
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Creates a vector using from and to points.
	 */
	public Vector(Point from, Point to) {
		x = to.getX() - from.getX();
		y = to.getY() - from.getY();
		z = to.getZ() - from.getZ();
	}
	
	/**
	 * Add x, y and z constant values to this vector.
	 */
	public void add(double x, double y, double z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}
	
	/**
	 * Add another vector to this vector.
	 */
	public void add(Vector other) {
		this.x += other.getX();
		this.y += other.getY();
		this.z += other.getZ();
	}
	
	/**
	 * Multiply a constant value with this vector and return the result as a new vector.
	 */
	public Vector multiply(double multi) {
		double xM = this.x * multi;
		double yM = this.y * multi;
		double zM = this.z * multi;
		Vector result = new Vector(xM,yM,zM);
		return result;
	}
		
	/**
	 * Calculates the dot product when 2 vectors are multiplied and returns a double.
	 */
	public static double dotProduct(Vector v1, Vector v2) {
		double result = v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();
		return result;
	}
	
	/**
	 * Calculates the cross product and returns a vector.
	 */
	public static Vector crossProduct(Vector v1, Vector v2) {
		double x = v1.getY() * v2.getZ() - v1.getZ() * v2.getY();
		double y = v1.getZ() * v2.getX() - v1.getX() * v2.getZ();
		double z = v1.getX() * v2.getY() - v1.getY() * v2.getX();
		Vector result = new Vector(x,y,z);
		return result;
	}
	
	/**
	 * Returns a normalized version of this vector.
	 */
	public static Vector normalizeVector(Vector v) {
		double length = Vector.length(v);
		double x = v.getX() / length;
		double y = v.getY() / length;
		double z = v.getZ() / length;
		Vector result = new Vector(x,y,z);
		return result;
	}
	
	/**
	 * Normalize THIS vector.
	 */
	public void normalize() {
		this.x = this.getX() / this.getLength();
		this.y = this.getY() / this.getLength();
		this.z = this.getZ() / this.getLength();
	}
	
	/**
	 * Calculates the length of a vector.
	 */
	public static double length(Vector v) {
		double result = Math.sqrt(Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2) + Math.pow(v.getZ(), 2));
		return result;
	}
	
	/**
	 * Returns true if the 2 vectors are orthogonal (within a margin of 1/100.000 "units")
	 */
	public static boolean isOrthogonal(Vector v1, Vector v2) {
		if(Vector.dotProduct(v1, v2) < 0.00001) {
			return true;
		}
		return false;
	}
	
	/**
	 * Calculates the angle between 2 vectors and returns the cosine.
	 */
	public static double angle(Vector v1, Vector v2) {
		double cosA = Vector.dotProduct(v1, v2) / (Vector.length(v1) * Vector.length(v2));
		return cosA;
	}
	
	/**
	 * Calculates and returns a reflected vector, given an in-bound vector and the normal 
	 * vector of the reflecting surface.
	 */
	public static Vector reflect(Vector v0, Vector N) {
		Vector normN = Vector.normalizeVector(N);
		double v0dotN = Vector.dotProduct(v0,normN);
		double x,y,z;
		x = v0.getX() - 2 * v0dotN * normN.getX();
		y = v0.getY() - 2 * v0dotN * normN.getY();
		z = v0.getZ() - 2 * v0dotN * normN.getZ();
		Vector result = new Vector(x,y,z);
		return result;
	}
	
	/**
	 * Calculates and returns the triple scalar product of 3 vector multiplication.
	 */
	public static double scalarTripleProduct(Vector v1, Vector v2, Vector v3) {
		Vector crossed = Vector.crossProduct(v2,v3);
		double result = Vector.dotProduct(v1,crossed);
		return result;
	}

	/**
	 * Returns a new reversed vector of THIS vector.
	 */
	public Vector getReversed() {
		double xR,yR,zR;
		xR = -x;
		yR = -y;
		zR = -z;
		Vector result = new Vector(xR,yR,zR);
		return result;
	}
	
	/**
	 * Returns the length of THIS vector.
	 */
	public double getLength() {
		return Vector.length(this);
	}
	
	public void print() {
		System.out.println(x + " | " + y + " | " + z);
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