package ca.mcgill.cs.swevo.minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCell
{
	@Test
	public void testConstructor()
	{
		Cell cell = new Cell();
		assertTrue(cell.isHidden());
		assertFalse(cell.isMarked());
		assertFalse(cell.isMined());
	}
	
	@Test
	public void testMine()
	{
		Cell cell = new Cell();
		assertFalse(cell.isMined());
		cell.mine();
		assertTrue(cell.isMined());
	}
	
	@Test
	public void testRevealNotMarked()
	{
		Cell cell = new Cell();
		cell.reveal();
		assertFalse(cell.isHidden());
		assertFalse(cell.isMarked());
		assertFalse(cell.isMined());
	}
	
	@Test
	public void testRevealMarked()
	{
		Cell cell = new Cell();
		cell.toggleMark();
		cell.reveal();
		assertFalse(cell.isHidden());
		assertFalse(cell.isMarked());
		assertFalse(cell.isMined());
	}
	
	@Test
	public void testToggleMark()
	{
		Cell cell = new Cell();
		cell.toggleMark();
		assertTrue(cell.isHidden());
		assertTrue(cell.isMarked());
		cell.toggleMark();
		assertTrue(cell.isHidden());
		assertFalse(cell.isMarked());
	}
}
