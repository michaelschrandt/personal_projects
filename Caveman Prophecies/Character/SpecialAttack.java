
package Character;

import java.awt.TextArea;


public abstract class SpecialAttack {
    Character myBuddy = null;
    public abstract boolean specialAttack(Character another, TextArea ta);
}

class SpecialAttackDefault extends SpecialAttack {

    @Override
    public boolean specialAttack(Character another, TextArea ta) 
    {
        ta.append("Not set yet. ");
        return true;
    }
}

class HeroSpecialTriple extends SpecialAttack {
    
    public HeroSpecialTriple(Character myCharacter)
    {
        if(myCharacter != null && myCharacter.getRole() == 0)
        {
            this.myBuddy = myCharacter;   
        }
        else
        {
            throw new IllegalArgumentException("Monster can't have this ability. ");
        }
    }

    @Override
    public boolean specialAttack(Character another, TextArea ta) 
    {
        if(this.myBuddy.checkIsTrue(this.myBuddy.myCharacter.getSpecialPercentage()))
        {
            ta.append("Wow. You attacked the " + another.myCharacter.getName() + " successfully. You got three chances to attack. ");
            boolean success = false;
            final int times = 3;
            for(int i=0; i<times; i++)
            {
                if(! another.isDead() && this.myBuddy.attack(another, ta))
                {
                    success = true;
                }
            }
            
            return success;
        }
        else
        {
            ta.append("Unfortunately, you missed the monster. ");
            return false;
        }
    }
}

class HeroSpecialMuscle extends SpecialAttack {

    public HeroSpecialMuscle(Character myCharacter)
    {
        if(myCharacter != null && myCharacter.getRole() == 0)
        {
            this.myBuddy = myCharacter;   
        }
        else
        {
            throw new IllegalArgumentException("Monster can't have this ability. ");
        }
    }
    
    @Override
    public boolean specialAttack(Character another, TextArea ta) 
    {
        if(this.myBuddy.checkIsTrue(this.myBuddy.myCharacter.getSpecialPercentage()))
        {
            ta.append("Good. " + another.myCharacter.getName() + " has been attacked by your strong muscles. ");
            another.myCharacter.beingAttacked(this.myBuddy.myCharacter.getHp() * 0.35);
            return true;
        }
        else
        {
            ta.append("Sorry, you missed the monster.. ");
            return false;
        }
    } 
}

class HeroSpecialDecrease extends SpecialAttack {
    
    public HeroSpecialDecrease(Character myCharacter)
    {
        if(myCharacter != null && myCharacter.getRole() == 0)
        {
            this.myBuddy = myCharacter;   
        }
        else
        {
            throw new IllegalArgumentException("Monster can't have this ability. ");
        }
    }

    @Override
    public boolean specialAttack(Character another, TextArea ta) 
    {
        if(this.myBuddy.checkIsTrue(this.myBuddy.myCharacter.getSpecialPercentage()))
        {
            ta.append("You successfully decrease the ability of " + another.myCharacter.getName() + ". ");
            another.myCharacter.setAttackNum(((int) (another.myCharacter.getAttackNum() * 0.40)));
            another.myCharacter.setDefenseNum(((int) (another.myCharacter.getDefenseNum() * 0.26)));
            another.myCharacter.setSpeedNum(another.myCharacter.getSpeedNum() * 0.33);
            return true;
        }
        else
        {
            ta.append("Sorry, you missed the monsters. ");
            return false;
        }
    }
}

class HeroSpecialRecover extends SpecialAttack {

    public HeroSpecialRecover(Character myCharacter)
    {
        if(myCharacter != null && myCharacter.getRole() == 0)
        {
            this.myBuddy = myCharacter;   
        }
        else
        {
            throw new IllegalArgumentException("Monster can't have this ability. ");
        }
    }
    
    @Override
    public boolean specialAttack(Character another, TextArea ta) 
    {
        if(this.myBuddy.checkIsTrue(this.myBuddy.myCharacter.getSpecialPercentage()))
        {
            ta.append("Good. You recovered your hp. ");
            this.myBuddy.myCharacter.setHp(this.myBuddy.myCharacter.getHp() * (another.generatePercentage(1.00)+1));
            return true;
        }
        else
        {
            ta.append("Sorry, you cannot recover your hp... ");
            return false;
        }
    }
}

class MonsterFireBall extends SpecialAttack {

    public MonsterFireBall(Character myCharacter)
    {
        if(myCharacter != null && myCharacter.getRole() == 1)
        {
            this.myBuddy = myCharacter;   
        }
        else
        {
            throw new IllegalArgumentException("Hero can't have this ability. ");
        }
    }
    
    @Override
    public boolean specialAttack(Character another, TextArea ta) 
    {
        if(this.myBuddy.checkIsTrue((int)(this.myBuddy.myCharacter.getSpecialPercentage() * 0.79)))
        {
            double damage = another.myCharacter.getHp() * 0.5;
            ta.append(this.myBuddy.myCharacter.getName() + " successfully use the fire attack to the " + another.myCharacter.getName()
                            + ", and caused " + damage + " points.");
            another.myCharacter.beingAttacked(damage);
            return true;
        }
        else
        {
            ta.append(another.myCharacter.getName() + " successfully avoided the big fire ball. ");
            return false;
        }
    }
}

class MonsterPoison extends SpecialAttack {

    public MonsterPoison(Character myCharacter)
    {
        if(myCharacter != null && myCharacter.getRole() == 1)
        {
            this.myBuddy = myCharacter;   
        }
        else
        {
            throw new IllegalArgumentException("Hero can't have this ability. ");
        }
    }
    
    @Override
    public boolean specialAttack(Character another, TextArea ta) 
    {
        if(this.myBuddy.checkIsTrue(this.myBuddy.myCharacter.getSpecialPercentage()))
        {
            ta.append(another.myCharacter.getName() + " is sick. ");
            another.setPoison(another.generateInteger(2, 8));
            return true;
        }
        else
        {
            ta.append(another + " has successfully avoided the poison arrow. ");
            return false;
        }
    }
}

class MonsterMasher extends SpecialAttack {
    
    public MonsterMasher(Character myCharacter)
    {
        if(myCharacter != null && myCharacter.getRole() == 1)
        {
            this.myBuddy = myCharacter;   
        }
        else
        {
            throw new IllegalArgumentException("Hero can't have this ability. ");
        }
    }

    @Override
    public boolean specialAttack(Character another, TextArea ta) 
    {
        if(this.myBuddy.checkIsTrue(this.myBuddy.myCharacter.getSpecialPercentage()))
        {
            ta.append(another.myCharacter.getName() + " will be attacked twice due to the special attack of " + this.myBuddy.myCharacter.getName());
            this.myBuddy.attack(another, ta);
            this.myBuddy.attack(another, ta);
            return true;
        }
        else
        {
            ta.append(another.myCharacter.getName() + " has successfully avoided the double attack. ");
            return false;
        }
    }
}

class MonsterCrazyBlow extends SpecialAttack {
    
    public MonsterCrazyBlow(Character myCharacter)
    {
        if(myCharacter != null && myCharacter.getRole() == 1)
        {
            this.myBuddy = myCharacter;   
        }
        else
        {
            throw new IllegalArgumentException("Hero can't have this ability. ");
        }
    }

    @Override
    public boolean specialAttack(Character another, TextArea ta) 
    {
        if(this.myBuddy.checkIsTrue(this.myBuddy.myCharacter.getSpecialPercentage()*0.53))
        {
            ta.append("Unfortunately, " + another.myCharacter.getName() + " has been surrounded with monsters at once. ");
            another.myCharacter.setHp(1.00);
            return true;
        }
        else
        {
            ta.append(another.myCharacter.getName() + " has successfully avoided the attack. ");
            return false;
        }
    }
}

class MonsterBoss extends SpecialAttack {
    
    public MonsterBoss(Character myCharacter)
    {
        if(myCharacter != null && myCharacter.getRole() == 1)
        {
            this.myBuddy = myCharacter;   
        }
        else
        {
            throw new IllegalArgumentException("Hero can't have this ability. ");
        }
    }

    @Override
    public boolean specialAttack(Character another, TextArea ta) 
    {
        if(this.myBuddy.checkIsTrue(another.myCharacter.getSpecialPercentage()*0.6))
        {
            ta.append(another.myCharacter.getName() + " has been hit by a snow ball, and it is sick. ");
            another.myCharacter.setHp(another.myCharacter.getHp() * 0.7);
            another.setPoison(3);
            return true;
        }
        else
        {
            ta.append(another.myCharacter.getName() + " has successfully avoided the attack. ");
            return false;
        }
    }
}