package raytracer;

import java.awt.Color;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import display.ImagePanel;
import display.ImageViewer;
import display.OFImage;

import primitives.Primitive;

public class Camera {
	
	private Point camPos;
	private Vector camRot;
	private static int width, height;
	private Color[][] canvas;
	private static double distanceToClosestPrimitive;
	private static int antiA;
	public static int counter = 0;
	
	public static boolean ambientOn = false;
	public static double ambientVal = 0.20;
	
	public static int reflectionCount = 0;
	public static int maxRefs = 10;

	private double xAxisRoll = -30.0;
	
	public Camera() {
		camPos = new Point(0,3,-0.3);
		camRot = new Vector(0.0,0.0,1.0);
		width = 600;
		height = 600;
		antiA = 1;
		
	}

	public void render() {
		canvas = new Color[height][width];
		castRays();
	}
	
	public void print() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(canvas[i][j] == null) System.out.print("-");
				else System.out.print(canvas[i][j]);
			}
			System.out.println();
		}
	}
	
	private Color getCollision(Ray ray, Primitive lastPrimitive) {
		// set up color channels
		int r = 0;
		int g = 0;
		int b = 0;
		
		// set up variables for storing closest primitive
		distanceToClosestPrimitive = Double.MAX_VALUE;
		Primitive closestPrimitive = null;
		Point pointOfIntersect = null;
		
		// check all primitives for collision with ray
		for(Primitive pri : Scene.primitives) {
			Point P = pri.rayIntersectPrimitive(ray);
			if(P != null && pri != lastPrimitive) {
				double thisDist = Point.distance(ray.getOrigin(),P);
				if(thisDist < distanceToClosestPrimitive) {
					closestPrimitive = pri;
					pointOfIntersect = P;
					distanceToClosestPrimitive = thisDist;
				}
			} 
		}
		
		// check if there was a collision, get the color if there was one
		if(closestPrimitive != null && pointOfIntersect != null) {
			
			// set up values to determine the color that is returned
			double reflectivity = closestPrimitive.getReflectivity();
			double transparency = closestPrimitive.getTransparency();
			double[] colorOfLight  = closestPrimitive.calcLight(pointOfIntersect);
			Color surfaceColor  = closestPrimitive.getColor();

			// set the base color
			r = (int)Math.abs((surfaceColor.getRed()   * (1 - reflectivity) * (1 - transparency) * colorOfLight[0]));
			g = (int)Math.abs((surfaceColor.getGreen() * (1 - reflectivity) * (1 - transparency) * colorOfLight[1]));
			b = (int)Math.abs((surfaceColor.getBlue()  * (1 - reflectivity) * (1 - transparency) * colorOfLight[2]));
			
			// checks for reflections
			if(reflectivity > 0.0 && reflectionCount < maxRefs) {
				Vector ref = Vector.reflect(ray.getDirection(),closestPrimitive.getNormal(pointOfIntersect));
				Ray newRay = new Ray(pointOfIntersect, ref);
				reflectionCount++;
				Color reflection = getCollision(newRay, closestPrimitive);
				r += (int)Math.abs((reflection.getRed()   * reflectivity * (1 - transparency)));
				g += (int)Math.abs((reflection.getGreen() * reflectivity * (1 - transparency)));
				b += (int)Math.abs((reflection.getBlue()  * reflectivity * (1 - transparency)));
			}
			
			// check for transparency
			if(transparency > 0.0) {
				Ray newRay = new Ray(pointOfIntersect, ray.getDirection());
				Color trans = getCollision(newRay, closestPrimitive);
				r += (int)Math.abs((trans.getRed()   * (1 - reflectivity) * transparency));
				g += (int)Math.abs((trans.getGreen() * (1 - reflectivity) * transparency));
				b += (int)Math.abs((trans.getBlue()  * (1 - reflectivity) * transparency));
			}
			
			
		} else {
			// return the background (sky) color
			Color color = Scene.backgroundColor;
			r += color.getRed();
			b += color.getBlue();
			g += color.getGreen();
		}
		
		// if RGB values exceed the allowed scope, cap them to max or min value
		if(r > 255) r = 255;
		if(g > 255) g = 255;
		if(b > 255) b = 255;
		
		if(r < 0) r = 0;
		if(g < 0) g = 0;
		if(b < 0) b = 0;
		
		
		Color result = new Color(r,g,b);
		return result;
	}
	
	public void castRays() {
		// let i and j determine the pixel to be painted
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				// set up color channels
				int r = 0;
				int g = 0;
				int b = 0;
					
				// if anti-aliasing is used, more than one ray is cast per pixel
				for(int y = 0; y < antiA; y++) {
					for(int x = 0; x < antiA; x++) {
						double xA = x;
						double yA = y;
						double iA = i + (yA / antiA);
						double jA = j + (xA / antiA);
						Ray ray = makeRay(iA,jA);
						
						// get the color this ray will add to the pixel
						Color collision = getCollision(ray,null);
						r += collision.getRed();
						g += collision.getGreen();
						b += collision.getBlue();
						
						// reset number of times ray has been reflected
						reflectionCount = 0;
					}
				}
				
				// divide each color channel with number of rays cast per pixel
				r = r / (antiA * antiA);
				g = g / (antiA * antiA);
				b = b / (antiA * antiA);
									
				canvas[i][j] = new Color(r,g,b);
			}
		}
		
		OFImage ofImage = new OFImage(width,height);
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				ofImage.setPixel(j, i, canvas[i][j]);
			}
		}
		
		// Autosave af billedet på desktop med timestamp.
		// Passer til Toms computer, skal ændres hvis programmet skal køres andre steder.
		
//		String pathName = new String("C:\\Users\\Tom\\Desktop\\Render ");
//		String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(Calendar.getInstance().getTime());
//		String fileName = new String(pathName + timeStamp + ".png");
//		File file = new File(fileName);
//		ofImage.saveImage(ofImage, file);
		
		// Image viewer, som åbner et vindue og viser billedet
		
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setImage(ofImage);
		ImageViewer IW = new ImageViewer(imagePanel);
	}
	
	private Vector rollAlongXaxis(Vector v) {
		double length = Math.sqrt(v.getY() * v.getY() + v.getZ() * v.getZ());
		double angle0 = Math.toDegrees(Math.sin(v.getY() / length));
		double angle1 = angle0 + xAxisRoll;
		
		double y = Math.sin(Math.toRadians(angle1)) * length;
		double z = Math.cos(Math.toRadians(angle1)) * length;
		
		Vector result = new Vector(v.getX(),y,z);		
		return result;
	}
	
	public Ray makeRay(double i, double j) {
		double x = j - ((width - 1) / 2);
		double y = ((height - 1) / 2) - i;
		double z = width;
		
		Vector vec = new Vector(x,y,z);
		vec = rollAlongXaxis(vec);
		
		Ray ray = new Ray(camPos,vec);
		return ray;
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static int getHeight() {
		return height;
	}
	
	
	public Point getPosition() {
		return camPos;
	}

	public void setPixelWidth(int w) {
		if(w > 0) {
			width = w;
		} else System.out.println("Width must be a whole, positive number");
	}

	public void setPixelHeight(int h) {
		if(h > 0) {
			height = h;
		} else System.out.println("Height must be a whole, positive number");
	}

	public void setPosition(double x, double y, double z) {
		camPos = new Point(x,y,z);		
	}

	public void setRotation(double d) {
		xAxisRoll = d;
	}
}