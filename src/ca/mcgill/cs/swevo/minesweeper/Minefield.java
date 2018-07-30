package ca.mcgill.cs.swevo.minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An instance of this class is responsible for keeping 
 * track of the entire state of the game, which includes,
 * for each cell, whether it's a bomb or not, and whether its
 * revealed or not.
 */
public class Minefield
{
	private final List<Cell> aCells;
	private final int aNumberOfRows;
	private final int aNumberOfColumns;
	private final List<MinefieldObserver> aObservers = new ArrayList<>();
	
	public Minefield(int pNumberOfBombs, int pNumberOfRows, int pNumberOfColumns)
	{
		aNumberOfColumns = pNumberOfColumns;
		aNumberOfRows = pNumberOfRows;
		aCells = new ArrayList<>(aNumberOfColumns * aNumberOfRows);
		initializeCells(pNumberOfBombs);
	}
	
	public int getNumberOfRows()
	{
		return aNumberOfRows;
	}
	
	public int getNumberOfColumns()
	{
		return aNumberOfColumns;
	}
	
	public void addObserver(MinefieldObserver pObserver)
	{
		aObservers.add(pObserver);
	}
	
	private void notifyObservers()
	{
		for( MinefieldObserver observer : aObservers )
		{
			observer.cellRevealed();
		}
	}
	
	public void reveal(int pColumnNumber, int pRowNumber)
	{
		getCell(pColumnNumber, pRowNumber).reveal();
		notifyObservers();
	}
	
	public CellView getView(int pColumnNumber, int pRowNumber)
	{
		assert pColumnNumber >= 0 && pColumnNumber < aNumberOfColumns;
		assert pRowNumber >= 0 && pRowNumber < aNumberOfRows;
		if( getCell(pColumnNumber, pRowNumber ).isHidden() )
		{
			return CellView.Hidden;
		}
		else
		{
			if( getCell(pColumnNumber, pRowNumber).isBomb() )
			{
				return CellView.Bomb;
			}
			else
			{
				return CellView.fromNumber(getNumberOfBombsInNeighBourhood(pColumnNumber, pRowNumber));
			}
		}
	}
	
	private int getNumberOfBombsInNeighBourhood(int pColumnNumber, int pRowNumber)
	{
		List<Cell> neighbours = new ArrayList<>(8);
		for(int columnNumber = Math.max(0, pColumnNumber-1); columnNumber < Math.min(aNumberOfColumns-1, pColumnNumber+1); columnNumber++)
		{
			for( int rowNumber = Math.max(0,  pRowNumber-1); rowNumber < Math.min(aNumberOfRows-1, pRowNumber+1); rowNumber++)
			{
				if( !(columnNumber == pColumnNumber && rowNumber == pRowNumber))
				{
					neighbours.add(getCell(columnNumber, rowNumber));
				}
			}
		}
		int sum = 0;
		for( Cell cell : neighbours )
		{
			if( cell.isBomb() )
			{
				sum++;
			}
		}
		return sum;
	}
	
	private Cell getCell(int pColumnNumber, int pRowNumber)
	{
		return aCells.get(pRowNumber * aNumberOfColumns + pColumnNumber);
	}
	
	private int getCapacity()
	{
		return aNumberOfColumns * aNumberOfRows;
	}
	
	private void initializeCells(int pNumberOfBombs)
	{
		for( int i = 0; i < pNumberOfBombs; i++ )
		{
			aCells.add(new Cell(true));
		}
		for( int i = 0; i < getCapacity() - pNumberOfBombs; i++ )
		{
			aCells.add( new Cell(false));
		}
		Collections.shuffle(aCells);
	}
}
