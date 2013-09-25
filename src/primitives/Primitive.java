package primitives;

import java.awt.Color;

import raytracer.Point;
import raytracer.Ray;
import raytracer.Vector;

import lights.Light;

public interface Primitive {

	public Point rayIntersectPrimitive(Ray ray);
	public Vector getNormal(Point point);
	public Color getColor();
	public double[] calcLight(Point pointOfIntersect);
	public Color directLight(Point P, Primitive pri, Light L);
	public double getReflectivity();
	public double getTransparency();
}
