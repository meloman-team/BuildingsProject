
package buildings;
import buildings.dwelling.*;
import buildings.interfaces.Space;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;

public class NewMain2 {


    public static void main(String[] args) throws CloneNotSupportedException {
        OfficeFloor[] floors = new OfficeFloor[2];
        floors[0] = new OfficeFloor(3);
        floors[1] = new OfficeFloor(3);
        OfficeBuilding d = new OfficeBuilding(floors);
        OfficeBuilding d2 = (OfficeBuilding) d.clone();
        
        d2.getFloor(1).getSpace(1).setArea(666);
        
        for (int i = 0; i < d.getFloorQuantity(); i++) {
            for (int j = 0; j < d.getFloor(i).getSpaceQuantity(); j++) {
                System.out.println("Floor " + i );
                System.out.println("    Space " + i );
                System.out.println("        area:" +  d.getFloor(i).getSpace(j).getArea());
            }
        }
        System.out.println("-------");
        for (int i = 0; i < d2.getFloorQuantity(); i++) {
            for (int j = 0; j < d2.getFloor(i).getSpaceQuantity(); j++) {
                System.out.println("Floor " + i );
                System.out.println("    Space " + i );
                System.out.println("        area:" +  d2.getFloor(i).getSpace(j).getArea());
            }
        }
    }
    
}
