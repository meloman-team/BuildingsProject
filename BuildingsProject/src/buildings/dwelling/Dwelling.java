package buildings.dwelling;

import buildings.interfaces.Building;
import buildings.interfaces.Space;
import buildings.interfaces.Floor;
import buildings.exceptions.FloorIndexOutOfBoundsException;
import iterator.DwellingIterator;
import java.io.Serializable;
import java.util.Iterator;

public class Dwelling implements Building, Serializable {

    protected Floor[] floors;

    protected Dwelling() {

    }

    public Dwelling(Floor[] floorArray) {
        floors = floorArray;
    }

    public Dwelling(int floor, int[] flatQuantities) {
        floors = new DwellingFloor[floor];
        for (int i = 0; i < floors.length; i++) {
            floors[i] = new DwellingFloor(flatQuantities[i]);
        }
    }

    @Override
    public int getFloorQuantity() {
        return floors.length;
    }

    @Override
    public int getSpaceQuantity() {
        int spaceQuantity = 0;
        for (Floor flats : floors) {
            spaceQuantity += flats.getSpaceQuantity();
        }
        return spaceQuantity;
    }

    @Override
    public double getAreaTotal() {
        double areaTotal = 0;
        for (Floor flats : floors) {
            areaTotal += flats.getAreaTotal();
        }
        return areaTotal;
    }

    @Override
    public int getRoomQuantity() {
        int roomQuantity = 0;
        for (Floor flats : floors) {
            roomQuantity += flats.getRoomQuantity();
        }
        return roomQuantity;
    }

    @Override
    public Floor[] getFloorArray() {
        return floors;
    }

    public Floor[] getFloorArrayClone() throws CloneNotSupportedException {
        Floor[] flatsClone = new Floor[floors.length];
        for (int i = 0; i < floors.length; i++) {
            flatsClone[i] = (Floor) floors[i].clone();
        }
        return flatsClone;
    }

    @Override
    public Floor getFloor(int numberFloor) {
        return floors[numberFloor];
    }

    @Override
    public void setFloor(int numberFloor, Floor floor) {
        if (numberFloor > getFloorQuantity() - 1 || numberFloor < 0) {
            throw new FloorIndexOutOfBoundsException();
        }
        floors[numberFloor] = floor;
    }

    private int[] searchFlatIndex(int numberFlat) {
        int indexFlat[] = new int[2];
        indexFlat[0] = -1;
        indexFlat[1] = -1;
        int numFlat = 0;
        met:
        for (int i = 0; i < floors.length; i++) {
            if (numberFlat > floors[i].getSpaceQuantity() + numFlat - 1) {
                numFlat += floors[i].getSpaceQuantity();
            } else {

                for (int j = 0; j < floors[i].getSpaceQuantity(); j++) {
                    if (numberFlat != numFlat) {
                        numFlat++;
                    } else {
                        indexFlat[0] = i;
                        indexFlat[1] = j;
                        break met;
                    }
                }
            }
        }
        return indexFlat;
    }

    @Override
    public Space getSpace(int numberFlat) {
        int[] indexFlat;
        indexFlat = searchFlatIndex(numberFlat);
        return floors[indexFlat[0]].getSpace(indexFlat[1]);
    }

    @Override
    public void setSpace(int numberFlat, Space flat) {
        int[] indexFlat;
        indexFlat = searchFlatIndex(numberFlat);
        floors[indexFlat[0]].setSpace(indexFlat[1], flat);
    }

    @Override
    public void insertSpace(int numberFlat, Space flat) {
        int[] indexFlat;
        indexFlat = searchFlatIndex(numberFlat);
        if (indexFlat[0] == -1) {
            floors[floors.length - 1].insertSpace(floors[floors.length - 1].getSpaceQuantity(), flat);
        } else {
            floors[indexFlat[0]].insertSpace(indexFlat[1], flat);
        }
    }

    @Override
    public void removeSpace(int numberFlat) {
        int[] indexFlat;
        indexFlat = searchFlatIndex(numberFlat);
        floors[indexFlat[0]].removeSpace(indexFlat[1]);
    }

    @Override
    public Space getBestSpace() {
        Space tempFlat = floors[0].getBestSpace();
        for (int i = 1; i < floors.length; i++) {
            if (tempFlat.getArea() < floors[i].getBestSpace().getArea()) {
                tempFlat = floors[i].getBestSpace();
            }
        }
        return tempFlat;
    }

    @Override
    public Space[] getSortingSpaceByArea() {

        Space[] spaceArray = new Flat[getSpaceQuantity()];
        int floorCounter = 0;
        for (Floor floor : floors) {
            for (int j = 0; j < floor.getSpaceQuantity(); j++) {
                spaceArray[floorCounter] = floor.getSpace(j);
                floorCounter++;
            }
        }

        for (int i = spaceArray.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (spaceArray[j].getArea() < spaceArray[j + 1].getArea()) {
                    Space tempFlat = spaceArray[j];
                    spaceArray[j] = spaceArray[j + 1];
                    spaceArray[j + 1] = tempFlat;
                }
            }
        }
        return spaceArray;
    }

    @Override
    public Iterator iterator() {
        return new DwellingIterator(floors);
    }

    @Override
    public String toString() {
        StringBuffer string = new StringBuffer("Dwelling " + "(" + floors.length);
        for (int i = 0; i < floors.length; i++) {
            string.append(", " + floors[i].toString());
        }
        string.append(")");
        return string.toString();
    }

    
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!object.getClass().getCanonicalName().equals("buildings.dwelling.Dwelling")) {
            return false;
        }
        Dwelling dwelling = (Dwelling) object;
        if (!(dwelling.getFloorQuantity() == this.floors.length)) {
            return false;
        }
        Floor[] floors = dwelling.getFloorArray();
        for (int i = 0; i < floors.length; i++) {
            if (!this.floors[i].equals(floors[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (Floor floor : floors) {
            hashCode = hashCode ^ floor.hashCode();
        }
        return hashCode ^ floors.length;
    }

    //использовать фабрику?
    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Dwelling(getFloorArrayClone());
    }
}
