package Items;

import java.util.ArrayList;

import Character.Character;
import Game.Game;

public class MeteorShard extends Item{
	
	public MeteorShard()
	{
	}
	
	public String toString()
	{
		return "Meteor Shard";
	}
	
	@Override
	public void applyBenefit(ArrayList<Character> partyToApplyTo) 
	{
		Game.getParty().setShard();
	}
}
