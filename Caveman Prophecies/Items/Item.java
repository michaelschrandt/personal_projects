package Items;

import java.util.ArrayList;
import Character.Character;

public abstract class Item 
{

	//fields that items should have
	int benefit;
	
	//methods that items should have
	public abstract void applyBenefit(ArrayList<Character> partyToApplyTo);
}
