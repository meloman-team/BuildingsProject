package buildings.office;


import buildings.dwelling.*;
import buildings.interfaces.*;
import java.io.*;
//import java.util.Scanner;

public class BuildingsProject_2 {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
//        //проверка этажа
//        Scanner read = new Scanner(System.in);
//        Office office = new Office(500, 3);
//        OfficeFloor floor = new OfficeFloor(5);
//        floor.insertOffice(4, office);
//        //floor.changeOffice(0, office);
//        for (int i = 0; i < floor.countOffice(); i++) 
//        {
//            System.out.println("Area office " +i+ " ="+floor.getOffice(i).getArea());
//        }
//        System.out.println("Area best ="+floor.getBestSpace().getArea());

        //проверка здания
        int[] countOffices = new int[4];
        countOffices[0] = 3;
        countOffices[1] = 2;
        countOffices[2] = 1;
        countOffices[3] = 1;

        Building building = new OfficeBuilding(4, countOffices);//2 этажа в здании
        Office[] officesArrey = new Office[3];
        officesArrey[0] = new Office(1, 1);//area/room
        officesArrey[1] = new Office(600, 2);
        officesArrey[2] = new Office(3, 3);
        OfficeFloor newFloor = new OfficeFloor(officesArrey);

        System.out.println("Area total =" + building.getSpaceQuantity());
        for (int i = 0; i < building.getFloorQuantity(); i++) {
            System.out.println("Floor:" + i);
            for (int j = 0; j < building.getFloor(i).getSpaceQuantity(); j++) {
                double area = building.getFloor(i).getSpace(j).getArea();
                System.out.println("    Office " + j + ":" + area + "area");
            }
        }

        //building.insertOffice(4, officesArrey[1]);
        //building.changeFloor(2, newFloor);
        building.setSpace(0, officesArrey[1]);
        building.removeSpace(0);
        System.out.println("после");
        for (int i = 0; i < building.getFloorQuantity(); i++) {
            System.out.println("Floor:" + i);
            for (int j = 0; j < building.getFloor(i).getSpaceQuantity(); j++) {
                double area = building.getFloor(i).getSpace(j).getArea();
                System.out.println("    Office " + j + ":" + area + "area");
            }
        }
        System.out.println("Area total =" + building.getAreaTotal());
        System.out.println("Area best =" + building.getBestSpace().getArea());

        OfficeFloor floor = new OfficeFloor(5);
        Flat flat = new Flat();
        floor.setSpace(0, flat);

        for (int j = 0; j < floor.getSpaceQuantity(); j++) {
            double area = floor.getSpace(j).getArea();
            System.out.println("    Office " + j + ":" + area + "area");
        }
        
        //Сериализация 
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Ilya\\Desktop\\test.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(building);
        oos.flush();
        oos.close();

        //десериализация 
        ObjectInputStream inn = new ObjectInputStream(new FileInputStream("C:\\Users\\Ilya\\Desktop\\test.out"));
        building = (Building) inn.readObject();
        inn.close();
        System.out.println("Проверка десериализация");
        for (int i = 0; i < building.getFloorQuantity(); i++) {
            System.out.println("Floor:" + i);
            for (int j = 0; j < building.getFloor(i).getSpaceQuantity(); j++) {
                double area = building.getFloor(i).getSpace(j).getArea();
                System.out.println("    Office " + j + ":" + area + "area");
            }
        }
    }
}
