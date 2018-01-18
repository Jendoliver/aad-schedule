package junit.raikish;

import static org.junit.Assert.*;

import org.junit.Test;

import globals.InputStrings;
import model.Request;
import model.RequestList;

public class RequestListTests 
{
	private final String CLOSE_KEY_CAT = "Tancat";
	private final String CLOSE_KEY_ESP = "Cerrado";
	private final String CLOSE_KEY_ENG = "Closed";

	@Test
	public void sortingCloseFirstFIFOStrategyCATTest() 
	{
		InputStrings.CLOSE_KEY = CLOSE_KEY_CAT;
		
		RequestList requestList = new RequestList();
		requestList.add(new Request("1"));
		requestList.add(new Request(CLOSE_KEY_CAT));
		requestList.add(new Request("1"));
		requestList.add(new Request(CLOSE_KEY_CAT));
		requestList.add(new Request("1"));
		requestList.add(new Request("2"));
		requestList.add(new Request("3"));
		requestList.add(new Request("2"));
		requestList.add(new Request("3"));
		requestList.add(new Request(CLOSE_KEY_CAT));
		
		requestList = requestList.sort();
		
		assertEquals(requestList.get(0).activityName, CLOSE_KEY_CAT);
		assertEquals(requestList.get(1).activityName, CLOSE_KEY_CAT);
		assertEquals(requestList.get(2).activityName, CLOSE_KEY_CAT);
		assertEquals(requestList.get(3).activityName, "1");
		assertEquals(requestList.get(4).activityName, "1");
		assertEquals(requestList.get(5).activityName, "1");
		assertEquals(requestList.get(6).activityName, "2");
		assertEquals(requestList.get(7).activityName, "2");
		assertEquals(requestList.get(8).activityName, "3");
		assertEquals(requestList.get(9).activityName, "3");
	}
	
	@Test
	public void sortingCloseFirstFIFOStrategyESPTest() 
	{
		InputStrings.CLOSE_KEY = CLOSE_KEY_ESP;
		
		RequestList requestList = new RequestList();
		requestList.add(new Request("1"));
		requestList.add(new Request(CLOSE_KEY_ESP));
		requestList.add(new Request("1"));
		requestList.add(new Request(CLOSE_KEY_ESP));
		requestList.add(new Request("1"));
		requestList.add(new Request("2"));
		requestList.add(new Request("3"));
		requestList.add(new Request("2"));
		requestList.add(new Request("3"));
		requestList.add(new Request(CLOSE_KEY_ESP));
		
		requestList = requestList.sort();
		
		assertEquals(requestList.get(0).activityName, CLOSE_KEY_ESP);
		assertEquals(requestList.get(1).activityName, CLOSE_KEY_ESP);
		assertEquals(requestList.get(2).activityName, CLOSE_KEY_ESP);
		assertEquals(requestList.get(3).activityName, "1");
		assertEquals(requestList.get(4).activityName, "1");
		assertEquals(requestList.get(5).activityName, "1");
		assertEquals(requestList.get(6).activityName, "2");
		assertEquals(requestList.get(7).activityName, "2");
		assertEquals(requestList.get(8).activityName, "3");
		assertEquals(requestList.get(9).activityName, "3");
	}
	
	@Test
	public void sortingCloseFirstFIFOStrategyENGTest() 
	{
		InputStrings.CLOSE_KEY = CLOSE_KEY_ENG;
		
		RequestList requestList = new RequestList();
		requestList.add(new Request("1"));
		requestList.add(new Request(CLOSE_KEY_ENG));
		requestList.add(new Request("1"));
		requestList.add(new Request(CLOSE_KEY_ENG));
		requestList.add(new Request("1"));
		requestList.add(new Request("2"));
		requestList.add(new Request("3"));
		requestList.add(new Request("2"));
		requestList.add(new Request("3"));
		requestList.add(new Request(CLOSE_KEY_ENG));
		
		requestList = requestList.sort();
		
		assertEquals(requestList.get(0).activityName, CLOSE_KEY_ENG);
		assertEquals(requestList.get(1).activityName, CLOSE_KEY_ENG);
		assertEquals(requestList.get(2).activityName, CLOSE_KEY_ENG);
		assertEquals(requestList.get(3).activityName, "1");
		assertEquals(requestList.get(4).activityName, "1");
		assertEquals(requestList.get(5).activityName, "1");
		assertEquals(requestList.get(6).activityName, "2");
		assertEquals(requestList.get(7).activityName, "2");
		assertEquals(requestList.get(8).activityName, "3");
		assertEquals(requestList.get(9).activityName, "3");
	}
}
