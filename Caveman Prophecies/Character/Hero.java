
package Character;

import java.util.Random;

/**
 * CSCD 439
 */

public abstract class Hero extends Character {
	
    @Override
    protected final int getRole()
    {
        return 0;
    }
    
}

class HeroGenerator extends Hero {
    
    public HeroGenerator(Random r)
    {
        this.generateHero(r);
    }
    
    private void generateHero(Random r)
    {
    	HeroNameList myList = new HeroNameList();
        this.myCharacter = new CharacterAttribute(myList.getName(), this.generateDouble(4280.0, 6130.0), this.generateInteger(9, 22), this.generateInteger(8, 29), this.generatePercentage(0.42), this.generatePercentage(0.33));
        this.myCharacter.setIcon(heroGen, r);
        
        int specNum = r.nextInt(4);
        switch(specNum)
        {
        case 0: 
        	this.setSpecialAttack(new HeroSpecialDecrease(this));
        	break;
        case 1:
        	this.setSpecialAttack(new HeroSpecialMuscle(this));
        	break;
        case 2:
        	this.setSpecialAttack(new HeroSpecialRecover(this));
        	break;
        case 3:
        	this.setSpecialAttack(new HeroSpecialTriple(this));
        	break;
        	
        }
    }
}