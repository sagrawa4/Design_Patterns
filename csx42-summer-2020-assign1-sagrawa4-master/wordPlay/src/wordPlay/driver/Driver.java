package wordPlay.driver;

/**
 * This program implements an algorithm that will read from an input file and perform
 * rotation on words one at a time basis the index.Once done, calculates the avg number
 * of words per sentence and avg word length.
 * 
 * @author Shruti Agrawal
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wordPlay.util.FileProcessor;
import wordPlay.handler.WordIndex;
import wordPlay.handler.WordIndexer;
import wordPlay.handler.WordRotator;
import wordPlay.metrics.MetricsCalculator;
import wordPlay.util.Results;

class InvalidInputException extends Exception { 
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}


public class Driver {
	/* The main method helps to create instances  of Results class,File Processor
	* various handler classes
	* @param args
	* @return void
	* @exception Exception FileNotFoundException On missing input file
	*/
	public static void main(String[] args) 
		throws IOException, FileNotFoundException, InvalidInputException, FileNotFoundException {

	
		if ((args.length != 3) || (args[0].equals("${input}")) || (args[1].equals("${output}")) || (args[2].equals("${metrics}"))) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			System.exit(0);
		}
		
		String inputFilePath = args[0];
		String outputFilePath = args[1];
		String metricsFilePath = args[2];
			
		FileProcessor fileReader = new FileProcessor(inputFilePath);
		WordIndexer indexer = new WordIndexer();
		WordRotator rotater = new WordRotator();
		MetricsCalculator calculator = new MetricsCalculator();

		Results rotateResult = new Results();
		Results metricResult = new Results();
						
		while(true)
		{
			// Index current word
			WordIndex current = indexer.next(fileReader);

			// End of file, break
			if(current.getWord() == null) break;
				
			// Check if word contains special char, if found throw.
			// https://stackoverflow.com/questions/1795402/check-if-a-string-contains-a-special-character
			Pattern p = Pattern.compile("[^A-Za-z0-9.]");
			Matcher m = p.matcher(current.getWord());
			boolean foundSpecialChar = m.find();		
			if(foundSpecialChar)
			{
				throw new InvalidInputException("Found Special Char in word -" + current.getWord());
			}

			// Rotate word and store in result
			String rotatedWord = rotater.rotate(current);	
			rotateResult.storeWord(rotatedWord);

			// Increment metrics
			calculator.increment(current);		
		}

		// Store calculated metrics in result.
		metricResult.storeMetrics(calculator.getCalculatedMetrics());

		// Write output to file.
		rotateResult.writeToFile(outputFilePath);
		metricResult.writeToFile(metricsFilePath);

		// Write to Screen 
		rotateResult.writeToStdOut();
		metricResult.writeToStdOut();
	}
}

