
package buildings.interfaces;

public interface Space{
    public int getQuantityRoom();
    public void setQuantityRoom(int quantityRoom);
    public double getArea();
    public void setArea(double area);
    public Object clone()throws CloneNotSupportedException;
}
