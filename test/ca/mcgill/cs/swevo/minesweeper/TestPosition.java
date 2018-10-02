package ca.mcgill.cs.swevo.minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPosition
{
	@Test
	public void testConstructorOrigin()
	{
		Position position = new Position(0,0);
		assertEquals(0, position.getRow());
		assertEquals(0, position.getColumn());
	}
	
	@Test
	public void testConstructorOther()
	{
		Position position = new Position(2,7);
		assertEquals(2, position.getRow());
		assertEquals(7, position.getColumn());
	}
}
