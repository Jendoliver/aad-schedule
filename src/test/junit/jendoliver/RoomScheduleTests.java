package junit.jendoliver;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import globals.CalendarInfo;
import model.Request;
import model.RoomSchedule;

public class RoomScheduleTests 
{
	@Test
	public void initializationTest() 
	{
		CalendarInfo.MONTH_DAY_NUM = 10;
		RoomSchedule roomSchedule = new RoomSchedule();
		
		assertFalse("Days should be initialized at 1", roomSchedule.containsKey(0));
		assertFalse("Days should end at CalendarInfo.MONTH_DAY_NUM value", roomSchedule.containsKey(CalendarInfo.MONTH_DAY_NUM + 1));
		assertTrue("Hours should be initalized at 0", roomSchedule.get(1).containsKey(0));
		assertFalse("Hours should end at 23", roomSchedule.get(1).containsKey(24));
		
		for(int day = 1; day <= CalendarInfo.MONTH_DAY_NUM; day++)
		{
			assertTrue("The RoomSchedule should contain this day!", roomSchedule.containsKey(day));
			for(int hour = 0; hour < 24; hour++)
			{
				assertTrue("The RoomSchedule should contain this hour!", roomSchedule.get(day).containsKey(hour));
			}
		}
	}
	
	@Test
	public void isEmptyHourFrameTest()
	{
		final int DAY = 1;
		final int HOUR = 17;
		
		CalendarInfo.MONTH_DAY_NUM = 10;
		RoomSchedule roomSchedule = new RoomSchedule();
		Request request = new Request();
		
		Map<Integer, Request> dayHours = roomSchedule.get(DAY);
		dayHours.put(HOUR, request);
		
		assertFalse("This hour frame shouldn't be empty!", roomSchedule.isEmptyHourFrame(DAY, HOUR));
	}

}
