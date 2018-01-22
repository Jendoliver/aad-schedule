package utils;

/**
 * Just some useful HTML snippets
 * 
 * @author Jendoliver
 *
 */
public class HTMLUtils 
{
	public static final String BOOTSTRAP_HEAD = "<head>\r\n" + 
			"    <meta charset='UTF-8'>\r\n" + 
			"    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\r\n" + 
			"    <meta http-equiv='X-UA-Compatible' content='ie=edge'>\r\n" + 
			"    <link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy' crossorigin='anonymous'>\r\n" + 
			"    <title>Schedule</title>\r\n" + 
			"</head>";
	
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
			"        .requested { background-color: palegreen; }\r\n" + 
			"    </style>";
}
