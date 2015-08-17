
package buildings.interfaces;

import java.util.Iterator;

public interface Building {
    public int getFloorQuantity();
    public int getSpaceQuantity();
    public double getAreaTotal();
    public int getRoomQuantity();
    public Floor[] getFloorArray();
    public Floor getFloor(int numberFloor);
    public void setFloor(int numberFloor, Floor floor);
    public Space getSpace(int numberSpace);
    public void setSpace(int numberSpace, Space space);
    public void insertSpace(int numberSpace, Space space);
    public void removeSpace(int numberSpace);
    public Space getBestSpace();
    public Space[] getSortingSpaceByArea();
    public Iterator iterator();
    public Object clone()throws CloneNotSupportedException;
}
