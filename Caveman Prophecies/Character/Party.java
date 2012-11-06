package Character;

import java.util.ArrayList;

public class Party
{
	protected static int NUM_PLAYERS = 3;
	protected ArrayList<Character> characters;
	private boolean hasShard;
	
	public Party()
	{
		hasShard = false;
		characters = new ArrayList<Character>();
	}

	public ArrayList<Character> getCharacters()
	{
		return characters;
	}
	
	public String toString()
	{
//		for(Character c : characters)
//		{
//			System.out.println(c.myCharacter.getName());
//		}
		return "";
	}
	
	public boolean hasShard()
	{
		return this.hasShard;
	}
	
	public void setShard()
	{
		this.hasShard = true;
	}

}
