package ca.mcgill.cs.swevo.minesweeper;

public class Cell
{
	private boolean aIsHidden = true; 
	private boolean aIsMined;
	private boolean aIsMarked = false;
	
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
	
	public boolean isUndiscovered()
	{
		return aIsHidden && isMined() && !aIsMarked;
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
