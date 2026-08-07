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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CellTest {
	
	private final Cell aCell = new Cell();
	
	@Test
	void testConstructor() {
		assertTrue(aCell.isHidden());
		assertFalse(aCell.isMarked());
		assertFalse(aCell.isMined());
	}

	@Test
	void testMine() {
		assertFalse(aCell.isMined());
		aCell.mine();
		assertTrue(aCell.isMined());
	}

	@Test
	void testRevealNotMarked() {
		aCell.reveal();
		assertFalse(aCell.isHidden());
		assertFalse(aCell.isMarked());
		assertFalse(aCell.isMined());
	}

	@Test
	void testRevealMarked() {
		aCell.toggleMark();
		aCell.reveal();
		assertFalse(aCell.isHidden());
		assertFalse(aCell.isMarked());
		assertFalse(aCell.isMined());
	}

	@Test
	void testToggleMark() {
		aCell.toggleMark();
		assertTrue(aCell.isHidden());
		assertTrue(aCell.isMarked());
		aCell.toggleMark();
		assertTrue(aCell.isHidden());
		assertFalse(aCell.isMarked());
	}
}
