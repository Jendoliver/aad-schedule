package exceptions;

import globals.Constants;

/**
 * The possible exception to be treated by model.parsers.ParserRequests
 * 
 * @author Jendoliver
 *
 */
public class BadFormattedRequestException extends Exception
{
	private static final long serialVersionUID = -7080642255115335692L;

	public enum Reason
	{
		ARGS("The number of arguments of the request is incorrect."),
		DAY_MASK_INVALID_CHARACTER("One or more characters used in the request's day mask is incorrect."),
		DAY_MASK_TOO_MANY_ARGS("The day mask has more than 7 days."),
		DAY_MASK_REPEATED_DAY("The day mask has a repeated day."),
		HOUR_FRAME_TOO_SMALL("The hour frames have to be at least one hour long."),
		HOUR_FRAME_FORMAT_INCORRECT("The hour frames are not well formated"),
		HOUR_FORMAT_INCORRECT("The hour format is not correct. Only two digits allowed."),
		DAY_FORMAT_INCORRECT("The input day for the date is incorrect"),
		FORMAT_INCORRECT("Only digits allowed."),
		MONTH_OUT_OF_BOUNDS("Requested month not included."),
		YEAR_OUT_OF_BOUNDS("Requested month not included."),
		TOO_MANY_HOUR_FRAMES("Too many hour frames were provided (max number: " + Constants.MAX_HOUR_FRAME_NUM + ")");
	
		private String msg;
		private Reason(String msg) { this.msg = msg; }
		public String getMsg() { return msg; }
	}
	
	public BadFormattedRequestException(Reason reason)
	{
		super(reason.msg);
	}
}
