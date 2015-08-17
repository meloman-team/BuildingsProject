
package iterator;

import buildings.interfaces.Space;
import buildings.office.OneLinkedNode;
import java.util.Iterator;

public class OfficeFloorIterator implements Iterator {
    
    private OneLinkedNode head;
    private OneLinkedNode position;
    
    public OfficeFloorIterator(OneLinkedNode head){
        this.head=head;
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
        return position.getOffice();
    }
    
}
