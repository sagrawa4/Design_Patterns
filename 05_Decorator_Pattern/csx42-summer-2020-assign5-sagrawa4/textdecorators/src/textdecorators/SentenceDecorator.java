package textdecorators;

import java.util.ArrayList;
import textdecorators.util.InputDetails;

/* This class extends AbstractTextDecorator and prefixes the sentence with BEGIN_SENTENCE__ 
 * and suffixes the sentence with __END_SENTENCE.
*/
public class SentenceDecorator extends AbstractTextDecorator 
{ 	
    private AbstractTextDecorator next_decorator;
	private InputDetails inputD;
	
	/**
     * This method is called to decorate the input file and prefixes the sentence with BEGIN_SENTENCE__ 
 	 * and suffixes the sentence with __END_SENTENCE.
     *
     * @param 
     * @return
     * @throws 
     */
	protected void decorate()
	{
		ArrayList<String> decoratedSentence = new ArrayList<String>();	

		for(int i = 0; i < inputD.getInputWords().size(); ++i)
		{
			String word = inputD.getInputWords().get(i);

			if(i ==0 )
			{
				word = "BEGIN_SENTENCE__" + word;
			}
			
			if(word.contains("."))
			{
				String newWord = word.replace(".", "");
				if(i !=inputD.getInputWords().size() -1)
				{
					newWord = newWord + "__END_SENTENCE.BEGIN_SENTENCE__";
					decoratedSentence.add(newWord);
				}
				else
				{
					newWord = newWord + "__END_SENTENCE.";
					decoratedSentence.add(newWord);
				}
			}
			else
			{
				decoratedSentence.add(word);
			}
		}
		inputD.setInputWords(decoratedSentence);
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
	public SentenceDecorator(AbstractTextDecorator next_decorator, InputDetails input) 
	{
		this.next_decorator = next_decorator;
		this.inputD = input;
	}

	@Override
	public void processInputDetails() 
	{
		decorate();	
		if (null != next_decorator) {
			next_decorator.processInputDetails();
		}
	}
}