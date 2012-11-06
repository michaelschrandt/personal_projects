package Game;


import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import Character.BadGuys;
import Character.GoodGuys;
import Character.Hero;
import Character.MonsterParty;
import Character.Party;
import GUI.BasicCanvas;
import GUI.BasicFrame;
import GameStates.GameState;
import GameStates.StateNull;
import GameStates.StateOverworld;
import GameStates.StateTitleScreen;
import Map.DungeonType;

//the game class. it switches states.

public abstract class Game
{
	//map info
	public static final int MAP_W = 40;
	public static final int MAP_H = 20;
	public static final int TILE_W = 20;
	public static final int NUM_LEVELS = 5;
	
	//state info
	private static int currentMapIndex;
	private static ArrayList<GameState> maps;
	private static GameState currentState = new StateTitleScreen();
	private static GameState nextState = new StateNull();
	private static GameState prevState = new StateNull();
	//Characters
	private static Party party;
	private static MonsterParty monsterParty;
	private static Random random = new Random(System.currentTimeMillis()); //to randomly grab an image
	//GUI stuff
	private static BasicCanvas canvas;
	private static BasicFrame frame;
	public static final int FPS = 20;
	
	//initialize game
	public static void init()
	{
		//initialize maps
		currentMapIndex = 0;
		maps = new ArrayList<GameState>();
		for(int i = 1; i < NUM_LEVELS; i++)
		{
			maps.add(new StateOverworld(MAP_W, MAP_H)) ;
		}
		maps.add(new StateOverworld(MAP_W, MAP_H, DungeonType.LAST_LEVEL));
		
		//create the characters
		//party = new Party();
		party = new GoodGuys(random);
		System.out.println(party);
		
		//create monsters, for now, they'll be the same every time
		monsterParty = new BadGuys(random);
		System.out.println(monsterParty);
		
		//initialize GUI
		frame = new BasicFrame();
		canvas = frame.canvas;
	}
	
	//any time a state requests a change, it must call this method
	public static void setNextState(GameState nextState)
	{
		Game.nextState = nextState;
	}
	
	//get map info. used to find 
	public static int getCurrentMapIndex(){return Game.currentMapIndex; }
	public static GameState getMap(int index){return Game.maps.get(index); }
	
	//this method is called each game loop. it switches the current state to the next state
	public static void changeState()
	{
		if(!(Game.nextState instanceof StateNull))
		{
			Game.prevState = Game.currentState;
			Game.currentState = Game.nextState;
			Game.nextState = new StateNull();
		}
		
		//update current map index
		if(Game.currentState instanceof StateOverworld)
			Game.currentMapIndex = maps.indexOf(Game.currentState);
	}
	
	public static GameState getState()
	{
		return Game.currentState;
	}
	
	public static BasicCanvas getCanvas()
	{
		return Game.canvas;
	}
	
	public static BasicFrame getFrame()
	{
		return Game.frame;
	}
	public static Party getParty()
	{
		return party;
	}
	
	public static MonsterParty getMonsterParty()
	{
		return monsterParty;
	}
	
	public static void newMonsterParty()
	{
		monsterParty = new BadGuys(random);
	}
	//delegate tasks to current game state
	public static void handleEvents(KeyEvent e)
	{
		currentState.handleEvents(e);
	}
	
	public static void logic()
	{
		currentState.logic();
	}
	
	public static void draw()
	{
		currentState.draw();
	}
	
}
