package buildings.threads;

import buildings.interfaces.Floor;

public class Cleaner extends Thread{
    
    Floor floor;
    
    public Cleaner (Floor floor) {
        this.floor = floor;
    }
    
    @Override
    public void run() {
        int spaceQuantity = floor.getSpaceQuantity();
        for (int i = 0; i < spaceQuantity; i++) {
            System.out.println("Cleaning room number " + i + " with total area " + floor.getSpace(i).getArea() + " square meters");
        }
    }
}
