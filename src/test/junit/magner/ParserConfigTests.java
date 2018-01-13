package junit.magner;

import static org.junit.Assert.*;

import org.junit.Test;

import globals.Configuration;
import globals.Constants;
import model.parsers.ParserConfig;

public class ParserConfigTests {

	@Test
	public void parseTest() 
	{
		ParserConfig parserConfig = new ParserConfig(Constants.FileNames.CONFIG);
		parserConfig.parse();
		
		assertEquals("2018", Configuration.YEAR_TO_PROCESS);
		assertEquals("01", Configuration.MONTH_TO_PROCESS);
		assertEquals("ESP", Configuration.INPUT_LANG);
		assertEquals("ENG", Configuration.OUTPUT_LANG);
	}

}
