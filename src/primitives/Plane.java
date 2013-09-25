package primitives;

import java.awt.Color;

import raytracer.Camera;
import raytracer.Point;
import raytracer.Ray;
import raytracer.Scene;
import raytracer.Vector;

import lights.Light;

public class Plane implements Primitive {

	private Point p;
	private Vector normal;
	private Color color;
	private double reflectivity, transparency;
	
	public Plane(Point a, Point b, Point c, Color color, double reflectivity) {
		Vector v1 = new Vector(a,b);
		Vector v2 = new Vector(a,c);
		normal = Vector.crossProduct(v1, v2);
		p = a;
		this.color = color;
		this.reflectivity = reflectivity;
	}
	
	public Plane(Vector normal, Point p, Color color, double reflectivity) {
		this.p = p;
		this.normal = Vector.normalizeVector(normal);
		this.color = color;
		this.reflectivity = reflectivity;
	}
	
	public Plane(Vector v0, Vector v1, Point p, Color color, double reflectivity) {
		normal = Vector.crossProduct(v0, v1);
		this.p = p;
		this.color = color;
		this.reflectivity = reflectivity;
	}
	
	public Point rayIntersectPrimitive(Ray ray) {
		double pa = normal.getX();
		double pb = normal.getY();
		double pc = normal.getZ();
		double pd = (pa*p.getX() + pb*p.getY() + pc*p.getZ()) * (-1);
		
		double t = - (pa * ray.getOrigin().getX() + pb * ray.getOrigin().getY() + pc * ray.getOrigin().getZ() + pd) / 
					 (Vector.dotProduct(normal,ray.getDirection()));
					
		if(t > 0.0) {
			double x = ray.getOrigin().getX() + t * ray.getDirection().getX();
			double y = ray.getOrigin().getY() + t * ray.getDirection().getY();
			double z = ray.getOrigin().getZ() + t * ray.getDirection().getZ();
			
			Point pointOfIntersect = new Point(x,y,z);
			return pointOfIntersect;
		}
		
		return null;
	}

	public Vector getNormal(Point point) {
		return normal;
	}
	
	public Vector getNormal() {
		return normal;
	}

	public Color getColor() {
		return color;
	}

	
	/**
	 * Will return an array containing color modifier values based
	 * on the color and power of the light source.
	 * 
	 * 0 = 0%
	 * 1 = 100%
	 */
	public double[] calcLight(Point P) {
		double[] result = new double[] {0,0,0};
		for(Light L : Scene.lights) {
		
			Color colorFromLight = directLight(P,this,L);
			double distance = Point.distance(P, L.getPosition());
			double percentage = Vector.angle(L.surfaceToLight(P),this.getNormal(P)) * L.getPower(distance);
			result[0] += percentage * colorFromLight.getRed();
			result[1] += percentage * colorFromLight.getGreen();
			result[2] += percentage * colorFromLight.getBlue();
		}
		
		if(Camera.ambientOn && result[0] < Camera.ambientVal * 255
							&& result[1] < Camera.ambientVal * 255
							&& result[2] < Camera.ambientVal * 255) {
			result[0] = Camera.ambientVal * 255;
			result[1] = Camera.ambientVal * 255;
			result[2] = Camera.ambientVal * 255;
		}
		
		result[0] /= 255;
		result[1] /= 255;
		result[2] /= 255;
				
		return result;
	}
		
	public Color directLight(Point P, Primitive pri, Light L) {
		double r = 255;
		double g = 255;
		double b = 255;
		
		if(L.isWithinCone(P)) {
			Vector rev = L.surfaceToLight(P);
			Ray ray = new Ray(P,rev);
			double distToLight = Point.distance(P, L.getPosition());
			for(Primitive prim : Scene.primitives) {
				Point shadowingPrimitiveIntersect = prim.rayIntersectPrimitive(ray);
				if(shadowingPrimitiveIntersect != null && prim != pri) {
					double distToPrimitive = Point.distance(P, shadowingPrimitiveIntersect);
					if(distToPrimitive < distToLight) {
							double shadowTransparency = prim.getTransparency();
							Color shadowPrim = prim.getColor();
							r *= ((double)shadowPrim.getRed()   / 255) * shadowTransparency;
							g *= ((double)shadowPrim.getGreen() / 255) * shadowTransparency;
							b *= ((double)shadowPrim.getBlue()  / 255) * shadowTransparency;
					}
				}
			}
		} else {
			Color result = Color.black;
			return result;
		}
		Color result = new Color((int)r,(int)g,(int)b);
		return result;
	}
	
	public double getReflectivity() {
		return reflectivity;
	}
	

	public void setTransparency(double newVal) {
		transparency = newVal;
	}
	
	public double getTransparency() {
		return transparency;
	}

}
