package Map;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Items.Item;
import Character.Character;

public class ItemIcon extends Icon
{
	private Item item;
	
	public ItemIcon(Coordinates c, Item i)
	{
		setPosition(c);
		img = new ImageIcon("pictures/itemIcon.jpg").getImage();
		this.item = i;
	}
	
	public void applyBenefit(ArrayList<Character> party)
	{
		this.item.applyBenefit(party);
	}
	
	
	public void setImg(String s)
	{
		img = new ImageIcon(s).getImage();
	}
	
	public String toString()
	{
		return item.toString();
	}
}
