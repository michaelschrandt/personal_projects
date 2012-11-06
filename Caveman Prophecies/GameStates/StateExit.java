package GameStates;

import java.awt.event.KeyEvent;

public class StateExit extends GameState {

	@Override
	public void handleEvents(KeyEvent e)
	{
		System.exit(0);
	}

	@Override
	public void logic()
	{
		//exit the game
		System.exit(0);
	}

	@Override
	public void draw()
	{
		

	}

}
