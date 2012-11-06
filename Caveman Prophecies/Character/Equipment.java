
package Character;

import java.util.ArrayList;
import java.util.Iterator;

public class Equipment {
    private ArrayList<Items.Item> myList = null;
    
    public Equipment()
    {
        this.myList = new ArrayList<Items.Item>();
    }

    public int count()
    {
        return this.myList.size();
    }
    
    public void setEquipment(Items.Item anItem)
    {
        this.myList.add(anItem);
        this.myList.trimToSize();
    }
    
    public boolean removeEquipment(Items.Item myItem, boolean mode)
    {
        if(mode)
        {
            boolean isRemoved = false;
            while(this.myList.remove(myItem))
            {
                isRemoved = true;
            }
            this.myList.trimToSize();
            return isRemoved;
        }
        else
        {
            boolean success = this.myList.remove(myItem);
            this.myList.trimToSize();
            return success;
        }
    } //mode=true remove all mode=false remove the first item
    
    public boolean removeEquipment(int index)
    {
        if(index >= 0 && index < this.count())
        {
            this.myList.remove(index);
            this.myList.trimToSize();
            return true;
        }
        else
        {
            throw new IndexOutOfBoundsException("No such index. ");
        }
    }
    
    public void removeAll()
    {
        this.myList.clear();
    }
    
    public Items.Item getEquipment(int index)
    {
        if(index < this.count() && index >= 0)
        {
            return this.myList.get(index);
        }
        else
        {
            throw new IndexOutOfBoundsException("Item not exists. ");
        }
    }
    
    public Iterator<Items.Item> createIterator()
    {
        return this.myList.iterator();
    }
    
    @Override
    public String toString()
    {
        String output = "The equipment(s): ";
        for(int i=0; i<this.count(); i++)
        {
            output += this.myList.get(i) + ", ";
        }
        if(this.count()==0)
        {
            output += "No weapon. ";
        }
        
        return output;
    }
}
