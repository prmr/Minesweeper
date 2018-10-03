package ca.mcgill.cs.swevo.minesweeper;

/**
 * Represents the current abstract state of a minefield.
 * 
 * @author Martin P. Robillard
 */
public enum MinefieldStatus
{
	/**
	 * One or more cells remain hidden.
	 */
	NOT_CLEARED, 
	
	/**
	 * All the cells have been revealed, and the location of all mines
	 * has been correctly marked.
	 */
	CLEARED, 
	
	/**
	 * At least one cell containing a mine has been revealed.
	 */
	EXPLODED
}
