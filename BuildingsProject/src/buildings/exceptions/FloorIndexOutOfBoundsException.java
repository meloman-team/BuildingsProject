
package buildings.exceptions;

public class FloorIndexOutOfBoundsException 
    extends IndexOutOfBoundsException
{
    public FloorIndexOutOfBoundsException()
    {
        super("Ошибка выхода за границы номеров этажей");
    }
}
