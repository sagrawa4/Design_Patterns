package wordPlay.util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import wordPlay.handler.WordRotator;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/* This class implements two interfaces FileDisplayInterface and StdoutDisplayInterface
* and implements the writeToFile() and writeToStdOut methods.
* @author Shruti Agrawal
*/
public class Results implements FileDisplayInterface, StdoutDisplayInterface 
{
    List<String> _data = null;

    public Results()
    {
        _data = new ArrayList<String>();
    }

    /**
     * This is the method which will write the rotated words and calculated metrics into the file.
     * @param filepath to write the processed word and metric
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
        } 
        catch (IOException e) 
        { 
            System.out.println("exception occoured" + e); 
        } 
    }

    /**
     * This is the method which will display the rotated words and calculated metrics into the screen.
     * @return Nothing
     */
    public void writeToStdOut()
    {
        for(int i=0;i< _data.size();i++)
        {
            System.out.print(_data.get(i)); 
        }    
    }

    /**
     * Stores that rotated word that needs to be flushed into the file
     * @param Rotatedword as an input
     * @return Nothing
     */
    public void storeWord(String word)
    {
        if(word.contains("."))
        {
            _data.add(word + "\n");
        }
        else
        {
            _data.add(word + " ");
        }
    }
    /**
     * Stores the calculated metric that needs to be flushed into the file
     * @param Rotatedword as an input
     * @return Nothing
     */
    public void storeMetrics(String metric)
    {
        _data.add(metric);
    }
}
