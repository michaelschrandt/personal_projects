package Items;

import java.util.ArrayList;

import Character.Character;

public class Sword extends Item
{
	int benefit;
	
	public Sword()
	{
		this.benefit = 10;
	}
	
	public String toString()
	{
		return "The Vanguard (sword): +" + this.benefit + " attack";
	}
	
	//will add to each party member's atk.
	@Override
	public void applyBenefit(ArrayList<Character> partyToApplyTo) 
	{
		int cur_atk;
		
		//add the bonus atk to each character in the party passed in
		for(Character c: partyToApplyTo)
		{
			
			cur_atk = c.getCharacterAttributes().getAttackNum();
			c.getCharacterAttributes().setAttackNum(cur_atk + this.benefit);
		}
	}
}
