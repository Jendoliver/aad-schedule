package model;

import java.util.ArrayList;

/**
 * Just a little extension of an ArrayList to add a custom sorting method.
 * 
 * @author Jendoliver
 *
 */
public class RequestList extends ArrayList<Request> 
{
	private static final long serialVersionUID = 7528412895061970460L;
	private RequestListSortingStrategy strategy;
	
	public RequestList() { strategy = new RequestListSortingCloseFirstFIFOStrategy(); }
	
	public RequestList sort() 
	{
		return strategy.sort(this);
	}
}
