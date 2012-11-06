package GameStates;

import java.awt.event.KeyEvent;
import java.util.Scanner;

import GUI.BattleCanvas;
import Game.Game;


public class StateBattle extends GameState
{
	Scanner keyboard = new Scanner(System.in);
	String command;

	public StateBattle()
	{
		 BattleCanvas b = new BattleCanvas();
	     b.setVisible(true);
	     Game.getFrame().setVisible(false);
	     
	     Game.newMonsterParty();
	}
	
	@Override
	public void handleEvents(KeyEvent e) {
		//command = keyboard.nextLine();

	}

	@Override
	public void logic() {
//		if(command.equals("flee"))
//		{
//			Game.setNextState(Game.getMap(Game.getCurrentMapIndex()));
//		}
//		else if(command.equals("exit"))
//		{
//			Game.setNextState(new StateExit());
//		}

	}

	@Override
	public void draw() {
//		System.out.println("There's monsters everywhere!");
//		System.out.println("Type 'flee' to run away or 'exit' to exit");
	}

}
