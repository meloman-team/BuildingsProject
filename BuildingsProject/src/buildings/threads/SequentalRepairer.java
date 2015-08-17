package buildings.threads;

import buildings.interfaces.Floor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SequentalRepairer implements Runnable {

    Floor floor;
    Semaphore semaphore;
    Semaphore semaphore2;

    public SequentalRepairer(Floor floor, Semaphore semaphore, Semaphore semaphore2) {
        this.floor = floor;
        this.semaphore = semaphore;
        this.semaphore2 = semaphore2;
    }

    @Override
    public void run() {
        int spaceQuantity = floor.getSpaceQuantity();
        
        for (int i = 0; i < spaceQuantity; i++) {
            
            try {
                semaphore.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(SequentalRepairer.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Repairing space number " + i + " with total area " + floor.getSpace(i).getArea() + " square meters");
            semaphore2.release();
        }
    }
}
