package model;

import java.util.Map;

import globals.CalendarInfo;
import globals.OutputStrings;

/**
 * The OutputGeneratorStrategy for transforming a Map<String, RoomSchedule> into an HTML calendar.
 * 
 * IMPORTANT: This class doesn't do any kind of checking over the RequestList as it expects it
 * to have all its possible issues already addressed by model.RequestPoliceman. Thus, it only iterates over
 * the List generating the HTML according to its requests.
 * 
 * @author Jendoliver
 *
 */
public class OutputGeneratorStrategyHTML implements OutputGeneratorStrategy 
{
	private StringBuilder output = new StringBuilder();
	
	@Override
	public void print(Map<String, RoomSchedule> roomSchedules) 
	{
		output.append("<table>");
		printHead();
		printTableHeader();
		for(int i = 0; i < CalendarInfo.MONTH_DAY_NUM; i++)
		{
			output.append("<tr>");
		}
	}
	
	private void printHead()
	{
		
	}

	private void printTableHeader() 
	{
		output.append("<thead><th>")
			.append(OutputStrings.Days.SUNDAY).append("</th><th>")
			.append(OutputStrings.Days.MONDAY).append("</th><th>")
			.append(OutputStrings.Days.TUESDAY).append("</th><th>")
			.append(OutputStrings.Days.WEDNESDAY).append("</th><th>")
			.append(OutputStrings.Days.THURSDAY).append("</th><th>")
			.append(OutputStrings.Days.FRIDAY).append("</th><th>")
			.append(OutputStrings.Days.SATURDAY).append("</th></thead>");
	}
	
<<<<<<< HEAD
	// TODO make moar beatiful
	private void printNonMonthDays(int num)
	{/*
=======
	// TODO implement
	/*private void printNonMonthDays(int num)
	{
>>>>>>> 1b84ea39738da281f7d340155f5717363e18bf95
		for(int i = 0; i < num; i++) {
			output.append("<td style='background-color: black;'/>)
		}
		
		// FIXME PLIS
	}
	
	private void printToFile()
	{
		
	}*/

}
