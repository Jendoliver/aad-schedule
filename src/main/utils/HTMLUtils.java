package utils;

/**
 * Just some useful HTML snippets
 * 
 * @author Jendoliver
 *
 */
public class HTMLUtils 
{
	public static final String BOOTSTRAP_HEAD = "    <meta charset='UTF-8'>\r\n" + 
			"    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\r\n" + 
			"    <meta http-equiv='X-UA-Compatible' content='ie=edge'>\r\n" + 
			"    <link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy' crossorigin='anonymous'>\r\n";
	
	public static final String OUT_BLACK_CLOSED_YELLOW_STYLE = "<style type='text/css'>\r\n" + 
			"        h3 { color: grey; font-style: oblique; }\r\n" + 
			"    \r\n" + 
			"        th { width: 200px; height: 100px; text-align: center; }\r\n" + 
			"        td { width: 200px; height: 50px; text-align: center; }\r\n" + 
			"        table { margin: auto; margin-bottom: 100px; }\r\n" + 
			"        \r\n" + 
			"        .hourFrame { font-style: oblique; font-weight: bold; }\r\n" + 
			"        .outOfMonth { background-color: rgba(0, 0, 0, 0.5); }\r\n" + 
			"        .closed { background-color: lemonchiffon; }\r\n" + 
			"    </style>";
	
	public static final String COLORS_CSS = "<style type='text/css'>\r\n" + 
			"        .indigo { background-color: #9fa8da; }\r\n" + 
			"        .lightblue { background-color: #4fc3f7; }\r\n" + 
			"        .cyan { background-color: #4dd0e1; }\r\n" + 
			"        .teal { background-color: #80cbc4; }\r\n" + 
			"        .green { background-color: #81c784; }\r\n" + 
			"        .lightgreen { background-color: #ccff90; }\r\n" + 
			"        .purple { background-color: #b39ddb; }\r\n" + 
			"    </style>";
	
	public static enum ColorClassedTdOpenTag
	{
		INDIGO("<td class='indigo'>"),
		LIGHTBLUE("<td class='lightblue'>"),
		CYAN("<td class='cyan'>"),
		TEAL("<td class='teal'>"),
		GREEN("<td class='green'>"),
		LIGHTGREEN("<td class='lightgreen'>"),
		PURPLE("<td class='purple'>");
		
		private String html;
		private ColorClassedTdOpenTag(String html) { this.html = html; }
		public String getHtml() { return html; }
	}
}
