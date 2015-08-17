
package iterator;

import buildings.office.TwoLinkedNode;
import java.util.Iterator;

public class OfficeBuildingIterator implements Iterator {
    
    private TwoLinkedNode head;
    private TwoLinkedNode position;
    
    public OfficeBuildingIterator (TwoLinkedNode head){
        this.head = head;
        position = head;
    }

    @Override
    public boolean hasNext() {
        if(position.getNextNode() == head || position.getNextNode() == null)
            return false;
        else return true;
    }

    @Override
    public Object next() {
        position = position.getNextNode();
        return position.getFloor();
    }
    
}
