
package factory;

import buildings.dwelling.Flat;
import buildings.dwelling.hotel.*;
import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class HotelFactory implements BuildingFactory {

    @Override
    public Space createSpace(double area) {
        Space space = new Flat(area);
        return space;
    }

    @Override
    public Space createSpace(int roomsCount, double area) {
        Space space = new Flat(area, roomsCount);
        return space;
    }

    @Override
    public Floor createFloor(int spacesCount) {
        Floor floor = new HotelFloor(spacesCount);
        return floor;
    }

    @Override
    public Floor createFloor(Space[] spaces) {
        Floor floor = new HotelFloor(spaces);
        return floor;
    }

    @Override
    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        Building building = new HotelBuilding(floorsCount, spacesCounts);
        return building;
    }

    @Override
    public Building createBuilding(Floor[] floors) {
        Building building = new HotelBuilding(floors);
        return building;
    }
    
}
