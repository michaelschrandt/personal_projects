
package Character;

import java.awt.TextArea;
import java.text.DecimalFormat;
import java.util.Random;

import GUI.HeroImageGenerator;
import GUI.ImageGenerator;
import GUI.MonsterImageGenerator;

/**
 * CSCD 439
 */

public abstract class Character {
    protected static Random RNG = new Random(System.currentTimeMillis());
    protected CharacterAttribute myCharacter = null;
    private SpecialAttack myAttack = new SpecialAttackDefault();
    protected abstract int getRole();
    private int poisonRole = 0;
    protected Equipment myEquipment = new Equipment();
    //image generators for Characters
    protected ImageGenerator heroGen = new HeroImageGenerator();
    protected ImageGenerator monGen = new MonsterImageGenerator();
    
    public void setPoison(int times)
    {
        this.poisonRole += times;
    }
    
    public void setSpecialAttack(SpecialAttack att)
    {
        this.myAttack = att;
    }
    
    public boolean doSpecialAttack(Character another, TextArea ta)
    {
        return this.myAttack.specialAttack(another, ta);
    }
    
    public boolean attack(Character another,TextArea textArea)
    {
        if(this.checkIsTrue(another.myCharacter.getSpeedNum()))
        {
            double damage = this.calcGeneralDamage(another);
            damage = Character.fixDecimalPlace(damage, 3);
            System.out.println(another.myCharacter.getName() + " has been attacked, and it caused " + damage + " damage. ");
            
            //update text area
            textArea.append(myCharacter.getName() + " unleashes hot fury against " + another.myCharacter.getName() + "!!!\n");
            textArea.append(another.myCharacter.getName() + " has been attacked, taking " + damage + " damage.\n");
            
            another.myCharacter.beingAttacked(damage);
            another.myCharacter.setHp(Character.fixDecimalPlace(another.myCharacter.getHp(), 3));
            System.out.println("The remaining Hp for " + (another.getRole()==0?"Hero":"Monster") + " is: " + ((another.myCharacter.getHp()<0)?0:another.myCharacter.getHp()) + " points. ");
            if(another.isDead())
            {
                this.gameOverScene();
                another.myCharacter.setInvalid();
            }
            return true;
        }
        else
        {
            System.out.println("Nice. " + another.myCharacter.getName() + " successfully avoided the attack. ");
            //update text area
            textArea.append(myCharacter.getName() + " unleashes hot fury against " + another.myCharacter.getName() + "!!!\n");
            textArea.append(another.myCharacter.getName() + " has dodged the attack!\n");
            return false;
        }
    }
	 
    public void setWeapon(Items.Item equip)
    {
        if(this.getRole() == 0) 
        {
            this.myEquipment.setEquipment(equip);
        }
        else 
        {
            System.out.println("Sorry, monster can't have a weapon. ");
        }
    }
    
    public Items.Item getWeapon(int index)
    {
        return this.myEquipment.getEquipment(index);
    }
    
    protected int generateInteger(int lowRange, int highRange)
    {
        if(lowRange < highRange && lowRange >= 0 && highRange >= 0)
        {
            int gotHp = Math.abs(Character.RNG.nextInt(highRange));
            if(gotHp > lowRange)
            {
                return gotHp;
            }
            else if(gotHp <= lowRange)
            {
                return gotHp % (highRange - lowRange) + lowRange;
            }
            else
            {
                throw new IllegalArgumentException("Invalid Number!! ");
            }
        }
        else
        {
            throw new IllegalArgumentException("Invali Number!! ");
        }
        
    }

    protected double generateDouble(double lowRange, double highRange)
    {
        if(lowRange < highRange && lowRange >= 0.00 && highRange >= 0.00)
        {
            double num = Math.abs(Character.RNG.nextInt()) + Character.RNG.nextDouble();
            if(num > lowRange && num < highRange)
            {
                return Character.fixDecimalPlace(num, 3);
            }
            else if(num <= lowRange)
            {
                return Character.fixDecimalPlace(num % (highRange - lowRange) + lowRange, 3);
            }
            else if(num >= highRange)
            {
                return Character.fixDecimalPlace(highRange - (num % (highRange - lowRange)), 3);
            }
            else
            {
                throw new IllegalArgumentException("Invalid Number! ");
            }
        }
        else
        {
            throw new IllegalArgumentException("lowRange and highRange is not in correct order or is negative. ");
        }
    }
    
    protected double generatePercentage(double lowLimit, double highLimit)
    {
        if(lowLimit < highLimit && lowLimit >= 0.00 && highLimit >= 0.00 && lowLimit <= 1.00 && highLimit <= 1.00)
        {
            double percentage = Character.RNG.nextDouble();
            if(percentage > lowLimit && percentage < highLimit)
            {
                return Character.fixDecimalPlace(percentage, 5);
            }
            else if(percentage <= lowLimit)
            {
                return Character.fixDecimalPlace(percentage % (highLimit - lowLimit) + lowLimit, 5);
            }
            else if(percentage >= highLimit)
            {
                return Character.fixDecimalPlace(highLimit - (percentage % (highLimit - lowLimit)), 5);
            }    
            else
            {
                throw new IllegalArgumentException("Invalid Number!! ");
            }
        }
        else
        {
            throw new IllegalArgumentException("Invalid Number!! ");
        }
    }
    
    protected double generatePercentage(double limit)
    {
        if(limit <= 1.00 && limit > 0.00)
        {
            double result = Character.RNG.nextDouble();
            return Character.fixDecimalPlace(result < limit? result:result%limit, 5);
        }
        else
        {
            throw new IllegalArgumentException("limit is invalid! ");
        }
    }
    
    protected boolean checkIsTrue(double checkNum)
    {
        return checkNum < this.generatePercentage(0.99) ? true:false;
    }

    @Override
    public String toString()
    {
        return this.myCharacter.getValid() ? (this.myCharacter.toString() + (this.getRole()==0?" " + this.myEquipment:"")):"The character is dead. ";
    }

    public boolean isDead()
    {
        if(this.myCharacter.getHp() < 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    protected void gameOverScene()
    {
        System.out.println("Oops. The " + (this.getRole()==0?"Monster":"Hero") + " died. " + (this.getRole()==0?"Hero":"Monster") + " win. ");
    }
    
    public CharacterAttribute getCharacterAttributes()
    {
    	return myCharacter;
    }

    protected double calcGeneralDamage(Character another)
    {
        if(this.checkIsTrue(((int) another.myCharacter.getSpeedNum() * 100)))
        {
            double result = this.myCharacter.getHp() * this.myCharacter.getAttackNum() / 100 - another.myCharacter.getHp() * another.myCharacter.getDefenseNum() * (1 - another.myCharacter.getDefenseNum()) / 1000;
            return result<0?0:result*this.generatePercentage(0.33, 0.51);
        }
        else
        {
            return 0.00;
        } //missed
    }
    
    public static double fixDecimalPlace(double x, int percison)
    {
        String formatMode = "#.";
        for(int i=0; i<percison; i++)
        {
            formatMode += "0";
        }
        DecimalFormat myFormat = new DecimalFormat(formatMode);
        return Double.valueOf(myFormat.format(x));
    }
}

interface NameList {
	public String getName();
}

class HeroNameList implements NameList {
	private static final String[] MYNAME = { "Crush", "Cetaceacut", "Tarantula", "Cyborg", "Dolphin", "Chill", "Flame Hit", "Golem Haunt", "Icedroid", "Knife Breeze", "Master Mace", "Reptile Sorceror", "Spear Sapphire", 
									"Spook Tide", "Trace Snare"};
	
	public HeroNameList()
	{
	}
	
        @Override
	public String getName()
	{
		return HeroNameList.MYNAME[Character.RNG.nextInt(HeroNameList.MYNAME.length)];
	}
}

class MonsterNameList implements NameList {
	private static final String[] MYNAME = { "Attacker-pudding", "Chokedart Mass", "Chromatic Freezer-seducer", "Consuming Tail", "Eyechitin Hornet", "Ghoul Desecrator", "Heavenly Tail Elemental", 
											"Massive Searcher", "Penta Vault Custodian", "Secretplant", "Sharp Shrieker", "Shiver Eater", "Snake Lurker", "Thiefmold Soul", 
											"Wyvern Kraken" };
	
	public MonsterNameList()
	{
	}
	
        @Override
	public String getName()
	{
		return MonsterNameList.MYNAME[Character.RNG.nextInt(MonsterNameList.MYNAME.length)];
	}
}
