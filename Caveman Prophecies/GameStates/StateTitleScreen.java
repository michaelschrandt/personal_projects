package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import javax.swing.ImageIcon;

import GUI.BasicFrame;
import Game.Game;

public class StateTitleScreen extends GameState {

	ImageIcon introScreen = new ImageIcon("pictures/titlescreen.jpg");
	Scanner keyboard = new Scanner(System.in);
	String s = "";
	
	@Override
	public void handleEvents(KeyEvent e) {
		
			Game.getCanvas().tellPlayer.setVisible(true);
			Game.setNextState(Game.getMap(Game.getCurrentMapIndex()));
	
	}

	@Override
	public void logic()
	{

		//if they input the word "exit", change the game state to "exit"
		//if(s.equals("exit"))
		//{
		//	Game.setNextState(new StateExit());
		//}
		//else //otherwise, just load up the first map
		//{
		//	Game.setNextState(Game.getMap(Game.getCurrentMapIndex()));
		//}

	}
	
	//FOR JEFF
	@Override
	public void draw()
	{
		/*
		System.out.println("The Game");
		System.out.println("By Aleksandr Melnikov, Michael Schrandt, and Sijie Xia");
		System.out.println("Press enter to continue...");
		*/
		//test

		Graphics g = Game.getCanvas().getGraphics();
		g.drawImage(introScreen.getImage(), 0, 0, BasicFrame.SCREEN_W, BasicFrame.SCREEN_H, Game.getCanvas());
		
	}

}
