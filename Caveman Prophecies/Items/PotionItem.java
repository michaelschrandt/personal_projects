package Items;


import java.util.ArrayList;
import Character.Character;

public class PotionItem extends Item
{
	int benefit;
	//may want to randomize the benefit
	public PotionItem()
	{
		this.benefit = 50;
	}
	
	public String toString()
	{
		return "Potion: +" + this.benefit + " HP";
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
