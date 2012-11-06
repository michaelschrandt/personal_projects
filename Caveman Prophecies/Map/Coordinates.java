package Map;

import java.util.Random;

/**
 * Coordinates are used to keep track of important locations on a map.
 * 
 * @author Mike
 *
 */

public class Coordinates
{
	private int x, y;
	public Coordinates(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	public int getX()
	{
		return this.x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	public int getY()
	{
		return this.y;
	}
	
	//the coordinate located above the current coord
	public Coordinates up()
	{
		return new Coordinates(this.x, this.y - 1);
	}
	
	//coordinate located below current coord
	public Coordinates down()
	{
		return new Coordinates(this.x, this.y + 1);
	}
	
	//coordinate to the left
	public Coordinates left()
	{
		return new Coordinates(this.x-1, this.y);
	}
	
	//coordinate to the right
	public Coordinates right()
	{
		return new Coordinates(this.x+1, this.y);
	}
	
	public boolean equals(Object other)
	{
		if(other instanceof Coordinates)
		{
			Coordinates that = (Coordinates)other;
			return this.getX() == that.getX() && this.getY() == that.getY();
		}
		
		return false;

	}
	
	public static Coordinates randomLocation(int x, int y)
	{
		Random rng = new Random();
		int x_pos = rng.nextInt(x);
		int y_pos = rng.nextInt(y);
		
		return new Coordinates(x_pos, y_pos);
	}
}