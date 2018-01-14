package model;

import java.util.ArrayList;
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
		
		public HourFrame() { }
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
	public List<HourFrame> hourFrames = new ArrayList<>();
	public List<RequestDays> requestedDays = new ArrayList<>();
	
	public Request() { }
	
	// Used to make tests easier on RequestListTests
	public Request(String activityName) { this.activityName = activityName; }
}
