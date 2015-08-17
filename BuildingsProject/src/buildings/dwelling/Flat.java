package buildings.dwelling;

import java.io.Serializable;
import buildings.interfaces.Space;

public class Flat implements Space, Serializable, Cloneable {

    final static double DEFAULT_AREA = 50;
    final static int DEFAULT_QUANTITY_ROOM = 2;
    private double area;
    private int quantityRoom;

    public Flat() {
        area = DEFAULT_AREA;
        quantityRoom = DEFAULT_QUANTITY_ROOM;
    }

    public Flat(double area) {
        this.area = area;
        this.quantityRoom = DEFAULT_QUANTITY_ROOM;
    }

    public Flat(double area, int quantityRoom) {
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
        this.quantityRoom = quantityRoom;
    }

    @Override
    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Flat (" + quantityRoom + ", " + area + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!object.getClass().getCanonicalName().equals("buildings.dwelling.Flat")) {
            return false;
        }
        Flat flat = (Flat) object;
        return flat.getArea() == area && flat.getQuantityRoom() == quantityRoom;
    }

    @Override
    public int hashCode() {
        return quantityRoom ^ (int)area ^ (int)(Double.doubleToLongBits(area)>>32);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
