package buildings.office;

import buildings.interfaces.Space;
import java.io.Serializable;

public class OneLinkedNode implements Serializable, Cloneable{

    private OneLinkedNode nextNode;
    private Space office;

    public OneLinkedNode() {
        nextNode = null;
        office = new Office();
    }

    public OneLinkedNode(Space office) {
        this.office = office;
        this.nextNode = null;
    }

    public OneLinkedNode(OneLinkedNode nextNode, Space office) {
        this.office = office;
        this.nextNode = nextNode;
    }

    public void setOffice(Space office) {
        this.office = office;
    }

    public void setNextNode(OneLinkedNode nextNode) {
        this.nextNode = nextNode;
    }

    public Space getOffice() {
        return office;
    }

    public OneLinkedNode getNextNode() {
        return nextNode;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!object.getClass().getCanonicalName().equals("buildings.office.OneLinkedNode")) {
            return false;
        }
        OneLinkedNode node = (OneLinkedNode) object;
        if (!office.equals(node.getOffice())) {
            return false;
        }
        return true;
    }
    
    @Override
     public Object clone() throws CloneNotSupportedException{
         OneLinkedNode node = (OneLinkedNode) super.clone();
         node.office = (Space) office.clone();
         return node;
     }
}
