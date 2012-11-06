
package Character;

import java.util.Random;

import Items.*;
import java.awt.TextArea;
import junit.framework.Assert;
import junit.framework.TestCase;

/*
 * CSCD 490
 */

public class CharacterTest extends TestCase {
    
    private Random rnd = new Random(System.currentTimeMillis());
	
    public CharacterTest(String testName) 
    {
        super(testName);
    }
    
    public void testRandom()
    {
        final int testSize = 20;
        for(int i=0; i<testSize; i++)
        {
            Character mine = new MonsterGenerator(rnd);
            System.out.println("Printout the Monster. ");
            System.out.println(mine);
        }
        System.out.println(" *** Testing finished. *** ");
    }
    
    public void testWeapon()
    {
        System.out.println("Testing weapons... ");
        System.out.println("Trying to create the Hero. ");
        Hero myHero = new HeroGenerator(rnd);
        System.out.println("Printing out information... ");
        System.out.println(myHero);
        System.out.println("Set the weapon. ");
        ItemsFactory myItem = new EquipmentItemsFactory();
        myHero.setWeapon(myItem.createItem());
        System.out.println("Set the second weapon. ");
        myHero.setWeapon(myItem.createItem());
        System.out.println("Printing information.. ");
        System.out.println(myHero);
        Assert.assertTrue(myHero.myEquipment.count()==2);
        System.out.println("Testing remove the weapon.. ");
        myHero.myEquipment.removeEquipment(0);
        System.out.println("Printing out information.. ");
        System.out.println(myHero);
        Assert.assertTrue(myHero.myEquipment.count()==1);
        System.out.println("Clear all the weapon.. ");
        myHero.myEquipment.removeAll();
        System.out.println("Printing out the information.. ");
        System.out.println(myHero);
        Assert.assertTrue(myHero.myEquipment.count()==0);
        System.out.println(" *** Testing finished. *** ");
    }
    
    private Hero initHero()
    {
        return new HeroGenerator(rnd);
    }
    
    private Monster initMonster()
    {
        return new MonsterGenerator(rnd);
    }
    
    public void testSpecialAttackTriple()
    {
        System.out.println("Start testing specialAttackTriple.. ");
        Hero myHero = initHero();
        Monster myMonster = initMonster();
        double hp = myMonster.myCharacter.getHp();
        System.out.println(myHero + "\n" + myMonster);
        System.out.println("Hero is trying to use speical attack.. ");
        myHero.setSpecialAttack(new HeroSpecialTriple(myHero));
        boolean success = myHero.doSpecialAttack(myMonster, new TextArea());
        if(success)
        {
            Assert.assertTrue(myMonster.myCharacter.getHp() < hp);
        }
        else
        {
            Assert.assertTrue(myMonster.myCharacter.getHp() == hp);
        }
        System.out.println("*** Testing finished *** ");
    }
    
    public void testSpecialAttackMuscle()
    {
        System.out.println("Start testing muscle.. ");
        Hero myHero = initHero();
        Monster myMonster = initMonster();
        double hp = myMonster.myCharacter.getHp();
        myHero.setSpecialAttack(new HeroSpecialMuscle(myHero));
        boolean success = myHero.doSpecialAttack(myMonster, null);
        if(success)
        {
            Assert.assertTrue(myMonster.myCharacter.getHp() < hp);
        }
        else
        {
            Assert.assertTrue(myMonster.myCharacter.getHp() == hp);
        }
        
        System.out.println(" *** Testing finished *** ");
    }
    
    public void testSpecialAttackRecover()
    {
        System.out.println("Testing recover.. ");
        Hero myHero = initHero();
        double hp = myHero.myCharacter.getHp();
        myHero.setSpecialAttack(new HeroSpecialRecover(myHero));
        boolean success = myHero.doSpecialAttack(myHero, new TextArea());
        if(success)
        {
            Assert.assertTrue(myHero.myCharacter.getHp() > hp);
        }
        else
        {
            Assert.assertTrue(myHero.myCharacter.getHp() == hp);
        }
        System.out.println(" *** Testing finished *** ");
    }
    
    public void testAttack()
    {
        System.out.println("Start testing attack.. ");
        System.out.println("Testing started...  ");
        System.out.println("While creating the hero.. ");
        Hero myHero = new HeroGenerator(rnd);
        System.out.println("While creating the monster.. ");
        Monster myMonster = new MonsterGenerator(rnd);
        System.out.println("Finished.. ");
        System.out.println("Trying to set a weapon for hero... ");
        ItemsFactory myWeapons = new EquipmentItemsFactory();
        myHero.setWeapon(myWeapons.createItem());
        System.out.println("Printing out the information.. ");
        System.out.println(myHero);
        System.out.println(myMonster);
        
        //test first
        System.out.println("Waa.. Hero is attacking monsters.. ");
        double hp = myMonster.myCharacter.getHp();
        boolean success = myHero.attack(myMonster, new TextArea());
        if(success)
        {
            Assert.assertTrue(myMonster.myCharacter.getHp() < hp);
        }
        else
        {
            Assert.assertTrue(myMonster.myCharacter.getHp() == hp);
        }
        
        //test another
        System.out.println(myMonster.isDead()?"The test finished, the monster died. ":"The monster is attacking heros. ");
        if(myMonster.myCharacter.getHp()>0)
        {
        	double hp2 = myHero.myCharacter.getHp();
            boolean success2 = myMonster.attack(myHero, new TextArea());
            if(success2)
            {
                Assert.assertTrue(myHero.myCharacter.getHp() < hp2);
            }
            else
            {
                Assert.assertTrue(myHero.myCharacter.getHp() == hp2);
            }
        }
        System.out.println(" *** Testing finished. *** ");
    }
}

//TODO: implement the specialattack test