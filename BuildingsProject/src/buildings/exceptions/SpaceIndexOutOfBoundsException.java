
package buildings.exceptions;

public class SpaceIndexOutOfBoundsException 
    extends IndexOutOfBoundsException
{
    public SpaceIndexOutOfBoundsException()
    {
        super("Ошибка выхода за границы номеров помещений");
    }
}
