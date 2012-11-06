package GUI;

import java.util.Random;

import javax.swing.ImageIcon;

public class HeroImageGenerator extends ImageGenerator
{
	public HeroImageGenerator()
	{
		//generate the list here
		this.imagePaths.add("pictures/Goodies/soldier.jpg");
		this.imagePaths.add("pictures/Goodies/batman.jpg");
		this.imagePaths.add("pictures/Goodies/captainAmerica.jpg");
		this.imagePaths.add("pictures/Goodies/hulk.jpg");
		this.imagePaths.add("pictures/Goodies/kirk.jpg");
		this.imagePaths.add("pictures/Goodies/thor.jpg");
		
	}

	@Override
	public ImageIcon getImageIcon(Random r)
	{
		return new ImageIcon(this.imagePaths.get(Math.abs(r.nextInt())%this.imagePaths.size()));
	}
	
	
}
