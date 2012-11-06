/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Component;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.xml.stream.events.Characters;

import Character.Character;
import Character.CharacterAttribute;
import Character.MonsterParty;
import Character.Party;
import Game.Game;
import GameStates.StateExit;

import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.Dimension;

/**
 *
 * @author butlerj
 */
public class BattleScreen extends javax.swing.JPanel implements MouseListener, ActionListener{

	
	private ArrayList<JProgressBar> progBars;
	private ArrayList<Character> chars, monsters;
	private ArrayList<JLabel> names;
	private ArrayList<JLabel> pics;
	private  final int PICSIZE = 125;
	private BattleCanvas parent;
	//private ImageGenerator heroGen = new HeroImageGenerator();
	//private ImageGenerator monGen = new MonsterImageGenerator();
	
	/**
     * Creates new form BattleScreen
     */
    public BattleScreen(BattleCanvas parent) {
		setMaximumSize(new Dimension(800, 650));
		this.parent = parent;
        initComponents();
        initGameComponents();
        
    }
    
    private void initGameComponents() {
    	
    	Party pParty = Game.getParty();
		chars = pParty.getCharacters();
		
		MonsterParty mParty = Game.getMonsterParty();
		monsters = mParty.getMonsters();
		//The party should be static for each game
		//This means statistics and pictures, those should not change once the party is generated.
			 
		//LOTS OF INITIALIZING

		initAll();
		
		
		
	}
    
    
    private void initAll()
    {
    	progBars = new ArrayList<JProgressBar>();
    	progBars.add(progBarA);
    	progBars.add(progBarB);
    	progBars.add(progBarC);
    	progBars.add(progBarD);
    	progBars.add(progBarE);
    	progBars.add(progBarF);
    	
    	names = new ArrayList<JLabel>();
    	names.add(NameA);
    	names.add(NameB);
    	names.add(NameC);
    	names.add(NameD);
    	names.add(NameE);
    	names.add(NameF);
    	
    	pics = new ArrayList<JLabel>();
    	
    

    	pics.add(picA);
    	pics.add(picB);
    	pics.add(picC);
    	pics.add(picD);
    	pics.add(picE);
    	pics.add(picF);
    	
    	setAll();
    	
    }
    
    
    public ImageIcon getResizedImage(Icon icon)
    {
    	ImageIcon ic = (ImageIcon) icon;
    	Image img = ic.getImage() ;   
 	   Image newimg = img.getScaledInstance( PICSIZE, PICSIZE,  java.awt.Image.SCALE_SMOOTH ) ;   
 	   return new ImageIcon(newimg);
    }
    private void setAll()
    {
    	for(int i = 0; i < chars.size(); i ++)
    	{
    		CharacterAttribute charac = chars.get(i).getCharacterAttributes();
    		names.get(i).setText(charac.getName());
    		progBars.get(i).setMaximum((int) charac.getMaxHp());
    		progBars.get(i).setStringPainted(true);
    		progBars.get(i).setValue((int) charac.getHp());
    		progBars.get(i).setString(Integer.toString(progBars.get(i).getValue()) + "/" + progBars.get(i).getMaximum());
    		ImageIcon image = getResizedImage(charac.getIcon());
    		
    		pics.get(i).setIcon(image);
    	}
    	
    	for(int i = 0; i < monsters.size(); i ++)
    	{
    		int j = i + chars.size();
    		CharacterAttribute charac = monsters.get(i).getCharacterAttributes();
    		names.get(j).setText(charac.getName());
    		progBars.get(j).setMaximum((int) charac.getHp());
    		progBars.get(j).setStringPainted(true);
    		progBars.get(j).setValue((int) charac.getHp());
    		progBars.get(j).setString(Integer.toString(progBars.get(j).getValue()) + "/" + progBars.get(j).getMaximum());
    		ImageIcon image = getResizedImage(charac.getIcon());
    		pics.get(j).setIcon(image);
    	}
    	
    }
    


	public JProgressBar getProgA()
    {
        return progBarA;
    }
    
      public JProgressBar getProgB()
    {
        return progBarB;
    }

        public JProgressBar getProgC()
    {
        return progBarC;
    }

          public JProgressBar getProgD()
    {
        return progBarD;
    }

            public JProgressBar getProgE()
    {
        return progBarE;
    }

              public JProgressBar getProgF()
    {
        return progBarF;
    }
              
             public JLabel getPicA()
             {
                 return picA;
                 
             }
             
             public JRadioButton getAttackerA()
             {
                 return attackerA;
             }
             
              public JRadioButton getAttackerB()
             {
                 return attackerB;
             }
              
               public JRadioButton getAttackerC()
             {
                 return attackerC;
             }

                 public JButton getAttackButton()
             {
                 return attackButton;
             }
               
                   public JToggleButton getSpecialAttackButton()
             {
                 return specialAttackButton;
             }
               public TextArea getTextField()
               {
                   return textArea;
                   
               }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        picA = new javax.swing.JLabel();
        picC = new javax.swing.JLabel();
        picB = new javax.swing.JLabel();
        NameA = new javax.swing.JLabel();
        progBarA = new javax.swing.JProgressBar();
        NameB = new javax.swing.JLabel();
        progBarB = new javax.swing.JProgressBar();
        NameC = new javax.swing.JLabel();
        progBarC = new javax.swing.JProgressBar();
        NameD = new javax.swing.JLabel();
        progBarD = new javax.swing.JProgressBar();
        NameF = new javax.swing.JLabel();
        progBarF = new javax.swing.JProgressBar();
        NameE = new javax.swing.JLabel();
        progBarE = new javax.swing.JProgressBar();
        jPanel4 = new javax.swing.JPanel();
        picD = new javax.swing.JLabel();
        picF = new javax.swing.JLabel();
        picE = new javax.swing.JLabel();
        targetD = new javax.swing.JRadioButton();
        targetE = new javax.swing.JRadioButton();
        targetF = new javax.swing.JRadioButton();
        attackerA = new javax.swing.JRadioButton();
        attackerB = new javax.swing.JRadioButton();
        attackerC = new javax.swing.JRadioButton();
        attackButton = new javax.swing.JButton();
        
        buttonGroup1.add(attackerA);
        buttonGroup1.add(attackerB);
        buttonGroup1.add(attackerC);
        
        buttonGroup1.add(targetD);
        buttonGroup1.add(targetE);
        buttonGroup1.add(targetF);
        
        attackButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		//Here be where the user clicks on the attack button
        		textArea.setText("");
        		//Check to see if a hero and an enemy is selected
        		ButtonModel hero = buttonGroup1.getSelection();
        		ButtonModel monster = buttonGroup2.getSelection();
        		
        		
        		//TRIAL BATTLE WITH SELECTION BUTTONS
        		
        		//assign curHero and curMonster based on the ButtonModels above
        		int charIndex = 0;
        		int monIndex = 3;
        		if(attackerA.isSelected())
        			charIndex = 0;
        		else if(attackerB.isSelected())
        			charIndex = 1;
        		else if(attackerC.isSelected())
        			charIndex = 2;
        		
        		if(targetD.isSelected())
        			monIndex = 3;
        		else if(targetE.isSelected())
        			monIndex = 4;
        		else if(targetF.isSelected())
        			monIndex = 5;
        		
        		
        		if(chars.get(charIndex).isDead())
        		{
        			if(charIndex == 0)
        				attackerA.setEnabled(false);
        			else if(charIndex == 1)
        				attackerB.setEnabled(false);
        			else if (charIndex == 2)
        				attackerC.setEnabled(false);
        			
        		}
        		
        		if(monsters.get(monIndex-3).isDead())
        		{
        			
        			if(monIndex == 3)
        				targetD.setEnabled(false);
        			else if(monIndex == 4)
        				targetE.setEnabled(false);
        			else if (monIndex == 5)
        				targetF.setEnabled(false);
        		}
        		
        		
        		Character curHero = chars.get(charIndex);
        		Character curMonster = monsters.get(monIndex - 3);
        		
        		//check for death
        		if(curHero.isDead() || curMonster.isDead())
        			System.out.println("Invalid attack");
        		
        		else
        		{
        			if(specialAttackButton.isSelected())
        			{
        				//perform hero attack
		        		curHero.doSpecialAttack(curMonster, textArea);
        			}
        			else
        			{
		        		//perform hero attack
		        		curHero.attack(curMonster, textArea);
        			}
        			
	        		progBars.get(monIndex).setStringPainted(true);
		    		progBars.get(monIndex).setValue((int) curMonster.getCharacterAttributes().getHp());
		    		progBars.get(monIndex).setString(Integer.toString(progBars.get(monIndex).getValue()) + "/" + progBars.get(monIndex).getMaximum());
		    		
		    		//perform monster attack
	        		if(!curMonster.isDead())
	        		{
	        			if(specialAttackButton.isSelected())
	        				curMonster.doSpecialAttack(curHero, textArea);
	        			else
	        				curMonster.attack(curHero, textArea);
	    				//update the labels
	    	    		progBars.get(charIndex).setStringPainted(true);
	    	    		progBars.get(charIndex).setValue((int) curHero.getCharacterAttributes().getHp());
	    	    		progBars.get(charIndex).setString(Integer.toString(progBars.get(charIndex).getValue()) + "/" + progBars.get(charIndex).getMaximum());
	        		}
	        		
        		}
        		if(isMonsterPartyDead())
        		{
	        		//check win
	    			textArea.append("The monsters have been vanquished!\n");
	    			//for now, disable the buttons
	    			attackButton.setEnabled(false);
	    			specialAttackButton.setEnabled(false);
	    			
	    			for(Character c : chars)
	    			{
	    				c.getCharacterAttributes().setHp(c.getCharacterAttributes().getMaxHp());
	    			}
	    			
	    			parent.onShutdown();
	    			
        		}
    			
    			//check loss
    			if(isHeroPartyDead())
    			{
        			textArea.append("The heroes are dead...Game over...\n");
        			//for now, disable the buttons
        			attackButton.setEnabled(false);
        			specialAttackButton.setEnabled(false);
        			
        			System.exit(0);
        			parent.onShutdown();

    			}
        		
        		
    			
    			
    			
    			// BATTLE WITHOUT SELECTION BUTTONS
    			/*
    			
        		//Character temp storage
        		Character tempHero, tempMonster;
        		
        		if(hero == null || monster == null) //if either is not selected
        		{
        			//notify via textArea of problem
        			textArea.append("Cannot attack without selecting a hero and a monster!\n");
        		}
        		
        		if(!isHeroPartyDead()) //check if the hero party is dead first
        		{
        			//Hero party is not dead, okay to attack with all the heroes
        		
	        		//Attack each hero and monster in sequence.
	        		//update the labels, and what-not, throughout
	        		for(Character c: chars)
	        		{
	        			//this assumes we have a limit of three monsters, which is completely shitty for a design patterns class but
	        			//i'll do this to get it to work
	        			if(monsters.get(0).isDead()) //if the monster is dead, try the next one
	        			{
	        				if(monsters.get(1).isDead()) //the second one is dead too, attack the third one
	        				{
	        					if(monsters.get(2).isDead()) //the third one be dead too
	        					{
	        						//SHOULD NOT GET HERE, should not be able to attack when all three are dead
	        					}
	        					else
	        					{
	        						//Attack the third monster
	        						tempMonster = monsters.get(2);
	                				c.attack(tempMonster, textArea);
	                				//update the labels
	                				//Hard-coding the monsters = bad
	                	    		progBars.get(5).setStringPainted(true);
	                	    		progBars.get(5).setValue((int) tempMonster.getCharacterAttributes().getHp());
	                	    		progBars.get(5).setString(Integer.toString(progBars.get(5).getValue()) + "/" + progBars.get(5).getMaximum());
	                				
	        					}
	        				}
	        				else
	        				{
	        					//attack the second monster
	        					tempMonster = monsters.get(1);
	            				c.attack(tempMonster, textArea);
	            				//update the labels
	            				//Hard-coding the monsters = bad
	            	    		progBars.get(4).setStringPainted(true);
	            	    		progBars.get(4).setValue((int) tempMonster.getCharacterAttributes().getHp());
	            	    		progBars.get(4).setString(Integer.toString(progBars.get(4).getValue()) + "/" + progBars.get(4).getMaximum());
	            				
	        				}
	        			}
	        			else //attack the first monster
	        			{
	        				tempMonster = monsters.get(0);
	        				c.attack(tempMonster, textArea);
	        				//update the labels
	        				//Hard-coding the monsters = bad
	        	    		progBars.get(3).setStringPainted(true);
	        	    		progBars.get(3).setValue((int) tempMonster.getCharacterAttributes().getHp());
	        	    		progBars.get(3).setString(Integer.toString(progBars.get(3).getValue()) + "/" + progBars.get(3).getMaximum());
	        				
	        			}
	        			
	        			
	        			
	        		} //end of for loop
        		}
        		else //other-wise, the hero-party is dead, change states to gameOver screen or message
        		{
        			textArea.append("The heroes are dead...Game over...\n");
        			//for now, disable the buttons
        			attackButton.setEnabled(false);
        			specialAttackButton.setEnabled(false);
        		}
        		
        		if(!isMonsterPartyDead()) //only attack with the monsters if their party is not dead
        		{
        		//then attack with the monsters
        		for(Character m: monsters)
        		{
        			if(chars.get(0).isDead())//first hero is dead
        			{
        				if(chars.get(1).isDead()) //second hero is dead
        				{
        					if(chars.get(2).isDead()) //third hero is dead
        					{
        						//Should not have gotten here...
        					}
        					else
        					{
        						//attack the third hero
        						tempHero = chars.get(2);
                				m.attack(tempHero, textArea);
                				//update the labels
                				//Hard-coding = bad
                	    		progBars.get(2).setStringPainted(true);
                	    		progBars.get(2).setValue((int) tempHero.getCharacterAttributes().getHp());
                	    		progBars.get(2).setString(Integer.toString(progBars.get(2).getValue()) + "/" + progBars.get(2).getMaximum());
                			
        					}
        				}
        				else
        				{
        					//attack the second hero
        					tempHero = chars.get(1);
            				m.attack(tempHero, textArea);
            				//update the labels
            				//Hard-coding = bad
            	    		progBars.get(1).setStringPainted(true);
            	    		progBars.get(1).setValue((int) tempHero.getCharacterAttributes().getHp());
            	    		progBars.get(1).setString(Integer.toString(progBars.get(1).getValue()) + "/" + progBars.get(1).getMaximum());
            			
        				}
        			}
        			else
        			{
        				//attack the first hero
        				tempHero = chars.get(0);
        				m.attack(tempHero, textArea);
        				//update the labels
        				//Hard-coding = bad
        	    		progBars.get(0).setStringPainted(true);
        	    		progBars.get(0).setValue((int) tempHero.getCharacterAttributes().getHp());
        	    		progBars.get(0).setString(Integer.toString(progBars.get(0).getValue()) + "/" + progBars.get(0).getMaximum());
        			
        			}
        			
        			//after each attack, add a check to see if the opposing party is all dead, so as to break out of the loop and state
            		//If all heroes dead, go to game over

        		}
        		}
        		else
        		{
        			//monster party is dead, go to victory message or state, then back to the over-world
        			textArea.append("The monsters have been vanquished!\n");
        			//for now, disable the buttons
        			attackButton.setEnabled(false);
        			specialAttackButton.setEnabled(false);
        		}


        		*/
        	}
        });
        specialAttackButton = new javax.swing.JToggleButton();
        textArea = new java.awt.TextArea();
        textArea.setEditable(false);

        setBackground(new java.awt.Color(102, 102, 0));
        setName("BattleScreen");
        setPreferredSize(new Dimension(800, 650));

        jPanel2.setBackground(new java.awt.Color(51, 51, 0));
        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel2.setPreferredSize(new java.awt.Dimension(125, 375));

        picA.setLabelFor(progBarA);
        picA.setText("jLabel7");

        picC.setIcon(new javax.swing.ImageIcon(getClass().getResource("MonsterA.png"))); // NOI18N
        picC.setLabelFor(progBarA);
        picC.setText("jLabel7");

        picB.setIcon(new javax.swing.ImageIcon(getClass().getResource("MonsterA.png"))); // NOI18N
        picB.setLabelFor(progBarA);
        picB.setText("jLabel7");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(picA, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(picC, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(picB, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(picA, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(picB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(picC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        picA.getAccessibleContext().setAccessibleName("jProgressBar1");
        picA.getAccessibleContext().setAccessibleDescription("jProgressBar1");

        NameA.setText("Name1");

        NameB.setText("Name2");

        NameC.setText("Name3");

        NameD.setText("Name4");

        NameF.setText("Name6");

        NameE.setText("Name5");

        jPanel4.setBackground(new java.awt.Color(51, 51, 0));
        jPanel4.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel4.setPreferredSize(new java.awt.Dimension(125, 375));

        picD.setIcon(new javax.swing.ImageIcon(getClass().getResource("MonsterA.png"))); // NOI18N
        picD.setLabelFor(progBarA);
        picD.setText("jLabel7");

        picF.setIcon(new javax.swing.ImageIcon(getClass().getResource("MonsterA.png"))); // NOI18N
        picF.setLabelFor(progBarA);
        picF.setText("jLabel7");

        picE.setIcon(new javax.swing.ImageIcon(getClass().getResource("MonsterA.png"))); // NOI18N
        picE.setLabelFor(progBarA);
        picE.setText("jLabel7");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(picD, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(picF, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(picE, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(picD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(picE, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(picF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        buttonGroup1.add(targetD);
        targetD.setSelected(true);
        targetD.setText("Select Monster");

        buttonGroup1.add(targetE);
        targetE.setText("Select Monster");

        buttonGroup1.add(targetF);
        targetF.setText("Select Monster");

        buttonGroup2.add(attackerA);
        attackerA.setSelected(true);
        attackerA.setText("Select Attacker");
        attackerA.setFocusable(false);

        buttonGroup2.add(attackerB);
        attackerB.setText("Select Attacker");
        attackerB.setFocusable(false);

        buttonGroup2.add(attackerC);
        attackerC.setText("Select Attacker");
        attackerC.setFocusable(false);

        attackButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        attackButton.setText("Attack");
        

        specialAttackButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        specialAttackButton.setText("Special Attack");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(progBarC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(NameC)
        								.addComponent(attackerC)
        								.addComponent(progBarB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        							.addPreferredGap(ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
        							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        								.addComponent(progBarE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addGroup(layout.createParallelGroup(Alignment.LEADING)
        									.addComponent(NameF)
        									.addComponent(progBarF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        									.addComponent(targetF))))
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(NameA)
        								.addComponent(progBarA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(attackerA)
        								.addComponent(attackerB)
        								.addComponent(NameB))
        							.addGap(47)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        								.addComponent(specialAttackButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
        								.addComponent(attackButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        							.addGap(39)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(NameD)
        								.addComponent(progBarD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(targetD)
        								.addComponent(targetE)
        								.addComponent(NameE))))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addComponent(textArea, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addGap(14))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        					.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGroup(layout.createSequentialGroup()
        						.addGroup(layout.createParallelGroup(Alignment.LEADING)
        							.addComponent(NameA)
        							.addComponent(NameD))
        						.addGap(30)
        						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        							.addComponent(targetD)
        							.addComponent(attackerA))
        						.addGap(18)
        						.addGroup(layout.createParallelGroup(Alignment.LEADING)
        							.addComponent(progBarD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addComponent(progBarA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addComponent(attackButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
        						.addPreferredGap(ComponentPlacement.RELATED)
        						.addComponent(specialAttackButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
        						.addGap(17)
        						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        							.addComponent(NameE)
        							.addComponent(NameB))
        						.addGroup(layout.createParallelGroup(Alignment.LEADING)
        							.addGroup(layout.createSequentialGroup()
        								.addGap(7)
        								.addComponent(targetE)
        								.addGap(18)
        								.addComponent(progBarE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        							.addGroup(layout.createSequentialGroup()
        								.addComponent(attackerB)
        								.addGap(24)
        								.addComponent(progBarB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        						.addGap(36)
        						.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        							.addComponent(NameC)
        							.addComponent(NameF))
        						.addGap(18)
        						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        							.addGroup(layout.createSequentialGroup()
        								.addComponent(targetF)
        								.addGap(0, 0, Short.MAX_VALUE))
        							.addGroup(layout.createSequentialGroup()
        								.addGap(0, 0, Short.MAX_VALUE)
        								.addComponent(attackerC)))
        						.addGap(18)
        						.addGroup(layout.createParallelGroup(Alignment.LEADING)
        							.addComponent(progBarF, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addComponent(progBarC, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(36, Short.MAX_VALUE))
        );
        this.setLayout(layout);
    }// </editor-fold>

    // Variables declaration - do not modify
    private javax.swing.JLabel NameA;
    private javax.swing.JLabel NameB;
    private javax.swing.JLabel NameC;
    private javax.swing.JLabel NameD;
    private javax.swing.JLabel NameE;
    private javax.swing.JLabel NameF;
    private javax.swing.JButton attackButton;
    private javax.swing.JRadioButton attackerA;
    private javax.swing.JRadioButton attackerB;
    private javax.swing.JRadioButton attackerC;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel picA;
    private javax.swing.JLabel picB;
    private javax.swing.JLabel picC;
    private javax.swing.JLabel picD;
    private javax.swing.JLabel picE;
    private javax.swing.JLabel picF;
    private javax.swing.JProgressBar progBarA;
    private javax.swing.JProgressBar progBarB;
    private javax.swing.JProgressBar progBarC;
    private javax.swing.JProgressBar progBarD;
    private javax.swing.JProgressBar progBarE;
    private javax.swing.JProgressBar progBarF;
    private javax.swing.JToggleButton specialAttackButton;
    private javax.swing.JRadioButton targetD;
    private javax.swing.JRadioButton targetE;
    private javax.swing.JRadioButton targetF;
    private java.awt.TextArea textArea;
    // End of variables declaration
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//=======================================
	//Helper methods for Battle logic
	//=======================================
	
	//Check to see if the hero party is dead
	//If at least one hero is alive, will return false
	private boolean isHeroPartyDead()
	{
		for(Character c: chars)
		{
			if(!c.isDead()) //if a hero is still alive, then the party is not dead
			{
				return false;
			}
		}
		return true;
	}
	
	//Check to see if the monster party is dead
	//If at least one monster is still alive, will return false
	private boolean isMonsterPartyDead()
	{
		for(Character m: monsters)
		{
			if(!m.isDead()) //if a monster is still alive, then the monster party is not dead
			{
				return false;
			}
		}
		return true;
	}
}
