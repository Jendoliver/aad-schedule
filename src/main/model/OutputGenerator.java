package model;

import java.util.Map;

/**
 * This class is responsible for executing its OutputGeneratorStrategy, which is set in the configuration file.
 * The default strategy is HTML.
 * 
 * @author Jendoliver
 *
 */
public class OutputGenerator 
{
	private OutputGeneratorStrategy strategy;
	
	public OutputGenerator() { strategy = new OutputGeneratorStrategyHTML(); }
	
	public void setStrategy(OutputGeneratorStrategy strategy)
	{
		this.strategy = strategy;
	}
	
	public void print(Map<String, RoomSchedule> roomSchedules)
	{
		strategy.print(roomSchedules);
	}
}
