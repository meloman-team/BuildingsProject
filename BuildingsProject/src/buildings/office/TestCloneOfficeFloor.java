
package buildings.office;

public class TestCloneOfficeFloor {

    public static void main(String[] args) throws CloneNotSupportedException {
        OfficeFloor officeFloor = new OfficeFloor(3);
        OfficeFloor officeFloor2 = new OfficeFloor(3);
        officeFloor2.getSpace(2).setArea(666);
        
//        for (int j = 0; j < officeFloor.getSpaceQuantity(); j++) {
//                double area = officeFloor.getSpace(j).getArea();
//                System.out.println("    Office " + j + ":" + area + "area");
//            }
//        
//        System.out.println("////////");
//        
//        for (int j = 0; j < officeFloor2.getSpaceQuantity(); j++) {
//                double area = officeFloor2.getSpace(j).getArea();
//                System.out.println("    Office " + j + ":" + area + "area");
//            }
//        System.out.println(officeFloor.equals(officeFloor2));
        
        OfficeFloor[] of=new OfficeFloor[2];
        of[0]=officeFloor;
        of[1]=officeFloor2;
        OfficeFloor[] of2=new OfficeFloor[2];
        of2[0]=(OfficeFloor) officeFloor.clone();
        of2[1]=(OfficeFloor) officeFloor2.clone();
        OfficeBuilding officeBuilding = new OfficeBuilding(of);
        //OfficeBuilding officeBuilding2 = new OfficeBuilding(of2);
        OfficeBuilding officeBuilding2 = (OfficeBuilding) officeBuilding.clone();
        officeBuilding2.getFloor(1).getSpace(1).setArea(444);
        for (int i = 0; i < officeBuilding.getFloorQuantity(); i++) {
            System.out.println("Floor:" + i);
            for (int j = 0; j < officeBuilding.getFloor(i).getSpaceQuantity(); j++) {
                double area = officeBuilding.getFloor(i).getSpace(j).getArea();
                System.out.println("    Office " + j + ":" + area + "area");
            }
        }
        System.out.println("////////");
        for (int i = 0; i < officeBuilding2.getFloorQuantity(); i++) {
            System.out.println("Floor:" + i);
            for (int j = 0; j < officeBuilding2.getFloor(i).getSpaceQuantity(); j++) {
                double area = officeBuilding2.getFloor(i).getSpace(j).getArea();
                System.out.println("    Office " + j + ":" + area + "area");
            }
        }
        System.out.println(officeBuilding.equals(officeBuilding2));
        
        System.out.println("////////");
        double n = 2.3;
        //Long z = Double.doubleToLongBits(n);
        int z = (int) (Double.doubleToLongBits(n)>>32);
        //z = z>>32;
        //int x = z.intValue();
        System.out.println(z);
    }
    
}
