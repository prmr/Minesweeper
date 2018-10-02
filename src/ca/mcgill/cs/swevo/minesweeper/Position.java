package ca.mcgill.cs.swevo.minesweeper;

/**
 * Represents a position in the minefield. A position
 * consists of a row and column index pairs, both
 * zero-indexed. Instances of this class are immutable.
 * 
 * @author Martin P. Robillard
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
}
