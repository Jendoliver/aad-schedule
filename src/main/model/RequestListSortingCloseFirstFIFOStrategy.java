package model;

import globals.Configuration;
import globals.InputStrings;

/**
 *  Observacions sobre el fitxer peticions.txt:
	a) Les activitats s’assignen per ordre d’aparició.
	En cas de conflicte, s’afegeix la informació de les peticions no assignades en un fitxer
	incidencies.log. (throw BadFormattedRequestException + catch to log)
	
	En cas que l’activitat es repeteixi, s’acumula la petició a l’assignada anteriorment,
	independentment de l’ordre en que es trobi, així doncs, les peticions de ReunioJava,
	encara que estiguin separades en el fitxer, rebran prioritat abans que les de ReunioC,
	malgrat que el conflicte es produeixi.
	
	b) Activitat especial “Tancat”:
	Aquesta activitat tindrà un comportament especial. En concret, l’acció corresponent
	serà, en el fitxer html resultant, mostrar el quadre ombrejat de color gris seguint el patró
	marcat per dies i hores.
	Aquesta incidència ha de ser la primera en executar-se independentment del seu ordre
	d’aparició al fitxer.
 * 
 * @author Jandol
 * @author Raikish
 *
 */
public class RequestListSortingCloseFirstFIFOStrategy implements RequestListSortingStrategy 
{
	@Override
	public RequestList sort(RequestList requestList) 
	{
		
		RequestList newRequestList = new RequestList();
		String activityNameComparator  = InputStrings.CLOSE_KEY;
		RequestList auxiliarRequestTest = new RequestList();
		auxiliarRequestTest = (RequestList) requestList.clone();
		
		while(!requestList.isEmpty()) {
			for (Request request : auxiliarRequestTest) 
			{
				if(request.activityName.equals(activityNameComparator)) 
				{
					newRequestList.add(request);
					requestList.remove(request);
				}		
			}
			//cambiar la activitynameComparator por el siguiente
			if(!requestList.isEmpty())
			{
				activityNameComparator = requestList.get(0).activityName;
			}
		}
		for (Request request : newRequestList)
		{
			System.out.println(request.activityName);
		}
		
		return newRequestList;
	}
}
