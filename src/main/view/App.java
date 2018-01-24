package view;

import java.util.Calendar;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;

import globals.CalendarInfo;
import globals.Configuration;
import globals.Constants.FileNames;
import model.OutputGenerator;
import model.RequestList;
import model.RequestPoliceman;
import model.RoomSchedule;
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
		BasicConfigurator.configure();
				
		// Benchmark init
		long runTimerInit = System.currentTimeMillis();
		
		// Parse the configuration file and set Configuration values
		ParserConfig parserConfig = new ParserConfig(FileNames.CONFIG);
		parserConfig.parse();
		
		// Parse an international file with INPUT_LANG extension and set InputStrings values
		ParserInternational parserInternational = new ParserInternational((FileNames.INTERNATIONAL_PRE.getName() + Configuration.INPUT_LANG), true);
		parserInternational.parse();
		
		// Setup CalendarInfo
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, Integer.parseInt(Configuration.MONTH_TO_PROCESS) - 1);
		cal.set(Calendar.YEAR, Integer.parseInt(Configuration.YEAR_TO_PROCESS));
		CalendarInfo.MONTH_DAY_NUM = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		CalendarInfo.MONTH_WEEKS_NUMBER = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
		CalendarInfo.MONTH_FIRST_DAY_OF_WEEK = cal.get(Calendar.DAY_OF_WEEK);
		CalendarInfo.MONTH_FIRST_WEEK_NUMBER = cal.get(Calendar.WEEK_OF_YEAR);
		cal.set(Calendar.DAY_OF_MONTH, CalendarInfo.MONTH_DAY_NUM);
		CalendarInfo.MONTH_LAST_DAY_OF_WEEK = cal.get(Calendar.DAY_OF_WEEK);
		
		// Parse an international file with OUTPUT_LANG extension and set OutputStrings values
		parserInternational = new ParserInternational(FileNames.INTERNATIONAL_PRE.getName() + Configuration.OUTPUT_LANG, false);
		parserInternational.parse();
		
		// Parse the request files
		ParserRequests parserRequests = new ParserRequests(FileNames.REQUESTS);
		RequestList requestList = parserRequests.parse();
		
		// Sort the requests by priority
		requestList = requestList.sort();
		
		// Investigate the collisions of the requests and solve them
		RequestPoliceman requestPoliceman = new RequestPoliceman();
		requestPoliceman.process(requestList);
		Map<String, RoomSchedule> roomSchedules = requestPoliceman.getRoomSchedules();
		
		// Print the calendar
		OutputGenerator outputGenerator = new OutputGenerator();
		outputGenerator.setStrategy(Configuration.OUTPUT_GENERATOR_STRATEGY);
		outputGenerator.print(roomSchedules);
		
		// Benchmark print
		System.out.println("\n\n" + requestList.size() + " well-formatted request/s (out of " +parserRequests.getLoadedRequestsNumber()+ ") from " 
								+ roomSchedules.keySet().size() + " different schedule/s processed in " + (System.currentTimeMillis() - runTimerInit) + "ms");
	}
}
