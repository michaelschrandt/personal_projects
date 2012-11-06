package GUI;

import java.util.Random;

import javax.swing.ImageIcon;

public class MonsterImageGenerator extends ImageGenerator
{
	public MonsterImageGenerator()
	{
		//generate the list here
		this.imagePaths.add("pictures/Baddies/balrog.jpg");
		this.imagePaths.add("pictures/Baddies/ghost.jpg");
		this.imagePaths.add("pictures/Baddies/goblin1.jpg");
		this.imagePaths.add("pictures/Baddies/goblin2.jpg");
		this.imagePaths.add("pictures/Baddies/golem1.jpg");
		this.imagePaths.add("pictures/Baddies/MonsterA.jpg");
		this.imagePaths.add("pictures/Baddies/pitchblack.jpg");
		this.imagePaths.add("pictures/Baddies/rancor.png");
		this.imagePaths.add("pictures/Baddies/troll1.jpg");
		this.imagePaths.add("pictures/Baddies/urukhai.jpg");
		this.imagePaths.add("pictures/Baddies/worm.png");
	}

	@Override
	public ImageIcon getImageIcon(Random r)
	{
		return new ImageIcon(this.imagePaths.get(Math.abs(r.nextInt())%this.imagePaths.size()));
	}
}
