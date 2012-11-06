package Items;

import java.util.ArrayList;

import Character.Character;

public class BandageItem extends Item
{
	int benefit;
	
	public BandageItem()
	{
		this.benefit = 1000;
	}
	
	public String toString()
	{
		return "Bandage: +" + this.benefit + " HP";
	}
	
	//will add to the HP in the entire party
	@Override
	public void applyBenefit(ArrayList<Character> partyToApplyTo) 
	{
		double cur_hp;
		
		//add the bonus hp to each character in the party passed in
		for(Character c: partyToApplyTo)
		{
			
			cur_hp = c.getCharacterAttributes().getMaxHp();
			c.getCharacterAttributes().setMaxHp( cur_hp + (double)this.benefit);
		}
	}
}
