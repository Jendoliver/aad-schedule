package model;

/**
 * The functional interface for the RequestList sorting strategy. Its objective is to make the job easier for the
 * RequestPoliceman, sorting the list by the priority of the requests defined in the current policy so it can be read only once.
 * 
 * @author Jandol
 *
 */
public interface RequestListSortingStrategy
{
	RequestList sort(RequestList requestList);
}
