package model;

import java.util.HashMap;
import java.util.Map;

import globals.CalendarInfo;

/**
 * This class represents the schedule of a month for a room. It is used (and filled) by model.RequestPoliceman
 * to ensure that there are no conflicts between well-formatted requests, and then passed to OutputGenerator to
 * generate the final schedule.
 * 
 * @author Jandol
 * @author Raikish
 *
 */
public class RoomSchedule 
{
	private Map<Integer, Map<Integer, Request>> grid; 
		
	public RoomSchedule() 
	{ 
		grid = new HashMap<>(); 
		init(); 
	}
	
	private void init()
	{
		// Generates the main keys from the schedule, the months (1-12)
		for (int i = 1; i <= CalendarInfo.MONTH_DAY_NUM; i++) 
		{
			grid.put(i, new HashMap<>());
			
			// Generates 24 hour keys for each month (0-23)
			for(int j = 0; j < 24; j++) 
				grid.get(i).put(j, null);
		}
	}
	
	public Map<Integer, Map<Integer, Request>> getGrid() 
	{
		return grid;
	}
	
	/**
	 * Used to determine whether an hour frame on this schedule is empty or not
	 * 
	 * @param day The day to check the hour frame from
	 * @param beginningHour The beginning hour for this hour frame
	 * @return
	 */
	public boolean isEmptyHourFrame(int day, int beginningHour) 
	{
		return grid.get(day).get(beginningHour).equals(null);
	}
}
