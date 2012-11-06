package Map;

import java.util.Random;

import javax.swing.ImageIcon;

public class MonsterIcon extends Icon
{
	public MonsterIcon(Coordinates c)
	{
		setPosition(c);
		img = new ImageIcon("pictures/monsterIcon.jpg").getImage();
	}
	
	public boolean equals(Object o)
	{
		if(o instanceof MonsterIcon)
		{
			MonsterIcon that = (MonsterIcon)o;
			return this.getPosition().equals(that.getPosition());
		}
		
		return false;
	}
	
	public void nextPosition(Map map)
	{
		Coordinates cur = getPosition();
		Coordinates next;
		
		Random rng = new Random();
		
		int n = rng.nextInt(5);
		
		switch(n)
		{
		case 0:
			next = cur.up();
			break;
		case 1:
			next = cur.down();
			break;
		case 2:
			next = cur.right();
			break;
		case 3:
			next = cur.left();
			break;
		case 4:
			next = cur;
			break;
		default:
			next = cur;
		}
		
		if(!map.collidesWith(next))
		{
			setPosition(next);
		}
	}
}
