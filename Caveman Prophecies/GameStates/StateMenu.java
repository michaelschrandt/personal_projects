package GameStates;

import java.awt.event.KeyEvent;
import java.util.Scanner;

import Game.Game;


public class StateMenu extends GameState {
	Scanner keyboard = new Scanner(System.in);
	String command;
	
	@Override
	public void handleEvents(KeyEvent e)
	{
		//command = keyboard.nextLine();
	}

	@Override
	public void logic() {
		if(command.equals("back"))
		{
			Game.setNextState(Game.getMap(Game.getCurrentMapIndex()));
		}
		else if(command.equals("exit"))
		{
			Game.setNextState(new StateExit());
		}
	}

	@Override
	public void draw()
	{
		System.out.println("Here are your stats...");
		System.out.println("Type 'back' to go to the map or 'exit' to quit");

	}

}
