package model.parsers;

/**
 * The class responsible for parsing the international files and setting the globals.InputStrings and globals.OutputStrings values
 * 
 * @author Jendoliver
 * @author Magner
 *
 */
public class ParserInternational extends Parser 
{
	private boolean isInput;
	
	public ParserInternational(String fileToParse, boolean isInput) {
		this.fileToParse = fileToParse;
	}

	public void parse() 
	{
		if(isInput)
		{
			// TODO parse fileToParse and fill InputStrings (003)
		}
		
		else
		{
			// TODO parse fileToParse and fill OutputStrings(001, 002, 004, 005)
		}
	}
}
