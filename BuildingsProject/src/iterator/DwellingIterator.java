package iterator;

import buildings.interfaces.Floor;
import java.util.Iterator;

public class DwellingIterator implements Iterator {

    private Floor space[];
    private int position = 0;

    public DwellingIterator(Floor[] space) {
        this.space = space;
    }

    @Override
    public boolean hasNext() {
        if (position >= space.length)
            return false;
        else return true;
    }

    @Override
    public Object next() {
        Floor nextSpace = space[position];
        position++;
        return nextSpace;
    }

}
