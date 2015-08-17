
package buildings.threads;

import buildings.dwelling.DwellingFloor;
import buildings.interfaces.Floor;


public class NewMain {


    public static void main(String[] args) {
        Floor floor1 = new DwellingFloor(2);
        Floor floor2 = new DwellingFloor(2);
        Floor floor3 = new DwellingFloor(2);
        
        floor1.getSpace(0).setArea(1);
        floor1.getSpace(1).setArea(1);
        
        floor2.getSpace(0).setArea(2);
        floor2.getSpace(1).setArea(2);
        
        floor3.getSpace(0).setArea(3);
        floor3.getSpace(1).setArea(3);
        
        Cleaner c1 = new Cleaner(floor1);
        Cleaner c2 = new Cleaner(floor2);
        Cleaner c3 = new Cleaner(floor3);
        
        Repairer r1 = new Repairer(floor1);
        Repairer r2 = new Repairer(floor2);
        Repairer r3 = new Repairer(floor3);
        
        c1.setPriority(6);
        c2.setPriority(5);
        c3.setPriority(4);
        
        r1.setPriority(3);
        r2.setPriority(2);
        r3.setPriority(1);
        
        c1.start();
        c2.start();
        c3.start();
        
        r1.start();
        r2.start();
        r3.start();
    }
    
}
