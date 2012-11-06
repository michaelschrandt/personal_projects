package Map;

import java.awt.Graphics;

import Game.Game;

//This class contains a grid of empty spaces and walkable spaces. Also contains an upstair and a downstair.
//Items may be randomly scattered across the map.
public abstract class Map
{
	//map attributes
	protected Tile[][] grid;
	protected Coordinates upstairs;
	protected Coordinates downstairs;
	private int width;
	private int height;
	
	public Map(int width, int height)
	{
		this.width = width;
		this.height = height;
		generateMap(width, height);
		//addItems();
	}
	
	protected abstract void generateMap(int width, int height);
	
	//getters
	public int getWidth(){ return this.width; }
	public int getHeight(){ return this.height; }
	public Coordinates getUpstairs(){ return this.upstairs; }
	public Coordinates getDownstairs(){ return this.downstairs; }
	
	//check collision
	public boolean collidesWith(Coordinates c)
	{
		if(c.getX() < 0 || c.getX() >= this.width)
			return true;
		if(c.getY() < 0 || c.getY() >= this.height)
			return true;
		
		return this.grid[c.getY()][c.getX()].getType() == DungeonFeature.BLANK;
	}
	
//	
//	//draw the map to a 2d array of chars
//	public void drawToBuffer(char[][] buffer)
//	{
//		for(int i = 0; i < grid.length; i++)
//		{
//			for(int j = 0; j < grid[i].length; j++)
//			{
//				char cur;
//				if(grid[i][j] == DungeonFeature.EMPTY)
//					cur = '.';
//				else if(grid[i][j] == DungeonFeature.WALL)
//					cur = '|';
//				else if(grid[i][j] == DungeonFeature.DOWN_STAIR)
//					cur = '>';
//				else if(grid[i][j] == DungeonFeature.UP_STAIR)
//					cur = '<';
//				else
//					cur = ' ';
//
//				buffer[i][j] = cur;
//			}
//		}
//		
//		
//		//print each dungeon feature in a 2d map. 
//		/*
//		for(DungeonFeature rows[] : grid)
//		{
//			for(DungeonFeature tile : rows)
//			{
//				if(tile == DungeonFeature.EMPTY)
//					System.out.print(".");
//				else if(tile == DungeonFeature.WALL)
//					System.out.print("|");
//				else if(tile == DungeonFeature.DOWN_STAIR)
//					System.out.print(">");
//				else if(tile == DungeonFeature.UP_STAIR)
//					System.out.print("<");
//				else
//					System.out.print(" ");
//				System.out.print(" ");
//			}
//			
//			System.out.println();
//		}*/
//	}

	public void draw(Graphics g2)
	{
		//Graphics g = Game.getCanvas().getGraphics();
		
		for(int i = 0; i < Game.MAP_H; i++)
		{
			for(int j = 0; j < Game.MAP_W; j++)
			{
				g2.drawImage(this.grid[i][j].getImage(), j*Game.TILE_W, i*Game.TILE_W, Game.TILE_W, Game.TILE_W, Game.getCanvas());
			}
		}
	}
}
