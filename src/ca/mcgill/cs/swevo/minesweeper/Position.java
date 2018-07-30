package ca.mcgill.cs.swevo.minesweeper;

import java.util.ArrayList;
import java.util.List;

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
	
	public static List<Position> all(int pRows, int pColumns)
	{
		List<Position> positions = new ArrayList<>(pRows * pColumns);
		for( int row = 0; row < pRows; row++)
		{
			for( int column = 0; column < pColumns; column++)
			{
				positions.add(new Position(row, column));
			}
		}
		return positions;
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
	
	public boolean isNeighbour(Position pPosition)
	{
		if( equals(pPosition ))
		{
			return false;
		}
		else
		{
			return pPosition.getColumn() >= getColumn() -1 && pPosition.getColumn() <= getColumn() +1 &&
					pPosition.getRow() >= getRow() - 1 && pPosition.getRow() <= getRow() + 1;
		}
	}
	
	public List<Position> getNeighbours(int pRows, int pColumns)
	{
		List<Position> neighbours = new ArrayList<>();
		for( int row = Math.max(0, getRow() -1); row <= Math.min(pRows-1, getRow()+1); row++)
		{
			for( int column = Math.max(0, getColumn()-1); column <= Math.min(pColumns-1, getColumn()+1); column++)
			{
				neighbours.add(new Position(row, column));
			}
		}
		return neighbours;
	}
}
