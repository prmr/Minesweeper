package ca.mcgill.msweeper.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Represents a range of cells in two dimensions. Columns run left-to-right
 * starting with index 0. Rows run top-to-bottom starting with index 0.
 */
public class Grid implements Iterable<Position> {
	
	private final List<Position> aPositions;
	private final int aHeight;
	private final int aWidth;
	
	/**
	 * Creates a new grid.
	 * 
	 * @param pHeight The number of rows.
 	 * @param pWidth The number of columns.
	 */
	public Grid(int pHeight, int pWidth) {
		aHeight = pHeight;
		aWidth = pWidth;
		List<Position> positions = new ArrayList<>();
		for (int row = 0; row < pHeight; row++) {
			for (int column = 0; column < pWidth; column++) {
				positions.add(new Position(row, column));
			}
		}
		aPositions = Collections.unmodifiableList(positions);
	}

	/**
	 * @return An unmodifiable list of all positions.
	 */
	public List<Position> allPositions() {
		return aPositions;
	}
	
	@Override
	public Iterator<Position> iterator() {
		return aPositions.iterator();
	}

	/**
	 * Returns a specified number of random positions.
	 * 
	 * @param pNumber The requested number of random positions.
	 * @return A list of random positions.
	 */
	public Set<Position> randomPositions(int pNumber) {
		assert pNumber <= aPositions.size();
		List<Position> positions = new ArrayList<>(aPositions);
		Collections.shuffle(positions);
		return new HashSet<>(positions.subList(0, pNumber));
	}
	
	/**
	 * Returns all the valid immediate neighbours of pPositions that 
	 * are within the grid. 
	 * 
	 * @param pPosition The center position.
	 * @return A set of up to 8 positions that are immediate neighbours of pPosition.
	 */
	public Set<Position> getNeighbours(Position pPosition) {
		assert pPosition != null;
		assert aPositions.contains(pPosition);
		Set<Position> neighbours = new HashSet<>();
		final int minRow = Math.max(0, pPosition.getRow()-1);
		final int maxRow = Math.min(pPosition.getRow() + 1, aHeight-1);
		final int minColumn = Math.max(0,  pPosition.getColumn()-1);
		final int maxColumn = Math.min(pPosition.getColumn() + 1, aWidth -1);
		for (int row = minRow; row <= maxRow; row++) {
			for (int column = minColumn; column <= maxColumn; column++) {
				neighbours.add(new Position(row, column));
			}
		}
		neighbours.remove(pPosition);
		return neighbours;
	}
}
