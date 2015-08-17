package buildings.office;

import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.exceptions.SpaceIndexOutOfBoundsException;
import buildings.exceptions.InexchangeableSpacesException;
import buildings.exceptions.FloorIndexOutOfBoundsException;

public class PlacementExchanger {

    public static boolean isPossibleToExchange(Space space1, Space space2) {
        return space1.getArea() == space2.getArea()
                && space1.getQuantityRoom() == space2.getQuantityRoom();
    }

    public static boolean isPossibleToExchange(Floor floor1, Floor floor2) {
        return floor1.getAreaTotal() == floor2.getAreaTotal()
                && floor1.getSpaceQuantity() == floor2.getRoomQuantity();
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2)
            throws InexchangeableSpacesException {
        Space space1 = floor1.getSpace(index1);
        Space space2 = floor2.getSpace(index2);
        if (!PlacementExchanger.isPossibleToExchange(space1, space2)) {
            throw new InexchangeableSpacesException();
        }
        if (index1 > floor1.getSpaceQuantity() - 1 || index1 < 0
                && index2 > floor2.getSpaceQuantity() - 1 || index2 < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        //Space tempSpace = space1; есть смысл в этой строке? в space1 и space2 копии? и поменяются ли они после
        //вызова следующих методов? если это копии то получается они заменятся на новые экземпляры а не обменяются
        //старыми экземплярами?
        floor1.setSpace(index1, space2);
        floor2.setSpace(index2, space1);
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2)
            throws InexchangeableSpacesException {
        Floor floor1 = building1.getFloor(index1);
        Floor floor2 = building2.getFloor(index2);
        if (!isPossibleToExchange(floor1, floor2)) {
            throw new InexchangeableSpacesException();
        }

        if (index1 > building1.getFloorQuantity() - 1 || index1 < 0
                && index2 > building2.getFloorQuantity() - 1 || index2 < 0) {
            throw new FloorIndexOutOfBoundsException();
        }
        building1.setFloor(index1, floor2);
        building2.setFloor(index2, floor1);
    }
}
