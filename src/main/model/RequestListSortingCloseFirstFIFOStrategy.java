package model;

import globals.InputStrings;

/**
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
