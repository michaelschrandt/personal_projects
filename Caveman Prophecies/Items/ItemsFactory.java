package Items;

import java.util.Random;

public abstract class ItemsFactory 
{
	public Random rnd = new Random(); //subclasses of factories will use this random object. The range of values
	//for random will depend on the number of classes the sub-factory will have to instantiate.
	
	public Item craftItem() //will randomly create an item and return it
	{
		Item item = createItem();//will return a random item object, based on subclass factory
		
		//other item methods here
		
		return item;
	}
	
	public abstract Item createItem(); //factory object, subclasses will decide what the item is made of
}
