
package buildings.interfaces;

import java.util.Iterator;

public interface Floor {
    public int getSpaceQuantity();
    public double getAreaTotal();
    public int getRoomQuantity();
    public Space[] getSpaceArray();
    public Space getSpace(int numberSpace);
    public void setSpace(int numberSpace, Space space);
    public void insertSpace(int numberSpace, Space space);
    public void removeSpace(int numberSpace);
    public Space getBestSpace();
    public Iterator iterator();
    public Object clone()throws CloneNotSupportedException;
}
