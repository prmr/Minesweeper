package ca.mcgill.cs.swevo.minesweeper;

/**
 * Represents a cell in the minefield. A cell has two orthogonal properties
 * that can be modified: a cell can be mined or not, and a cell can be hidden,
 * marked (as hiding a mine), or revealed.
 * 
 * @author Martin P. Robillard
 *
 */
public class Cell
{
	/**
	 * Represents how the user interacted with a cell.
	 * 
	 * @author Martin P. Robillard
	 */
	private enum CellInteractionStatus
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

	private boolean aIsMined = false;
	private CellInteractionStatus aInteractionStatus = CellInteractionStatus.HIDDEN;
	
	/**
	 * @return True if the cell is hidden, whether it is marked or not.
	 */
	public boolean isHidden() 
	{
		return aInteractionStatus != CellInteractionStatus.REVEALED;
	}
	
	/**
	 * @return True if the cell is marked.
	 */
	public boolean isMarked()
	{
		return aInteractionStatus == CellInteractionStatus.MARKED;
	}
	
	/**
	 * @return True if the cell is mined, false otherwise.
	 */
	public boolean isMined() 
	{
		return aIsMined;
	}
	
	/**
	 * Change the status of this cell to represent a revealed cell.
	 */
	public void reveal()
	{
		aInteractionStatus = CellInteractionStatus.REVEALED;
	}
	
	/**
	 * If this cell is not marked, mark it. If the cell is marked,
	 * unmark it.
	 * @pre isHidden() == true;
	 */
	public void toggleMark()
	{
		assert isHidden();
		if(aInteractionStatus == CellInteractionStatus.MARKED)
		{
			aInteractionStatus = CellInteractionStatus.HIDDEN;
		}
		else
		{
			aInteractionStatus = CellInteractionStatus.MARKED;
		}
	}

	/**
	 * Marks the cell as mined.
	 */
	public void mine()
	{
		aIsMined = true;
	}
}
