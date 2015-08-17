
package buildings.threads;

import buildings.interfaces.Floor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SequentalCleaner implements Runnable {
    
    Floor floor;
    Semaphore semaphore;
    Semaphore semaphore2;
    
    public SequentalCleaner (Floor floor, Semaphore semaphore, Semaphore semaphore2) {
        this.floor = floor;
        this.semaphore = semaphore;
        this.semaphore2 = semaphore2;
    }
    
    @Override
    public void run() {
        int spaceQuantity = floor.getSpaceQuantity();
        for (int i = 0; i < spaceQuantity; i++) {
            try {
                semaphore2.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(SequentalCleaner.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Cleaning room number " + i + " with total area " + floor.getSpace(i).getArea() + " square meters");
            semaphore.release();
        }
    }
}
