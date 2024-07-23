/*******************************************************************************
 * Minesweeper
 *
 * Copyright (C) 2018-2024 by Martin P. Robillard
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
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *******************************************************************************/
package ca.mcgill.cs.swevo.minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A rectangular array of cells that can be mined
 * and interacted with.
 */
public class Minefield
{
	private Cell[][] aCells;
	private final ArrayList<Position> aAllPositions = new ArrayList<>();
	
	/**
	 * Creates a new mine field with pRow and pColumns, 
	 * which contains pMines randomly-distributed mines.
	 * 
	 * @param pRows The number of rows in the field.
	 * @param pColumns The number of columns in the field.
	 * @param pMines the number of mines in the field.
	 * @pre pRows > 0 && pColumns > 0 && pMines >= 0 && pMines <= pColumns * pRows;
	 */
	public Minefield(int pRows, int pColumns, int pMines)
	{
		assert pRows > 0 && pColumns > 0 && pMines >= 0 && pMines <= pColumns * pRows;
		aCells = new Cell[pRows][pColumns];
		initialize();
		placeMines(pMines);
	}
	
	/**
	 * @return An iterator over all positions in the mine field.
	 */
	public Iterable<Position> getAllPositions()
	{
		return aAllPositions;
	}
	
	/**
	 * @return A description of the current status of the mine field.
	 */
	public MinefieldStatus getStatus()
	{
		MinefieldStatus status = MinefieldStatus.NOT_CLEARED; 
		int totalMines = 0;
		int markedMines = 0;
		for( Position position : aAllPositions )
		{
			Cell cell = getCell(position);
			if( cell.isMined() )
			{
				totalMines++;
				if( !cell.isHidden())
				{
					status = MinefieldStatus.EXPLODED;
					break;
				}
				else
				{
					if( cell.isMarked() )
					{
						markedMines++;
					}
				}
			}
		}
		if( status == MinefieldStatus.EXPLODED )
		{
			return status;
		}
		else if( totalMines == markedMines )
		{
			return MinefieldStatus.CLEARED;
		}
		else
		{
			return MinefieldStatus.NOT_CLEARED;
		}
	}
	
	/**
	 * The number of mines left is computed by subtracting the 
	 * number of marked mines from the total number of mines.
	 * 
	 * @return The number of mines left according to the player.
	 */
	public int getNumberOfMinesLeft()
	{
		int totalMines = 0;
		int totalMarks = 0;
		for( Position position : aAllPositions )
		{
			if( getCell(position).isMined() )
			{
				totalMines++;
			}
			if( getCell(position).isMarked() )
			{
				totalMarks++;
			}
		}
		return totalMines - totalMarks;
	}
	
	/**
	 * @param pPosition The position to query.
	 * @return True if the cell at pPosition is revealed.
	 * @pre pPosition != null
	 */
	public boolean isRevealed(Position pPosition)
	{
		assert pPosition != null;
		return !getCell(pPosition).isHidden();
	}
	
	/**
	 * @param pPosition The position to query.
	 * @return True if the cell at pPosition is marked.
	 * @pre pPosition != null
	 */
	public boolean isMarked(Position pPosition)
	{
		assert pPosition != null;
		return getCell(pPosition).isMarked();
	}
	
	/**
	 * @param pPosition The position to query.
	 * @return True if the cell at pPosition is mined.
	 * @pre pPosition != null
	 */
	public boolean isMined(Position pPosition)
	{
		assert pPosition != null;
		return getCell(pPosition).isMined();
	}
	
	/**
	 * Reveal the cell at pPosition.
	 * 
	 * @param pPosition The position of the cell to reveal.
	 * @pre pPosition != null;
	 */
	public void reveal(Position pPosition)
	{
		assert pPosition != null;
		Cell cell = getCell(pPosition);
		if(cell.isMined())
		{
			revealAll();
		}
		else
		{
			cell.reveal();
			autoReveal(pPosition);
		}
	}
	
	/**
	 * Toggle the mark at cell pPosition.
	 * 
	 * @param pPosition The position of the cell to mark/unmark.
	 * @pre pPosition != null.
	 */
	public void toggleMark(Position pPosition)
	{
		assert pPosition != null;
		getCell(pPosition).toggleMark();
	}
	
	/**
	 * Return the number of mined cells in the 8 cells that surround
	 * pPosition.
	 * 
	 * @param pPosition The position to query.
	 * @return The number of mined cells around pPosition.
	 * @pre pPosition != null.
	 */
	public int getNumberOfMinedNeighbours(Position pPosition)
	{
		assert pPosition != null;
		return internalGetNumberOfMinedNeighbours(pPosition);
	}
		
	private void initialize()
	{
		for( int row = 0; row < aCells.length; row++)
		{
			for( int column = 0; column < aCells[0].length; column++)
			{
				aCells[row][column] = new Cell();
				aAllPositions.add(new Position(row, column));
			}
		}
	}
	
	private Cell getCell(Position pPosition)
	{
		return aCells[pPosition.getRow()][pPosition.getColumn()];
	}
	
	private void placeMines(int pNumberOfMines)
	{
		List<Position> positions = aAllPositions;
		Collections.shuffle(positions);
		for( int i = 0; i < pNumberOfMines; i++ )
		{
			getCell(positions.get(i)).mine();
		}
	}
	
	private int getNumberOfRows()
	{
		return aCells.length;
	}
	
	private int getNumberOfColumns()
	{
		return aCells[0].length;
	}
	
	private void revealAll()
	{
		for( Position position : aAllPositions )
		{
			getCell(position).reveal();
		}
	}
	
	private void autoReveal(Position pPosition)
	{
		if( internalGetNumberOfMinedNeighbours(pPosition) == 0)
		{
			for( Position neighbour : getNeighbours(pPosition))
			{
				if(getCell(neighbour).isHidden() && !getCell(neighbour).isMarked() )
				{
					getCell(neighbour).reveal();
					autoReveal(neighbour);
				}
			}
		}
	}
	
	private int internalGetNumberOfMinedNeighbours(Position pPosition)
	{
		int total = 0;
		for( Position neighbour : getNeighbours(pPosition) )
		{
			if( getCell(neighbour).isMined())
			{
				total++;
			}
		}
		return total;
	}
	
	private List<Position> getNeighbours(Position pPosition)
	{
		final List<Position> neighbours = new ArrayList<>();
		for( int row = Math.max(0, pPosition.getRow() -1); row <= Math.min(getNumberOfRows()-1, pPosition.getRow()+1); row++)
		{
			for( int column = Math.max(0, pPosition.getColumn()-1); 
					column <= Math.min(getNumberOfColumns()-1, 
							pPosition.getColumn()+1); column++)
			{
				final Position position = new Position(row, column);
				if( !position.equals(pPosition))
				{
					neighbours.add(position);
				}
			}
		}
		return neighbours;
	}
}
