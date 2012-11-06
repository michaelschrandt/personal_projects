package Character;

import java.util.ArrayList;

public class MonsterParty 
{
	protected static int NUM_MONSTERS = 3;
	protected ArrayList<Character> monsters;
	
	public MonsterParty()
	{
		monsters = new ArrayList<Character>();
	}
	
	public ArrayList<Character> getMonsters()
	{
		return monsters;
	}
	
	public String toString()
	{
		return "";
	}
}