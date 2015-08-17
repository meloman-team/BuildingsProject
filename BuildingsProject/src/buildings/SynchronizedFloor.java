
package buildings;

import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import java.util.Iterator;

public class SynchronizedFloor implements Floor{
    
    Floor floor;
    
    public SynchronizedFloor (Floor floor){
        this.floor = floor;
    }

    @Override
    public synchronized int getSpaceQuantity() {
        return floor.getSpaceQuantity();
    }

    @Override
    public synchronized double getAreaTotal() {
        return floor.getAreaTotal();
    }

    @Override
    public synchronized int getRoomQuantity() {
        return floor.getRoomQuantity();
    }

    @Override
    public synchronized Space[] getSpaceArray() {
        return floor.getSpaceArray();
    }

    @Override
    public synchronized Space getSpace(int numberSpace) {
        return floor.getSpace(numberSpace);
    }

    @Override
    public synchronized void setSpace(int numberSpace, Space space) {
         floor.setSpace(numberSpace, space);
    }

    @Override
    public synchronized void insertSpace(int numberSpace, Space space) {
        floor.insertSpace(numberSpace, space);
    }

    @Override
    public synchronized void removeSpace(int numberSpace) {
        floor.removeSpace(numberSpace);
    }

    @Override
    public synchronized Space getBestSpace() {
        return floor.getBestSpace();
    }

    @Override
    public Iterator iterator() {
        return floor.iterator();
    }
    
    @Override
    public String toString() {
        return floor.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!object.getClass().getCanonicalName().equals("buildings.SynchronizedFloor")) {
            return false;
        }
        SynchronizedFloor synchronizedFloor = (SynchronizedFloor) object;
        if (!(synchronizedFloor.getSpaceQuantity() == floor.getSpaceQuantity())) {
            return false;
        }
        return synchronizedFloor.floor.equals(floor);
    }

    //доделать и проверить
    @Override
    public int hashCode() {
        return floor.hashCode() ^ Integer.MIN_VALUE;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new SynchronizedFloor((Floor) floor.clone());
    }
}
