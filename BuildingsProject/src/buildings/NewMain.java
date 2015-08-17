
package buildings;

import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.office.Office;
import buildings.office.OfficeFloor;

public class NewMain {

    public static void main(String[] args) throws CloneNotSupportedException {
        Office f = new Office();
        Office f2 = (Office) f.clone();
        System.out.println("f area: " + f.getArea());
        System.out.println("f QuantityRoom: " + f.getQuantityRoom());
        System.out.println("f2 area: " + f2.getArea());
        System.out.println("f2 QuantityRoom: " + f2.getQuantityRoom());
        
        f2.setArea(10);
        f2.setQuantityRoom(10);
        System.out.println();
        System.out.println("f area: " + f.getArea());
        System.out.println("f QuantityRoom: " + f.getQuantityRoom());
        System.out.println("f2 area: " + f2.getArea());
        System.out.println("f2 QuantityRoom: " + f2.getQuantityRoom());
        
        System.out.println();
        
        OfficeFloor floor1 = new OfficeFloor(3);
        
        OfficeFloor floor2 = (OfficeFloor) floor1.clone();
        
        floor2.getSpace(1).setArea(1000);
        
        Space[] space1 = floor1.getSpaceArray();
        Space[] space2 = floor2.getSpaceArray();
        
        for (int i = 0; i < space1.length; i++) {
            System.out.println("Space " + i );
            System.out.println("    area:" +  space1[i].getArea());
            System.out.println("    QuantityRoom:" +  space1[i].getQuantityRoom());
        }
        
        
        for (int i = 0; i < space2.length; i++) {
            System.out.println("Space " + i );
            System.out.println("    area:" +  space2[i].getArea());
            System.out.println("    QuantityRoom:" +  space2[i].getQuantityRoom());
        }
    }
    
}
