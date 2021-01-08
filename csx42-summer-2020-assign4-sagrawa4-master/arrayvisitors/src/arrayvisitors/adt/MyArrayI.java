package arrayvisitors.adt;

import arrayvisitors.visitors.Element;

public interface MyArrayI extends Element{
    public void add(int newValue);
    public int get(int index);
    public int length();
   
}