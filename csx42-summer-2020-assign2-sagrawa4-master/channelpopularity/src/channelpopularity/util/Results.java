package channelpopularity.util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/* This class implements two interfaces FileDisplayInterface and StdoutDisplayInterface
* and implements the writeToFile() and writeToStdOut methods.
* @author Shruti Agrawal
*/

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	
    List<String> _data = new ArrayList<String>();

    /**
     * @param filepath 
     * @return Nothing
     * @exception IOException On input error.
     * @see IOException
     */
    public void writeToFile(String filepath)
    {
        try
        {
            File myOutputFile = new File(filepath);      
            FileWriter writer = new FileWriter(myOutputFile);
            BufferedWriter bw = new BufferedWriter(writer);

            for(int i=0;i< _data.size();i++)
            {
                bw.write(_data.get(i));
            }
            
            bw.close();
            writer.close();
        } 
        catch (IOException e) 
        { 
            System.out.println("exception occoured" + e); 
        } 
        
    }

    /**
     * This is the method which will display the processed line into the screen.
     * @return Nothing
     */
    public void writeToStdOut()
    {
        for(int i=0;i< _data.size(); i++)
        {
            System.out.print(_data.get(i));
        }
    }

    /**
     * Stores the line that needs to be flushed into the file
     * @param String as an input
     * @return Nothing
     */
    public void storeline(String line)
    {       
        _data.add(line + "\n");
    }

}
