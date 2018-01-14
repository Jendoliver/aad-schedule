package junit.raikish;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Request;
import model.RequestDays;
import model.Request.HourFrame;
import model.RequestList;

public class RequestPolicemanTests 
{
	@Test
	// processNonErrorProneRequestListTest
	public void notYetImplementedTest() 
	{
		RequestList requestList = new RequestList();
		Request request = new Request();
		request.activityName = "ReunionJava";
		request.roomName = "Sala1";
		request.dayFrame.startDay = 1;
		request.dayFrame.endDay = 10;
		request.hourFrames.add(request.new HourFrame(3, 5));
		request.hourFrames.add(request.new HourFrame(7, 9));
		request.hourFrames.add(request.new HourFrame(11, 13));
		request.hourFrames.add(request.new HourFrame(15, 17));
		request.requestedDays.add(RequestDays.MONDAY);
		
		Request request2 = new Request();
		Request request3 = new Request();
		Request request4 = new Request();
	}
}
