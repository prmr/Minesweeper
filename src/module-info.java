/**
 * Main javafx module for the minesweeper application.
 */
module minesweeper {
	requires javafx.controls;
	requires transitive javafx.graphics;	
	exports ca.mcgill.cs.swevo.minesweeper;
}