package Items;

public class EquipmentItemsFactory extends ItemsFactory
{
	
	public Item createItem()
	{
		int item_num = this.rnd.nextInt(4); //4 is excluded, so 0-3 will be returned
		
		if(item_num == 0)
		{
			return new Sword();
		}
		else if(item_num == 1)
		{
			return new Shield();
		}
		else if(item_num == 2)
		{
			return new Staff();
		}
		else if(item_num == 3)
		{
			return new Rod();
		}
		return null; //should not get to this point
	}
}
