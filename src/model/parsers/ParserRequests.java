package model.parsers;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.time.chrono.IsoChronology;

import exceptions.BadFormattedRequestException;
import exceptions.BadFormattedRequestException.Reason;
import globals.CalendarInfo;
import globals.Configuration;
import globals.InputStrings;
import model.Request;
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

		String mainLine;
		BufferedReader parserRequestReader = null;
		Request request = new Request();
		RequestList requestList = new RequestList();

		try{
			parserRequestReader = new BufferedReader(new FileReader(fileToParse));
			mainLine = parserRequestReader.readLine();

			while(mainLine != null) {
				String[] mainLineSplited = mainLine.split(" ");

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

					//ADDS ACTIVITY NAME AND ROOM NAME
					request.activityName = activityName;
					request.roomName = roomName;

					//CHECK IF THE DAYFRAMESTART IS OK
					String[] dayFrameStartSplited = dayFrameStart.split("/");
					String[] dayFrameFinishSplited = dayFrameFinish.split("/");

					//CHECK IF DATES ARE CORRECT
					//START DATE
					int dayStart = 0;
					if(isParseable(dayFrameStartSplited[0]) != 0) {
						dayStart = isParseable(dayFrameStartSplited[0]);
						monthStart = dayFrameStartSplited[1];
						yearStart = dayFrameStartSplited[2];	
					}else if(isCorrectDayInMonth(dayStart)) {
						request.dayFrame.startDay = dayStart;
					}else {
						throw new BadFormattedRequestException(Reason.DAY_FORMAT_INCORRECT);
					}
								
					//FINISH DATE
					int dayFinish = 0;
					if(isParseable(dayFrameFinishSplited[0]) != 0) {
						dayFinish = Integer.parseInt(dayFrameFinishSplited[0]);
						monthFinish = dayFrameFinishSplited[1];
						yearFinish = dayFrameStartSplited[2];	
						
					}else if(isCorrectDayInMonth(dayFinish)) {
						request.dayFrame.endDay = dayFinish;
					}else {
						throw new BadFormattedRequestException(Reason.DAY_FORMAT_INCORRECT);
					}
																
					if(!monthStart.equals(Configuration.MONTH_TO_PROCESS)) {
						throw new BadFormattedRequestException(Reason.MONTH_OUT_OF_BOUNDS);
					}
					//MEJORAR: SI EL MES ES EL SIGUIENTE, AÑADIR LA REQUEST HASTA EL FINAL
					else if(!monthFinish.equals(Configuration.MONTH_TO_PROCESS)) {
						throw new BadFormattedRequestException(Reason.MONTH_OUT_OF_BOUNDS);
					}
					if (!yearStart.equals(Configuration.YEAR_TO_PROCESS)) {
						throw new BadFormattedRequestException(Reason.YEAR_OUT_OF_BOUNDS);
					}
					//MEJORAR: SI EL MES ES EL SIGUIENTE, AÑADIR LA REQUEST HASTA EL FINAL
					else if (!yearFinish.equals(Configuration.YEAR_TO_PROCESS)) {
						throw new BadFormattedRequestException(Reason.YEAR_OUT_OF_BOUNDS);
					}

					//THIS CHECKS IF DAY MASK IS OK
					//split("") will return an empty first value, so [0] doesn't count, start at 1
					String[] dayMaskSplited = dayMask.split("");
					if(dayMaskSplited.length <= 8) {
						for(String day : dayMaskSplited) {
							if(day.equals(dayMaskSplited[0])) continue;
							if(!InputStrings.isValidDay(day)) {
								throw new BadFormattedRequestException(Reason.DAY_MASK_INVALID_CHARACTER);
							}else {
								request.requestedDays.add(InputStrings.toRequestDays(day));
							}
						}
					}else {
						throw new BadFormattedRequestException(Reason.DAY_MASK_TOO_MANY_ARGS);
					}

					//THIS CHECKS IF HOUR FRAME IS CORRECT
					//TO-DO CHECK IF THE CHAR TO SPLIT IS CORRECT AKA IF THEY INPUT SMTH ELSE
					String[] hours = hourFrames.split("_");
					for(String hour : hours) {
						//THIS CHECKS IF THE HOUR FRAME IS CORRECT
						String[] startAndEndHour = hour.split("-");
						boolean isCorrectHourFrame = checkHourFrame(startAndEndHour);
						if(isCorrectHourFrame) {
							Request.HourFrame hourFrameForRequest = null;
							hourFrameForRequest.startHour = Integer.parseInt(startAndEndHour[0]);
							hourFrameForRequest.endHour = Integer.parseInt(startAndEndHour[1]);
							request.hourFrames.add(hourFrameForRequest);
						}
					}
				}else {
					throw new BadFormattedRequestException(Reason.ARGS);
				}
				requestList.add(request);
				mainLine = parserRequestReader.readLine();
			}
		}catch(IOException ex) {
			System.out.println(ex.getMessage());
		} catch (BadFormattedRequestException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return requestList;
	}
	
	
	public boolean isCorrectDayInMonth(int dayToCheck) {
		boolean isCorrect = true;
		if((dayToCheck-CalendarInfo.MONTH_DAY_NUM) > 0 || dayToCheck > 0) {
			isCorrect = true;
		}
		return isCorrect;
	}

	//CHECKS IF THE START AND END HOUR HAVE SENSE
	public boolean checkHourFrame(String[] startAndEndHour) throws BadFormattedRequestException {
		boolean isCorrect = false;
		if(startAndEndHour.length == 2) {
			try {
				String startHour = startAndEndHour[0];
				String endHour = startAndEndHour[1];
				if(checkHoursFormat(startHour) && checkHoursFormat(endHour)) {
					int intStartHour = Integer.parseInt(startHour);
					int intEndHour = Integer.parseInt(endHour);
					if(!(intStartHour >= intEndHour)) {
						isCorrect = true;
					}else {
						throw new BadFormattedRequestException(Reason.HOUR_FRAME_FORMAT_INCORRECT);
					}
				}
			}catch(BadFormattedRequestException ex) {
				throw ex;
			}
		}
		return isCorrect;
	}

	//CHECKS IF THE HOUR FORMAT IS CORRECT
	public boolean checkHoursFormat(String hour) throws BadFormattedRequestException {
		boolean isCorrect = false;
		if(hour.length() == 2) {
			try {
				int hourInt = Integer.parseInt(hour);
				isCorrect = true;
			}catch(NumberFormatException ex) {
				isCorrect = false;
				throw new BadFormattedRequestException(Reason.HOUR_FORMAT_INCORRECT);
			}

		}else {
			throw new BadFormattedRequestException(Reason.HOUR_FORMAT_INCORRECT);
		}
		return isCorrect;
	}

	//CHECKS IF DAY IS INCLUDED IN THE MONTH
	public boolean checkDayIsInTheMonth(int dayToCheck) {
		boolean isCorrect = false;
		if((dayToCheck-CalendarInfo.MONTH_DAY_NUM) > 0 || dayToCheck > 0) {
			isCorrect = true;
		}
		return isCorrect;
	}
	
	//CHECKS IF THE STRING IS PARSEABLE
	public int isParseable(String toParse) throws BadFormattedRequestException, NumberFormatException {
		int toParseInt = 0;
		try {
			toParseInt = Integer.parseInt(toParse);
		}catch(NumberFormatException ex) {
			throw new BadFormattedRequestException(Reason.FORMAT_INCORRECT);
		}
		return toParseInt;
	}
}
