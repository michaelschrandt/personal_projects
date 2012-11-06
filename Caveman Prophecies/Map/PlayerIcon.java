package Map;

import javax.swing.ImageIcon;

//this is just to keep track of the player as it walks around the overworld map

public class PlayerIcon extends Icon
{
	
	/**
	 * @param c starting position
	 */
	public PlayerIcon(Coordinates c)
	{
		img = new ImageIcon("pictures/player.jpg").getImage();
		setPosition(c);
	}
}
