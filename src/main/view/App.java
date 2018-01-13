package view;

import java.util.Calendar;
import java.util.Date;

import globals.CalendarInfo;
import globals.Configuration;
import globals.Constants;
import model.OutputGenerator;
import model.RequestList;
import model.RequestPoliceman;
import model.parsers.ParserConfig;
import model.parsers.ParserInternational;
import model.parsers.ParserRequests;

/**
 * Hot dawg fam this looks clean AF
 * 
 * @author Jendoliver
 *
 */
public class App 
{
	public static void main(String[] args) 
	{
		// Parse the configuration file and set Configuration values
		ParserConfig parserConfig = new ParserConfig(Constants.FileNames.CONFIG);
		parserConfig.parse();
		
		System.out.println(Configuration.MONTH_TO_PROCESS);
		/*
		// Parse an international file with INPUT_LANG extension and set InputStrings values
		ParserInternational parserInternational = new ParserInternational(Constants.FileNames.INTERNATIONAL_PRE + Configuration.INPUT_LANG, true);
		parserInternational.parse();
		
		// Setup CalendarInfo
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, Integer.parseInt(Configuration.MONTH_TO_PROCESS));
		cal.set(Calendar.YEAR, Integer.parseInt(Configuration.YEAR_TO_PROCESS));
		CalendarInfo.MONTH_DAY_NUM = cal.getActualMaximum(Calendar.MONTH);
		CalendarInfo.MONTH_FIRST_DAY_OF_WEEK = cal.get(Calendar.DAY_OF_WEEK);
		
		// Parse an international file with OUTPUT_LANG extension and set OutputStrings values
		parserInternational = new ParserInternational(Constants.FileNames.INTERNATIONAL_PRE + Configuration.OUTPUT_LANG, false);
		parserInternational.parse();
		
		// Parse the request files
		ParserRequests parserRequests = new ParserRequests(Constants.FileNames.REQUESTS);
		RequestList requestList = parserRequests.parse();
		
		// Sort the requests by priority
		requestList = requestList.sort();
		
		// Investigate the collisions of the requests and solve them
		RequestList processedRequestList = RequestPoliceman.process(requestList);
		
		// Print the calendar
		OutputGenerator outputGenerator = new OutputGenerator();
		outputGenerator.setStrategy(Configuration.OUTPUT_GENERATOR_STRATEGY);
		outputGenerator.print(processedRequestList);
		*/
	}
}
