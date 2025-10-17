/*******************************************************************************
 * Minesweeper
 *
 * Copyright (C) 2025 by Maximilian Schiedermeier
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
 * this program. If not, see http://www.gnu.org/licenses/.
 *******************************************************************************/
package ca.mcgill.cs.swevo.minesweeper;

/**
 * Required for packaging to self-contained jar. The shade plugin needs a
 * "standard" `main` method to run the standalone application, but JavaFX
 * applications have their own launch mechanism, inherited from `Application`.
 * This class serves as proxy to bridge the gap between the shade plugin and
 * JavaFX's startup by delegating the `main` call to the existing JavaFX
 * launcher. See: https://stackoverflow.com/a/57691362
 */
public final class ShadeMinesweeper {
	/**
	 * Default constructor. Required by checkstyle.
	 */
	private ShadeMinesweeper() {
	}

	/**
	 * Main method to delegate program startup to Minesweeper class.
	 *
	 * @param pArgs as program arguments. Can be left empty.
	 */
	public static void main(String[] pArgs) {
		Minesweeper.main(pArgs);
	}
}