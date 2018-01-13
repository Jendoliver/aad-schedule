package model;

import java.util.Map;

public class MonthToProcess 
{
	// Map<Dias, FranjasHorarias>
	private Map<Integer, Map<Integer, Request>> grid;
	
	// TODO initialise
	// canelo
	// inicializa aqui hasta 28 (27) y en el ParserConfig anade los dias que PUEDEN faltar
	
	private void init()
	{
		//for(int i = 1; i < )
	}
	
	public int getMonthDays()
	{
		return grid.keySet().size();
	}
}
