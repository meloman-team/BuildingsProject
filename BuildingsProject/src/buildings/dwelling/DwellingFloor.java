package buildings.dwelling;

import buildings.interfaces.Space;
import buildings.interfaces.Floor;
import buildings.exceptions.SpaceIndexOutOfBoundsException;
import iterator.DwellingFloorIterator;
import java.io.Serializable;
import java.util.Iterator;

public class DwellingFloor implements Floor, Serializable, Cloneable {

    private Space[] flats;

    public DwellingFloor(int quantityFlat) {
        flats = new Space[quantityFlat];
        for (int i = 0; i < flats.length; i++) {
            flats[i] = new Flat();
        }
    }

    public DwellingFloor(Space arraySpace[]) {
        flats = arraySpace;
    }

    @Override
    public int getSpaceQuantity() {
        return flats.length;
    }

    @Override
    public double getAreaTotal() {
        double areaTotal = 0;
        for (Space flat : flats) {
            areaTotal += flat.getArea();
        }
        return areaTotal;
    }

    @Override
    public int getRoomQuantity() {
        int roomCount = 0;
        for (Space room : flats) {
            roomCount += room.getQuantityRoom();
        }
        return roomCount;
    }

    @Override
    public Space[] getSpaceArray() {
        return flats;
    }

    public Space[] getSpaceArrayClone() throws CloneNotSupportedException {
        Space[] flatsClone = new Space[flats.length];
        for (int i = 0; i < flats.length; i++) {
            flatsClone[i] = (Space) flats[i].clone();
        }
        return flatsClone;
    }

    @Override
    public Space getSpace(int numberFlat) {
        return flats[numberFlat];
    }

    @Override
    public void setSpace(int numberFlat, Space flat) {
        if (numberFlat > flats.length - 1 || numberFlat < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        flats[numberFlat] = flat;
    }

    @Override
    public void insertSpace(int numberFlat, Space flat) {
        Space tempFloor[] = new Space[flats.length + 1];
        if (numberFlat < flats.length) {
            System.arraycopy(flats, 0, tempFloor, 0, numberFlat);
            System.arraycopy(flats, numberFlat, tempFloor, numberFlat + 1, flats.length - numberFlat);
            tempFloor[numberFlat] = flat;
        } else {
            System.arraycopy(flats, 0, tempFloor, 0, flats.length);
            tempFloor[flats.length] = flat;
        }
        flats = tempFloor;
    }

    @Override
    public void removeSpace(int numberFlat) {
        Space tempFloor[] = new Space[flats.length];
        System.arraycopy(flats, 0, tempFloor, 0, numberFlat);
        System.arraycopy(flats, numberFlat + 1, tempFloor, numberFlat, flats.length - numberFlat - 1);
        flats = tempFloor;
    }

    @Override
    public Space getBestSpace() {
        Space bestSpace = flats[0];
        for (Space room : flats) {
            if (room.getArea() > bestSpace.getArea()) {
                bestSpace = room;
            }
        }
        return bestSpace;
    }

    @Override
    public Iterator iterator() {
        return new DwellingFloorIterator(flats);
    }

    @Override
    public String toString() {
        StringBuffer string = new StringBuffer("DwellingFloor " + "(" + flats.length);
        for (Space flat : flats) {
            string.append(", ").append(flat.toString());
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
        if (!object.getClass().getCanonicalName().equals("buildings.dwelling.DwellingFloor")) {
            return false;
        }
        DwellingFloor dwellingFloor = (DwellingFloor) object;
        if (!(dwellingFloor.getSpaceQuantity() == flats.length)) {
            return false;
        }
        Space[] spaces = dwellingFloor.getSpaceArray();
        for (int i = 0; i < spaces.length; i++) {
            if (!flats[i].equals(spaces[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (Space flat : flats) {
            hashCode = hashCode ^ flat.hashCode();
        }
        return hashCode ^ flats.length;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new DwellingFloor(getSpaceArrayClone());
    }
}
