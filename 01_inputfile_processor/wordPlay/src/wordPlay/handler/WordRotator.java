package wordPlay.handler;

import wordPlay.util.FileProcessor;
import wordPlay.driver.Driver;
import java.io.FileNotFoundException;
import java.io.IOException;
import wordPlay.handler.WordIndex;
import wordPlay.handler.WordIndexer;
import java.util.*;

/*This class takes the current word and performs rotation based on index.*/


public class WordRotator {

    private String currentWord;
     
    /* Its a gettermethod
    * @Return currentword String*/
    public String getcurrentWord()
    {
        return currentWord;
    }

    /* This method takes the old string and performs one index rotation and stores it to newString variable.
    * @param String
    * @return String
    */
    public String rotateOnce(String word)
    {
        char[] newString = new char[word.length()];
        char[] oldString = word.toCharArray();

        for(int i=0; i< oldString.length -1; i++)
        {
            newString[i+1] = oldString[i];
        }

        newString[0]= oldString[oldString.length - 1];
        String rotatedString = new String(newString);
        
        return rotatedString;
    }

    /* Rotates word by n index
    * @param WordIndex
    * @return String
    */
    public String rotate(WordIndex current)
    {
        currentWord = current.getWord();

        if(currentWord.length() == 0)
            return currentWord;

        for(int i =0 ; i < current.getIndex(); ++i)
        {
            if(currentWord.contains("."))
            {
                currentWord = currentWord.replace(".","");
                currentWord = rotateOnce(currentWord);
                currentWord =currentWord.concat(".");
            }
            else
            {
                currentWord = rotateOnce(currentWord);
            }
        }
        return currentWord;
    }
}