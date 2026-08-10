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

import org.junit.jupiter.api.Test;

public class MinefieldTest {
	
	private Minefield aMinefield = new Minefield(5, 5, 5);

	@Test
	void testInitialization() {
		int mines = 0;
		for (Position position : aMinefield.getAllPositions()) {
			assertFalse(aMinefield.isFlagged(position));
			assertFalse(aMinefield.isRevealed(position));
			if (aMinefield.isMined(position)) {
				mines++;
			}
		}
		assertEquals(5, mines);
	}
}
