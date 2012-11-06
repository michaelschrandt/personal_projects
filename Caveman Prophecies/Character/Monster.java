
package Character;

import java.util.Random;

/**
 * CSCD 439
 */

public abstract class Monster extends Character {

    public void recover(Character another)
    {
        if (this.checkIsTrue(another.myCharacter.getSpecialPercentage())) 
        {
            this.recover();
        } 
        else 
        {
            System.out.println("Oops. This time you cannot recover. ");
        }
    }
    
    private void recover()
    {
        if(this.checkIsTrue(this.myCharacter.getSpecialPercentage()))
        {
            this.myCharacter.recovery(this.myCharacter.getHp() * this.myCharacter.getSpecialPercentage() * (this.generateInteger(0, 100)/100));
        }
    }
	 
    protected int calcCaller()
    {
        if(this.myCharacter.getValid())
        {
            return ((int) Math.floor((this.myCharacter.getHp() * this.myCharacter.getSpecialPercentage()) / this.myCharacter.getAttackNum() / this.myCharacter.getSpeedNum()));
        }
        else
        {
            return 0;
        }
    }
	 
    @Override
    protected final int getRole()
    {
        return 1;
    }
}

class MonsterGenerator extends Monster {
    
    public MonsterGenerator(Random r)
    {
        this.generateCharacter(r);
    }
    
    private void generateCharacter(Random r)
    {
    	MonsterNameList myList = new MonsterNameList();
        this.myCharacter = new CharacterAttribute(myList.getName(), this.generateDouble(6300.0, 11005.0), this.generateInteger(18, 38), this.generateInteger(23, 45), this.generatePercentage(0.15), this.generatePercentage(0.37));
        this.myCharacter.setIcon(monGen, r);
        
        int specNum = r.nextInt(5);
        switch(specNum)
        {
        case 0: 
        	this.setSpecialAttack(new MonsterBoss(this));
        	break;
        case 1:
        	this.setSpecialAttack(new MonsterCrazyBlow(this));
        	break;
        case 2:
        	this.setSpecialAttack(new MonsterFireBall(this));
        	break;
        case 3:
        	this.setSpecialAttack(new MonsterMasher(this));
        	break;
        case 4:
        	this.setSpecialAttack(new MonsterPoison(this));
        	break;
        	
        }
    }
}
