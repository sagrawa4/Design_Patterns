package studentskills.util;
import studentskills.util.FileDisplayInterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* This class implements two interfaces FileDisplayInterface and StdoutDisplayInterface
* and implements the writeToFile() and writeToStdOut methods.
* @author Shruti Agrawal
*/

public class Results implements FileDisplayInterface, StdoutDisplayInterface
{

    /**
     * This is the method which will write the node values  into the file.
     * @param String,String
     * @return Nothing
     * @exception IOException On input error.
     * @see IOException
     */
    public void writeToFile(String fileName, String data)
    {
        try 
        {      
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(data);                       
            bw.close();
        } 
        catch (IOException e) 
        { 
            System.out.println("exception occoured" + e); 
        } 
    }

    /**
     * This is the method which will display the node values into the screen.
     *@param String
     * @return Nothing
     */
    public void writeToStdout(String data)
    {
        System.out.println(data);
    }
}