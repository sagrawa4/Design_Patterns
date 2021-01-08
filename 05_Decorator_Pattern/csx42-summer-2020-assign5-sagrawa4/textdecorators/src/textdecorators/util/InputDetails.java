package textdecorators.util;

import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

/**
* INPUTDetails reads in the input files wordby word and writes to outputfile and screen
*
* @author Shruti Agrawal
*/

public class InputDetails implements FileDisplayInterface, StdoutDisplayInterface
{
    private int sentenceCount=0;
    
	private ArrayList<String> inputWords = new ArrayList<String>();
    private ArrayList<String> keyWords = new ArrayList<String>();
	private ArrayList<String> misspelledWord = new ArrayList<String>();

	public ArrayList<String> getInputWords()
	{
		return inputWords;
	}
	
	public void setInputWords(ArrayList<String> newList)
	{
		inputWords = newList;
	}

	public ArrayList<String> getKeyWords()
	{
		return keyWords;
	}

	public void setKeyWords(ArrayList<String> newList)
	{
		keyWords = newList;
	}

	public ArrayList<String> getMisspelledWord()
	{
		return misspelledWord;
	}

	public String toString()
	{
		return "Input Details does not support this!";
	}

	public void setMisspelledWord(ArrayList<String> newList)
	{
		misspelledWord = newList;
	}

	private String getOutputResult()
	{
		String output = String.join(" ", inputWords);
		output = output.replaceAll(" \\.", ".");
		return output;
	}

	/**
	* Write the final output to output.txt
	* @param String inputfile
	* @return Nothing
	* @exception Nothing
	*/
	public void writeToFile(String filepath)
    {
        try 
        { 
            File myOutputFile = new File(filepath);      
            FileWriter writer = new FileWriter(myOutputFile);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(getOutputResult());
            bw.close();
        } 
        catch (IOException e) 
        { 
            System.out.println("exception occoured" + e); 
        } 
    }

    /**
     * This is the method which will display final result into the screen.
     * @return Nothing
     */
    public void writeToStdOut()
    {
		System.out.print(getOutputResult());
    }


	/**
	* Takes in inputfile and misspelled file to check if the input file has any 
	* misspelled word.
	* @param String inputFileName,String SpellCheckFileName.
	* @exception InvalidPathException On invalid path string.
	* @exception SecurityException On not having necessary read permissions to the input file.
	* @exception FileNotFoundException On input file not found.
	* @exception IOException On any I/O errors while reading lines from input file.
	*/
	public void spellCheck(String inputFileName,String SpellCheckFileName) throws FileNotFoundException,IOException
    {
		int totalWords =0;
        FileProcessor file3 = new FileProcessor(SpellCheckFileName);
        try
        {
            while(true)
            {
                String misspelled = file3.poll();

				if(misspelled == null && totalWords == 0)
				{
					throw new RuntimeException("Misspelled File cannot be empty!"); 
				}
				++totalWords;

                if(misspelled == null || misspelled.length() == 0)
				{
					break;
				}
                misspelledWord.add(misspelled);
            }
        }
        catch(FileNotFoundException e)
		{
			throw new FileNotFoundException("Missing File at path - " );
		}
		catch(InvalidPathException ex)
		{	
			throw new InvalidPathException(null, "Bad path [" + ex.getInput() + "] at position "+ ex.getIndex());
		}
		catch(SecurityException s)
		{
			throw new SecurityException("Need read permissions to the input file");
		}
		catch(IOException i)
		{
			throw new IOException("Error while reading lines from input file");
		}
        
    }

	/**
	* Takes in inputfile and Keyword file to check if the input file has any 
	* Keyword word.
	* @param String inputFileName,String KeyWordFileName.
	* @exception InvalidPathException On invalid path string.
	* @exception SecurityException On not having necessary read permissions to the input file.
	* @exception FileNotFoundException On input file not found.
	* @exception IOException On any I/O errors while reading lines from input file.
	*/
    public void keyWord(String inputFileName,String KeyWordFileName) throws FileNotFoundException,IOException
    {
		int totalWords =0;

        FileProcessor file2 = new FileProcessor(KeyWordFileName);
        try
        {
            while(true)
            {
                String keyword = file2.poll();

				if(keyword == null && totalWords == 0)
				{
					throw new RuntimeException("Keyword File cannot be empty!"); 
				}
				++totalWords;

                if(keyword == null || keyword.length() == 0)
				{
					break;
				}
                keyWords.add(keyword);
            }
        }
        catch(FileNotFoundException e)
		{
			throw new FileNotFoundException("Missing File at path - " );
		}
		catch(InvalidPathException ex)
		{	
			throw new InvalidPathException(null, "Bad path [" + ex.getInput() + "] at position "+ ex.getIndex());
		}
		catch(SecurityException s)
		{
			throw new SecurityException("Need read permissions to the input file");
		}
		catch(IOException i)
		{
			throw new IOException("Error while reading lines from input file");
		}
        
    }

	/**
	* COnstructor takes in all inputfiles and calles the poll function in fileProcessor to
	* read the words one by one.
	* @param String inputFileName,String OutputFileName, String KeyWordFileName, String SpellCheckFileName.
	* @exception InvalidPathException On invalid path string.
	* @exception SecurityException On not having necessary read permissions to the input file.
	* @exception FileNotFoundException On input file not found.
	* @exception IOException On any I/O errors while reading lines from input file.
	*/
    public InputDetails(String inputFileName, String OutputFileName, String KeyWordFileName, String SpellCheckFileName) throws FileNotFoundException,IOException
    {             
        try
		{
			FileProcessor file1 = new FileProcessor(inputFileName);
            
            keyWord(inputFileName,KeyWordFileName);
			spellCheck(inputFileName,SpellCheckFileName);
			writeToFile(OutputFileName);
			writeToStdOut();

			int totalWords =0;
			while(true)
            {
                String word = file1.poll();
				
				if(word == null && totalWords == 0)
				{
					// empty file
					throw new RuntimeException("File cannot be empty!"); 
				}
				++totalWords;

				// We always spit the dot with word into two different words.
				if(word == null || word.length() == 0)
				{
					break;
				}			

				if(!word.matches("^[a-zA-Z0-9\\.,\\s]*$"))
				{
					throw new RuntimeException("Invalid Char Found!"); 
				}	

				inputWords.add(word);
            }
		}
		catch(FileNotFoundException e)
		{
			throw new FileNotFoundException("Missing File at path - " );
		}
		catch(InvalidPathException ex)
		{	
			throw new InvalidPathException(null, "Bad path [" + ex.getInput() + "] at position "+ ex.getIndex());
		}
		catch(SecurityException s)
		{
			throw new SecurityException("Need read permissions to the input file");
		}
		catch(IOException i)
		{
			throw new IOException("Error while reading lines from input file");
		}
    }
}