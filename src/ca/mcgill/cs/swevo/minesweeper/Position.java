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
 * Represents a position in the mine field. A position
 * consists of a row and column index pairs, both
 * zero-indexed. Instances of this class are immutable.
 */
public class Position
{
	private final int aRowIndex;
	private final int aColumnIndex;
	
	/**
	 * Creates a new position.
	 * 
	 * @param pRow The zero-indexed row of this position.
	 * @param pColumn The zero-index column of this position.
	 * @pre pRow >= 0 && pColumn >= 0;
	 */
	public Position(int pRow, int pColumn)
	{
		assert pRow >= 0 && pColumn >= 0;
		aRowIndex = pRow;
		aColumnIndex = pColumn;
	}
	
	/**
	 * @return The row index of this position.
	 */
	public int getRow()
	{
		return aRowIndex;
	}
	
	/**
	 * @return The column index of this position.
	 */
	public int getColumn()
	{
		return aColumnIndex;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + aColumnIndex;
		result = prime * result + aRowIndex;
		return result;
	}

	@Override
	public boolean equals(Object pObject)
	{
		if(this == pObject)
		{
			return true;
		}
		if(pObject == null)
		{
			return false;
		}
		if(getClass() != pObject.getClass())
		{
			return false;
		}
		Position other = (Position) pObject;
		return aColumnIndex == other.aColumnIndex && aRowIndex == other.aRowIndex;
	}
	
	@Override
	public String toString()
	{
		return String.format("(r=%d, c=%d)", aRowIndex, aColumnIndex);
	}
}
