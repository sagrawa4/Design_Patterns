package textdecorators;

import java.util.HashMap;
import java.util.ArrayList;
import textdecorators.util.InputDetails;
import java.util.Collections;

/* This class extends AbstractTextDecorator and finds the most frequent word in the input file 
/* and decorates it.
*/
public class MostFrequentWordDecorator extends AbstractTextDecorator 
{    
	private AbstractTextDecorator next_decorator;
	private InputDetails inputD;
	private HashMap<String, Integer> wordsFrequency= new HashMap<String, Integer>();

	/**
     * This method is called to decorate the input file for the most frequent word.
     *
     * @param 
     * @return
     * @throws 
     */
	protected void decorate()
	{	
		//https://www.geeksforgeeks.org/count-occurrences-elements-list-java/
		for(String word : inputD.getInputWords())
		{
			Integer count = wordsFrequency.get(word.toLowerCase());
			wordsFrequency.put(word.toLowerCase(), (count == null) ? 1 : ++count);
		}

		int maxCount = 0;
		String mostFrequentWord= "";

		for(HashMap.Entry<String, Integer> currentEntry : wordsFrequency.entrySet())
		{
			if(currentEntry.getValue() > maxCount)
			{
				maxCount = currentEntry.getValue();
				mostFrequentWord = currentEntry.getKey();
			}
		}

		ArrayList<String> decoratedWords = new ArrayList<String>();
		for(String word : inputD.getInputWords())
		{
			if(word.toLowerCase().equals(mostFrequentWord.toLowerCase()))
			{
				String newString  =  "MOST_FREQUENT_" + word + "_MOST_FREQUENT" ;
				decoratedWords.add(newString);
			}
			else
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

	public MostFrequentWordDecorator(AbstractTextDecorator next_decorator, InputDetails input) 
	{
		this.next_decorator = next_decorator;
		inputD = input;
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