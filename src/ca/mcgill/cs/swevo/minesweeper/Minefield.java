package ca.mcgill.cs.swevo.minesweeper;

/**
 * An instance of this class is responsible for keeping 
 * track of the entire state of the game, which includes,
 * for each cell, whether it's a bomb or not, and 
 *
 */
public class Minefield
{

	public Cell[][] field= new Cell [10][10];

	public boolean isOpen(int column, int row) {
		return false;
	}

	public boolean isBomb(int column, int row) {
		return false; // TODO
	}
	
	public int numberOfBombsInNeighbourhood(int column, int row)
	{
		return 0 ; // TODO
	}
	
	public Minefield(int pNumberOfBombs, int pNumberOfRows, int pNumberOfColumns)
	{
		// TODO
	}

}
