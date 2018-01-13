package model;

import java.util.HashMap;
import java.util.Map;

import globals.CalendarInfo;

public class MonthToProcess 
{
	// Map<Dias, FranjasHorarias>
	private Map<Integer, Map<Integer, Request>> grid;
	
	// TODO initialise
	// canelo
	// inicializa aqui hasta 28 (27) y en el ParserConfig anade los dias que PUEDEN faltar
	
	private void init()
	{
		for (int i=0; i < CalendarInfo.MONTH_DAY_NUM; i++) {
			grid.put(i, null);
			for(int j=0; j < 24; j++) {
				//TODO Refactor!
				Map<Integer, Request> dayMap = null ;
				dayMap.put(j, null);
				grid.put(i,dayMap);
			}
		}
	}
	public boolean isEmptyHour(int day, int hour) {
		if(grid.get(day).get(hour) == null) {
			return true;
		}
		return false;
	}
	
	public int getMonthDays()
	{
		return grid.keySet().size();
	}
}
