package Game;

import GameStates.StateExit;


public class StateMachineTester
{
	public static void main(String [] args)
	{
		//initialization. load pictures, windows, whatever
		
		Game.init();
		
		Game.draw();
		
		//game loop
		while( !(Game.getState() instanceof StateExit) )
		{
			long start = System.currentTimeMillis();
			
			//get input
			//Game.handleEvents();
			
			//apply logic (update)
			//Game.logic();
			
			//change states, if necessary
			Game.changeState();
			
			//draw state
			Game.draw();
			
			long end = System.currentTimeMillis();
			
			//burn time
			try{
				Thread.sleep((long) (1000.0/Game.FPS - (end - start)));
			}
			catch(Exception e)
			{
				System.out.println("Thread not sleeping!");
			}

		}
	}
}
