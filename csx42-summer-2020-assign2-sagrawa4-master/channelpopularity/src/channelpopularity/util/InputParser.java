package channelpopularity.util;

import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import channelpopularity.operation.Operation;
import channelpopularity.util.ParserResult;

/*This class Parses the input file */

public class InputParser {
    
    /* This method takes in the line with operation ADD_VIDEO,REMOVE_VIDEO and splits it further
    * @param String
    * @return ParserResult
    */
    public ParserResult addRemoveVideoLineSplits(String line)
    {
        String secondSplit[] = line.split("::");
        if(secondSplit.length == 2)
        {
            Operation currOperation = Operation.valueOf(secondSplit[0]);
            String videoName = secondSplit[1];
            Metrics currMetrics = new Metrics(0,0,0,0);
            return new ParserResult(currOperation, videoName, currMetrics);
        }
        return null;
    }

    //https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    /*This method takes in the line with operation METRICS__ and splits it further
    * @param String
    * @return ParserResult
    */
    public ParserResult metricsLineSplit(String lineWithoutOperationName)
    {
        String secondSplit[] = lineWithoutOperationName.split("::");
        //System.out.println("secondSplit.length" + secondSplit.length);
        if(secondSplit.length == 2)
        {
            //System.out.println("secondSplit.length" + secondSplit.length);
            Operation currOperation = Operation.METRICS__;
            String videoName = secondSplit[0];
            
            // We have to future split to get value of metrics.
            String thirdSplit[] = secondSplit[1].split(",");
            if(thirdSplit.length == 3)
            {
                String views[] = thirdSplit[0].split("=");
                if(views.length != 2) return null;

                if(views[0].contains("[VIEWS") == false)
                {
                    return null;
                }
                if(!isInteger(views[1]))
                {
                    throw new RuntimeException("Views can only be integers.");
                }

                if(Integer.parseInt(views[1]) < 0)
                {
                    throw new RuntimeException("Views cannot be negative for - " + videoName);
                }
                
                String likes[] = thirdSplit[1].split("=");
                if(likes.length != 2) return null;

                if(likes[0].contains("LIKES") == false)
                {
                    return null;
                }
                if(!isInteger(likes[1]))
                {
                    throw new RuntimeException("Likes can only be integers.");
                }

                String dislikes[] = thirdSplit[2].split("=");
                if(dislikes.length != 2) return null;
    
                if(dislikes[1].contains("]") == false)
                {
                    return null;
                }
                
                if(dislikes[0].contains("DISLIKES") == false)
                {
                    return null;
                }

                dislikes[1] = (dislikes[1].replace("]",""));
                if(!isInteger(dislikes[1]))
                {
                    throw new RuntimeException("Dislikes can only be integers.");
                }

                Metrics currMetrics = new Metrics(Integer.parseInt(views[1]), 
                                                  Integer.parseInt(likes[1]), 
                                                  Integer.parseInt(dislikes[1]),0);
                return new ParserResult(currOperation, videoName, currMetrics);
            }
        }
        return null;
    }

    /*This method takes in the line with operation AD_REQUEST__ and splits it further
    * @param String
    * @return ParserResult
    */
    public ParserResult adRequestLineSplit(String lineWithoutOperationName)
    {
        String secondSplit[] = lineWithoutOperationName.split("::");
        //System.out.println("secondSplit[1]"+ secondSplit[1]);
        if(secondSplit.length == 2)
        {
            Operation currOperation = Operation.AD_REQUEST__;
            String videoName = secondSplit[0];

            String thirdSplit[] = secondSplit[1].split("=");
            if(thirdSplit.length ==2)
            {
                /*String adLength[] = thirdSplit[0].split("=");
                System.out.println("adLength[]"+ adLength[1]);
                if(adLength.length !=2) return null;*/

                 if(!isInteger(thirdSplit[1]))
                {
                    throw new RuntimeException("advertisment Length can only be integers.");
                }

                int adLength = Integer.parseInt(thirdSplit[1]);               

                Metrics currMetrics = new Metrics(0,0,0,adLength);
                return new ParserResult(currOperation, videoName, currMetrics);
            }
           
        }
        return null;
    }
    
    /*This method takes in the line and using split method splits it in operation,videoname,metrics.
    * @param String
    * @return ParserResult
    */
    public ParserResult getMyOperation(String line)
    {
        // We split the line first by __
        String firstSplit[] = line.split("__");
        if(firstSplit.length == 1)
        {
            // operation is add video or remove video
            return addRemoveVideoLineSplits(firstSplit[0]);
        }
        else if(firstSplit.length == 2)
        {
            // operation is metrics details and add request
            if(firstSplit[0].contains("METRICS"))
            {
                
                return metricsLineSplit(firstSplit[1]);
            }
            else if(firstSplit[0].contains("AD_REQUEST"))
            {
                return adRequestLineSplit(firstSplit[1]);
            }
        }
        return null;
    }
}

