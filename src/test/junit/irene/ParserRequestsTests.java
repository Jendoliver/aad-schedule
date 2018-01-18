package junit.irene;

import static org.junit.Assert.*;

<<<<<<< HEAD
import org.junit.Test;

public class ParserRequestsTests 
{
	@Test
	public void goodRequestESPTest() 
	{
		fail("Not yet implemented");
=======
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import globals.Configuration;
import globals.Constants;
import model.RequestList;
import model.parsers.ParserRequests;

public class ParserRequestsTests 
{
	// UNCOMMENT TO START TESTING
	/*private final List<String> GOOD_REQUESTS_ESP = 
			Arrays.asList("Cerrado Sala1 01/01/2018 31/01/2018 LMXJVSD 00-07_22-24", 
			"ReunionJava Sala1 10/01/2018 16/01/2018 XJV 10-16_21-22");
	
	private final List<String> GOOD_REQUESTS_CAT = 
			Arrays.asList("Tancat Sala1 01/01/2018 31/01/2018 DMCJVSG 00-07_22-24\r\n",
			"ReunionJava Sala1 10/01/2018 16/01/2018 CJV 10-16_21-22");
			
	private final List<String> GOOD_REQUESTS_ENG = 
			Arrays.asList("Closed Room1 01/01/2018 31/01/2018 MTWHFSN 00-07_22-24",
			"MeetingJava Room1 10/01/2018 16/01/2018 WHF 10-16_21-22");
	
	private final List<String> BAD_ARGS_NUM_REQUESTS = // Requests not meeting 6 parameters
			Arrays.asList("Meeting", "Meeting Meeting", "Meeting Meeting 22/01/2018",
			"Meeting Meeting 22/01/2018 26/01/2018", "Meeting Meeting 22/01/2018 26/01/2018 TWH", 
			"Meeting Meeting 22/01/2018 26/01/2018 TWH 18-20",
			"Meeting Meeting 22/01/2018 26/01/2018 TWH 18-20 20-22");
	
	private final List<String> BAD_DATE_FORMAT_REQUESTS = 
			Arrays.asList("Closed Room1 01-01/2018 31/01/2018 MTWHFSN 00-07_22-24", // Invalid char
			"Closed Room1 01/01/2018 31*12/2018 MTWHFSN 00-07_22-24", // Invalid char
			"Closed Room1 01/2018/03 31/12/2018 MTWHFSN 00-07_22-24", // Wrong format
			"Closed Room1 01/01/2018 12/31/2018 MTWHFSN 00-07_22-24", // Wrong format
			"Closed Room1 010112018 31/12/2018 MTWHFSN 00-07_22-24"); // Bad separators
	
	private final List<String> BAD_DAY_MASK_REQUESTS = 
			Arrays.asList("Closed Room1 01/01/2018 31/01/2018 MTQHFSN 00-07_22-24", // Invalid day code
			"Closed Room1 01/1/2018 31/12/2018 MMMMMMM 00-07_22-24", // Repeated day code
			"Closed Room1 01/1/2018 31/12/2018 MTWHFSNM 00-07_22-24"); // More than 7 day codes DISCUSS this could be adapted clamping at 7
	
	private final List<String> OUT_OF_BOUNDS_DATE_REQUESTS = 
			Arrays.asList("Closed Room1 01/02/2018 31/12/2018 MTWHFSN 00-07_22-24",
			"MeetingJava Room1 10/05/2018 16/08/2018 WHF 10-16_21-22", // Month to process not compressed between dates (wrong month, good year)
			"MeetingJava Room1 10/01/2019 16/01/2019 WHF 10-16_21-22"); // Month to process not compressed between dates (good month, wrong year)
	
	private final List<String> BAD_HOUR_FRAMES_REQUESTS = 
			Arrays.asList("Closed Room1 01/01/2018 31/01/2018 MTWHFSN 00-03_05-07_09-17_18-20_20-21_22-24", // More than five
			"MeetingJava Room1 10/01/2018 16/01/2018 WHF 10-03"); // DISCUSS this could be rejected or adapted flipping the values
	
	private final List<String> CLAMP_NEEDED_REQUESTS = 
			Arrays.asList("Closed Room1 01/01/2018 31/03/2018 MTWHFSN 00-07_22-24", // Full clamp
			"MeetingJava Room1 25/01/2018 5/02/2018 WHF 10-16_21-22", // Left clamp
			"MeetingJava Room1 25/02/2018 5/03/2018 WHF 10-16_21-22"); // Right clamp
	@Test
	public void goodRequestESPTest() 
	{
		Configuration.MONTH_TO_PROCESS = "01";
		Configuration.YEAR_TO_PROCESS = "2018";
		Configuration.INPUT_LANG = "ESP";
		ParserRequests parserRequests = new ParserRequests(GOOD_REQUESTS_ESP);
		RequestList requestList = parserRequests.parse();
		assertEquals("There should be two requests!", 2, requestList.size());
>>>>>>> 1b84ea39738da281f7d340155f5717363e18bf95
	}
	
	@Test
	public void goodRequestCATTest()
	{
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		Configuration.MONTH_TO_PROCESS = "01";
		Configuration.YEAR_TO_PROCESS = "2018";
		Configuration.INPUT_LANG = "CAT";
		ParserRequests parserRequests = new ParserRequests(GOOD_REQUESTS_CAT);
		RequestList requestList = parserRequests.parse();
		assertEquals("There should be two requests! Is INPUT_LANG considered?", 2, requestList.size());
>>>>>>> 1b84ea39738da281f7d340155f5717363e18bf95
	}
	
	@Test
	public void goodRequestENGTest()
	{
<<<<<<< HEAD
		fail("Not yet implemented");
	}
	
	@Test
	public void badArgsRequestTest()
	{
		fail("Not yet implemented");
=======
		Configuration.MONTH_TO_PROCESS = "01";
		Configuration.YEAR_TO_PROCESS = "2018";
		Configuration.INPUT_LANG = "ENG";
		ParserRequests parserRequests = new ParserRequests(GOOD_REQUESTS_ENG);
		RequestList requestList = parserRequests.parse();
		assertEquals("There should be two requests! Is INPUT_LANG considered?", 2, requestList.size());
	}
	
	@Test
	public void badArgsNumRequestTest()
	{
		Configuration.MONTH_TO_PROCESS = "01";
		Configuration.YEAR_TO_PROCESS = "2018";
		Configuration.INPUT_LANG = "ENG";
		ParserRequests parserRequests = new ParserRequests(BAD_ARGS_NUM_REQUESTS);
		RequestList requestList = parserRequests.parse();
		assertTrue("Requests must have exactly 6 parameters.", requestList.isEmpty());
>>>>>>> 1b84ea39738da281f7d340155f5717363e18bf95
	}
	
	@Test
	public void badDateFormatRequestTest()
	{
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		Configuration.MONTH_TO_PROCESS = "01";
		Configuration.YEAR_TO_PROCESS = "2018";
		Configuration.INPUT_LANG = "ENG";
		ParserRequests parserRequests = new ParserRequests(BAD_DATE_FORMAT_REQUESTS);
		RequestList requestList = parserRequests.parse();
		assertTrue("Date format should be dd/mm/yyyy or d/m/yyyy (parseInt is key).", requestList.isEmpty());
>>>>>>> 1b84ea39738da281f7d340155f5717363e18bf95
	}
	
	@Test
	public void badDayMaskRequestTest()
	{
<<<<<<< HEAD
		fail("Not yet implemented");
=======
		Configuration.MONTH_TO_PROCESS = "01";
		Configuration.YEAR_TO_PROCESS = "2018";
		Configuration.INPUT_LANG = "ENG";
		ParserRequests parserRequests = new ParserRequests(BAD_DAY_MASK_REQUESTS);
		RequestList requestList = parserRequests.parse();
		assertTrue("A day mask can only contain InputStrings, can't contain repeated days even if correct "
				+ "and can't contain more than 7 days.", requestList.isEmpty());
>>>>>>> 1b84ea39738da281f7d340155f5717363e18bf95
	}
	
	@Test
	public void outOfBoundsDateRequestTest()
	{
<<<<<<< HEAD
		// Comprueba que no se anaden requests en las que el mes a procesar no esta comprendido
		fail("Not yet implemented");
	}
	
	@Test
	public void badHourMaskArgsTest()
	{
		// Requests containing more than 5 hour masks shouldn't be accepted
		fail("Not yet implemented");
=======
		Configuration.MONTH_TO_PROCESS = "01";
		Configuration.YEAR_TO_PROCESS = "2018";
		Configuration.INPUT_LANG = "ENG";
		ParserRequests parserRequests = new ParserRequests(OUT_OF_BOUNDS_DATE_REQUESTS);
		RequestList requestList = parserRequests.parse();
		assertTrue("The month to process has to be comprended by the dateFrame of the request", requestList.isEmpty());
	}
	
	@Test
	public void badHourFramesTest()
	{
		Configuration.MONTH_TO_PROCESS = "01";
		Configuration.YEAR_TO_PROCESS = "2018";
		Configuration.INPUT_LANG = "ENG";
		ParserRequests parserRequests = new ParserRequests(BAD_HOUR_FRAMES_REQUESTS);
		RequestList requestList = parserRequests.parse();
		assertTrue("No more than " + Constants.MAX_HOUR_FRAME_NUM + " hour frames shall pass.", requestList.isEmpty());
>>>>>>> 1b84ea39738da281f7d340155f5717363e18bf95
	}
	
	@Test
	public void dayClampingTest()
	{
<<<<<<< HEAD
		// request.dayFrame.beginDay should be clamped to the first day of month and request.dayFrame.endDay to the last if the request contains the month to process between others
		fail("Not yet implemented");
	}
=======
		Configuration.MONTH_TO_PROCESS = "02";
		Configuration.YEAR_TO_PROCESS = "2018";
		Configuration.INPUT_LANG = "ENG";
		ParserRequests parserRequests = new ParserRequests(CLAMP_NEEDED_REQUESTS);
		RequestList requestList = parserRequests.parse();
		
		assertFalse("Requests that don't start or end at MONTH_TO_PROCESS but still "
				+ "contain it should be accepted, but the resulting Request object"
				+ "must have its dayFrame clamped accordingly at needs", requestList.isEmpty());
		
		assertEquals("Left day (as well as right) should be clamped when the month is contained in the date",
				1, requestList.get(0).dayFrame.startDay);
		assertEquals("Right day (as well as left) should be clamped when the month is contained in the date",
				31, requestList.get(0).dayFrame.endDay);
		assertEquals("Left day should be clamped when only a left part of the month is contained in the date",
				1, requestList.get(1).dayFrame.startDay);
		assertEquals("Right day should be clamped when only a right part of the month is contained in the date",
				31, requestList.get(2).dayFrame.startDay);
	}*/
>>>>>>> 1b84ea39738da281f7d340155f5717363e18bf95
}
