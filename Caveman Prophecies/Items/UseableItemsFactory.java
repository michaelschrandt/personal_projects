package Items;

public class UseableItemsFactory extends ItemsFactory
{
	public Item createItem()
	{
		int item_num = this.rnd.nextInt(3);
		
		if(item_num == 0)
		{
			return new PotionItem();
		}
		else if(item_num == 1)
		{
			return new HiPotionItem();
		}
		else if(item_num == 2)
		{
			return new BandageItem();
		}
		return null; //should not get to 'zis point
	}
}
