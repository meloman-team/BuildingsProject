
package buildings;

import buildings.office.Office;
import buildings.office.OneLinkedNode;

public class NewMain1 {

    public static void main(String[] args) throws CloneNotSupportedException {
       OneLinkedNode node = new OneLinkedNode();
       OneLinkedNode node2 = new OneLinkedNode(node, new Office());
       
       System.out.println(node2.getOffice().getArea());
       System.out.println(node2.getNextNode().getOffice().getArea());
       
       OneLinkedNode node3 = (OneLinkedNode) node2.clone();
       
        System.out.println(node3.getOffice().getArea());
       System.out.println(node3.getNextNode().getOffice().getArea());
    }
    
}
