package model;

import java.util.List;

/**
 * This is the POJO for a syntax-correct parsed request from the requests file.
 * 
 * @author Jendoliver
 *
 */
public class Request 
{
	public class HourFrame
	{
		public int startHour;
		public int endHour;
		
		private HourFrame() { }
	}
	
	public class DayFrame
	{
		public int startDay;
		public int endDay;
		
		private DayFrame() { }
	}
	
	public String activityName;
	public String roomName;
	public DayFrame dayFrame;
	public List<HourFrame> hourFrames;
	public List<RequestDays> requestedDays;
	
	public Request() { }
}
