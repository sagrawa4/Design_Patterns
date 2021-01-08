package textdecorators.driver;

import textdecorators.util.FileDisplayInterface;
import textdecorators.util.StdoutDisplayInterface;
import textdecorators.util.InputDetails;
import textdecorators.AbstractTextDecorator;
import textdecorators.MostFrequentWordDecorator;
import textdecorators.KeywordDecorator;
import textdecorators.SpellCheckDecorator;
import textdecorators.SentenceDecorator;
import textdecorators.util.MyLogger;

import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;


/**
 *  This program implements an algorithm that will read from two input files
 * 	store the common integers in one output file and the missingintegers
 *  of each output file in second output file
 *  @author Shruti Agrawal
 *
 */

class InvalidInputException extends Exception { 
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}

public class Driver 
{
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		/*
		 * This is the main method which takes input files and perform decorators to get output
		 * @param args
		 * @return Nothing
		 * @exception IOException On input error and FileNotFoundException
		 * @see IOException, FileNotFoundException
		 */
		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 5) || (args[0].equals("${input}")) || (args[1].equals("${misspelled}")) || (args[2].equals("${keywords}")) ||
            (args[3].equals("${output}")) || (args[4].equals("${debug}"))) 
		{
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}

		MyLogger.setDebugValue(MyLogger.DebugLevel.NONE);

		try
		{
			String inputFilePath= args[0];
			MyLogger.writeMessage("inputFilePath arg0 - " + args[0], MyLogger.DebugLevel.DRIVER);
			String misspelledFilePath = args[1];
			MyLogger.writeMessage("misspelledFilePath arg1 - " + args[1], MyLogger.DebugLevel.DRIVER);
			String keyWordFilePath = args[2];
			MyLogger.writeMessage("keyWordFile arg2 - " + args[2], MyLogger.DebugLevel.DRIVER);
			String outputFilePath = args[3];
			MyLogger.writeMessage("outputFile arg3 - " + args[3], MyLogger.DebugLevel.DRIVER);
			String debugFilePath = args[4];
            MyLogger.writeMessage("debugFilePath arg4 - " + args[4], MyLogger.DebugLevel.DRIVER);


			if(inputFilePath.equals(misspelledFilePath) || misspelledFilePath.equals(keyWordFilePath) || inputFilePath.equals(keyWordFilePath))
			{
				throw new RuntimeException("Filepath and name cannot be same!"); 
			}
			
			InputDetails inputD = new InputDetails(inputFilePath,outputFilePath,keyWordFilePath,misspelledFilePath);

			AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);
			AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD);
			AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD);
			AbstractTextDecorator mostFreqWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD);

			mostFreqWordDecorator.processInputDetails();

			inputD.writeToFile(outputFilePath);
			inputD.writeToStdOut();
		}
		catch(FileNotFoundException e)
        {
            throw new FileNotFoundException(e.getMessage());
        }
		catch(IOException i)
		{
			throw new IOException("Error while reading lines from file");
		}
	}
}