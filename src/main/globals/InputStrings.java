package globals;

import model.RequestDays;

/**
 * This class contains the keywords that might change between languages. Its values are set by model.parsers.ParserInternational when used in input mode.
 * This data is the 003 row at the international.X file. It also contains the extra key 005, @CLOSE_KEY, which is used to detect the Close event.
 * 
 * It also provides utilities for effectively transforming a parsed request into a Request object
 * 
 * @author Jendoliver
 *
 */
public final class InputStrings 
{
	// 003
	public static String MONDAY;
	public static String TUESDAY;
	public static String WEDNESDAY;
	public static String THURSDAY;
	public static String FRIDAY;
	public static String SATURDAY;
	public static String SUNDAY;
	
	// 005
	public static String CLOSE_KEY;
	
	/**
	 * This function is called to ensure that a day read from a request's day mask is correct in the current input language. It should always be called before toRequestDays
	 * 
	 * @param day The day to check the validity from
	 * @return Whether day is valid or not
	 */
	public static boolean isValidDay(String day)
	{
		return day.equals(MONDAY) 
				|| day.equals(TUESDAY) 
				|| day.equals(WEDNESDAY)
				|| day.equals(THURSDAY)
				|| day.equals(FRIDAY)
				|| day.equals(SATURDAY)
				|| day.equals(SUNDAY);
	}
	
	// TODO check this, as it could cleanly replace the language strategy VVVVVV
	/**
	 * This function should be called every time that we read one VALID day (aka previously tested with isValidDay) from a request's day mask
	 * to transform it into a RequestDays so we can push it into the List<RequestDays> from Request
	 * 
	 * @param day The day to be transformed
	 * @return The equivalent RequestDays object
	 */
	public static RequestDays toRequestDays(String day)
	{
		if(day.equals(MONDAY)) return RequestDays.MONDAY;
		if(day.equals(TUESDAY)) return RequestDays.TUESDAY;
		if(day.equals(WEDNESDAY)) return RequestDays.WEDNESDAY;
		if(day.equals(THURSDAY)) return RequestDays.THURSDAY;
		if(day.equals(FRIDAY)) return RequestDays.FRIDAY;
		if(day.equals(SATURDAY)) return RequestDays.SATURDAY;
		if(day.equals(SUNDAY)) return RequestDays.SUNDAY;
		
		return null;
	}
}
