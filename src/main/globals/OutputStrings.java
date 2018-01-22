package globals;

/**
 * These are the Strings to be used by model.OutputGenerator when generating the calendar.
 * Codes: 001, 002, 004
 * 
 * @author Jendoliver
 *
 */
public final class OutputStrings 
{
	// 001
	public static String TITLE;
		
	// 002
	public static class Days
	{
		public static String MONDAY;
		public static String TUESDAY;
		public static String WEDNESDAY;
		public static String THURSDAY;
		public static String FRIDAY;
		public static String SATURDAY;
		public static String SUNDAY;
	}
	
	// 004
	public static class Months
	{
		public static String JANUARY;
		public static String FEBRUARY;
		public static String MARCH;
		public static String APRIL;
		public static String MAY;
		public static String JUNE;
		public static String JULY;
		public static String AUGUST;
		public static String SEPTEMBER;
		public static String OCTOBER;
		public static String NOVEMBER;
		public static String DECEMBER;
	}
	
	// 005
	public static String CLOSE_KEY;
	
	public static String numericMonthToFullString(int month)
	{
		if(month == 1) return Months.JANUARY;
		if(month == 2) return Months.FEBRUARY;
		if(month == 3) return Months.MARCH;
		if(month == 4) return Months.APRIL;
		if(month == 5) return Months.MAY;
		if(month == 6) return Months.JUNE;
		if(month == 7) return Months.JULY;
		if(month == 8) return Months.AUGUST;
		if(month == 9) return Months.SEPTEMBER;
		if(month == 10) return Months.OCTOBER;
		if(month == 11) return Months.NOVEMBER;
		if(month == 12) return Months.DECEMBER;
		
		return null;
	}
}
