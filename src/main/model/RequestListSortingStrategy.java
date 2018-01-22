package model;

/**
 * The functional interface for the RequestList sorting strategy. Its objective is to make the job easier for the
 * RequestPoliceman, sorting the list by the priority of the requests defined in the current policy so it can be read only once.
 * 
<<<<<<< HEAD
 * @author Jandol
 * 
=======
 * @author Jendoliver
 *
>>>>>>> c689415eaaefdc26385f07c8b79de2061f4653ca
 */
public interface RequestListSortingStrategy
{
	RequestList sort(RequestList requestList);
}
