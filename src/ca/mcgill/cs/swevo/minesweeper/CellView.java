package ca.mcgill.cs.swevo.minesweeper;

/**
 * Represents the status of a cell during a game.
 * 
 * @author Martin P. Robillard
 */
public enum CellView
{
	/**
	 * The cell has been marked by the player as hiding a mine.
	 */
	MARKED,
	
	
	/**
	 * The cell is hidden and not marked.
	 */
	HIDDEN, 
	
	/**
	 * The cell contains a mine and the mine is revealed.
	 */
	MINE, 
	
	/**
	 * The cell has been revealed, does not contain a mine, and
	 * none of its neighbouring cells hides a mine.
	 */
	CLEAR, 
	
	/**
	 * The cell has been revealed, does not contain a min,
	 * and one or more of its neighbouring cells hides a mine.
	 */
	PROXIMITY
}
