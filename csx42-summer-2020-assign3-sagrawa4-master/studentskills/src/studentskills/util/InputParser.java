package studentskills.util;

import studentskills.mytree.StudentRecord;
import studentskills.mytree.ModifyRecord;
import studentskills.util.MyLogger;
import java.util.TreeSet;
import java.util.Set;
import java.util.Iterator;

/* this class reads the input and modify files
* and parses them 
*/
public class InputParser 
{
    // <B_NUMBER>:<FIRST_NAME>,<LAST_NAME>,<GPA>,<MAJOR>,[<SKILL>,[<SKILL>, ...]]
    /*Reads input file and parses it
    * @param String
    * @return StudentRecord
    */
    public StudentRecord readInsertFileLine(String line)
    {
        String firstSplit[] = line.split(":");
        int bNumber = Integer.parseInt(firstSplit[0]);
        if(!(bNumber>999 && bNumber<=9999))
        {
            throw new RuntimeException("Bnumber should be 4 digit positive integer");
        }
        MyLogger.writeMessage("[readInsertFileLine] Bnumber is :" + bNumber, MyLogger.DebugLevel.INPUT_PARSER);

        String str =    firstSplit[1]; 
        String secondSplit[] = str.split(",",-1);
        String firstName = secondSplit[0];
        MyLogger.writeMessage("[readInsertFileLine] FirstName is : " + firstName, MyLogger.DebugLevel.INPUT_PARSER);
             
        String lastName = secondSplit[1];
        MyLogger.writeMessage("[readInsertFileLine] lastName is : " + lastName, MyLogger.DebugLevel.INPUT_PARSER);
            
        Double gpa = Double.parseDouble(secondSplit[2]);
        MyLogger.writeMessage("[readInsertFileLine] gpa is : " + gpa, MyLogger.DebugLevel.INPUT_PARSER);
            
        String major = secondSplit[3];
        MyLogger.writeMessage("[readInsertFileLine] Major is : " + major, MyLogger.DebugLevel.INPUT_PARSER);
     
        Set<String> skills =new TreeSet();
        for(int i= 4; i < secondSplit.length;i++)
        {
            skills.add(secondSplit[i]); 
        }  
         
        if(skills.size() >10)
        {
            throw new RuntimeException("Skills cannot be more than 10");  
        }
        return new StudentRecord(bNumber, firstName, lastName, gpa, major, skills);
        
    }

    // <REPLICA_ID>,<B_NUMBER>,<ORIG_VALUE>:<NEW_VALUE>

    /*Reads modify file and parses it
    * @param String
    * @return StudentRecord
    */
    public ModifyRecord readModifyLine(String line)
    {
        String firstSplit[] = line.split(",",-1);
        if(firstSplit.length != 3)
        {
            return null;
        }
        int replicaNumber = Integer.parseInt(firstSplit[0]);
        MyLogger.writeMessage("[readModifyLine] replicaNumber is : " + replicaNumber, MyLogger.DebugLevel.INPUT_PARSER);
            
        int bNumber = Integer.parseInt(firstSplit[1]);
        MyLogger.writeMessage("[readModifyLine] bNumber is :"  + bNumber, MyLogger.DebugLevel.INPUT_PARSER);
        
        String secondSplit[] = firstSplit[2].split(":");
        if(secondSplit.length != 2)
        {
            return null;
        }
        
        String originalValue = secondSplit[0];
        MyLogger.writeMessage("[readModifyLine] originalValue is :"  + originalValue, MyLogger.DebugLevel.INPUT_PARSER);
        
        String newValue = secondSplit[1];  
        MyLogger.writeMessage("[readModifyLine] newValue is : " + newValue, MyLogger.DebugLevel.INPUT_PARSER);

        return new ModifyRecord(replicaNumber, bNumber, originalValue, newValue);
    }
}