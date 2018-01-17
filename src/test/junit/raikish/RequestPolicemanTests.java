package junit.raikish;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import globals.CalendarInfo;
import globals.Configuration;
import model.Request;
import model.RequestDays;
import model.RequestList;
import model.RequestPoliceman;
import model.RoomSchedule;

public class RequestPolicemanTests 
{
	@Test
	public void processNonErrorProneRequestListTest() 
	{
		Configuration.MONTH_TO_PROCESS = "1";
		Configuration.YEAR_TO_PROCESS = "2018";
		CalendarInfo.MONTH_DAY_NUM = 31;
		
		RequestList requestList = new RequestList();
		Request request = new Request();
		request.activityName = "ReunionJava";
		request.roomName = "Sala1";
		request.dayFrame.startDay = 7;
		request.dayFrame.endDay = 14;
		request.hourFrames.add(request.new HourFrame(3, 5));
		request.hourFrames.add(request.new HourFrame(6, 8));
		request.requestedDays.add(RequestDays.MONDAY);
		request.requestedDays.add(RequestDays.WEDNESDAY);
		requestList.add(request);
		
		Request request2 = new Request();
		request2.activityName = "ReunionCpp";
		request2.roomName = "Sala2";
		request2.dayFrame.startDay = 7;
		request2.dayFrame.endDay = 14;
		request2.hourFrames.add(request.new HourFrame(3, 5));
		request2.hourFrames.add(request.new HourFrame(6, 8));
		request2.requestedDays.add(RequestDays.MONDAY);
		request2.requestedDays.add(RequestDays.TUESDAY);
		requestList.add(request2);
		
		Request request3 = new Request();
		request3.activityName = "ReunionCpp";
		request3.roomName = "Sala1";
		request3.dayFrame.startDay = 7;
		request3.dayFrame.endDay = 14;
		request3.hourFrames.add(request.new HourFrame(3, 8));
		request3.requestedDays.add(RequestDays.MONDAY);
		requestList.add(request3);
		
		RequestPoliceman policeman = new RequestPoliceman();
		policeman.process(requestList);
		Map<String, RoomSchedule> roomSchedules = policeman.getRoomSchedules();
		assertEquals("There should be two schedules!", 2, roomSchedules.keySet().size());
		
		RoomSchedule sala1Schedule = roomSchedules.get("Sala1");
		assertNull("This should be empty", sala1Schedule.get(8).get(2));
		assertEquals("This should be reserved by ReunionJava", "ReunionJava", sala1Schedule.get(8).get(3).activityName);
		assertEquals("This should be reserved by ReunionJava", "ReunionJava", sala1Schedule.get(8).get(4).activityName);
		assertEquals("This should be reserved by ReunionCpp", "ReunionCpp", sala1Schedule.get(8).get(5).activityName);		
		assertEquals("This should be reserved by ReunionJava", "ReunionJava", sala1Schedule.get(8).get(6).activityName);
		assertEquals("This should be reserved by ReunionJava", "ReunionJava", sala1Schedule.get(8).get(7).activityName);
		assertNull("This should be empty", sala1Schedule.get(8).get(8));
		
		assertNull("This should be empty", sala1Schedule.get(10).get(2));
		assertEquals("This should be reserved by ReunionJava", "ReunionJava", sala1Schedule.get(10).get(3).activityName);
		assertEquals("This should be reserved by ReunionJava", "ReunionJava", sala1Schedule.get(10).get(4).activityName);	
		assertEquals("This should be reserved by ReunionJava", "ReunionJava", sala1Schedule.get(10).get(6).activityName);
		assertEquals("This should be reserved by ReunionJava", "ReunionJava", sala1Schedule.get(10).get(7).activityName);
		assertNull("This should be empty", sala1Schedule.get(10).get(8));
		
		RoomSchedule sala2Schedule = roomSchedules.get("Sala2");
		assertNull("This should be empty", sala2Schedule.get(8).get(2));
		assertEquals("This should be reserved by ReunionCpp", "ReunionCpp", sala2Schedule.get(8).get(3).activityName);
		assertEquals("This should be reserved by ReunionCpp", "ReunionCpp", sala2Schedule.get(8).get(4).activityName);
		assertNull("This should be empty", sala2Schedule.get(8).get(5));	
		assertEquals("This should be reserved by ReunionCpp", "ReunionCpp", sala2Schedule.get(8).get(6).activityName);
		assertEquals("This should be reserved by ReunionCpp", "ReunionCpp", sala2Schedule.get(8).get(7).activityName);
		assertNull("This should be empty", sala2Schedule.get(8).get(8));
	}
	
	@Test
	public void processErrorProneList()
	{
		Configuration.MONTH_TO_PROCESS = "2";
		Configuration.YEAR_TO_PROCESS = "2018";
		CalendarInfo.MONTH_DAY_NUM = 28;
		
		RequestList requestList = new RequestList();
		Request request = new Request();
		request.activityName = "ReunionJava";
		request.roomName = "Sala1";
		request.dayFrame.startDay = 1;
		request.dayFrame.endDay = 28;
		request.hourFrames.add(request.new HourFrame(3, 5));
		request.requestedDays.add(RequestDays.MONDAY);
		requestList.add(request);
		
		RequestPoliceman policeman = new RequestPoliceman();
		policeman.process(requestList);
		Map<String, RoomSchedule> roomSchedules = policeman.getRoomSchedules();
		assertEquals("There should be one schedule!", 1, roomSchedules.keySet().size());
		
		RoomSchedule sala1Schedule = roomSchedules.get("Sala1");
		assertEquals("This should be reserved by ReunionJava", "ReunionJava", sala1Schedule.get(5).get(3).activityName);
		assertEquals("This should be reserved by ReunionJava", "ReunionJava", sala1Schedule.get(5).get(4).activityName);
		assertNull("This should be empty", sala1Schedule.get(5).get(5));
		assertEquals("This should be reserved by ReunionJava", "ReunionJava", sala1Schedule.get(12).get(3).activityName);
		assertEquals("This should be reserved by ReunionJava", "ReunionJava", sala1Schedule.get(12).get(4).activityName);
		assertNull("This should be empty", sala1Schedule.get(12).get(5));
		assertEquals("This should be reserved by ReunionJava", "ReunionJava", sala1Schedule.get(19).get(3).activityName);
		assertEquals("This should be reserved by ReunionJava", "ReunionJava", sala1Schedule.get(19).get(4).activityName);
		assertNull("This should be empty", sala1Schedule.get(19).get(5));
		assertEquals("This should be reserved by ReunionJava", "ReunionJava", sala1Schedule.get(26).get(3).activityName);
		assertEquals("This should be reserved by ReunionJava", "ReunionJava", sala1Schedule.get(26).get(4).activityName);
		assertNull("This should be empty", sala1Schedule.get(26).get(5));
	}
}
