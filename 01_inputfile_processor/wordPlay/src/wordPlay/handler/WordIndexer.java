package wordPlay.handler;

import wordPlay.util.FileProcessor;
import wordPlay.driver.Driver;
import java.io.FileNotFoundException;
import java.io.IOException;
import wordPlay.handler.WordIndex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*This class process the file as one word at a time and creates index of coreesponding word basis certain conditions.
 * @param object of fileprocessor.
 * @Returns Word and Index.
 */

public class WordIndexer
{
    private int index = 0;
    private int indexToReturn;
    private String myWord;

    /*The method process the file as one word at a time and creates index of coreesponding word basis certain conditions.
     *@return Word and Index
     */
 
    public WordIndex next(FileProcessor file) throws FileNotFoundException,IOException
    {
        myWord = file.poll();
        if(myWord != null)
        {
            indexToReturn = ++index;
            if(myWord.contains("."))
            {           
                index = 0;
            }
        }
        else
        {
            index = -1;
        }
        return new WordIndex(indexToReturn, myWord);      
    }
}

