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
	
	/**
	 * Creates a new grid.
	 * 
	 * @param pHeight The number of rows.
 	 * @param pWidth The number of columns.
	 */
	public Grid(int pHeight, int pWidth) {
		List<Position> positions = new ArrayList<>();
		for (int row = 0; row < pHeight; row++) {
			for (int column = 0; column < pWidth; column++) {
				positions.add(new Position(row, column));
			}
		}
		aPositions = Collections.unmodifiableList(positions);
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
}
