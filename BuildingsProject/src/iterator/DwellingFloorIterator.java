
package iterator;

import buildings.interfaces.Space;
import java.util.Iterator;

public class DwellingFloorIterator implements Iterator {
    private Space[] space;
    private int position = 0;
    
    public DwellingFloorIterator(Space[] space){
        this.space=space;
    }

    @Override
    public boolean hasNext() {
        if (position >= space.length)
            return false;
        else return true;
    }

    @Override
    public Object next() {
        Space nextSpace = space[position];
        position++;
        return nextSpace;
    }

}
