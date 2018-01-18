package junit.magner;

import static org.junit.Assert.*;

import org.junit.Test;

import globals.Configuration;
import globals.Constants;
import globals.InputStrings;
import globals.OutputStrings;
import model.parsers.ParserInternational;

public class ParserInternationalTests 
{
	@Test
	public void parseTestInput() 
	{
		Configuration.INPUT_LANG = "ESP";
		ParserInternational parserInternational = new ParserInternational(Constants.FileNames.INTERNATIONAL_PRE.getName() + Configuration.INPUT_LANG, true);
		parserInternational.parse();
		
		assertEquals("L", InputStrings.MONDAY);
		assertEquals("M", InputStrings.TUESDAY);
		assertEquals("X", InputStrings.WEDNESDAY);
		assertEquals("J", InputStrings.THURSDAY);
		assertEquals("V", InputStrings.FRIDAY);
		assertEquals("S", InputStrings.SATURDAY);
		assertEquals("D", InputStrings.SUNDAY);
		
		assertEquals("Cerrado", InputStrings.CLOSE_KEY);
	}
	
	@Test
	public void parseTestOutput()
	{
		Configuration.OUTPUT_LANG = "ESP";
		ParserInternational parserInternational = new ParserInternational(Constants.FileNames.INTERNATIONAL_PRE.getName() + Configuration.OUTPUT_LANG, false);
		parserInternational.parse();
		
		assertEquals("Agenda", OutputStrings.TITLE);
		
		assertEquals("Lunes", OutputStrings.Days.MONDAY);
		assertEquals("Martes", OutputStrings.Days.TUESDAY);
		assertEquals("Miercoles", OutputStrings.Days.WEDNESDAY);
		assertEquals("Jueves", OutputStrings.Days.THURSDAY);
		assertEquals("Viernes", OutputStrings.Days.FRIDAY);
		assertEquals("Sabado", OutputStrings.Days.SATURDAY);
		assertEquals("Domingo", OutputStrings.Days.SUNDAY);
		
		assertEquals("Enero", OutputStrings.Months.JANUARY);
		assertEquals("Febrero", OutputStrings.Months.FEBRUARY);
		assertEquals("Marzo", OutputStrings.Months.MARCH);
		assertEquals("Abril", OutputStrings.Months.APRIL);
		assertEquals("Mayo", OutputStrings.Months.MAY);
		assertEquals("Junio", OutputStrings.Months.JUNE);
		assertEquals("Julio", OutputStrings.Months.JULY);
		assertEquals("Agosto", OutputStrings.Months.AUGUST);
		assertEquals("Septiembre", OutputStrings.Months.SEPTEMBER);
		assertEquals("Octubre", OutputStrings.Months.OCTOBER);
		assertEquals("Noviembre", OutputStrings.Months.NOVEMBER);
		assertEquals("Diciembre", OutputStrings.Months.DECEMBER);
	}

}
