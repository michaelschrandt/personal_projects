package GUI;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

//This will return an image object when the creation method is called
//A random image is returned each time it's called.
//Factory pattern
public abstract class ImageGenerator {
	
	ArrayList<String> imagePaths = new ArrayList<String>();
	//Random r = new Random(System.currentTimeMillis()); //to randomly grab an image
	
	public abstract ImageIcon getImageIcon(Random r);

}
