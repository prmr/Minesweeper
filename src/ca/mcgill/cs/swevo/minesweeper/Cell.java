package ca.mcgill.cs.swevo.minesweeper;

public class Cell
{
	private boolean aIsOpen = false; 
	
	public boolean isOpen() 
	{
		return aIsOpen;
	}

	public boolean isBomb() {
		return false; //TODO
	}
}
