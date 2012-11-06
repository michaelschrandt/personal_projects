package Map;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tile
{
	private ImageIcon img;
	private ImageIcon fog;
	
	private DungeonFeature type;
	private boolean visible;
	private boolean walkable;
	
	public Tile(DungeonFeature type)
	{
		this.type = type;
		
		if(type == DungeonFeature.EMPTY)
			this.img = new ImageIcon("pictures/emptyTile.jpg");
		else if(type == DungeonFeature.BLANK)
			this.img = new ImageIcon("pictures/wallTile.jpg");
		else if(type == DungeonFeature.DOWN_STAIR)
			this.img = new ImageIcon("pictures/downstairsTile.jpg");
		else if(type == DungeonFeature.UP_STAIR)
			this.img = new ImageIcon("pictures/upstairsTile.jpg");
		else
			System.out.println("No picture for tile: " + type);
	}
	
	public Image getImage()
	{
		return this.img.getImage();
	}
	
	public boolean isWalkable()
	{
		return this.walkable;
	}
	
	public boolean isVisible()
	{
		return this.visible;
	}
	
	public DungeonFeature getType()
	{
		return this.type;
	}

	
}
