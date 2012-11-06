
package Character;

import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import GUI.ImageGenerator;

/**
 * CSCD 439
 */

public class CharacterAttribute {
	private String name = "Not set yet. "; //Character Name
        private double max_hp = 0;
	private double hp = 0; //hp
	private int attackNum = 0; //Attack ability
	private int defenseNum = 0; //Defense Ability
	private double speedNum = 0.00; //Percentage to avoid attack < 1
	private double specialPercentage = 0.00; //Percentage have special attack < 1
	private boolean isValid = false;
	private Icon icon; //will be generated internally, but needs a factory passed in from the outside
	
	protected CharacterAttribute(String name)
	{
		this.name = name;
	} //reserved

	public CharacterAttribute(String name, double mx_hp, int attackNum, int defenseNum, double speedNum, double special)
	{
		if(hp<0 || attackNum<0 || defenseNum<0 || speedNum<0.00 || speedNum>1 || special<0.00 || special>1)
		{
			throw new IllegalArgumentException("Invalid attributes set. ");
		}
		this.name = name;
		this.hp = mx_hp;
                this.max_hp = mx_hp;
		this.attackNum = attackNum;
		this.defenseNum = defenseNum;
		this.speedNum = speedNum;
		this.specialPercentage = special;
		this.isValid = true;
	}
        
        public double getMaxHp()
        {
            return this.max_hp;
        }
        
        
        public void setMaxHp(double max_hp)
        {
            this.max_hp = max_hp;
            this.hp = max_hp;
        } // I don't think MaxHp should be set except in the constructor

	public boolean getValid()
	{
		return this.isValid;
	}

	public void setAttackNum(int attackNum) 
	{
		this.attackNum = attackNum;
	}

	public void setDefenseNum(int defenseNum) 
	{

		this.defenseNum = defenseNum;
		
		if(this.defenseNum > 0)
			this.defenseNum = 0;
	}

	public void setHp(double hp) 
	{
		this.hp = hp;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}

	public void setSpecialPercentage(double specialPercentage) 
	{
		this.specialPercentage = specialPercentage;
	}

	public void setValid()
	{
		this.isValid = true;
	}

	public void setInvalid()
	{
		this.isValid = false;
	}

	public void setSpeedNum(double speedNum) 
	{
		this.speedNum = speedNum;
	}

	//pass either the heroes or monsters image generator
	public void setIcon(ImageGenerator factory, Random r)
	{
		this.icon = factory.getImageIcon(r); //will get the ImageIcon based on the factory passed in
	}
	
	public int getAttackNum() 
	{
		return this.attackNum;
	}

	public int getDefenseNum() 
	{
		return this.defenseNum;
	}

	public double getHp() 
	{
		return this.hp;
	}

	public String getName() 
	{
		return this.name;
	}
	
	public Icon getIcon()
	{
		return this.icon;
	}

	public double getSpecialPercentage() 
	{
		return this.specialPercentage;
	}

	public double getSpeedNum() 
	{
		return this.speedNum;
	}

	public Icon getIcon(ImageGenerator factory)
	{
		return this.icon;
	}
	
	public void beingAttacked(double decrease)
	{
		this.hp -= decrease;
	}

	public void recovery(double points)
	{
		this.hp += points;
	}

	@Override
	public String toString()
	{
		return "Name: " + this.name + 
		" HP: " + this.hp + 
		" Speed: " + this.speedNum;
	}
}

class CharacterAttributeDefault extends CharacterAttribute {

	public CharacterAttributeDefault()
	{
		super("Please implement this Attribute. ");
	}
}
