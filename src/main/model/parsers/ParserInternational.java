package model.parsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import globals.InputStrings;
import globals.OutputStrings;

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
		this.isInput = isInput;
	}

	public void parse() 
	{
		BufferedReader br = null;
		String[] parameters;
		if(isInput) // Parse fileToParse and fill InputStrings (003,005)
		{
			try 
			{
				br = new BufferedReader(new FileReader(fileToParse));
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null) 
				{
					String[] configEntry = sCurrentLine.split(";");
					if(configEntry[0].equalsIgnoreCase("003")) //003 -> Days code 
					{
		                parameters = configEntry[1].split(",");
		                InputStrings.MONDAY = parameters[0];
		                InputStrings.TUESDAY = parameters[1]; 
		                InputStrings.WEDNESDAY = parameters[2]; 
		                InputStrings.THURSDAY = parameters[3]; 
		                InputStrings.FRIDAY = parameters[4]; 
		                InputStrings.SATURDAY = parameters[5]; 
		                InputStrings.SUNDAY = parameters[6]; 
					}
					else if(configEntry[0].equalsIgnoreCase("005")) //005 -> Close code
					{
						InputStrings.CLOSE_KEY = configEntry[1];
					}
	            }
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		else // Parse fileToParse and fill OutputStrings(001, 002, 004)
		{
			try 
			{
				br = new BufferedReader(new FileReader(fileToParse));
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null) 
				{
					String[] configEntry = sCurrentLine.split(";");
					if(configEntry[0].equalsIgnoreCase("001")) // 001 -> Title Code
					{
		                OutputStrings.TITLE = configEntry[1];
					}
					else if(configEntry[0].equalsIgnoreCase("002")) // 002 -> Days (full) code
					{
						parameters = configEntry[1].split(",");
						OutputStrings.Days.MONDAY=parameters[0];
						OutputStrings.Days.TUESDAY=parameters[1]; 
						OutputStrings.Days.WEDNESDAY=parameters[2]; 
						OutputStrings.Days.THURSDAY=parameters[3]; 
						OutputStrings.Days.FRIDAY=parameters[4]; 
						OutputStrings.Days.SATURDAY=parameters[5]; 
						OutputStrings.Days.SUNDAY=parameters[6]; 
					}
					else if(configEntry[0].equalsIgnoreCase("004")) // 004 -> Month code
					{
						parameters = configEntry[1].split(",");
						OutputStrings.Months.JANUARY=parameters[0];
						OutputStrings.Months.FEBRUARY=parameters[1];
						OutputStrings.Months.MARCH=parameters[2];
						OutputStrings.Months.APRIL=parameters[3];
						OutputStrings.Months.MAY=parameters[4];
						OutputStrings.Months.JUNE=parameters[5];
						OutputStrings.Months.JULY=parameters[6];
						OutputStrings.Months.AUGUST=parameters[7];
						OutputStrings.Months.SEPTEMBER=parameters[8];
						OutputStrings.Months.OCTOBER=parameters[9];
						OutputStrings.Months.NOVEMBER=parameters[10];
						OutputStrings.Months.DECEMBER=parameters[11];
					}
	            }
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}