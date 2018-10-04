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
	
	@Test
	public void testHashCode()
	{
		assertEquals(961, new Position(0,0).hashCode());
		assertEquals(1180, new Position(2,7).hashCode());
		assertEquals(1030, new Position(7,2).hashCode());
		assertEquals(1825, new Position(27,27).hashCode());
	}
	
	@Test
	public void testEquals()
	{
		Position p1 = new Position(2,7);
		Position p2 = new Position(2,7);
		Position p3 = new Position(7,2);
		assertFalse(p1.equals(null));
		assertTrue(p1.equals(p1));
		assertTrue(p1.equals(p2));
		assertTrue(p2.equals(p1));
		assertFalse(p1.equals(p3));
	}
}
