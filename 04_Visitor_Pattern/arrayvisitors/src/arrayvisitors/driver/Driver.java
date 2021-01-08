package arrayvisitors.driver;

import arrayvisitors.util.FileProcessor;
import arrayvisitors.visitors.PopulateMyArrayVisitor;
import arrayvisitors.visitors.VisitorI;
import arrayvisitors.visitors.Element;
import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayList;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.visitors.CommonIntsVisitor;
import arrayvisitors.util.Results;
import arrayvisitors.visitors.MissingIntsVisitor;
import arrayvisitors.util.MyLogger;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *  This program implements an algorithm that will read from two input files
 * 	store the common integers in one output file and the missingintegers
 *  of each output file in second output file
 *  @author Shruti Agrawal
 *
 */

public class Driver 
{
    private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		/*
		 * As the build.xml specifies the arguments as input1,input2,commonintsout,missingintsout
		 * debug. in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 5) || (args[0].equals("${input1}")) || (args[1].equals("${input2}")) ||
			(args[2].equals("${commonintsout}")) || (args[3].equals("${missingintsout }")) || (args[4].equals("${debug}"))) 
		{
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		
		MyLogger.setDebugValue(MyLogger.DebugLevel.NONE);

		try
		{
			String inputFilePath1 = args[0];
			MyLogger.writeMessage("inputFilePath1 arg0 - " + args[0], MyLogger.DebugLevel.DRIVER);
			String inputFilePath2 = args[1];
			MyLogger.writeMessage("inputFilePath2 arg1 - " + args[1], MyLogger.DebugLevel.DRIVER);
			String commonIntsFilePath = args[2];
			MyLogger.writeMessage("commonIntsFilePath arg2 - " + args[2], MyLogger.DebugLevel.DRIVER);
			String missingIntsFilePath = args[3];
			MyLogger.writeMessage("missingIntsFilePath arg3 - " + args[3], MyLogger.DebugLevel.DRIVER);
			String debugFilePath = args[4];
            MyLogger.writeMessage("debugFilePath arg4 - " + args[4], MyLogger.DebugLevel.DRIVER);

			Results commonVisitorResult = new Results();
			Results missingVisitorResult = new Results();

			FileProcessor file1 = new FileProcessor(inputFilePath1);
			FileProcessor file2 = new FileProcessor(inputFilePath2);	

			VisitorI populateVisitor = new PopulateMyArrayVisitor(file1);

			MyArrayI array1 = new MyArray();
			array1.accept(populateVisitor);

			// Casting so we do not have to add set FileProcessor method on interface.
			// Alternative would be to create another visitor for file2 as compared
			// to using same vistor but as per requirenment, we only need to create 
			// 3 visitor.
			PopulateMyArrayVisitor casted_populated_visitor = (PopulateMyArrayVisitor)populateVisitor;
			casted_populated_visitor.setFileProcessor(file2);

			MyArrayI array2 = new MyArray();
			array2.accept(populateVisitor);

			MyArrayListI list = new MyArrayList();
			list.add(array1);
			list.add(array2);
			
			//Visitor2 initialized
			CommonIntsVisitor commonIntVisitor = new CommonIntsVisitor(commonVisitorResult);
			list.accept(commonIntVisitor);

			/*enabling System.out for readability purposes*/
			System.out.println("Command Visitor :");
			//MyLogger.writeMessage("CommonIntsVisitor : - ", MyLogger.DebugLevel.DRIVER);

			commonVisitorResult.writeToStdout();
			commonVisitorResult.writeToFile(commonIntsFilePath);

			//Visitor3 initialized
			MissingIntsVisitor missingIntVisitor = new MissingIntsVisitor(missingVisitorResult);
			array1.accept(missingIntVisitor);
			System.out.println("MissingIntsVisitor(Array1):");
			//MyLogger.writeMessage("MissingIntsVisitor(Array1):- ", MyLogger.DebugLevel.DRIVER);
			missingVisitorResult.writeToFile(missingIntsFilePath, "MissingIntsVisitor(Array1):");
			missingVisitorResult.writeToStdout();
			missingVisitorResult.appendToFile(missingIntsFilePath);
			
			missingVisitorResult.clear();
			array2.accept(missingIntVisitor);

			missingVisitorResult.appendToFile(missingIntsFilePath, "MissingIntsVisitor(Array2):");
			System.out.println("MissingIntsVisitor(Array2):");
			//MyLogger.writeMessage("MissingIntsVisitor(Array2):- ", MyLogger.DebugLevel.DRIVER);
			missingVisitorResult.writeToStdout();
			missingVisitorResult.appendToFile(missingIntsFilePath);
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