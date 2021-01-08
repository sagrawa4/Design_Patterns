package arrayvisitors.visitors;

import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.util.Results;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

/*this class implenets visitor interface and finds the common integers in 
* both array objects
*/
public class CommonIntsVisitor implements VisitorI{
    private Results results;

    
    public CommonIntsVisitor(Results results)
    {
        this.results = results;
    }

    public void visit(MyArrayI array)
    {
        throw new RuntimeException("CommonInts Visitor does not support array!!!");
    }

    public void visit(MyArrayListI arrayList)
    {
        MyArrayI arrayOne = arrayList.get(0);
        MyArrayI arrayTwo = arrayList.get(1);

        List<Integer> listWithDuplicates = new ArrayList<Integer>();

        for(int i = 0 ; i < arrayOne.length(); ++i)
        {
            for(int j = 0; j < arrayTwo.length(); j++)
            {
                if(arrayOne.get(i) == arrayTwo.get(j))
                {
                    listWithDuplicates.add(arrayOne.get(i));
                    break;
                }
            }
        }

        List<Integer> listWithoutDuplicates = new ArrayList<>(new HashSet<>(listWithDuplicates));
        for(Integer a : listWithoutDuplicates)
        {
            this.results.add(a);
        }
    }
}