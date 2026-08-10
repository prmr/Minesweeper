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
	
	@Test
	void testGetNeighbours_All8() {
		Grid grid = new Grid(10, 10);
		Set<Position> neighbours = grid.getNeighbours(new Position(5,5));
		assertEquals(8, neighbours.size());
		assertTrue(neighbours.contains(new Position(4,4)));
		assertTrue(neighbours.contains(new Position(4,5)));
		assertTrue(neighbours.contains(new Position(4,6)));
		assertTrue(neighbours.contains(new Position(5,4)));
		assertTrue(neighbours.contains(new Position(5,6)));
		assertTrue(neighbours.contains(new Position(6,4)));
		assertTrue(neighbours.contains(new Position(6,5)));
		assertTrue(neighbours.contains(new Position(6,6)));
	}
	
	@Test
	void testGetNeighbours_NoTop() {
		Grid grid = new Grid(10, 10);
		Set<Position> neighbours = grid.getNeighbours(new Position(0,5));
		assertEquals(5, neighbours.size());
		assertTrue(neighbours.contains(new Position(0,4)));
		assertTrue(neighbours.contains(new Position(0,6)));
		assertTrue(neighbours.contains(new Position(1,4)));
		assertTrue(neighbours.contains(new Position(1,5)));
		assertTrue(neighbours.contains(new Position(1,6)));
	}
	
	@Test
	void testGetNeighbours_NoLeft() {
		Grid grid = new Grid(10, 10);
		Set<Position> neighbours = grid.getNeighbours(new Position(5,0));
		assertEquals(5, neighbours.size());
		assertTrue(neighbours.contains(new Position(4,0)));
		assertTrue(neighbours.contains(new Position(4,1)));
		assertTrue(neighbours.contains(new Position(5,1)));
		assertTrue(neighbours.contains(new Position(6,0)));
		assertTrue(neighbours.contains(new Position(6,1)));
	}
	
	@Test
	void testGetNeighbours_NoRight() {
		Grid grid = new Grid(10, 10);
		Set<Position> neighbours = grid.getNeighbours(new Position(5,9));
		assertEquals(5, neighbours.size());
		assertTrue(neighbours.contains(new Position(4,8)));
		assertTrue(neighbours.contains(new Position(4,9)));
		assertTrue(neighbours.contains(new Position(5,8)));
		assertTrue(neighbours.contains(new Position(6,8)));
		assertTrue(neighbours.contains(new Position(6,9)));
	}
	
	@Test
	void testGetNeighbours_NoBottom() {
		Grid grid = new Grid(10, 10);
		Set<Position> neighbours = grid.getNeighbours(new Position(9,5));
		assertEquals(5, neighbours.size());
		assertTrue(neighbours.contains(new Position(8,4)));
		assertTrue(neighbours.contains(new Position(8,5)));
		assertTrue(neighbours.contains(new Position(8,6)));
		assertTrue(neighbours.contains(new Position(9,4)));
		assertTrue(neighbours.contains(new Position(9,6)));
	}
	
	@Test
	void testGetNeighbours_NoTopLeft() {
		Grid grid = new Grid(10, 10);
		Set<Position> neighbours = grid.getNeighbours(new Position(0,0));
		assertEquals(3, neighbours.size());
		assertTrue(neighbours.contains(new Position(0,1)));
		assertTrue(neighbours.contains(new Position(1,0)));
		assertTrue(neighbours.contains(new Position(1,1)));
	}
	
	@Test
	void testGetNeighbours_NoTopRight() {
		Grid grid = new Grid(10, 10);
		Set<Position> neighbours = grid.getNeighbours(new Position(0,9));
		assertEquals(3, neighbours.size());
		assertTrue(neighbours.contains(new Position(0,8)));
		assertTrue(neighbours.contains(new Position(1,8)));
		assertTrue(neighbours.contains(new Position(1,9)));
	}
	
	@Test
	void testGetNeighbours_NoBottomLeft() {
		Grid grid = new Grid(10, 10);
		Set<Position> neighbours = grid.getNeighbours(new Position(9,0));
		assertEquals(3, neighbours.size());
		assertTrue(neighbours.contains(new Position(8,0)));
		assertTrue(neighbours.contains(new Position(8,1)));
		assertTrue(neighbours.contains(new Position(9,1)));
	}
	
	@Test
	void testGetNeighbours_NoBottomRight() {
		Grid grid = new Grid(10, 10);
		Set<Position> neighbours = grid.getNeighbours(new Position(9,9));
		assertEquals(3, neighbours.size());
		assertTrue(neighbours.contains(new Position(8,8)));
		assertTrue(neighbours.contains(new Position(8,9)));
		assertTrue(neighbours.contains(new Position(9,8)));
	}
}
