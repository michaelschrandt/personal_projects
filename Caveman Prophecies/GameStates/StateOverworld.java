package GameStates;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Game.Game;
import Items.EquipmentItemsFactory;
import Items.MeteorShard;
import Items.UseableItemsFactory;
import Map.BottomFloorMap;
import Map.Coordinates;
import Map.DungeonType;
import Map.ItemIcon;
import Map.Map;
import Map.MeteorIcon;
import Map.MonsterIcon;
import Map.OverworldMap;
import Map.PlayerIcon;

//every state overworld object has a map and a playerIcon that moves around said map.
public class StateOverworld extends GameState
{
	private static final int NUM_MONSTERS = 35;
	private static final int NUM_USABLE_ITEMS = 2;
	private static final int NUM_EQUIPMENT_ITEMS = 2;

	//information that this state contains
	private Map map;
	private PlayerIcon player;
	private ArrayList<MonsterIcon> monsters;
	private ArrayList<ItemIcon> items;
	//private boolean needsDraw;

	//initialize map and player location
	public StateOverworld(int width, int height)
	{
		this.map = new OverworldMap(width, height);
		this.player = new PlayerIcon(map.getUpstairs());
		
		this.monsters = new ArrayList<MonsterIcon>();
		addMonsters(width, height);
		
		this.items = new ArrayList<ItemIcon>();
		addItems(width, height);
	//	needsDraw = false;

	}
	
	public StateOverworld(int width, int height, DungeonType t)
	{
		this.monsters = new ArrayList<MonsterIcon>();
		this.items = new ArrayList<ItemIcon>();

		if(t == DungeonType.LAST_LEVEL)
		{
			BottomFloorMap f = new BottomFloorMap(width, height);
			this.map = f;
			items.add(new MeteorIcon(f.meteorShard(), new MeteorShard()));
		}
		else
			this.map = new OverworldMap(width, height);
		
		this.player = new PlayerIcon(map.getUpstairs());
		

		addMonsters(width, height);
		addItems(width, height);
	}



	@Override
	public void handleEvents(KeyEvent e)
	{
		Coordinates newPos = player.getPosition();

		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			newPos = player.getPosition().up();
		}	
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			newPos = player.getPosition().down();
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			newPos = player.getPosition().right();
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			newPos = player.getPosition().left();
		}
		else if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			GameState nextLevel = this;

			try
			{
				if(player.getPosition().equals(map.getDownstairs()))
				{
					nextLevel = Game.getMap(Game.getCurrentMapIndex() + 1);
				}
				else if(player.getPosition().equals(map.getUpstairs()))
				{
					nextLevel = Game.getMap(Game.getCurrentMapIndex() - 1);
				}
			}
			catch(Exception ex)
			{
				if(Game.getParty().hasShard())
				{
					Game.getCanvas().getLabel().setText("Victory! press enter to quit...");
					Game.setNextState(new StateExit());
					return;
				}
				else
				{
					Game.getCanvas().getLabel().setText("You must collect the Meteor Shard before you can use these stairs");
				}
			}

			Game.setNextState(nextLevel);
		}



		//update position
		if(!map.collidesWith(newPos))
		{
			player.setPosition(newPos);
			logic();
		}
		//needsDraw = true;
	}

	@Override
	public void logic()
	{
		//move monsters
		for(MonsterIcon m : monsters)
		{
			m.nextPosition(map);
		}

		//check for battles
		MonsterIcon temp = null;
		for(MonsterIcon m : monsters)
		{
			if(m.collidesWith(player))
			{
				Game.setNextState(new StateBattle());
				//Game.getCanvas().getLabel().setText("BATTLE");
				temp = m;
				break;
			}
		}
		monsters.remove(temp);
		
		//check for item collision
		ItemIcon temp2 = null;
		for(ItemIcon i : items)
		{
			if(i.collidesWith(player))
			{
				//how do i use an item???
				i.applyBenefit(Game.getParty().getCharacters());
				Game.getCanvas().getLabel().setText("You found " + i.toString());
				//System.out.println(i);
				temp2 = i;
				break;
			}
		}
		items.remove(temp2);
		
	}

	//FOR JEFF
	@Override
	public void draw()
	{

	
		BufferedImage bufferImg = new BufferedImage(Game.getCanvas().getWidth(), Game.getCanvas().getHeight(), BufferedImage.TYPE_INT_RGB);
		drawBuffer(bufferImg);
		


	}

	//draw our buffered Image.
	public void drawBuffer(BufferedImage buffer)
	{
		
		//map -> items -> player -> monsters
		
		Graphics g = buffer.getGraphics();

		map.draw(g);
		
		for(ItemIcon i : items)
			i.draw(g);
		
		player.draw(g);

		for(MonsterIcon m : monsters)
			m.draw(g);

		g = Game.getCanvas().getGraphics();
		g.drawImage(buffer, 0, 0, Game.getCanvas() );

	}


	public void actionPerformed(ActionEvent e) {

	}

	private void addMonsters(int width, int height)
	{
		//add a number of monsters to the map at a random location
		for(int i = 0; i < NUM_MONSTERS; i++)
		{
			Coordinates location;

			do
			{
				location = Coordinates.randomLocation(width, height);

				//don't let the monster spawn in a wall or on the stairs
			} while(map.collidesWith(location) || location.equals(map.getUpstairs()));

			monsters.add(new MonsterIcon(location));
		}
	}
	
	private void addItems(int width, int height)
	{
		EquipmentItemsFactory e = new EquipmentItemsFactory();
		UseableItemsFactory u = new UseableItemsFactory();
		
		//add a number of monsters to the map at a random location
		for(int i = 0; i < NUM_USABLE_ITEMS; i++)
		{
			Coordinates location;

			do
			{
				location = Coordinates.randomLocation(width, height);

				//don't let the monster spawn in a wall or on the stairs
			} while(map.collidesWith(location));

			items.add(new ItemIcon(location, u.craftItem()));
		}
		
		//add a number of monsters to the map at a random location
		for(int i = 0; i < NUM_EQUIPMENT_ITEMS; i++)
		{
			Coordinates location;

			do
			{
				location = Coordinates.randomLocation(width, height);

				//don't let the monster spawn in a wall or on the stairs
			} while(map.collidesWith(location));

			items.add(new ItemIcon(location, e.craftItem()));
		}
		
	}

}
