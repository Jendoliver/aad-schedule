package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Map;

import globals.CalendarInfo;
import globals.Configuration;
import globals.InputStrings;
import globals.OutputStrings;
import utils.HTMLUtils;

/**
 * The OutputGeneratorStrategy for transforming a Map<String, RoomSchedule> into an HTML calendar.
 * 
 * IMPORTANT: This class doesn't do any kind of checking over the RequestList as it expects it
 * to have all its possible issues already addressed by model.RequestPoliceman. Thus, it only iterates over
 * the RoomSchedules generating the HTML according to its requests.
 * 
 * @author Jendoliver
 *
 */
public class OutputGeneratorStrategyHTML implements OutputGeneratorStrategy 
{
	private StringBuilder fullFileOutput = new StringBuilder();
	private int firstDayOfWeek = 0;
	private RoomSchedule currentRoomSchedule = null;
	
	@Override
	public void print(Map<String, RoomSchedule> roomSchedules) 
	{
		for(String room : roomSchedules.keySet())
		{
			printSchedule(room, roomSchedules.get(room));
			writeToFile(room.concat(".html"), fullFileOutput.toString());
			fullFileOutput = new StringBuilder();
		}
	}
	
	/**
	 * Processes the schedule of a room and fills
	 * fullFileOutput to write it in a file
	 * 
	 * @param schedule The schedule to process
	 */
	private void printSchedule(String roomName, RoomSchedule schedule)
	{	
		// Assign working schedule to be able to work on it through the different methods
		currentRoomSchedule = schedule;
		
		// Add HTML info, bootstrap head and custom style
		fullFileOutput.append("<!DOCTYPE html>\r\n<html lang='en'>");
		fullFileOutput.append(HTMLUtils.BOOTSTRAP_HEAD); // FIXME this prints the title independently from the language, check OutputStrings.TITLE
		fullFileOutput.append("<body>");
		fullFileOutput.append(HTMLUtils.OUT_BLACK_CLOSED_YELLOW_STYLE);
		
		// Open the container and add file h1 (roomName) and h3 (year, month)
		fullFileOutput.append("<div class='container-fluid'>\r\n");
		fullFileOutput.append("<h1>").append(roomName).append("</h1>");
		fullFileOutput.append("<h3>").append(Configuration.YEAR_TO_PROCESS).append(", ")
		.append(OutputStrings.numericMonthToFullString(Integer.parseInt(Configuration.MONTH_TO_PROCESS))).append("</h3>");
		
		// Get the beginning day of the calendar (it can be a day from the previous month) to set currentDay
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(Configuration.YEAR_TO_PROCESS));
		calendar.set(Calendar.MONTH, Integer.parseInt(Configuration.MONTH_TO_PROCESS) - 1);
		// If the month starts on Sunday, the calendar will start at exactly the month to process. Else, it will contain part of the previous month.
		int lastDayPreviousMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		firstDayOfWeek = CalendarInfo.MONTH_FIRST_DAY_OF_WEEK == 1 ?
						CalendarInfo.MONTH_FIRST_DAY_OF_WEEK : lastDayPreviousMonth - (CalendarInfo.MONTH_FIRST_DAY_OF_WEEK - 2);
		
		// Alias to read better
		int firstWeek = CalendarInfo.MONTH_FIRST_WEEK_NUMBER;
		int monthWeeksNum = CalendarInfo.MONTH_WEEKS_NUMBER;
		
		// Iterate through all the weeks of the month
		for(int week = firstWeek; week < firstWeek + monthWeeksNum; week++)
		{
			// If first week of month
			if(week == firstWeek)
				printFirstWeek(week);
			// If last week of month
			else if(week == (firstWeek + monthWeeksNum) - 1)
				printLastWeek(week);
			else
				printWeek(week);
		}
		
		// Close document
		fullFileOutput.append("</div>\r\n</body>\r\n</html>");
	}
	
	/**
	 * The first week of the month, which could contain part of the previous month
	 * @param weekNum The exact number of the week within the year
	 */
	private void printFirstWeek(int weekNum)
	{
		fullFileOutput.append("<table class='table-condensed table-bordered table-striped'>");
		printTableHeader(firstDayOfWeek, weekNum);
		fullFileOutput.append("<tbody>");
		
		// Get the number of days of the previous month contained on this week
		int prevMonthDayNum = CalendarInfo.MONTH_FIRST_DAY_OF_WEEK - 1;
		// Get the number of days of this month contained on this week
		int thisWeekMonthDayNum = 8 - CalendarInfo.MONTH_FIRST_DAY_OF_WEEK;
		for(int hour = 0; hour < 24; hour++)
		{
			// Open the row and print the hour cell
			fullFileOutput.append("<tr>");
			fullFileOutput.append("<td class='hourFrame'>")
							.append(hour).append(":00 - ").append(hour + 1).append(":00").append("</td>");
			
			// Fill the out of month days on this hour
			for(int i = 0; i < prevMonthDayNum; i ++)
				fullFileOutput.append("<td class='outOfMonth'></td>");
			
			// Fill the days within this month on this hour
			for(int day = 1; day <= thisWeekMonthDayNum; day++)
			{		
				Request request = currentRoomSchedule.get(day).get(hour);
				if(request != null) 
				{
					if(request.activityName.equals(InputStrings.CLOSE_KEY))
						fullFileOutput.append("<td class='closed'>").append(OutputStrings.CLOSE_KEY).append("</td>");
					else
						fullFileOutput.append("<td class='requested'>").append(request.activityName).append("</td>");
				}
				else
					fullFileOutput.append("<td></td>");
			}
			fullFileOutput.append("</tr>");
		}
		
		// Set firstDayOfWeek value to be used in the next week;
		firstDayOfWeek = firstDayOfWeek == 1 ? 8 : thisWeekMonthDayNum + 1;
		fullFileOutput.append("</tbody>");
		fullFileOutput.append("</table>");
	}
	
	/**
	 * A week in the middle of the month, which can't contain part of the previous or next month
	 * @param weekNum The exact number of the week within the year
	 */
	private void printWeek(int weekNum)
	{
		fullFileOutput.append("<table class='table-condensed table-bordered table-striped'>");
		printTableHeader(firstDayOfWeek, weekNum);
		fullFileOutput.append("<tbody>");
		
		for(int hour = 0; hour < 24; hour++)
		{
			fullFileOutput.append("<tr>");
			fullFileOutput.append("<td class='hourFrame'>")
							.append(hour).append(":00 - ").append(hour + 1).append(":00").append("</td>");
			for(int day = firstDayOfWeek; day < firstDayOfWeek + 7; day++)
			{
				Request request = currentRoomSchedule.get(day).get(hour);
				if(request != null) 
				{
					if(request.activityName.equals(InputStrings.CLOSE_KEY))
						fullFileOutput.append("<td class='closed'>").append(OutputStrings.CLOSE_KEY).append("</td>");
					else
						fullFileOutput.append("<td class='requested'>").append(request.activityName).append("</td>");
				}
				else
					fullFileOutput.append("<td></td>");
			}
			fullFileOutput.append("</tr>");
		}
		
		firstDayOfWeek = firstDayOfWeek + 7;
		fullFileOutput.append("</tbody>");
		fullFileOutput.append("</table>");
	}
	
	/**
	 * The last week of the month, which could contain part of the next month
	 * @param weekNum The exact number of the week within the year
	 */
	private void printLastWeek(int weekNum)
	{
		fullFileOutput.append("<table class='table-condensed table-bordered table-striped'>");
		printTableHeader(firstDayOfWeek, weekNum);
		fullFileOutput.append("<tbody>");
		
		// Get the number of days of the next month contained on this week
		int nextMonthDayNum = 8 - CalendarInfo.MONTH_LAST_DAY_OF_WEEK;
		// Get the number of days of this month contained on this week
		int thisWeekMonthDayNum = CalendarInfo.MONTH_LAST_DAY_OF_WEEK;
		for(int hour = 0; hour < 24; hour++)
		{
			fullFileOutput.append("<tr>");
			fullFileOutput.append("<td class='hourFrame'>")
							.append(hour).append(":00 - ").append(hour + 1).append(":00").append("</td>");
			for(int day = firstDayOfWeek; day < firstDayOfWeek + thisWeekMonthDayNum; day++)
			{
				Request request = currentRoomSchedule.get(day).get(hour);
				if(request != null) 
				{
					if(request.activityName.equals(InputStrings.CLOSE_KEY))
						fullFileOutput.append("<td class='closed'>").append(OutputStrings.CLOSE_KEY).append("</td>");
					else
						fullFileOutput.append("<td class='requested'>").append(request.activityName).append("</td>");
				}
				else
					fullFileOutput.append("<td></td>");
			}
			
			// Fill the out of month days on this hour
			for(int i = 0; i < nextMonthDayNum - 1; i ++)
				fullFileOutput.append("<td class='outOfMonth'></td>");
						
			fullFileOutput.append("</tr>");
		}
		fullFileOutput.append("</tbody>");
		fullFileOutput.append("</table>");
	}

	private void printTableHeader(int firstDay, int weekNum)
	{
		// TODO refactor OutputStrings.Days to inherit from ArrayList so it can be iterated
		
		int day = firstDay;
		fullFileOutput.append("<thead>");
		fullFileOutput.append("<th>").append(OutputStrings.WEEK).append(" ").append(weekNum).append("</th>");
		fullFileOutput.append("<th>").append(OutputStrings.Days.SUNDAY).append(" ").append(day).append("</th>");
		day = day + 1 > CalendarInfo.MONTH_DAY_NUM ? 1 : day + 1;
		fullFileOutput.append("<th>").append(OutputStrings.Days.MONDAY).append(" ").append(day).append("</th>");
		day = day + 1 > CalendarInfo.MONTH_DAY_NUM ? 1 : day + 1;
		fullFileOutput.append("<th>").append(OutputStrings.Days.TUESDAY).append(" ").append(day).append("</th>");
		day = day + 1 > CalendarInfo.MONTH_DAY_NUM ? 1 : day + 1;
		fullFileOutput.append("<th>").append(OutputStrings.Days.WEDNESDAY).append(" ").append(day).append("</th>");
		day = day + 1 > CalendarInfo.MONTH_DAY_NUM ? 1 : day + 1;
		fullFileOutput.append("<th>").append(OutputStrings.Days.THURSDAY).append(" ").append(day).append("</th>");
		day = day + 1 > CalendarInfo.MONTH_DAY_NUM ? 1 : day + 1;
		fullFileOutput.append("<th>").append(OutputStrings.Days.FRIDAY).append(" ").append(day).append("</th>");
		day = day + 1 > CalendarInfo.MONTH_DAY_NUM ? 1 : day + 1;
		fullFileOutput.append("<th>").append(OutputStrings.Days.SATURDAY).append(" ").append(day).append("</th>");
		fullFileOutput.append("</thead>");
	}
	
	private void writeToFile(String fileName, String in)
	{
		try(  PrintWriter out = new PrintWriter( fileName )  ){
		    out.println( in );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
