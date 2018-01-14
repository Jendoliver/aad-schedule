package model;

import java.util.Map;

/**
 * The functional interface for the OutputGenerator different strategies
 * 
 * @author Jendoliver
 *
 */
public interface OutputGeneratorStrategy 
{
	void print(Map<String, RoomSchedule> requestList);
}
