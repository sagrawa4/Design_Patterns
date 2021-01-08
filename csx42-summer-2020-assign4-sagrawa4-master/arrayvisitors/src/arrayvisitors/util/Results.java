package arrayvisitors.util;

import arrayvisitors.util.FileDisplayInterface;
import arrayvisitors.util.StdoutDisplayInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

/* This class implements two interfaces FileDisplayInterface and StdoutDisplayInterface
* and implements the writeToFile() and writeToStdOut methods.
* @author Shruti Agrawal
*/
public class Results implements FileDisplayInterface, StdoutDisplayInterface
{
    private ArrayList<Integer> result = new ArrayList<Integer>();

    public void add(int value)
    {
        result.add(value);
    }

    public void clear()
    {
        result.clear();
    }

    /**
     * This is the method which will write common ints and missing ints into the respective o/p file.
     * @param fileName to write the processed word and metric
     * @return Nothing
     * @exception IOException On input error.
     * @see IOException
     */
    public void writeToFile(String fileName)
    {
        try 
        {
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(writer);
            for(Integer i : result)
            {
               bw.write(String.format("%02d", i));
               bw.write("\n");
            }
            
            bw.close();
        } 
        catch (IOException e) 
        { 
            System.out.println("exception occoured" + e); 
        } 
    }

    /**
     * This is the method which will write common ints and missing ints into the respective o/p file.
     * @param fileName,data
     * @return Nothing
     */
    public void writeToFile(String fileName, String data)
    {
        try 
        {
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(writer);
           
            bw.write(data);
            bw.write("\n");
            
            bw.close();
        } 
        catch (IOException e) 
        { 
            System.out.println("exception occoured" + e); 
        } 
    }

    public void appendToFile(String fileName)
    {
        try 
        {
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(writer);
            for(Integer i : result)
            {
               bw.write(String.format("%02d", i));
               bw.write("\n");
            }
            
            bw.close();
        } 
        catch (IOException e) 
        { 
            System.out.println("exception occoured" + e); 
        } 
    }

    public void appendToFile(String fileName, String data)
    {
        try 
        {
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(writer);
            
            bw.write(data);
            bw.write("\n");

            bw.close();
        } 
        catch (IOException e) 
        { 
            System.out.println("exception occoured" + e); 
        } 
    }

    
    /**
     * This is the method which will display common ints and missing ints into the screen.
     * @return Nothing
     */
    public void writeToStdout()
    {
        for(Integer i : result)
        {
            System.out.println(String.format("%02d", i));
        }
    }    
}