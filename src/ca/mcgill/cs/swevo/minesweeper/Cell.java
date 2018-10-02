package ca.mcgill.cs.swevo.minesweeper;

public class Cell
{
	private boolean aIsMined;
	private CellInteractionStatus aInteractionStatus = CellInteractionStatus.HIDDEN;
	
	public boolean isHidden() 
	{
		return aInteractionStatus == CellInteractionStatus.HIDDEN;
	}
	
	public void reveal()
	{
		aInteractionStatus = CellInteractionStatus.REVEALED;
	}
	
	public boolean isMarked()
	{
		return aInteractionStatus == CellInteractionStatus.MARKED;
	}
	
	public void toggleMark()
	{
		assert aInteractionStatus != CellInteractionStatus.REVEALED;
		if(aInteractionStatus == CellInteractionStatus.MARKED)
		{
			aInteractionStatus = CellInteractionStatus.HIDDEN;
		}
		else
		{
			aInteractionStatus = CellInteractionStatus.MARKED;
		}
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
