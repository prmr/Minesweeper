package ca.mcgill.cs.swevo.minesweeper;

/**
 * Represents how the user interacted with a cell.
 * 
 * @author Martin P. Robillard
 */
public enum CellInteractionStatus
{
	/**
	 * The cell is hidden and not marked.
	 */
	HIDDEN, 
	
	/**
	 * The cell is hidden but has been marked by the player as hiding a mine.
	 */
	MARKED,
	
	/**
	 * The cell has been revealed.
	 */
	REVEALED
}
