package studentskills.driver;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import studentskills.util.FileProcessor;
import studentskills.util.InputParser;
import studentskills.mytree.StudentRecord;
import studentskills.mytree.TreeHelper;
import studentskills.mytree.BST;
import studentskills.mytree.ModifyRecord;
import studentskills.util.Results;
import studentskills.util.MyLogger;

/**
 *  This program implements an algorithm that will read from an input file and insert
 * 	the values as nodes in three tree suc that the two trees are replicas of first tree
 *  where first tree is subject and the other two trees will be its observers.
 * @author Shruti Agrawal
 *
 */
public class Driver
{
    private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 7;

    public static void main(String[] args) throws FileNotFoundException, IOException, CloneNotSupportedException
    {
        /*
		 * As the build.xml specifies the arguments as input,modify,output1,output2,output3,error,debug in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
        
        if((args.length !=7) || (args[0].equals("${input}")) || (args[1].equals("${modify")) || (args[2].equals("${output1}")) || (args[3].equals("${output2}")) || (args[4].equals("${output3}")) || (args[5].equals("${error}")) || (args[6].equals("${debug}")))
        {
            System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
        }
      
        MyLogger.setDebugValue(MyLogger.DebugLevel.NONE);
        InputParser parser = new InputParser();
        TreeHelper tree = new TreeHelper();

        try
        {
            String inputFilePath = args[0];
            MyLogger.writeMessage("inputFilePath arg0 - " + args[0], MyLogger.DebugLevel.DRIVER);
            String modifyFilePath = args[1];
            MyLogger.writeMessage("modifyFilePath arg1 - " + args[1], MyLogger.DebugLevel.DRIVER);
            String outputFilePath1 = args[2];
            MyLogger.writeMessage("outputFilePath1 arg2 - " + args[2], MyLogger.DebugLevel.DRIVER);
            String outputFilePath2 = args[3];
            MyLogger.writeMessage("outputFilePath2 arg3 - " + args[3], MyLogger.DebugLevel.DRIVER);
            String outputFilePath3 = args[4];
            MyLogger.writeMessage("outputFilePath3 arg4 - " + args[4], MyLogger.DebugLevel.DRIVER);
            String errorFilePath = args[5];
            MyLogger.writeMessage("errorFilePath arg5 - " + args[5], MyLogger.DebugLevel.DRIVER);
            String debugFilePath = args[6];
            MyLogger.writeMessage("debugFilePath arg6 - " + args[6], MyLogger.DebugLevel.DRIVER);

            Results errorResult = new Results();
            
            // Insert
            FileProcessor file1 = new FileProcessor(inputFilePath);
            while(true)
            {
                String line = file1.poll();
                if(line == null || line.length() == 0)
				{
                    
					break;
				}
                StudentRecord node =  parser.readInsertFileLine(line);
                tree.insertTree(node);
            }

            // Modify 
            FileProcessor file2 = new FileProcessor(modifyFilePath);
            while(true)
            {
                String line = file2.poll();
                if(line == null || line.length() == 0)
				{
					break;
				}
                ModifyRecord node = parser.readModifyLine(line);
                if(node == null)
                {
                    errorResult.writeToFile(errorFilePath, 
                        "Error while reading modify line, line might not be in correct format");
                    continue;
                }
                tree.modifyTree(node);
            }

            //Display
            Results result1 = new Results();
            Results result2 = new Results();
            Results result3 = new Results();

            //write output to file and Stoutput for PrimaryTree
            result1.writeToFile(outputFilePath1, tree.toStringPrimaryTree());
            result1.writeToStdout(tree.toStringPrimaryTree());

            //Write output to file and Stoutput for replicaTree1
            result2.writeToFile(outputFilePath2, tree.toStringReplicaTree1());
            result2.writeToStdout(tree.toStringReplicaTree1());

            //Write output to file and Stoutput for replicaTree2
            result3.writeToFile(outputFilePath3, tree.toStringReplicaTree2());
            result3.writeToStdout(tree.toStringReplicaTree2());
        }
        catch(FileNotFoundException e)
        {
            throw new FileNotFoundException(e.getMessage());
        }
        catch(SecurityException s)
		{
			throw new SecurityException("Need read permissions to the file");
		}
		catch(IOException i)
		{
			throw new IOException("Error while reading lines from file");
		}
    }
}