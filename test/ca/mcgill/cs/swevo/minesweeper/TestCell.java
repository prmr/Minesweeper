/*******************************************************************************
 * Minesweeper
 *
 * Copyright (C) 2018 by Martin P. Robillard
 *     
 * See: https://github.com/prmr/Minesweeper
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
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
