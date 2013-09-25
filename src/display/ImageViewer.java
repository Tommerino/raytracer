package display;

import java.awt.*;
import javax.swing.*;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

/**
 * ImageViewer is the main class of the image viewer application. It builds and
 * displays the application GUI and initialises all other components.
 * 
 * To start the application, create an object of this class.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 0.4
 */
public class ImageViewer
{
    private JFrame frame;
    private ImagePanel imagePanel;    
    
    /**
     * Create an ImageViewer show it on screen.
     */
    public ImageViewer()
    {
        makeFrame();
    }
	
    public ImageViewer(ImagePanel ip)
    {
    	makeFrame(ip);
    }
    
    private void makeFrame()
    {
        frame = new JFrame();
        
        Container contentPane = frame.getContentPane();
        
        imagePanel = new ImagePanel();
        contentPane.add(imagePanel);

        // building is done - arrange the components and show        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void makeFrame(ImagePanel imagePanel)
    {
        frame = new JFrame("ImageViewer");
        
        Container contentPane = frame.getContentPane();
        
//      imagePanel = new ImagePanel();
        contentPane.add(imagePanel);

        // building is done - arrange the components and show        
        frame.pack();
        frame.setVisible(true);
    }
    
    

}
