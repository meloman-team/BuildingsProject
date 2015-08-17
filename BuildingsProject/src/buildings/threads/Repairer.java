package buildings.threads;

import buildings.interfaces.Floor;

public class Repairer extends Thread {
    
    Floor floor;

    public Repairer (Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run() {
        int spaceQuantity = floor.getSpaceQuantity();
        for (int i = 0; i < spaceQuantity; i++) {
            System.out.println("Repairing space number " + i + " with total area " + floor.getSpace(i).getArea() + " square meters");
        }
    }
}
