package model.parsers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.BadFormattedRequestException;
import exceptions.BadFormattedRequestException.Reason;
import globals.CalendarInfo;
import globals.Configuration;
import globals.InputStrings;
import globals.Constants.FileNames;
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

public class ParserRequests extends Parser 
{
	
	private static final Logger logger = LoggerFactory.getLogger(ParserRequests.class);
	
	private List<String> loadedRequests;
	private RequestList acceptedRequests = new RequestList();

	public ParserRequests(FileNames fileToParse) {
		loadedRequests = new ArrayList<>();
		loadRequests(fileToParse.getName());
	}

	public ParserRequests(List<String> stringsToParse) {
		loadedRequests = stringsToParse;
	}

	public RequestList parse() 
	{
		for(String request : loadedRequests)
		{
			try {
				parse(request);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return acceptedRequests;
	}
	
	public int getLoadedRequestsNumber() { return loadedRequests.size(); }

	private void parse(String request) throws BadFormattedRequestException
	{
		Request requestToAdd = new Request();
		String activityName;
		String roomName;
		String dayFrameStart;
		String dayFrameFinish;
		String dayMask;
		String hourFrames;
		boolean isEverythingCorrect;
		String[] mainLineSplited = request.split(" ");

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

			//				ADDS ACTIVITY NAME AND ROOM NAME
			requestToAdd.activityName = activityName;
			requestToAdd.roomName = roomName;

			//CHECKS IF DAYFRAME IS CORRECT
			String[] dayFrameStartSplited = dayFrameStart.split("/");
			String[] dayFrameFinishSplited = dayFrameFinish.split("/");

			Request.DayFrame isCorrectDayFrame = isCorrectDayFrame(dayFrameStartSplited, dayFrameFinishSplited, requestToAdd);
			if(isCorrectDayFrame != null) {
				requestToAdd.dayFrame = isCorrectDayFrame;
			}else {
				isEverythingCorrect = false;
				throw new BadFormattedRequestException(Reason.DAY_FORMAT_INCORRECT);
			}

			//CHECKS IF THE DAYMASK IS CORRECT
			//split("") will return an empty first value, so [0] doesn't count, start at 1
			int cont = 0;
			String[] dayMaskSplited = dayMask.split("");
			if(dayMaskSplited.length <= 7 && dayMaskSplited.length > 0) {
				for(String day : dayMaskSplited) {
					cont = 0;
					//CHECK IF THE DAY IS REPEATED IN THE DAYMASK
					for(int j = 0; j < dayMaskSplited.length; j++) {
						if(day.equals(dayMaskSplited[j])) {
							cont++;
							if(cont == 2) {
								throw new BadFormattedRequestException(Reason.DAY_MASK_REPEATED_DAY);
							}
						}
					}
					//						if(day.equals(dayMaskSplited[0])) continue;
					if(InputStrings.isValidDay(day)) {
						requestToAdd.requestedDays.add(InputStrings.toRequestDays(day));
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
			ArrayList<Request.HourFrame> hourFrameToAdd = checkHourFrame(hourFrames, requestToAdd);
			if(hourFrameToAdd != null) {
				requestToAdd.hourFrames = hourFrameToAdd;
			}else {
				isEverythingCorrect = false;
			}

			//IF EVERYTHING IS CORRECT ADDS THE REQUEST TO THE REQUEST LIST
			if(isEverythingCorrect) {
				acceptedRequests.add(requestToAdd);
			}
		}else {
			isEverythingCorrect = false;
			throw new BadFormattedRequestException(Reason.ARGS);
		}
	}

	private void loadRequests(String fileToParse)
	{
		try (BufferedReader br = new BufferedReader(new FileReader(fileToParse)))
		{
			String sCurrentLine = "";
			while ((sCurrentLine = br.readLine()) != null) 
			{
				loadedRequests.add(sCurrentLine);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	/*
	 * HELPER METHODS
	 * 
	 * 
	 */

	//CHECKS IF THE START AND END DAY ARE PLAUSIBLE AND IF THE MONTH AND YEAR ARE LIKE THE CONFIGURED ONES
	//IF THE MONTH START IS BEFORE THE MONTH CONFIGURED, IT CLAMPS IT
	//IF THE MONTH FINISH IS AFTER THE MONTH CONFIGURED, IT CLAMPS IT
	private Request.DayFrame isCorrectDayFrame(String[] dayFrameStart, String[] dayFrameFinish, Request request) throws BadFormattedRequestException {
		Request.DayFrame dayFrameForRequest = null;
		if(dayFrameStart.length == 3 && dayFrameFinish.length == 3) {

			int monthStart = isParseable(dayFrameStart[1]);
			int monthFinish = isParseable(dayFrameFinish[1]);
			int dayStart = isParseable(dayFrameStart[0]);
			int dayFinish = isParseable(dayFrameFinish[0]);
			int monthToProcess = isParseable(Configuration.MONTH_TO_PROCESS);
			int yearStart = isParseable(dayFrameStart[2]);
			// int yearFinish = isParseable(dayFrameFinish[2]); CHECK not used?
			int yearToProcess = isParseable(Configuration.YEAR_TO_PROCESS);
			if(isMonthPlausible(monthStart) && isMonthPlausible(monthFinish)) {
				if(yearStart > yearToProcess) {
					throw new BadFormattedRequestException(Reason.YEAR_OUT_OF_BOUNDS);
				}
				else if(yearStart < yearToProcess){
					monthStart = monthToProcess;
					dayStart = 1;
				}else {
					if(monthFinish == 0 && monthStart == 0) {
						throw new BadFormattedRequestException(Reason.FORMAT_INCORRECT);
					}else {
						if(monthStart > monthToProcess) {
							throw new BadFormattedRequestException(Reason.MONTH_OUT_OF_BOUNDS);
						}else {
							if(monthStart < monthToProcess) {
								dayStart = 1;
								monthStart = monthToProcess;
							}
						}
						if(monthFinish < monthToProcess) {
							throw new BadFormattedRequestException(Reason.MONTH_OUT_OF_BOUNDS);
						}else {
							if(monthFinish > monthToProcess) {
								dayFinish = CalendarInfo.MONTH_DAY_NUM;
								monthFinish = monthToProcess;
							}
						}
					}
				}
				if(isCorrectDayInMonth(dayStart) && isCorrectDayInMonth(dayFinish)) {
					dayFrameForRequest =  request.new DayFrame(dayStart, dayFinish);
				}else {
					throw new BadFormattedRequestException(Reason.DAY_FORMAT_INCORRECT);
				}
			}else {
				throw new BadFormattedRequestException(Reason.FORMAT_INCORRECT);
			}

		}else {
			throw new BadFormattedRequestException(Reason.FORMAT_INCORRECT);
		}
		return dayFrameForRequest;
	}

	//CHECKS IF THE MONTH IS PLAUSIBLE
	private boolean isMonthPlausible(int month) {
		if(month >=1 && month <=12) {
			return true;
		}else {
			return false;
		}
	}

	//CHECKS IF THE STRING IS PARSEABLE
	private int isParseable(String toParse) {
		int toParseInt = 0;
		try {
			toParseInt = Integer.parseInt(toParse);
		}catch(NumberFormatException ex) {
			logger.error(ex.getMessage());
		}
		return toParseInt;
	}

	//CHECKS IF THE DAY IS PLAUSIBLE IN THE CONFIGURATED MONTH
	private boolean isCorrectDayInMonth(int dayToCheck) {
		if((CalendarInfo.MONTH_DAY_NUM-dayToCheck) >= 0 && dayToCheck > 0) {
			return true;
		}else {
			return false;
		}
	}

	//CHECKS IF THE START AND END HOUR HAVE SENSE
	private ArrayList<Request.HourFrame> checkHourFrame(String hourFrames, Request request) {
		Request.HourFrame hourFrame = null;
		ArrayList<HourFrame> hourFramesList = new ArrayList<>();
		String[] hourFramesSplited = hourFrames.split("_");
		int startHour = 0;
		int endHour = 0;
		try {
			if(hourFramesSplited.length < 6) {

				for(int i = 0; i < hourFramesSplited.length; i++) {
					String[] hours = hourFramesSplited[i].split("-");
					startHour = checkHoursFormat(hours[0]);
					endHour = checkHoursFormat(hours[1]);
					if(startHour != -1 && endHour != -1) {
						if(startHour < endHour) {
							hourFrame = request.new HourFrame(startHour, endHour);
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
			logger.error(ex.getMessage());
			return null;
		}

	}

	//CHECKS IF THE HOUR FORMAT IS CORRECT
	private int checkHoursFormat(String hour) {
		int hourInt = -1;
		try {
			if(hour.length() <= 2) {
				try {
					hourInt = Integer.parseInt(hour);
				}catch(NumberFormatException ex) {
					logger.error(ex.getMessage());
				}
			}else {
				throw new BadFormattedRequestException(Reason.HOUR_FORMAT_INCORRECT);
			}
		}catch(BadFormattedRequestException ex) {
			logger.error(ex.getMessage());
		}
		return hourInt;
	}
}
