package junit.irene;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParserRequestsTests 
{
	@Test
	public void goodRequestESPTest() 
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void goodRequestCATTest()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void goodRequestENGTest()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void badArgsRequestTest()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void badDateFormatRequestTest()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void badDayMaskRequestTest()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void outOfBoundsDateRequestTest()
	{
		// Comprueba que no se anaden requests en las que el mes a procesar no esta comprendido
		fail("Not yet implemented");
	}
	
	@Test
	public void badHourMaskArgsTest()
	{
		// Requests containing more than 5 hour masks shouldn't be accepted
		fail("Not yet implemented");
	}
	
	@Test
	public void dayClampingTest()
	{
		// request.dayFrame.beginDay should be clamped to the first day of month and request.dayFrame.endDay to the last if the request contains the month to process between others
		fail("Not yet implemented");
	}
}
