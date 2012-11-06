package Map;

public class BottomFloorMap extends OverworldMap
{
	private Coordinates meteorShard;
	
	public BottomFloorMap(int width, int height) {
		super(width, height);
		

	}
	
	protected void addStairs(DungeonFeature[][] modelMap, int width, int height)
	{
		super.addStairs(modelMap, width, height);
		
		meteorShard = new Coordinates(this.downstairs.getX(), this.downstairs.getY());
		
		modelMap[downstairs.getY()][downstairs.getX()] = DungeonFeature.EMPTY;
		this.downstairs.setX(-1);
		this.downstairs.setY(-1);
	}
	
	public Coordinates meteorShard()
	{
		return this.meteorShard;
	}

}
