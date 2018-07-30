package ca.mcgill.cs.swevo.minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Minefield
{
	private Cell[][] aCells;
	
	public Minefield(int pRows, int pColumns, int pMines)
	{
		aCells = new Cell[pRows][pColumns];
		initialize();
		placeMines(pMines);
	}
	
	private void initialize()
	{
		for( int row = 0; row < aCells.length; row++)
		{
			for( int column = 0; column < aCells[0].length; column++)
			{
				aCells[row][column] = new Cell();
			}
		}
	}
	
	private Cell getCell(Position pPosition)
	{
		return aCells[pPosition.getRow()][pPosition.getColumn()];
	}
	
	private void placeMines(int pNumberOfMines)
	{
		List<Position> positions = allPositions();
		Collections.shuffle(positions);
		for( int i = 0; i < pNumberOfMines; i++ )
		{
			getCell(positions.get(i)).mine();
		}
	}
	
	public boolean isMarked(Position pPosition)
	{
		return getCell(pPosition).isMarked();
	}
	
	private List<Position> allPositions()
	{
		return Position.all(getNumberOfRows(), getNumberOfColumns());
	}
	
	public int getNumberOfRows()
	{
		return aCells.length;
	}
	
	public int getNumberOfColumns()
	{
		return aCells[0].length;
	}
	
	public void reveal(Position pPosition)
	{
		getCell(pPosition).reveal();
		autoReveal(pPosition);
	}
	
	private void autoReveal(Position pPosition)
	{
		if( getNumberOfMinedNeighbours(pPosition) == 0)
		{
			for( Position neighbour : pPosition.getNeighbours(getNumberOfRows(), getNumberOfColumns()))
			{
				if(getCell(neighbour).isHidden() && !getCell(neighbour).isMarked() )
				{
					getCell(neighbour).reveal();
					autoReveal(neighbour);
				}
			}
		}
	}
	
	public void toggleMark(Position pPosition)
	{
		getCell(pPosition).toggleMark();
		autoReveal(pPosition);
	}
	
	public Iterable<Position> getHiddenPositions()
	{
		List<Position> positions = new ArrayList<>();
		for( Position position : allPositions() )
		{
			if( getCell(position).isHidden() )
			{
				positions.add(position);
			}
		}
		return positions;
	}
	
	public Iterable<Position> getRevealedPositions()
	{
		List<Position> positions = new ArrayList<>();
		for( Position position : allPositions() )
		{
			if( !getCell(position).isHidden() )
			{
				positions.add(position);
			}
		}
		return positions;
	}
	
	public boolean isMined(Position pPosition)
	{
		return getCell(pPosition).isMined();
	}
	
	public int getNumberOfMinedNeighbours(Position pPosition)
	{
		int total = 0;
		for( Position neighbour : pPosition.getNeighbours(getNumberOfRows(), getNumberOfColumns()) )
		{
			if( getCell(neighbour).isMined())
			{
				total++;
			}
		}
		return total;
	}
}
