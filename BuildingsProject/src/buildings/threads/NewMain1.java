
package buildings.threads;

import buildings.dwelling.DwellingFloor;
import buildings.interfaces.Floor;

public class NewMain1 {

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
        
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore11 = new Semaphore(0);
        Semaphore semaphore2 = new Semaphore(1);
        Semaphore semaphore22 = new Semaphore(0);
        Semaphore semaphore3 = new Semaphore(1);
        Semaphore semaphore33 = new Semaphore(0);
        
        SequentalCleaner c11 = new SequentalCleaner(floor1,semaphore1, semaphore11);
        SequentalCleaner c22 = new SequentalCleaner(floor2,semaphore2, semaphore22);
        SequentalCleaner c33 = new SequentalCleaner(floor3,semaphore3, semaphore33);
        
        SequentalRepairer r11 = new SequentalRepairer(floor1,semaphore1, semaphore11);
        SequentalRepairer r22 = new SequentalRepairer(floor2,semaphore2, semaphore22);
        SequentalRepairer r33 = new SequentalRepairer(floor3,semaphore3, semaphore33);
        
        Thread c1 = new Thread(c11);
        Thread c2 = new Thread(c22);
        Thread c3 = new Thread(c33);
        
        Thread r1 = new Thread(r11);
        Thread r2 = new Thread(r22);
        Thread r3 = new Thread(r33);
        
        r1.start();
//        r2.start();
//        r3.start();
        
        c1.start();
//        c2.start();
//        c3.start();
        

    }
    
}
