/*******************************************************************************
 * Minesweeper
 *
 * Copyright (C) 2018-2026 by Martin P. Robillard
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
package ca.mcgill.msweeper.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A rectangular array of cells that can be mined and flagged for mines.
 */
public class Minefield {
	
	private final Grid aGrid;
	private final Set<Position> aMinedPositions = new HashSet<>();
	private final Set<Position> aFlaggedPositions = new HashSet<>();
	private final Set<Position> aRevealedPositions = new HashSet<>();
	
	/**
	 * Creates a new minefield of a specified size which contains a specified number of mines
	 * placed randomly throughout the field.
	 * 
	 * @param pRows The number of rows in the field.
	 * @param pColumns The number of columns in the field.
	 * @param pMines the number of mines in the field.
	 */
	public Minefield(int pRows, int pColumns, int pMines) {
		assert pRows > 0 && pColumns > 0 && pMines >= 0 && pMines <= pColumns * pRows;
		aGrid = new Grid(pRows, pColumns);
		aMinedPositions.addAll(aGrid.randomPositions(pMines));
	}
	
	private static Set<Position> intersection(Set<Position> pSet1, Set<Position> pSet2) {
		Set<Position> intersection = new HashSet<>(pSet1);
		intersection.retainAll(pSet2);
		return intersection;
	}
	
	/**
	 * The number of mines left is the number of flagged mines 
	 * minus the total number of mines.
	 * 
	 * @return The number of mines left according to the player.
	 */
	public int getNumberOfMinesLeft() {
		return aMinedPositions.size() - aFlaggedPositions.size();
	}
	
	/**
	 * Returns if a position is revealed.
	 *
	 * @param pPosition The position to query.
	 * @return True if pPosition is revealed.
	 * @pre pPosition != null
	 */
	public boolean isRevealed(Position pPosition) {
		assert pPosition != null;
		return aRevealedPositions.contains(pPosition);
	}
	
	/**
	 * Returns if a position is flagged.
	 *
	 * @param pPosition The position to query.
	 * @return True if pPosition is flagged.
	 * @pre pPosition != null
	 */
	public boolean isFlagged(Position pPosition) {
		assert pPosition != null;
		return aFlaggedPositions.contains(pPosition);
	}

	/**
	 * Returns whether a position is mined.
	 *
	 * @param pPosition The position to query.
	 * @return True if pPosition is mined.
	 * @pre pPosition != null
	 */
	public boolean isMined(Position pPosition) {
		assert pPosition != null;
		return aMinedPositions.contains(pPosition);
	}
	
	/**
	 * Toggle the flag for pPosition.
	 * 
	 * @param pPosition The position of the cell to flag/unflag.
	 * @pre pPosition != null.
	 */
	public void toggleFlag(Position pPosition) {
		assert pPosition != null;
		if (aFlaggedPositions.contains(pPosition)) {
			aFlaggedPositions.remove(pPosition);
		}
		else {
			aFlaggedPositions.add(pPosition);
		}
	}
	
	/**
	 * Return the number of mined cells in the up to 8 cells that surround pPosition.
	 * 
	 * @param pPosition The position to query.
	 * @return The number of mined cells around pPosition.
	 * @pre pPosition != null.
	 */
	public int getNumberOfMinedNeighbours(Position pPosition) {
		assert pPosition != null;
		return intersection(aMinedPositions, aGrid.getNeighbours(pPosition)).size();
	}
	
	/**
	 * @return True if all the unmined positions are cleared.
	 */
	public boolean isCleared() {
		return !isExploded() && 
				aRevealedPositions.size() == aGrid.allPositions().size() - aMinedPositions.size() && 
				aFlaggedPositions.equals(aMinedPositions);
	}
	
	/**
	 * @return True if any revealed position is mined.
	 */
	public boolean isExploded() {
		return !intersection(aMinedPositions, aRevealedPositions).isEmpty();
	}
	
	/**
	 * Reveal whether there is a mine or not at pPosition.
	 * 
	 * @param pPosition The position to reveal.
	 * @pre pPosition != null;
	 */
	public void reveal(Position pPosition) {
		assert pPosition != null;
		if (aMinedPositions.contains(pPosition)) {
			revealAll();
		}
		else {
			aRevealedPositions.add(pPosition);
			autoReveal(pPosition);
		}
	}
	
	/**
	 * @return All the positions in the minefield
	 */
	public List<Position> getAllPositions() {
		return aGrid.allPositions();
	}

	private void revealAll() {
		aRevealedPositions.addAll(aGrid.allPositions());
	}

	private void autoReveal(Position pPosition) {
		if (getNumberOfMinedNeighbours(pPosition) == 0) {
			for (Position neighbour : aGrid.getNeighbours(pPosition)) {
				if (!isRevealed(neighbour) && !isFlagged(neighbour)) {
					reveal(neighbour);
				}
			}
		}
	}
}
