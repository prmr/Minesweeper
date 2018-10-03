package ca.mcgill.cs.swevo.minesweeper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestMinefield
{
	private Minefield aMinefield;
	
	@Before
	public void setUp()
	{
		aMinefield = new Minefield(5, 5, 5);
	}
	
	@Test
	public void testInitialization()
	{
		int mines = 0;
		for( Position position : aMinefield.getAllPositions() )
		{
			assertFalse( aMinefield.isMarked(position));
			assertFalse( aMinefield.isRevealed(position));
			if( aMinefield.isMined(position))
			{
				mines++;
			}
		}
		assertEquals(5, mines);
	}
}
