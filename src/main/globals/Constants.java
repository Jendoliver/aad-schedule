package globals;

/**
 * The Constants of the application
 * 
 * @author Jendoliver
 *
 */
public final class Constants 
{
	public static final int MAX_HOUR_FRAME_NUM = 5;
	
	public enum FileNames 
	{
		CONFIG("config.txt"), 
		INTERNATIONAL_PRE("international."), // This doesn't have an extension because it depends on the configuration file.
		REQUESTS("requests.txt"); 
		
		private String name;
		private FileNames(String name) { this.name = name; }
		
		/**
		 * Returns the actual name of the file as a String
		 */
		public String getName() { return name; }
	}
}
