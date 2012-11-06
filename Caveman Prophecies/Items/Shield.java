package Items;

import java.util.ArrayList;

import Character.Character;

public class Shield extends Item
{
	int benefit;
	
	public Shield()
	{
		this.benefit = 10;
	}
	
	public String toString()
	{
		return "Stalwart Shield: +" + this.benefit + " defense";
	}
	
	//will add to each party member's atk.
	@Override
	public void applyBenefit(ArrayList<Character> partyToApplyTo) 
	{
		int cur_def;
		
		//add the bonus atk to each character in the party passed in
		for(Character c: partyToApplyTo)
		{
			
			cur_def = c.getCharacterAttributes().getDefenseNum();
			c.getCharacterAttributes().setDefenseNum(cur_def - this.benefit);
		}
	}
}
