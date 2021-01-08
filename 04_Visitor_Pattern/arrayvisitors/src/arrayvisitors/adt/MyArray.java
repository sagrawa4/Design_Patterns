package arrayvisitors.adt;

import arrayvisitors.visitors.VisitorI;

import java.util.Arrays;

public class MyArray implements MyArrayI
{
    private int array[] = new int[10];
    private int totalValues=0;

    public int get(int index)
    {
        if(index >= totalValues)
        {
            throw new RuntimeException("MyArray Index out of bounds!");
        }
        return this.array[index];
    }

    public int length()
    {
        return totalValues;
    }

    public void add(int newValue)
    {
        if(totalValues == array.length)
        {
            extendArray();
        }
        array[totalValues] = newValue;
        totalValues = totalValues + 1;
    }

    public int extendArray()
    {
        int extendArraySize = (int) (1.5 * array.length);
        int tempArray[] = new int[extendArraySize];
        //System.out.println(Arrays.toString(tempArray));
        for(int i=0; i<array.length; i++)
        {
            tempArray[i] = array[i];
        }

        this.array = tempArray;
        return extendArraySize;
    }

    public void accept(VisitorI visitor)
    {
        visitor.visit(this);
    }

    public String toString() 
    {
	    return Arrays.toString(this.array);
    }   
}