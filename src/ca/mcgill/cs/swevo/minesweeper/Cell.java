package ca.mcgill.cs.swevo.minesweeper;

public class Cell
{
	private boolean aIsHidden = true; 
	private boolean aIsBomb;
	
	public Cell(boolean pIsBomb)
	{
		aIsBomb = pIsBomb;
	}
	
	public boolean isHidden() 
	{
		return aIsHidden;
	}
	
	public void reveal()
	{
		aIsHidden = false;
	}

	public boolean isBomb() 
	{
		return aIsBomb;
	}
}
