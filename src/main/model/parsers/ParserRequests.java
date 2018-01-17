package model.parsers;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;

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
		String activityName;
		String roomName;
		String dayFrameStart;
		String dayFrameFinish;
		String dayMask;
		String hourFrames;
		boolean isEverythingCorrect;

		try {
			parserRequestReader = new BufferedReader(new FileReader(fileToParse));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			mainLine = parserRequestReader.readLine();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}

		while(mainLine != null) {
			String[] mainLineSplited = mainLine.split(" ");
			System.out.println("Request numero: " + contador);
			contador++;
			try {
				//CHECK NUMBER OF ARGUMENTS
				if(mainLineSplited.length == 6) {

					//VALUES FROM MAIN LINE SPLITED
					activityName = mainLineSplited[0];
					roomName = mainLineSplited[1];
					dayFrameStart = mainLineSplited[2];
					dayFrameFinish = mainLineSplited[3];
					dayMask = mainLineSplited[4];
					hourFrames = mainLineSplited[5];
					isEverythingCorrect = true;

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
						isEverythingCorrect = false;
						throw new BadFormattedRequestException(Reason.DAY_FORMAT_INCORRECT);
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
								isEverythingCorrect = false;
								throw new BadFormattedRequestException(Reason.DAY_MASK_INVALID_CHARACTER);
							}
						}
					}else {
						isEverythingCorrect = false;
						throw new BadFormattedRequestException(Reason.DAY_MASK_TOO_MANY_ARGS);
					}

					//CHECKS IF THE HOURS REQUESTED ARE WELL FORMATED
					if(checkHourFrame(hourFrames) != null) {
						request.hourFrames = checkHourFrame(hourFrames);
					}else {
						isEverythingCorrect = false;
					}

					//IF EVERYTHING IS CORRECT ADDS THE REQUEST TO THE REQUEST LIST
					if(isEverythingCorrect) {
						requestList.add(request);
					}
				}else {
					isEverythingCorrect = false;
					throw new BadFormattedRequestException(Reason.ARGS);
				}
			}catch(BadFormattedRequestException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return requestList;
	}


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
	public ArrayList<Request.HourFrame> checkHourFrame(String hourFrames) {
		Request.HourFrame hourFrame = null;
		ArrayList<HourFrame> hourFramesList = new ArrayList<>();
		String[] hourFramesSplited = hourFrames.split("_");
		int startHour = 0;
		int endHour = 0;
		try {
			if(hourFramesSplited.length < 6) {
				for(int i = 0; i < hourFramesSplited.length; i = i+2) {
					startHour = checkHoursFormat(hourFramesSplited[i]);
					endHour = checkHoursFormat(hourFramesSplited[i+1]);
					if(startHour != -1 && endHour != -1) {
						if(startHour < endHour) {
							hourFrame.startHour = startHour;
							hourFrame.endHour = endHour;
							hourFramesList.add(hourFrame);
						}else {
							throw new BadFormattedRequestException(Reason.HOUR_FRAME_TOO_SMALL);
						}
					}
				}
				if(hourFramesList.size() == hourFramesSplited.length) {
					return hourFramesList;
				}else {
					throw new BadFormattedRequestException(Reason.HOUR_FRAME_FORMAT_INCORRECT);
				}
			}else {
				throw new BadFormattedRequestException(Reason.TOO_MANY_HOUR_FRAMES);
			}
		}catch(BadFormattedRequestException ex) {
			System.out.println(ex.getMessage());
			return null;
		}

	}

	//CHECKS IF THE HOUR FORMAT IS CORRECT
	public int checkHoursFormat(String hour) {
		int hourInt = -1;
		try {
			if(hour.length() == 2) {
				try {
					hourInt = Integer.parseInt(hour);
				}catch(NumberFormatException ex) {
					System.out.println(ex.getMessage());
				}
			}else {
				throw new BadFormattedRequestException(Reason.HOUR_FORMAT_INCORRECT);
			}
		}catch(BadFormattedRequestException ex) {
			System.out.println(ex.getMessage());
		}
		return hourInt;
	}
}
