package ca.mcgill.cs.swevo.minesweeper;

public enum CellView
{
	Hidden, Bomb, Zero, One, Two, Three, Four, Five, Six, Seven, Eight;
	
	public static CellView fromNumber(int pNumber)
	{
		assert pNumber >=0 && pNumber <= 8;
		return values()[pNumber+2];
	}
}
