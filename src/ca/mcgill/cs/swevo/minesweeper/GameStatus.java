package ca.mcgill.cs.swevo.minesweeper;

/**
 * Main abstract states for the game.
 * 
 * @author Martin P. Robillard
 */
public enum GameStatus
{
	/**
	 * One or more cells remain hidden.
	 */
	IN_PLAY, 
	
	/**
	 * All the cells have been revealed, and the location of all mines
	 * has been correctly marked.
	 */
	WON, 
	
	/**
	 * At least one cell containing a mine has been revealed.
	 */
	LOST
}
