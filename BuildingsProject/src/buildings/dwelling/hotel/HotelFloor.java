package buildings.dwelling.hotel;

import buildings.dwelling.DwellingFloor;
import buildings.interfaces.Space;

public class HotelFloor extends DwellingFloor {

    final static int DEFAULT_QUANTITY_STARS = 1;
    private int quantityStars;

    public HotelFloor(int quantitySpace) {
        super(quantitySpace);
        quantityStars = DEFAULT_QUANTITY_STARS;
    }

    public HotelFloor(Space[] arraySpace) {
        super(arraySpace);
        quantityStars = DEFAULT_QUANTITY_STARS;
    }

    public HotelFloor(Space[] arraySpace, int quantityStars) {
        super(arraySpace);
        this.quantityStars = quantityStars;
    }

    public int getQuantityStars() {
        return quantityStars;
    }

    public void setQuantityStars(int quantityStars) {
        this.quantityStars = quantityStars;
    }

    @Override
    public String toString() {
        Space[] spaces = getSpaceArray();
        StringBuffer string = new StringBuffer("HotelFloor (" + quantityStars + "," + spaces.length);
        for (Space space : spaces) {
            string.append(", " + space.toString());
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
        if (!object.getClass().getCanonicalName().equals("buildings.dwelling.hotel.HotelFloor")) {
            return false;
        }
        HotelFloor hotelFloor = (HotelFloor) object;
        if (!(hotelFloor.getQuantityStars() == quantityStars)) {
            return false;
        }
        if (!(hotelFloor.getSpaceQuantity() == this.getSpaceQuantity())) {
            return false;
        }
        Space[] spaces = hotelFloor.getSpaceArray();
        Space[] thisSpaces = this.getSpaceArray();
        for (int i = 0; i < spaces.length; i++) {
            if (!thisSpaces[i].equals(spaces[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ quantityStars;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new HotelFloor(this.getSpaceArrayClone(), quantityStars);
    }
}
