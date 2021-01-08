package arrayvisitors.visitors;

import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.FileProcessor;


public interface VisitorI{
    public void visit(MyArrayI array);
    public void visit(MyArrayListI arrayList);
}