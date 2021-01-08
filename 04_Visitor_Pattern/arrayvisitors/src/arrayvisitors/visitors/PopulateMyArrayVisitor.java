package arrayvisitors.visitors;

import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.FileProcessor;
import java.util.List;

import java.io.IOException;

/*this class implements visitor interface and populates the arrays
*/
public class PopulateMyArrayVisitor implements VisitorI{

    private int totalLines = 0;
    private FileProcessor fileProcessor;

    public PopulateMyArrayVisitor(FileProcessor inputFileProcessor)
    {
        this.fileProcessor = inputFileProcessor;
    }

    public void setFileProcessor(FileProcessor inputFileProcessor2)
    {
        this.fileProcessor = inputFileProcessor2;
        totalLines = 0;
    }

    public void visit(MyArrayI array)
    {
        try
        {
            while(true)
            {
                String line =  this.fileProcessor.poll();
    
                if(line == null && totalLines == 0)
                {
                    //Empty file
                    throw new RuntimeException("File cannot be empty!");
                }
                
                ++totalLines;
                
                if(line == null || line.length() == 0)
                {
                    break;
                }
                
                array.add(Integer.parseInt(line));
            }
        }
        catch(IOException i)
		{
			throw new RuntimeException("Error while reading lines from file");
		}
        catch (NumberFormatException exception) 
        {
            throw new RuntimeException("File does not contains number, invalid format.");
        } 
    }

    public void visit(MyArrayListI arrayList)
    {
        throw new RuntimeException("Populate Visitor is not support on array list!!!");
    }
}