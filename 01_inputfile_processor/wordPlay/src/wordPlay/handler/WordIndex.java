package wordPlay.handler;

/* This class stores the information about the word and its index.*/


public class WordIndex
{
    // private 
    private int _index;
    private String _word;
    
    public WordIndex(int i, String w)
    {
         _index = i;
         _word = w;
    }

    public int getIndex()
    {
        return _index;
    }

    void setIndex(int x)
    {
        _index = x;
    }

    public String getWord()
    {
        return _word;
    }

    void setWord(String y)
    {
        _word = y;
    }

    
}