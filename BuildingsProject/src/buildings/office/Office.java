package buildings.office;

import buildings.exceptions.InvalidSpaceAreaException;
import buildings.exceptions.InvalidRoomsCountException;
import buildings.interfaces.Space;
import java.io.Serializable;

public class Office implements Space, Serializable, Cloneable {

    final static double DEFAULT_AREA = 250;
    final static int DEFAULT_QUANTITY_ROOM = 1;
    private double area;
    private int quantityRoom;

    public Office() {
        area = DEFAULT_AREA;
        quantityRoom = DEFAULT_QUANTITY_ROOM;
    }

    public Office(double area) {
        if (area < 1) {
            throw new InvalidSpaceAreaException();
        }
        this.area = area;
        this.quantityRoom = DEFAULT_QUANTITY_ROOM;
    }

    public Office(double area, int quantityRoom) {
        if (area < 1) {
            throw new InvalidSpaceAreaException();
        }
        if (quantityRoom < 1) {
            throw new InvalidRoomsCountException();
        }
        this.area = area;
        this.quantityRoom = quantityRoom;
    }

    @Override
    public int getQuantityRoom() {
        return quantityRoom;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public void setQuantityRoom(int quantityRoom) {
        if (quantityRoom < 1) {
            throw new InvalidRoomsCountException();
        }
        this.quantityRoom = quantityRoom;
    }

    @Override
    public void setArea(double area) {
        if (area < 1) {
            throw new InvalidSpaceAreaException();
        }
        this.area = area;
    }

    @Override
    public String toString() {
        return "Office (" + quantityRoom + ", " + area + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!object.getClass().getCanonicalName().equals("buildings.office.Office")) {
            return false;
        }
        Office office = (Office) object;
        return office.getArea() == area && office.getQuantityRoom() == quantityRoom;
    }

    @Override
    public int hashCode() {
        return  Integer.MAX_VALUE ^ quantityRoom ^ (int)area ^ (int)(Double.doubleToLongBits(area)>>32);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
