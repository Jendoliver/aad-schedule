package model.parsers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.chrono.IsoChronology;

import exceptions.BadFormattedRequestException;
import exceptions.BadFormattedRequestException.Reason;
import globals.CalendarInfo;
import globals.Configuration;
import globals.InputStrings;
import model.Request;
import model.Request.HourFrame;
import model.RequestList;

/**
 * The class responsible for parsing the requests file and generating a RequestList.
 * // IMPORTANTE una mascara horaria pot incloure com a maxim 5 franges horaries
 * 
 * @author Jendoliver
 * @author Irene
 *
 */
public class ParserRequests extends Parser {

	public ParserRequests(String fileToParse) {
		this.fileToParse = fileToParse;
	}




	public RequestList parse() {

		String mainLine = "";
		BufferedReader parserRequestReader = null;
		Request request = new Request();
		RequestList requestList = new RequestList();
		int contador = 0;
		try {
			parserRequestReader = new BufferedReader(new FileReader(fileToParse));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			mainLine = parserRequestReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		while(mainLine != null) {
			String[] mainLineSplited = mainLine.split(" ");
			System.out.println("Request numero: " + contador);
			contador++;

			//CHECK NUMBER OF ARGUMENTS
			if(mainLineSplited.length == 6) {

				//VALUES FROM MAIN LINE SPLITED
				String activityName = mainLineSplited[0];
				String roomName = mainLineSplited[1];
				String dayFrameStart = mainLineSplited[2];
				String dayFrameFinish = mainLineSplited[3];
				String dayMask = mainLineSplited[4];
				String hourFrames = mainLineSplited[5];

				String monthStart = null;
				String yearStart = null;
				String monthFinish = null;
				String yearFinish = null;
				Request.HourFrame hourFrameForRequest = null;

				//ADDS ACTIVITY NAME AND ROOM NAME
				request.activityName = activityName;
				request.roomName = roomName;

				//CHECKS IF DAYFRAME IS CORRECT
				String[] dayFrameStartSplited = dayFrameStart.split("/");
				String[] dayFrameFinishSplited = dayFrameFinish.split("/");

				Request.DayFrame isCorrectDayFrame = isCorrectDayFrame(dayFrameStartSplited, dayFrameFinishSplited);
				if(isCorrectDayFrame != null) {
					request.dayFrame = isCorrectDayFrame;
				}else {
					//aqui error al log
					System.out.println("dayframe incorrect");
				}

				//CHECKS IF THE DAYMASK IS CORRECT
				//split("") will return an empty first value, so [0] doesn't count, start at 1
				String[] dayMaskSplited = dayMask.split("");
				if(dayMaskSplited.length <= 8 && dayMaskSplited.length > 0) {
					for(String day : dayMaskSplited) {
						if(day.equals(dayMaskSplited[0])) continue;
						if(InputStrings.isValidDay(day)) {
							request.requestedDays.add(InputStrings.toRequestDays(day));
						}else {
							//							throw new BadFormattedRequestException(Reason.DAY_MASK_INVALID_CHARACTER);
							System.out.println("DayMask invalid character");
						}
					}
				}else {
					//					throw new BadFormattedRequestException(Reason.DAY_MASK_TOO_MANY_ARGS);
					System.out.println("Request Days too many or not enough");
				}





			}
		}

		return requestList;
	}

	//NUEVO

	//CHECKS IF THE START AND END DAY ARE PLAUSIBLE AND IF THE MONTH AND YEAR ARE LIKE THE CONFIGURED ONES
	Request.DayFrame isCorrectDayFrame(String[] dayFrameStart, String[] dayFrameFinish) {
		Request.DayFrame dayFrame = null;
		if(dayFrameStart.length == 3 && dayFrameFinish.length == 3) {
			if(isCorrectMonth(dayFrameStart[1]) && isCorrectMonth(dayFrameFinish[1]) && isCorrectYear(dayFrameStart[2]) && isCorrectYear(dayFrameFinish[2])) {
				int dayStart = isParseable(dayFrameStart[0]);
				int dayFinish = isParseable(dayFrameFinish[0]);
				if(isCorrectDayInMonth(dayStart) && isCorrectDayInMonth(dayFinish)) {
					dayFrame.startDay = dayStart;
					dayFrame.endDay = dayFinish;
				}
			}
		}
		return dayFrame;
	}

	//CHECKS IF THE MONTH IS THE SAME AS THE CONFIGURED ONE
	boolean isCorrectMonth(String monthToCheck) {
		boolean isCorrect = false;
		if(monthToCheck.equals(Configuration.MONTH_TO_PROCESS)) {
			isCorrect = true;
		}
		return isCorrect;
	}

	//CHECKS IF THE YEAR IS THE SAME AS THE CONFIGURED ONE
	boolean isCorrectYear(String yearToCheck) {
		boolean isCorrect = false;
		if(yearToCheck.equals(Configuration.YEAR_TO_PROCESS)) {
			isCorrect = true;
		}
		return isCorrect;
	}

	//CHECKS IF THE STRING IS PARSEABLE
	public int isParseable(String toParse) {
		int toParseInt = 0;
		try {
			toParseInt = Integer.parseInt(toParse);
		}catch(NumberFormatException ex) {
			ex.printStackTrace();
		}
		return toParseInt;
	}

	public boolean isCorrectDayInMonth(int dayToCheck) {
		boolean isCorrect = false;
		if((dayToCheck-CalendarInfo.MONTH_DAY_NUM) > 0 && dayToCheck > 0) {
			isCorrect = true;
		}
		return isCorrect;
	}

	//CHECKS IF THE START AND END HOUR HAVE SENSE
	public boolean checkHourFrame(String[] startAndEndHour){
		boolean isCorrect = false;
		if(startAndEndHour.length == 2) {
			//			try {
			String startHour = startAndEndHour[0];
			String endHour = startAndEndHour[1];
			if(checkHoursFormat(startHour) && checkHoursFormat(endHour)) {
				int intStartHour = Integer.parseInt(startHour);
				int intEndHour = Integer.parseInt(endHour);
				if(!(intStartHour >= intEndHour)) {
					isCorrect = true;
				}else {
					//						throw new BadFormattedRequestException(Reason.HOUR_FRAME_FORMAT_INCORRECT);
					//error para el log
					System.out.println("HourFrame incorrect");
				}
			}
			//			}catch(BadFormattedRequestException ex) {
			//				throw ex;
			//			}
		}
		return isCorrect;
	}
	
	//CHECKS IF THE HOUR FORMAT IS CORRECT
		public boolean checkHoursFormat(String hour) {
			boolean isCorrect = false;
			if(hour.length() == 2) {
				try {
					int hourInt = Integer.parseInt(hour);
					isCorrect = true;
				}catch(NumberFormatException ex) {
					isCorrect = false;
					//				throw new BadFormattedRequestException(Reason.HOUR_FORMAT_INCORRECT);
					//error para el log
				}

			}else {
				//			throw new BadFormattedRequestException(Reason.HOUR_FORMAT_INCORRECT);
				//error para el log
			}
			return isCorrect;
		}

	///VIEJO


	//	public boolean isCorrectDayInMonth(int dayToCheck) {
	//		boolean isCorrect = true;
	//		if((dayToCheck-CalendarInfo.MONTH_DAY_NUM) > 0 || dayToCheck > 0) {
	//			isCorrect = true;
	//		}
	//		return isCorrect;
	//	}

	//CHECKS IF THE START AND END HOUR HAVE SENSE
	//	public boolean checkHourFrame(String[] startAndEndHour) throws BadFormattedRequestException {
	//		boolean isCorrect = false;
	//		if(startAndEndHour.length == 2) {
	//			//			try {
	//			String startHour = startAndEndHour[0];
	//			String endHour = startAndEndHour[1];
	//			if(checkHoursFormat(startHour) && checkHoursFormat(endHour)) {
	//				int intStartHour = Integer.parseInt(startHour);
	//				int intEndHour = Integer.parseInt(endHour);
	//				if(!(intStartHour >= intEndHour)) {
	//					isCorrect = true;
	//				}else {
	//					//						throw new BadFormattedRequestException(Reason.HOUR_FRAME_FORMAT_INCORRECT);
	//					System.out.println("HourFrame incorrect");
	//				}
	//			}
	//			//			}catch(BadFormattedRequestException ex) {
	//			//				throw ex;
	//			//			}
	//		}
	//		return isCorrect;
	//	}

	

	//CHECKS IF DAY IS INCLUDED IN THE MONTH
	public boolean checkDayIsInTheMonth(int dayToCheck) {
		boolean isCorrect = false;
		if((dayToCheck-CalendarInfo.MONTH_DAY_NUM) > 0 || dayToCheck > 0) {
			isCorrect = true;
		}
		return isCorrect;
	}

	//CHECKS IF THE STRING IS PARSEABLE
	//	public int isParseable(String toParse) throws BadFormattedRequestException, NumberFormatException {
	//		int toParseInt = 0;
	//		try {
	//			toParseInt = Integer.parseInt(toParse);
	//		}catch(NumberFormatException ex) {
	//			throw new BadFormattedRequestException(Reason.FORMAT_INCORRECT);
	//		}
	//		return toParseInt;
	//	}
}
