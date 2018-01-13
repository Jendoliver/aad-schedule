package model.parsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import globals.Configuration;

/**
 * The class responsible for parsing the configuration file and setting the globals.Configuration values.
 * 
 * @author Jendoliver
 * @author Magner
 *
 */
public class ParserConfig extends Parser 
{
	public ParserConfig(String fileToParse) {
		this.fileToParse = fileToParse;
	}

	public void parse()  // Parse fileToParse and fill Configuration
	{
		String[] configEntry;
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(fileToParse));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) 
			{
				configEntry = sCurrentLine.split(";");
				switch(configEntry[0]) 
				{
					case "001": //001 -> Year code
						Configuration.YEAR_TO_PROCESS = configEntry[1];
						break;
					case "002": //002 -> Month code
						Configuration.MONTH_TO_PROCESS = configEntry[1];
						break;
					case "003": //003 -> Input code
						Configuration.INPUT_LANG = configEntry[1];
						break;
					case "004": //004 -> Output code
						Configuration.OUTPUT_LANG = configEntry[1];
						break;
					default:
						break;
				}
				br.close();
            }
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
