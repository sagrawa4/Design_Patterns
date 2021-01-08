package arrayvisitors.visitors;

import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.util.Results;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Arrays;

/*this class implenets visitor interface and finds the missing integers in 
* both arrays
*/
public class MissingIntsVisitor implements VisitorI{
    private Results results;

    public MissingIntsVisitor(Results results)
    {
        this.results = results;
    }

    public void visit(MyArrayI array)
    {
        HashSet<Integer> set = new HashSet<>();

        for (int i = 00; i <= 99; i++) 
        {
            set.add(i);
        }

        for (int i = 0; i < array.length(); i++) 
        {
            set.remove(array.get(i));
        }

        for (int x : set) 
        {
            this.results.add(x);
        }
    } 
    
    public void visit(MyArrayListI arrayList)
    {
        throw new RuntimeException("Missing Visitor does not support arrayList!!!");
    }
}