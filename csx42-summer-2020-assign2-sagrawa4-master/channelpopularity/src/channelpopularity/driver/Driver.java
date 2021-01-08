package channelpopularity.driver;
import channelpopularity.context.ChannelContext;
import channelpopularity.util.FileProcessor;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.operation.Operation;
import channelpopularity.state.AbstractState;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.util.InputParser;
import channelpopularity.util.ParserResult;
import java.util.Arrays;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import channelpopularity.state.Unpopular;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import channelpopularity.util.Results;

/**
 *  This program implements an algorithm that will read from an input file and perform
 * 	operations like adding,removing and calculating popularity score of a channel basis
 *  the state a video is in.
 * @author Shruti Agrawal
 *
 */

class InvalidInputException extends Exception { 
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}

public class Driver 
{
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) 
		{
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		
		try
		{
			String inputFilePath = args[0];
			String outputFilePath = args[1];
			SimpleStateFactoryI factory= new SimpleStateFactory();

			List<StateName> states = new ArrayList<StateName>();
			states.add(StateName.UNPOPULAR);
			states.add(StateName.MILDLY_POPULAR);
			states.add(StateName.HIGHLY_POPULAR);
			states.add(StateName.ULTRA_POPULAR);

			ChannelContext Context = new ChannelContext(factory, states);
			FileProcessor file = new FileProcessor(inputFilePath);
			Results result = new Results();

			InputParser parser = new InputParser();
			int totalLines = 0;	
			
			while(true)
			{
				String line = file.poll();
				if(line == null && totalLines == 0)
				{
					// empty file
					throw new RuntimeException("File cannot be empty!"); 
				}
				++totalLines;

				if(line == null || line.length() == 0)
				{
					break;
				}
				ParserResult parsedResult = parser.getMyOperation(line);

				if(parsedResult == null)
				{
					throw new RuntimeException("Error while parsing file"); 
				}

				String videoName = parsedResult.getVideoName();
				StateI currentState = Context.getCurrentState();
				String resultLine = null;

				if(parsedResult.operationName.equals(Operation.ADD_VIDEO))
				{
					currentState.AddVideo(parsedResult.videoName);
					resultLine = currentState.GetStateName() +  "__VIDEO_ADDED::" + videoName;
					Context.ChangeState();
				}
				else if(parsedResult.operationName.equals(Operation.METRICS__))
				{	
					currentState.MetricsOfVideo(parsedResult.videoName, parsedResult.metrics);
					resultLine = currentState.GetStateName() +  "__POPULARITY_SCORE_UPDATE::" + currentState.PopularityScoreOfChannel();
					Context.ChangeState();
				}
				else if(parsedResult.operationName.equals(Operation.AD_REQUEST__))
				{
					boolean approved = currentState.AdvertisementRequest(parsedResult.videoName,
													parsedResult.metrics.getAdLength());
					
					resultLine = currentState.GetStateName() +  "__AD_REQUEST::";
					if(approved)
					{
						resultLine += "APPROVED";
					}
					else
					{
						resultLine += "REJECTED";
					}
					Context.ChangeState();
				}
				else if(parsedResult.operationName.equals(Operation.REMOVE_VIDEO))
				{
					currentState.RemoveVideo(parsedResult.videoName);
					resultLine = currentState.GetStateName() +  "__VIDEO_REMOVED::" + videoName;
					Context.ChangeState();
				}
				result.storeline(resultLine);
			}

			// Write output to file.
			result.writeToFile(outputFilePath);

			// Write to Screen 
			result.writeToStdOut();
			file.close();
		}
		catch(FileNotFoundException e)
		{
			throw new FileNotFoundException("Missing File at path - "  + args[0]);
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
