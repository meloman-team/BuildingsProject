package buildings;

import buildings.interfaces.*;
import factory.DwellingFactory;
import java.io.*;

public class Buildings {
    final static int END_OF_LINE = 99999;
    
    static private BuildingFactory factory = new DwellingFactory();
    
    public static void setBuildingFactory (BuildingFactory factory){
        Buildings.factory = factory;
    }
    
    public static Building createBuilding (Floor[] floorArray){
        return factory.createBuilding(floorArray);
    }
    
    public static Building createBuilding (int floorsCount, int[] spacesCounts){
        return factory.createBuilding(floorsCount, spacesCounts);
    }
    
    public static Floor createFloor (Space[] spaces){
        return factory.createFloor(spaces);
    }
    
    public static Floor createFloor (int spacesCount){
        return factory.createFloor(spacesCount);
    }
    
    public static Space createSpace(int roomsCount, double area){
        return factory.createSpace(roomsCount, area);
    }
    
    public static Space createSpace(double area){
        return factory.createSpace(area);
    }

    public static void outputBuilding(Building building, OutputStream out) {
        PrintStream out2 = new PrintStream(new BufferedOutputStream(out));
            int getFloorQuantity = building.getFloorQuantity();
            out2.print(getFloorQuantity);
            out2.print(" ");
            for (int i = 0; i < building.getFloorQuantity(); i++) {
                int spaceQuantity = building.getFloor(i).getSpaceQuantity();
                out2.print(spaceQuantity);
                out2.print(" ");
                for (int j = 0; j < spaceQuantity; j++) {
                    out2.print(building.getFloor(i).getSpace(j).getQuantityRoom());
                    out2.print(" ");
                    out2.print(building.getFloor(i).getSpace(j).getArea());  
                    out2.print(" ");
                }
            }
            out2.flush();
    }

    public static Building inputBuilding(InputStream in) {
         Building building = null;
         InputStreamReader inStR = new InputStreamReader(in);
        try {
            StreamTokenizer in2 = new StreamTokenizer(inStR);
            
            in2.parseNumbers();
            in2.nextToken();
                    int floorQuantity = (int)in2.nval;//этажи
                    Floor[] floorArray = new Floor[floorQuantity];
                    int i = 0;
                    while (i < floorQuantity) {
                        in2.nextToken();
                            int spaceQuantity = (int) in2.nval;//квартир на этаже
                            Space[] arraySpace = new Space[spaceQuantity];
                            int j = 0;
                            while (j < spaceQuantity) {
                                int spaceRoom = 0;
                                double spaceArea = 0;
                                in2.nextToken();
                                    spaceRoom =(int) in2.nval;//комнат на этаже
                                in2.nextToken();
                                    spaceArea = in2.nval;
                                Space space = createSpace(spaceRoom, spaceArea);
                                //Space space = new Flat(spaceArea, spaceRoom);
                                arraySpace[j] = space;
                                j++;
                            }
                            Floor floor = createFloor(arraySpace);
                            //Floor floor = new DwellingFloor(arraySpace);
                            floorArray[i] = floor;
                            i++;
                    }
                    building = createBuilding(floorArray);
                    //building = new Dwelling(floorArray); 
                
            
        } catch (IOException e) {
            System.out.println("Some error occurred!");
        }
        return building;
    }

    public static void writeBuilding(Building building, Writer out) {
        try (PrintWriter out2 = new PrintWriter(new BufferedWriter(out))) {
            out2.print(building.getFloorQuantity() + " ");
            for (int i = 0; i < building.getFloorQuantity(); i++) {
                int spaceQuantity = building.getFloor(i).getSpaceQuantity();
                out2.print(spaceQuantity + " ");
                for (int j = 0; j < spaceQuantity; j++) {
                    out2.print(building.getFloor(i).getSpace(j).getQuantityRoom() + " ");
                    out2.print(building.getFloor(i).getSpace(j).getArea() + " ");
                }
            }
        }
    }

    public static Building readBuilding(Reader in) {
        Building building = null;
        try {
            StreamTokenizer in2 = new StreamTokenizer(in);
            
            in2.parseNumbers();
            in2.nextToken();
                    int floorQuantity = (int)in2.nval;//этажи
                    Floor[] floorArray = new Floor[floorQuantity];
                    int i = 0;
                    while (i < floorQuantity) {
                        in2.nextToken();
                            int spaceQuantity = (int) in2.nval;//квартир на этаже
                            Space[] arraySpace = new Space[spaceQuantity];
                            int j = 0;
                            while (j < spaceQuantity) {
                                int spaceRoom = 0;
                                double spaceArea = 0;
                                in2.nextToken();
                                    spaceRoom =(int) in2.nval;//комнат на этаже
                                in2.nextToken();
                                    spaceArea = in2.nval;
                                Space space = createSpace(spaceRoom, spaceArea);
                                //Space space = new Flat(spaceArea, spaceRoom);
                                arraySpace[j] = space;
                                j++;
                            }
                            Floor floor = createFloor(arraySpace);
                            //Floor floor = new DwellingFloor(arraySpace);
                            floorArray[i] = floor;
                            i++;
                    }
                    building = createBuilding(floorArray);
                    //building = new Dwelling(floorArray);
            
        } catch (IOException e) {
            System.out.println("Some error occurred!");
        }
        return building;
    }
    
    Floor SynchronizedFloor (Floor floor){
        return new SynchronizedFloor(floor);
    }
    
    public static Building inputBuilding2(InputStream in, int numberString) {
        
        if(numberString<0)throw new IllegalArgumentException("numberString < 0");
        
         Building building = null;
         InputStreamReader inStR = new InputStreamReader(in);
        try {
            StreamTokenizer in2 = new StreamTokenizer(inStR);            

            do{
                in2.nextToken();
            }while(in2.lineno() != numberString);
            
            
                    int floorQuantity = (int)in2.nval;//этажи
                    Floor[] floorArray = new Floor[floorQuantity];
                    int i = 0;
                    while (i < floorQuantity) {
                        in2.nextToken();
                            int spaceQuantity = (int) in2.nval;//квартир на этаже
                            Space[] arraySpace = new Space[spaceQuantity];
                            int j = 0;
                            while (j < spaceQuantity) {
                                int spaceRoom = 0;
                                double spaceArea = 0;
                                in2.nextToken();
                                    spaceRoom =(int) in2.nval;//комнат на этаже
                                in2.nextToken();
                                    spaceArea = in2.nval;
                                Space space = createSpace(spaceRoom, spaceArea);
                                //Space space = new Flat(spaceArea, spaceRoom);
                                arraySpace[j] = space;
                                j++;
                            }
                            Floor floor = createFloor(arraySpace);
                            //Floor floor = new DwellingFloor(arraySpace);
                            floorArray[i] = floor;
                            i++;
                    }
                    building = createBuilding(floorArray);
                    //building = new Dwelling(floorArray); 
               
            
        } catch (IOException e) {
            System.out.println("Some error occurred!");
        }
        return building;
    }
}
