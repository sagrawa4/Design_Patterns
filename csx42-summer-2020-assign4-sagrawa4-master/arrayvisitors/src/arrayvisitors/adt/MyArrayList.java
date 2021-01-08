package arrayvisitors.adt;

import java.util.ArrayList;

import java.util.Arrays;
import arrayvisitors.visitors.VisitorI;

public class MyArrayList implements MyArrayListI
{
    private ArrayList<MyArrayI> arrayList = new ArrayList<MyArrayI>();

    public void add(MyArrayI newArray)
    {
        arrayList.add(newArray);
    }

    public MyArrayI get(int index)
    {
        return arrayList.get(index);
    }

    public void accept(VisitorI visitor)
    {
        visitor.visit(this);
    }

    public String toString()
    {
        return arrayList.toString();
    }
}