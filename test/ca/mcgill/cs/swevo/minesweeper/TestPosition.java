package ca.mcgill.cs.swevo.minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPosition
{
	@Test
	public void testIsNeighbour()
	{
		Position position = new Position(5,5);
		assertFalse(position.isNeighbour(new Position(5,5)));
		assertFalse(new Position(5,5).isNeighbour(position));
		
		assertTrue(position.isNeighbour(new Position(5,6)));
		assertTrue(new Position(5,6).isNeighbour(position));
		
		assertTrue(position.isNeighbour(new Position(6,6)));
		assertTrue(new Position(6,6).isNeighbour(position));
		
		assertTrue(position.isNeighbour(new Position(6,5)));
		assertTrue(new Position(6,5).isNeighbour(position));
		
		assertTrue(position.isNeighbour(new Position(6,4)));
		assertTrue(new Position(6,4).isNeighbour(position));
		
		assertTrue(position.isNeighbour(new Position(5,4)));
		assertTrue(new Position(5,4).isNeighbour(position));
		
		assertTrue(position.isNeighbour(new Position(4,4)));
		assertTrue(new Position(4,4).isNeighbour(position));
		
		assertTrue(position.isNeighbour(new Position(4,5)));
		assertTrue(new Position(4,5).isNeighbour(position));
		
		assertFalse(position.isNeighbour(new Position(5,7)));
		assertFalse(new Position(5,7).isNeighbour(position));
	}
}
