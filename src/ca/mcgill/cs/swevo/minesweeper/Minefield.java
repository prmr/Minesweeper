package ca.mcgill.cs.swevo.minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Minefield
{
	private Cell[][] aCells;
	private final ArrayList<Position> aAllPositions = new ArrayList<>();
	
	public Minefield(int pRows, int pColumns, int pMines)
	{
		aCells = new Cell[pRows][pColumns];
		initialize();
		placeMines(pMines);
		computeNeighbourhoods();
	}
	
	public Iterable<Position> getAllPositions()
	{
		return aAllPositions;
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
	
	private void computeNeighbourhoods()
	{
		for( Position position : aAllPositions )
		{
			getCell(position).setNeighbours(internalGetNumberOfMinedNeighbours(position));
		}
	}
	
	public CellStatus getStatus(Position pPosition)
	{
		return getCell(pPosition).getStatus();
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
	
	public void reveal(Position pPosition)
	{
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
	
	public void toggleMark(Position pPosition)
	{
		getCell(pPosition).toggleMark();
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
	
	public int getNumberOfMinedNeighbours(Position pPosition)
	{
		return getCell(pPosition).getNumberOfNeighbours();
	}
	
	private List<Position> getNeighbours(Position pPosition)
	{
		List<Position> neighbours = new ArrayList<>();
		for( int row = Math.max(0, pPosition.getRow() -1); row <= Math.min(getNumberOfRows()-1, pPosition.getRow()+1); row++)
		{
			for( int column = Math.max(0, pPosition.getColumn()-1); column <= Math.min(getNumberOfColumns()-1, pPosition.getColumn()+1); column++)
			{
				Position position = new Position(row, column);
				if( !position.equals(pPosition))
				{
					neighbours.add(position);
				}
			}
		}
		return neighbours;
	}
}
