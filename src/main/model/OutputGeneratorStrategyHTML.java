package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import globals.CalendarInfo;
import globals.Configuration;
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
	private String fullFileOutput;
	private Integer currentDay = 0;
	private Integer currentHour = 0;
	
	@Override
	public void print(Map<String, RoomSchedule> roomSchedules) 
	{
		for(String room : roomSchedules.keySet())
		{
			processSchedule(room, roomSchedules.get(room));
			writeToFile(room.concat(".html"), fullFileOutput);
			fullFileOutput = "";
		}
	}
	
	/**
	 * Processes the schedule of a room and generates an output String added to
	 * outputFilesContent to be written in a file
	 * 
	 * @param schedule The schedule to process
	 */
	private void processSchedule(String roomName, RoomSchedule schedule)
	{
		StringBuilder out = new StringBuilder();
		
		// Adds HTML info, bootstrap head and custom style
		out.append("<!DOCTYPE html>\r\n<html lang='en'>");
		out.append(HTMLUtils.BOOTSTRAP_HEAD); // FIXME this prints the title independently from the language, check OutputStrings.TITLE
		out.append("<body>");
		out.append(HTMLUtils.OUT_BLACK_CLOSED_YELLOW_STYLE);
		
		// Adds file h1 (roomName) and h3 (year, month)
		out.append("<h1>").append(roomName).append("</h1>");
		out.append("<h3>").append(Configuration.YEAR_TO_PROCESS).append(", ")
						.append(OutputStrings.numericMonthToFullString(Integer.parseInt(Configuration.MONTH_TO_PROCESS)));
		out.append("<div class='container-fluid'>\r\n")
				.append("<table class='table-condensed table-bordered table-striped'>");
		
		// TODO Generate table header
		
		// TODO Generate table content
		
		
		fullFileOutput = out.toString();
	}

	private void printTableHeader() 
	{
		StringBuilder out = new StringBuilder();
		out.append("<thead><th>")
			.append(OutputStrings.Days.SUNDAY).append("</th><th>")
			.append(OutputStrings.Days.MONDAY).append("</th><th>")
			.append(OutputStrings.Days.TUESDAY).append("</th><th>")
			.append(OutputStrings.Days.WEDNESDAY).append("</th><th>")
			.append(OutputStrings.Days.THURSDAY).append("</th><th>")
			.append(OutputStrings.Days.FRIDAY).append("</th><th>")
			.append(OutputStrings.Days.SATURDAY).append("</th></thead>");
	}
	
	// TODO implement
	/*private void printNonMonthDays(int num)
	{
		for(int i = 0; i < num; i++) {
			output.append("<td style='background-color: black;'/>)
		}
		
		// FIXME PLIS
	}*/
	
	private void writeToFile(String fileName, String in)
	{
		
	}

}
