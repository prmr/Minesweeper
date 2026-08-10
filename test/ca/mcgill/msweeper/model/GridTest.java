/*******************************************************************************
 * Minesweeper
 *
 * Copyright (C) 2026 by Martin P. Robillard
 * 
 * See: https://github.com/prmr/Minesweeper
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package ca.mcgill.msweeper.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GridTest {
	
	@Test
	void testIterator() {
		Grid grid = new Grid(2,3);
		Set<Position> positions = new HashSet<>();
		for (Iterator<Position> iterator = grid.iterator(); iterator.hasNext(); ) {
			positions.add(iterator.next());
		}
		assertEquals(6, positions.size());
		assertTrue(positions.contains(new Position(0,0)));
		assertTrue(positions.contains(new Position(0,1)));
		assertTrue(positions.contains(new Position(0,2)));
		assertTrue(positions.contains(new Position(1,0)));
		assertTrue(positions.contains(new Position(1,1)));
		assertTrue(positions.contains(new Position(1,2)));
	}
	
	@Test
	void testRandomPositions_Zero() {
		Grid grid = new Grid(2,3);
		Set<Position> random = grid.randomPositions(0);
		assertEquals(0, random.size());
	}
	
	@Test
	void testRandomPositions_All() {
		Grid grid = new Grid(2,3);
		Set<Position> positions = grid.randomPositions(6);
		assertEquals(6, positions.size());
		assertTrue(positions.contains(new Position(0,0)));
		assertTrue(positions.contains(new Position(0,1)));
		assertTrue(positions.contains(new Position(0,2)));
		assertTrue(positions.contains(new Position(1,0)));
		assertTrue(positions.contains(new Position(1,1)));
		assertTrue(positions.contains(new Position(1,2)));
	}
}
