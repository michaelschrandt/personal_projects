package GameStates;

import java.awt.event.KeyEvent;

public abstract class GameState
{
	public abstract void handleEvents(KeyEvent e);
	public abstract void logic();
	public abstract void draw();
}
