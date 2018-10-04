/*******************************************************************************
 * Minesweeper
 *
 * Copyright (C) 2018 by Martin P. Robillard
 *     
 * See: https://github.com/prmr/Minesweeper
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
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
