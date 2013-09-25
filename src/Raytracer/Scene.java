package raytracer;

import java.awt.Color;
import java.util.ArrayList;

import primitives.*;
import lights.*;

public class Scene {

	public static ArrayList<Primitive> primitives;
	public static ArrayList<Light> lights;
	private Camera cam;
	public final static Color backgroundColor = new Color(159,255,255);
	
	public Scene() {
		lights = new ArrayList<Light>();
		primitives = new ArrayList<Primitive>();
		cam = new Camera();
		
		loadBoxes();
//		loadSpheres();
		
		cam.render();
	}
	
	public static ArrayList<Primitive> getPrimitives() {
		return primitives;
	}
	
	public void loadBoxes() {
		// spot light
//		Point position = new Point(-3,4,-2);
//		Vector vector = new Vector(4,-4,8);
//		SpotLight sL = new SpotLight(position, vector, 5, 20);
//		lights.add(sL);
		
		
		// point light
		Point position = new Point(-3,10,-2);
		PointLight pL = new PointLight(position, 130, 1);
		lights.add(pL);
				
		// dual distant lights
//		Vector dir = new Vector(1,-5,3);
//		DistantLight dLight = new DistantLight(dir,1.2);
//		lights.add(dLight);
//		dir = new Vector(1,-2,-1);
//		dLight = new DistantLight(dir,0.3);
//		lights.add(dLight);
		
		
		Triangle tri;
		Point a,b,c,d,e,f,g,h;
		
		// floor
//		a = new Point(-1.5,0,1.75);
//		b = new Point(-1.5,0,5);
//		c = new Point(1.5,0,5);
//		d = new Point(1.5,0,1.75);
//		tri = new Triangle(a,c,b,Color.lightGray);
//		primitives.add(tri);
//		tri = new Triangle(a,d,c,Color.lightGray);
//		primitives.add(tri);
		
		// new floor
		Vector normal = new Vector(0,1,0);
		Point point = new Point(0,0,0);
		Plane plane = new Plane(normal, point, Color.white,0);
		primitives.add(plane);
		
		// wall mirror
//		a = new Point(-1.5,0,6);
//		b = new Point(-1.5,3,6);
//		c = new Point(1.5,3,6);
//		d = new Point(1.5,0,6);
//		tri = new Triangle(a,b,c,Color.white,1);
//		primitives.add(tri);
//		tri = new Triangle(a,c,d,Color.white,1);
//		primitives.add(tri);
		
		// tall box
		a = new Point(-1,0,4);
		b = new Point(-1,0,4.5);
		c = new Point(-0.5,0,4.5);
		d = new Point(-0.5,0,4);
		e = new Point(-1,2.5,4);
		f = new Point(-1,2.5,4.5);
		g = new Point(-0.5,2.5,4.5);
		h = new Point(-0.5,2.5,4);
		tri = new Triangle(a,e,h,Color.cyan);
		primitives.add(tri);
		tri = new Triangle(a,h,d,Color.blue);
		primitives.add(tri);
		tri = new Triangle(e,f,h,Color.red);
		primitives.add(tri);
		tri = new Triangle(f,g,h,Color.green);
		primitives.add(tri);
		tri = new Triangle(h,g,d,Color.magenta);
		primitives.add(tri);
		tri = new Triangle(d,g,c,Color.yellow);
		primitives.add(tri);
		tri = new Triangle(a,b,e,Color.pink);
		primitives.add(tri);
		tri = new Triangle(b,f,e,Color.lightGray);
		primitives.add(tri);
		tri = new Triangle(b,c,g,Color.red);
		primitives.add(tri);
		tri = new Triangle(b,g,f,Color.yellow);
		primitives.add(tri);
		
		// big box
//		a = new Point(-1.25,0,2);
//		b = new Point(-0.25,0,3);
//		c = new Point(-0.25,0,2);
//		d = new Point(-1.25,1,2);
//		e = new Point(-1.25,1,3);
//		f = new Point(-0.25,1,3);
//		g = new Point(-0.25,1,2);
//		h = new Point(-1.25,0,3);
//		tri = new Triangle(e,d,a,Color.pink);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(e,a,h,Color.blue);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(f,e,h,Color.pink);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(b,f,h,Color.green);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(a,d,c,Color.yellow);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(d,g,c,Color.red);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(e,f,d,Color.cyan);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(d,f,g,Color.green);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(g,f,c,Color.magenta);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(c,f,b,Color.orange);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
		
		// big PINK box
		a = new Point(-1.25,0,2);
		b = new Point(-0.25,0,3);
		c = new Point(-0.25,0,2);
		d = new Point(-1.25,1,2);
		e = new Point(-1.25,1,3);
		f = new Point(-0.25,1,3);
		g = new Point(-0.25,1,2);
		h = new Point(-1.25,0,3);
		Color myPink = new Color(255,170,211);
		tri = new Triangle(e,d,a,myPink,0);
		primitives.add(tri);
		tri = new Triangle(e,a,h,myPink,0);
		primitives.add(tri);
		tri = new Triangle(f,e,h,myPink,0);
		primitives.add(tri);
		tri = new Triangle(b,f,h,myPink,0);
		primitives.add(tri);
		tri = new Triangle(a,d,c,myPink,0);
		primitives.add(tri);
		tri = new Triangle(d,g,c,myPink,0);
		primitives.add(tri);
		tri = new Triangle(e,f,d,myPink,0);
		primitives.add(tri);
		tri = new Triangle(d,f,g,myPink,0);
		primitives.add(tri);
		tri = new Triangle(g,f,c,myPink,0);
		primitives.add(tri);
		tri = new Triangle(c,f,b,myPink,0);
		primitives.add(tri);
		
		// big 2-COLOR box
//		a = new Point(-1.25,0,2);
//		b = new Point(-0.25,0,3);
//		c = new Point(-0.25,0,2);
//		d = new Point(-1.25,1,2);
//		e = new Point(-1.25,1,3);
//		f = new Point(-0.25,1,3);
//		g = new Point(-0.25,1,2);
//		h = new Point(-1.25,0,3);
//		Color myPink = new Color(247,120,161);
//		tri = new Triangle(e,d,a,myPink);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(e,a,h,Color.cyan);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(f,e,h,Color.cyan);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(b,f,h,myPink);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(a,d,c,myPink);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(d,g,c,Color.cyan);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(e,f,d,Color.cyan);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(d,f,g,myPink);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(g,f,c,myPink);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
//		tri = new Triangle(c,f,b,Color.cyan);
//		tri.setTransparency(0.5);
//		primitives.add(tri);
		
		
		
		// angled box
		a = new Point(0.75,0,2.5);
		b = new Point(0.25,0,3);
		c = new Point(0.75,0.5,3.5);
		d = new Point(1.25,0,3);
		e = new Point(0.75,0.5,2.5);
		f = new Point(0.25,0.5,3);
		g = new Point(1.25,0.5,3);
		h = new Point(0.75,0,3.5);
		tri = new Triangle(c,f,b,Color.green);
		tri.setTransparency(0);
		primitives.add(tri);
		tri = new Triangle(c,b,h,Color.blue);
		tri.setTransparency(0);
		primitives.add(tri);
		tri = new Triangle(g,c,h,Color.red);
		tri.setTransparency(0);
		primitives.add(tri);
		tri = new Triangle(g,h,d,Color.pink);
		tri.setTransparency(0);
		primitives.add(tri);
		tri = new Triangle(b,f,a,Color.orange);
		tri.setTransparency(0);
		primitives.add(tri);
		tri = new Triangle(f,e,a,Color.yellow);
		tri.setTransparency(0);
		primitives.add(tri);
		tri = new Triangle(f,c,g,Color.red);
		tri.setTransparency(0);
		primitives.add(tri);
		tri = new Triangle(f,g,e,Color.blue);
		tri.setTransparency(0);
		primitives.add(tri);
		tri = new Triangle(e,g,a,Color.green);
		tri.setTransparency(0);
		primitives.add(tri);
		tri = new Triangle(a,g,d,Color.cyan);
		tri.setTransparency(0);
		primitives.add(tri);
		
		// ball
		Point center  = new Point(0.4,0.5,4.2);
		double radius = 0.5;
		Sphere ball = new Sphere(center, radius, Color.white, 1);
		primitives.add(ball);
	}
	
	public void loadSpheres() {
		Point center;
		Sphere ball;
		double radius;
		
		// adding balls
		center  = new Point(0,3,17);
		radius = 3;
		ball = new Sphere(center, radius, Color.white, 0.8);
		primitives.add(ball);
		center  = new Point(5,2.5,13);
		radius = 2.5;
		ball = new Sphere(center, radius, Color.white, 0.8);
		primitives.add(ball);
		center  = new Point(4,2,7.5);
		radius = 2;
		ball = new Sphere(center, radius, Color.white, 0.8);
		primitives.add(ball);
		center  = new Point(0,1.5,5);
		radius = 1.5;
		ball = new Sphere(center, radius, Color.white, 0.8);
		primitives.add(ball);
		center  = new Point(-3,1,7);
		radius = 1;
		ball = new Sphere(center, radius, Color.white, 0.8);
		primitives.add(ball);
		center  = new Point(-4.5,0.75,9);
		radius = 0.75;
		ball = new Sphere(center, radius, Color.white, 0.8);
		primitives.add(ball);
		center  = new Point(-4,0.5,11.5);
		radius = 0.5;
		ball = new Sphere(center, radius, Color.white, 0.8);
		primitives.add(ball);
		center  = new Point(-3.5,0.35,13);
		radius = 0.35;
		ball = new Sphere(center, radius, Color.white, 0.8);
		primitives.add(ball);
		System.out.println("balls added");
		
		// new floor
		Vector normal = new Vector(0,1,0);
		Point point = new Point(0,0,0);
		Plane plane = new Plane(normal, point, Color.white,0);
		primitives.add(plane);
		
		// set up camera
		cam.setPosition(1,7,-3);
		cam.setRotation(-22);
	}
	
	public static void main(String args[]) {
		Scene scene1 = new Scene();
	}
	
}