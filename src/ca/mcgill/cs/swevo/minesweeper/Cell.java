package ca.mcgill.cs.swevo.minesweeper;

public class Cell
{
	private boolean aIsHidden = true; 
	private boolean aIsMined;
	private boolean aIsMarked = false;
	private int aNumberOfNeighbours = 0;
	
	public void setNeighbours(int pNumberOfNeighbours)
	{
		aNumberOfNeighbours = pNumberOfNeighbours;
	}
	
	public CellStatus getStatus()
	{
		if( aIsMarked )
		{
			return CellStatus.MARKED;
		}
		else if( aIsHidden )
		{
			return CellStatus.HIDDEN;
		}
		else if( aIsMined )
		{
			return CellStatus.MINE;
		}
		else if( aNumberOfNeighbours == 0)
		{
			return CellStatus.CLEAR;
		}
		else
		{
			return CellStatus.PROXIMITY;
		}
	}
	
	public int getNumberOfNeighbours()
	{
		return aNumberOfNeighbours;
	}
	
	public boolean isHidden() 
	{
		return aIsHidden;
	}
	
	public void reveal()
	{
		aIsHidden = false;
	}
	
	public boolean isMarked()
	{
		return aIsMarked;
	}
	
	public void toggleMark()
	{
		aIsMarked = !aIsMarked;
	}

	public boolean isMined() 
	{
		return aIsMined;
	}
	
	public void mine()
	{
		aIsMined = true;
	}
}
