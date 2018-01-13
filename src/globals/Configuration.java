package globals;

import model.OutputGeneratorStrategy;
import model.OutputGeneratorStrategyHTML;
import model.RequestListSortingCloseFirstFIFOStrategy;
import model.RequestListSortingStrategy;

/**
 * This class provides information to model.parsers.ParserRequests, model.parsers.ParserInternational, model.RequestList and model.OutputGenerator
 * Its values are set by model.parsers.ParserConfig
 * 
 * @YEAR_TO_PROCESS The year which model.parsers.ParserRequests should receive requests for. 
 * 	The requests containing years different than this should be instantly invalidated and logged.
 * 
 * @MONTH_TO_PROCESS The month which model.parsers.ParserRequests should receive requests for. 
 * 	IMPORTANT: If it receives a request that CONTAINS this month, it should still be processed.
 * 
 * @INPUT_LANG The extension of the international.X file which should be parsed by model.parser.ParserInternational
 * 	to set the values of globals.InputStrings
 * 
 * @OUTPUT_LANG The extension of the international.X file which should be parsed by model.parser.ParserInternational
 * 	to set the values of globals.OutputStrings
 * 
 * @PARSER_REQUEST_STRATEGY The language strategy to use when parsing the requests file. This depends on @INPUT_LANG and should be
 * 	set accordingly by model.parsers.ParserConfig
 * 
 * @REQUEST_SORTING_STRATEGY The sorting strategy to use by model.RequestList, aka the priority of requests. 
 * 	NOTE: This is for future extensions and doesn't need to be set by model.parsers.ParserConfig.
 * 
 * @OUTPUT_GENERATOR_STRATEGY The output strategy to use by model.OutputGenerator (whether to output an HTML, XML, etc...).
 *  NOTE: This is for future extensions and doesn't need to be set by model.parsers.ParserConfig.
 * 
 * @author Jendoliver
 *
 */
public final class Configuration 
{	
	public static String YEAR_TO_PROCESS = null; // ParserRequests THIS IS A NUMBER (declared String to avoid casting on ParserRequests)
	public static String MONTH_TO_PROCESS = null; // ParserRequests THIS IS A NUMBER (declared String to avoid casting on ParserRequests)
	public static String INPUT_LANG = null; // ParserInternational
	public static String OUTPUT_LANG = null; // ParserInternational
	public static RequestListSortingStrategy REQUEST_SORTING_STRATEGY = new RequestListSortingCloseFirstFIFOStrategy(); // RequestList
	public static OutputGeneratorStrategy OUTPUT_GENERATOR_STRATEGY = new OutputGeneratorStrategyHTML(); // OutputGenerator
}
