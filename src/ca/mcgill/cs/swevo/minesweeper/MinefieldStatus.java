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

/**
 * Represents the current abstract state of a minefield.
 * 
 * @author Martin P. Robillard
 */
public enum MinefieldStatus
{
	/**
	 * One or more cells remain hidden.
	 */
	NOT_CLEARED, 
	
	/**
	 * All the cells have been revealed, and the location of all mines
	 * has been correctly marked.
	 */
	CLEARED, 
	
	/**
	 * At least one cell containing a mine has been revealed.
	 */
	EXPLODED
}
