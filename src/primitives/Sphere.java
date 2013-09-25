package primitives;

import java.awt.Color;

import raytracer.Camera;
import raytracer.Point;
import raytracer.Ray;
import raytracer.Scene;
import raytracer.Vector;

import lights.Light;

public class Sphere implements Primitive {

	private Point center;
	private double radius, reflectivity, transparency;
	private Color color;
	
	public Sphere(Point c, double r, Color col, double ref) {
		center = c;
		radius = r;
		color = col;
		reflectivity = ref;
	}
	
	public Sphere(Point c, double r) {
		center = c;
		radius = r;
		color = Color.red;
	}

	public Point rayIntersectPrimitive(Ray ray) {
		double a,b,c,disc;
		Vector d = ray.getDirection();
		
		double pX,pY,pZ;
		pX = ray.getOrigin().getX() - center.getX();
		pY = ray.getOrigin().getY() - center.getY();
		pZ = ray.getOrigin().getZ() - center.getZ();
		Vector o = new Vector(pX,pY,pZ);
		
		a = Vector.dotProduct(d,d);
		b = 2 * Vector.dotProduct(d,o);
		c = Vector.dotProduct(o,o) - (radius * radius);
		
		disc = (b * b) - (4 * a * c);
		
		if(disc < 0.0) {
			
			return null;
		}
		else if(disc == 0.0) {
			double t = - b / (2 * a);
			Point result = new Point(ray,t);
			return result;
		} 
		else {
			double t1,t2,dist1,dist2;
			t1 = (- b + Math.sqrt(disc)) / (2 * a);
			Point p1 = new Point(ray,t1);
			dist1 = Point.distance(ray.getOrigin(), p1);
			
			t2 = (- b - Math.sqrt(disc)) / (2 * a);
			Point p2 = new Point(ray,t2);
			dist2 = Point.distance(ray.getOrigin(), p2);
			
			if(dist1 < dist2 && t1 > 0) {
				return p1;
			}
			else if(dist2 < dist1 && t2 > 0) {
				return p2;
			}
		}
		
		return null;
		
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
//			Vector rev = L.surfaceToLight(P);
//			Ray ray = new Ray(P,rev);
//			double distToLight = Point.distance(P, L.getPosition());
//			for(Primitive prim : Scene.primitives) {
//				Point shadowingPrimitiveIntersect = prim.rayIntersectPrimitive(ray);
//				if(shadowingPrimitiveIntersect != null && prim != pri) {
//					double distToPrimitive = Point.distance(P, shadowingPrimitiveIntersect);
//					if(distToPrimitive < distToLight) {
//							double shadowTransparency = prim.getTransparency();
//							Color shadowPrim = prim.getColor();
//							r *= ((double)shadowPrim.getRed()   / 255) * shadowTransparency;
//							g *= ((double)shadowPrim.getGreen() / 255) * shadowTransparency;
//							b *= ((double)shadowPrim.getBlue()  / 255) * shadowTransparency;
//					}
//				}
//			}
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
	

	public Vector getNormal(Point point) {
		Vector result = new Vector(center,point);
		return result;
	}
	
	public Color getColor() {
		return color;
	}
	

	public void setTransparency(double newVal) {
		transparency = newVal;
	}
	
	public double getTransparency() {
		return transparency;
	}

}
