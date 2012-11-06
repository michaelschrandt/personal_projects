package Map;

import java.awt.Graphics;
import java.awt.Image;

import Game.Game;

public abstract class Icon 
{
	private Coordinates position;
	protected Image img;
	
	public void draw(Graphics g2)
	{
		//Graphics g = Game.getCanvas().getGraphics();
		g2.drawImage(this.img, position.getX()*Game.TILE_W, position.getY()*Game.TILE_W, Game.TILE_W, Game.TILE_W, Game.getCanvas());
	}
	
	public Coordinates getPosition()
	{
		return this.position;
	}
	
	public boolean collidesWith(Icon other)
	{
		return this.getPosition().equals(other.getPosition());
	}
	
	public void setPosition(Coordinates newPos)
	{
		this.position = newPos;
	}
}
