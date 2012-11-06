package Items;

import java.util.ArrayList;

public class ItemFactoryTester {

	//This is to just test the item creation of the factories
	//Should be rather random each time you run it
	public static void main(String[] args) 
	{
		ItemsFactory useable = new UseableItemsFactory();
		ItemsFactory equipment = new EquipmentItemsFactory();
		
		Item one = useable.craftItem();
		Item two = useable.craftItem();
		Item three = useable.craftItem();
		
		System.out.println(one + "\n" + two+ "\n" + three);
		
		ArrayList<Item> equiplist = new ArrayList<Item>();
		
		equiplist.add(equipment.craftItem());
		equiplist.add(equipment.craftItem());
		equiplist.add(equipment.craftItem());
		equiplist.add(equipment.craftItem());
		
		System.out.println(equiplist.toString());

	}

}
