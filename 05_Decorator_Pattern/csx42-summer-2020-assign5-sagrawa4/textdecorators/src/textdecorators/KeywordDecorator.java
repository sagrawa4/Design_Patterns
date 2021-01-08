package textdecorators;

import java.util.ArrayList;
import textdecorators.util.InputDetails;
import java.util.Collections;

    /**
     * This class takes in the input and decorates it if the word matches the Keywords
	 * from the file.
     *
     */
public class KeywordDecorator extends AbstractTextDecorator 
{
    private AbstractTextDecorator next_decorator;
	private InputDetails inputD;

     /**
     * This method is called to decorate the input file if keyword found.
     *
     * @param 
     * @return
     * @throws 
     */
    protected void decorate()
	{
        ArrayList<String> decoratedWords = new ArrayList<String>();
		for (String word : inputD.getInputWords())
        {
			boolean isKeyWord = false;
            for (String keyWord : inputD.getKeyWords())
            {
				String wordWithOutDot = word.replace(".", "");
                if (wordWithOutDot.toLowerCase().equals(keyWord.toLowerCase()))
                {
					isKeyWord = true;
					String newString = "";
					if(word.contains("."))
					{
						newString = "KEYWORD_" + wordWithOutDot + "_KEYWORD.";
					}
					else
					{
						newString = "KEYWORD_" + wordWithOutDot + "_KEYWORD";
					}
					decoratedWords.add(newString);
                    break;
                }
            }
			if(isKeyWord == false)
			{
				decoratedWords.add(word);
			}
        }
		inputD.setInputWords(decoratedWords);
	}

    public String toString()
	{
		return "KeywordDecorator does not support this!";
	}

     /**
     * The constructor takes in input while and also the next_decorator.
     *
     * @param next_decorator
     * @param input
     * @throws 
     */
    public KeywordDecorator(AbstractTextDecorator next_decorator, InputDetails input) 
    {
        this.next_decorator = next_decorator;
		inputD = input;
	}

	@Override
	public void processInputDetails() 
    {
		decorate();
		if (null != this.next_decorator) {
			next_decorator.processInputDetails();
		}
	}

}