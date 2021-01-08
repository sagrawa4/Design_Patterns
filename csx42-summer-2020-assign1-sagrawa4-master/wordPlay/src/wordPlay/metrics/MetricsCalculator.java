package wordPlay.metrics;
import wordPlay.handler.WordIndex;
import wordPlay.handler.WordIndexer;
import java.lang.*;


public class MetricsCalculator {
    private int _totalwords = 0;
    private int _totalsentence = 0;
    private int _totalcharacter = 0;
   
   /*calculated totalwords,totalsentences and totalcharachters 
   * @param WordIndex
   * @return void
   */
    public void increment(WordIndex current)
    {
        if(current.getWord().length() == 0)
        {
            return;
        }

        // Increment word always.
        ++_totalwords;

        // Reached end of sentence.
        if(current.getWord().contains("."))
        {
            // Increment sentence counter
            ++_totalsentence;

            // Ignore . in word char calculation
            _totalcharacter += current.getWord().length() - 1;  
        }
        else
        {
            // Increment to len of word
            _totalcharacter += current.getWord().length();  
        }        
    }

    /*This method calculates the avg_words_per_sentence and avg_ word_length
    * @return String
    */
    public String getCalculatedMetrics()
    {
        String final_output = "";
        
        double words_per_sentence = Math.round((_totalwords*1.0/_totalsentence)*100)/100.00 ;
        String words_per_sentence_str = Double.toString(words_per_sentence);

        final_output += "AVG_NUM_WORDS_PER_SENTENCE - " + words_per_sentence_str + "\n";

        double word_length = Math.round((_totalcharacter*1.0/_totalwords)*100)/100.00 ;
        String word_length_str = Double.toString(word_length);

        final_output += "AVG_WORD_LENGTH - " + word_length_str;

        return final_output;
    }
}