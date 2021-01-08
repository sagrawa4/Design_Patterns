package textdecorators;

import java.util.ArrayList;
import textdecorators.util.InputDetails;
import java.util.Collections;

public class SpellCheckDecorator extends AbstractTextDecorator {

    private AbstractTextDecorator next_decorator;
	private InputDetails inputD;


	/**
     * This method is called to decorate the input file if misspelled word found.
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
			boolean isMispelled = false;
            for (String misspelledword : inputD.getMisspelledWord())
            {
				String wordWithOutDot = word.replace(".", "");
                if (wordWithOutDot.toLowerCase().equals(misspelledword.toLowerCase()))
                {
					isMispelled = true;
					String newString = "";
					if(word.contains("."))
					{
						newString = "SPELLCHECK_" + wordWithOutDot + "_SPELLCHECK.";
					}
					else
					{
						newString = "SPELLCHECK_" + wordWithOutDot + "_SPELLCHECK";
					}
					decoratedWords.add(newString);
                    break;
                }
            }
			if(isMispelled == false)
			{
				decoratedWords.add(word);
			}
        }
		inputD.setInputWords(decoratedWords);
	}

	public String toString()
	{
		return "MostFrequentWordDecorator does not support this!";
	}

	/**
     * The constructor takes in input while and also the next_decorator.
     *
     * @param next_decorator
     * @param input
     * @throws 
     */	
    public SpellCheckDecorator(AbstractTextDecorator next_decorator, InputDetails input) 
	{
        this.next_decorator = next_decorator;
		this.inputD = input;
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