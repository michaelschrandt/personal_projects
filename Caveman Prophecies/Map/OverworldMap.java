package Map;
import java.util.ArrayList;
import java.util.Random;


/**
 * This class is designed to create a 2d array that represents a map
 *
 */
public class OverworldMap extends Map
{
	//information for generating maps
	private static final int NUM_PASSES = 5;
	private static final int MAX_BLOBS = 5;
	private static final int MIN_BLOBS = 6;
	private static final Random RNG = new Random();
	
	
	public OverworldMap(int width, int height)
	{
		super(width, height);
	}

	/**
	 * creates a randomly generated map with an up and down stair.
	 * 
	 * @param width
	 * @param height
	 * @return
	 */
	public void generateMap(int width, int height)
	{
		//create array to hold map
		DungeonFeature[][] modelMap = new DungeonFeature[height][width];
		Tile[][] map = new Tile[height][width];
		
		//initialize to "empty" tiles
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				modelMap[i][j] = DungeonFeature.EMPTY;
			}
		}
		
		//add randomness to the map
		addBlobs(modelMap, width, height);
		
		//add up and downstairs
		addStairs(modelMap, width, height);
		
		//convert grid of dungeon features to tiles
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				map[i][j] = new Tile(modelMap[i][j]);
			}
		}
		
		this.grid = map;

	}
	
	/**
	 * 
	 * Takes a blank map and adds a random amount of blank "blobs" of space. It makes plain maps have
	 * some variation.
	 * 
	 * @param modelMap
	 * @param width
	 * @param height
	 */
	private void addBlobs(DungeonFeature[][] modelMap, int width, int height)
	{
		//corners will be expanded by default
		modelMap[0][0] = DungeonFeature.BLANK;
		modelMap[0][width-1] = DungeonFeature.BLANK;
		modelMap[height-1][0] = DungeonFeature.BLANK;
		modelMap[height-1][width-1] = DungeonFeature.BLANK;
		
		//set more random tiles that will be expanded
		int numBlobs = Math.abs(RNG.nextInt()%MAX_BLOBS) + MIN_BLOBS;
		
		for(int i = 0; i < numBlobs; i++)
		{
			int row = Math.abs(RNG.nextInt()%height);
			int col = Math.abs(RNG.nextInt()%width);
			
			modelMap[row][col] = DungeonFeature.BLANK;
		}
		
		//expand every BLANK tile several times
		for(int i = 0; i < NUM_PASSES; i++)
		{
			expand(modelMap);
		}
		
	}
	
	/**
	 * Add stairs to an existing map. Guarantees a path exists from upstairs to downstairs. The map will be modified to
	 * satisfy this constraint.
	 * 
	 * @param modelMap
	 * @param width
	 * @param height
	 */
	protected void addStairs(DungeonFeature[][] modelMap, int width, int height)
	{
		//cordinates of the upstair
		int startX = 0, startY = 0;
		//coordinates of the downstair
		int endX = 0, endY = 0;
		
		//set random x value for upstair
		startX = Math.abs(RNG.nextInt() % width);
		
		//set random x value for downstair, ensure it is different than upstair
		do{	endX = Math.abs(RNG.nextInt() % height);} while(endX == startX);
		
		
		//next we carve a path from the downstair to the upstair. the path starts at this coordinate
		int pathY = Math.abs(RNG.nextInt() % height);
		
		//the path will go from left to right
		for(int i = 0; i < width; i++)
		{
			int pathWidth = Math.abs(RNG.nextInt() % 3) + 2;
			
			//replace existing dungeon feature with walkable EMPTY tiles
			for(int j = pathY; j < pathY + pathWidth && j < height; j++)
				modelMap[j][i] = DungeonFeature.EMPTY;
			
			//the upstair and downstair will be placed on this path
			if(i == startX)
				startY = pathY;
			if(i == endX)
				endY = pathY;
			
			//move the path up or down 1 unit
			if(RNG.nextBoolean() && pathY < height - 1)
				pathY++;
			else if(pathY > 0)
				pathY--;
		}
		
		//add the stairs to the map
		modelMap[startY][startX] = DungeonFeature.UP_STAIR;
		modelMap[endY][endX] = DungeonFeature.DOWN_STAIR;
		
		upstairs = new Coordinates(startX, startY);
		downstairs = new Coordinates(endX, endY);
	}
	
	/**
	 * Iterates through every existing BLANK tile, randomly causing adjacent tiles to also turn BLANK.
	 * This creates a "blob" effect making dungeons look random.
	 * 
	 * @param modelMap
	 */
	private void expand(DungeonFeature[][] modelMap)
	{
		ArrayList<Coordinates> coordsToExpand = new ArrayList<Coordinates>();
		
		//first add all blank coordinates to a list
		for(int i = 0; i < modelMap.length; i++)
		{
			for(int j = 0; j < modelMap[i].length; j++)
			{
				if(modelMap[i][j] == DungeonFeature.BLANK)
				{
					coordsToExpand.add(new Coordinates(j, i));
				}
					
			}
		}
		
		//then with certain probability, expand them
		for(Coordinates c : coordsToExpand)
		{
			if(c.getX() > 0 && RNG.nextBoolean())
				modelMap[c.getY()][c.getX() - 1] = DungeonFeature.BLANK;
			
			if(c.getX() < modelMap[0].length - 1 && RNG.nextBoolean())
				modelMap[c.getY()][c.getX() + 1] = DungeonFeature.BLANK;
			
			if(c.getY() > 0 && RNG.nextBoolean())
				modelMap[c.getY() - 1][c.getX()] = DungeonFeature.BLANK;
			
			if(c.getY() < modelMap.length - 1 && RNG.nextBoolean())
				modelMap[c.getY() + 1][c.getX()] = DungeonFeature.BLANK;
		}
	}
}
