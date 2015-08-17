
package factory;

import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;

public class OfficeFactory implements BuildingFactory {

    @Override
    public Space createSpace(double area) {
        Space space = new Office(area);
        return space;
    }

    @Override
    public Space createSpace(int roomsCount, double area) {
        Space space = new Office(area, roomsCount);
        return space;
    }

    @Override
    public Floor createFloor(int spacesCount) {
        Floor floor = new OfficeFloor(spacesCount);
        return floor;
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        Floor floor = new OfficeFloor(spaces);
        return floor;
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        Building building = new OfficeBuilding(floorsCount, spacesCounts);
        return building;
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        Building building = new OfficeBuilding(floors);
        return building;
    }
    
}
