package arrayvisitors.adt;

import arrayvisitors.visitors.Element;
import arrayvisitors.adt.MyArrayI;

public interface MyArrayListI extends Element{
    public void add(MyArrayI newArray);
    public MyArrayI get(int index);
}