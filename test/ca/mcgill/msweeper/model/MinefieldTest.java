/*******************************************************************************
 * Minesweeper
 *
 * Copyright (C) 2018 by Martin P. Robillard
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class MinefieldTest {
	
	private final Minefield aMinefield = new Minefield(5, 5, 5);
	
	private void setMinedPositions(Set<Position> pMinedPositions) {
		try {
			Field field = Minefield.class.getDeclaredField("aMinedPositions");
			field.setAccessible(true);
			@SuppressWarnings("unchecked")
			Set<Position> positions = (Set<Position>)field.get(aMinefield);
			positions.clear();
			positions.addAll(pMinedPositions);
		} 
		catch (ReflectiveOperationException exception)
		{
			fail();
		}
	}

	@Test
	void testGetNumberOfMinesLeft_All() {
		assertEquals(5, aMinefield.getNumberOfMinesLeft());
	}
	
	@Test
	void testGetNumberOfMinesLeft_Some() {
		aMinefield.toggleFlag(new Position(0,0));
		assertEquals(4, aMinefield.getNumberOfMinesLeft());
	}
	
	@Test
	void testGetNumberOfMinesLeft_None() {
		aMinefield.toggleFlag(new Position(0,0));
		aMinefield.toggleFlag(new Position(0,1));
		aMinefield.toggleFlag(new Position(0,2));
		aMinefield.toggleFlag(new Position(0,3));
		aMinefield.toggleFlag(new Position(0,4));
		assertEquals(0, aMinefield.getNumberOfMinesLeft());
	}
	
	@Test
	void testIsRevealed_False() {
		assertFalse(aMinefield.isRevealed(new Position(0,0)));
	}
	
	@Test
	void testIsRevealed_True() {
		aMinefield.reveal(new Position(0,0));
		assertTrue(aMinefield.isRevealed(new Position(0,0)));
	}
	
	@Test
	void testIsFlagged_True() {
		aMinefield.toggleFlag(new Position(0,0));
		assertTrue(aMinefield.isFlagged(new Position(0,0)));
	}
	
	@Test
	void testIsFlagged_Flag() {
		assertFalse(aMinefield.isFlagged(new Position(0,0)));
	}
	
	@Test
	void testIsMined() {
		int total = 0;
		for (Position position : aMinefield.getAllPositions()) {
			if (aMinefield.isMined(position)) {
				total++;
			}
		}
		assertEquals(5, total);
	}
	
	@Test
	void testToggleFlag() {
		final Position position = new Position(2,2);
		assertFalse(aMinefield.isFlagged(position));
		aMinefield.toggleFlag(position);
		assertTrue(aMinefield.isFlagged(position));
		aMinefield.toggleFlag(position);
		assertFalse(aMinefield.isFlagged(position));
	}
	
	@Test
	void testIsCleared_True() {
		aMinefield.toggleFlag(new Position(0,0));
		aMinefield.toggleFlag(new Position(0,1));
		aMinefield.toggleFlag(new Position(0,2));
		aMinefield.toggleFlag(new Position(0,3));
		aMinefield.toggleFlag(new Position(0,4));
		assertTrue(aMinefield.isCleared());
	}
	
	@Test
	void testIsCleared_False() {
		assertFalse(aMinefield.isCleared());
	}
	
	@Test
	void testIsExploded_True() {
		setMinedPositions(Set.of(new Position(0,0)));
		aMinefield.reveal(new Position(0,0));
		assertTrue(aMinefield.isExploded());
	}
	
	@Test
	void testIsExploded_False() {
		assertFalse(aMinefield.isExploded());
	}
	
	@Test
	void testReveal_NotMinedNoAutoReveal() {
		setMinedPositions(Set.of( new Position(0,1)));
		aMinefield.reveal(new Position(0,0));
		assertTrue(aMinefield.isRevealed(new Position(0,0)));
		assertFalse(aMinefield.isRevealed(new Position(0,1)));
		assertFalse(aMinefield.isRevealed(new Position(1,0)));
		assertFalse(aMinefield.isRevealed(new Position(1,1)));
	}
	
	@Test
	void testReveal_Mined() {
		setMinedPositions(Set.of( new Position(0,0)));
		aMinefield.reveal(new Position(0,0));
		for (Position position : aMinefield.getAllPositions()) {
			assertTrue(aMinefield.isRevealed(position));
		}
	}
	
	@Test
	void testReveal_NotMinedAutoReveal() {
		setMinedPositions(Set.of( new Position(2,0), new Position(3,4)));
		aMinefield.reveal(new Position(2,2));
		
		assertTrue(aMinefield.isRevealed(new Position(0,0)));
		assertTrue(aMinefield.isRevealed(new Position(0,1)));
		assertTrue(aMinefield.isRevealed(new Position(0,2)));
		assertTrue(aMinefield.isRevealed(new Position(0,3)));
		assertTrue(aMinefield.isRevealed(new Position(0,4)));
		
		assertTrue(aMinefield.isRevealed(new Position(1,0)));
		assertTrue(aMinefield.isRevealed(new Position(1,1)));
		assertTrue(aMinefield.isRevealed(new Position(1,2)));
		assertTrue(aMinefield.isRevealed(new Position(1,3)));
		assertTrue(aMinefield.isRevealed(new Position(1,4)));
		
		assertFalse(aMinefield.isRevealed(new Position(2,0)));
		assertTrue(aMinefield.isRevealed(new Position(2,1)));
		assertTrue(aMinefield.isRevealed(new Position(2,2)));
		assertTrue(aMinefield.isRevealed(new Position(2,3)));
		assertTrue(aMinefield.isRevealed(new Position(2,4)));
		
		assertTrue(aMinefield.isRevealed(new Position(3,0)));
		assertTrue(aMinefield.isRevealed(new Position(3,1)));
		assertTrue(aMinefield.isRevealed(new Position(3,2)));
		assertTrue(aMinefield.isRevealed(new Position(3,3)));
		assertFalse(aMinefield.isRevealed(new Position(3,4)));
		
		assertTrue(aMinefield.isRevealed(new Position(4,0)));
		assertTrue(aMinefield.isRevealed(new Position(4,1)));
		assertTrue(aMinefield.isRevealed(new Position(4,2)));
		assertTrue(aMinefield.isRevealed(new Position(4,3)));
		assertFalse(aMinefield.isRevealed(new Position(4,4)));
	}
	
	@Test
	void testReveal_NotMinedFlaggedAutoReveal() {
		setMinedPositions(Set.of( new Position(2,0), new Position(3,4), new Position(2,3)));
		aMinefield.reveal(new Position(2,2));
		
		assertFalse(aMinefield.isRevealed(new Position(0,0)));
		assertFalse(aMinefield.isRevealed(new Position(0,1)));
		assertFalse(aMinefield.isRevealed(new Position(0,2)));
		assertFalse(aMinefield.isRevealed(new Position(0,3)));
		assertFalse(aMinefield.isRevealed(new Position(0,4)));
		
		assertFalse(aMinefield.isRevealed(new Position(1,0)));
		assertFalse(aMinefield.isRevealed(new Position(1,1)));
		assertFalse(aMinefield.isRevealed(new Position(1,2)));
		assertFalse(aMinefield.isRevealed(new Position(1,3)));
		assertFalse(aMinefield.isRevealed(new Position(1,4)));
		
		assertFalse(aMinefield.isRevealed(new Position(2,0)));
		assertFalse(aMinefield.isRevealed(new Position(2,1)));
		assertTrue(aMinefield.isRevealed(new Position(2,2)));
		assertFalse(aMinefield.isRevealed(new Position(2,3)));
		assertFalse(aMinefield.isRevealed(new Position(2,4)));
		
		assertFalse(aMinefield.isRevealed(new Position(3,0)));
		assertFalse(aMinefield.isRevealed(new Position(3,1)));
		assertFalse(aMinefield.isRevealed(new Position(3,2)));
		assertFalse(aMinefield.isRevealed(new Position(3,3)));
		assertFalse(aMinefield.isRevealed(new Position(3,4)));
		
		assertFalse(aMinefield.isRevealed(new Position(4,0)));
		assertFalse(aMinefield.isRevealed(new Position(4,1)));
		assertFalse(aMinefield.isRevealed(new Position(4,2)));
		assertFalse(aMinefield.isRevealed(new Position(4,3)));
		assertFalse(aMinefield.isRevealed(new Position(4,4)));
	}
}
