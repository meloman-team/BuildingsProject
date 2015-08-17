package buildings.dwelling.hotel;

import buildings.dwelling.Dwelling;
import buildings.interfaces.*;

public class HotelBuilding extends Dwelling implements Building {

    public HotelBuilding(Floor[] floorArray) {
        super(floorArray);
    }

    public HotelBuilding(int floor, int[] flatQuantities) {
        floors = new HotelFloor[floor];
        for (int i = 0; i < floors.length; i++) {
            floors[i] = new HotelFloor(flatQuantities[i]);
        }
    }

    public int getQuantityStars() {
        int floorQuantity = getFloorQuantity();
        int quantityStars = 0;
        HotelFloor hotelFloor = null;
        boolean bool = false;

        for (int i = 0; i < floorQuantity; i++) {

            try {
                hotelFloor = (HotelFloor) getFloor(i);
                bool = true;
            } catch (Exception e) {
            }

            if (bool && quantityStars < hotelFloor.getQuantityStars()) {
                quantityStars = hotelFloor.getQuantityStars();
            }
        }

        return quantityStars;
    }

    @Override
    public Space getBestSpace() {
        double coeff[] = new double[5];
        coeff[0] = 0.25;
        coeff[1] = 0.5;
        coeff[2] = 1;
        coeff[3] = 1.25;
        coeff[4] = 1.5;

        int floorQuantity = getFloorQuantity();
        Space bestSpace = null;
        HotelFloor hotelFloor = null;
        boolean bool = false;

        for (int i = 0; i < floorQuantity; i++) {
            bool = hotelFloor.getClass().getCanonicalName().equals("buildings.dwelling.hotel.HotelFloor");
            if (bool) {
                int spaceQuantity = hotelFloor.getSpaceQuantity();
                double coefficient = coeff[hotelFloor.getQuantityStars() - 1];

                if (bestSpace == null) {
                    bestSpace = hotelFloor.getSpace(0);
                }

                for (int j = 0; j < spaceQuantity; j++) {
                    if (hotelFloor.getSpace(j).getArea() * coefficient
                            > bestSpace.getArea() * coefficient) {
                        bestSpace = hotelFloor.getSpace(j);
                    }
                }
            }
        }
        return bestSpace;
    }

    @Override
    public String toString() {
        Floor[] floors = getFloorArray();
        StringBuffer string = new StringBuffer("HotelBuilding " + "(" + getQuantityStars() + "," + floors.length);
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
        if (!object.getClass().getCanonicalName().equals("buildings.dwelling.hotel.HotelBuilding")) {
            return false;
        }
        Dwelling dwelling = (Dwelling) object;
        if (dwelling.getFloorQuantity() != this.getFloorQuantity()) {
            return false;
        }
        Floor[] floors = dwelling.getFloorArray();
        Floor[] thisFloors = this.getFloorArray();
        for (int i = 0; i < floors.length; i++) {
            if (!thisFloors[i].equals(floors[i])) {
                return false;
            }
        }
        return true;
    }

    //переделать
    @Override
    public int hashCode() {
        return super.hashCode() ^ getQuantityStars();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new HotelBuilding(getFloorArrayClone());
    }
}
