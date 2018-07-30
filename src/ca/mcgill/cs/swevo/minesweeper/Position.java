package ca.mcgill.cs.swevo.minesweeper;

public class Position
{
	private final int aRowIndex;
	private final int aColumnIndex;
	
	public Position(int pRow, int pColumn)
	{
		aRowIndex = pRow;
		aColumnIndex = pColumn;
	}
	
	public int getRow()
	{
		return aRowIndex;
	}
	
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
