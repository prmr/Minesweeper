package ca.mcgill.cs.swevo.minesweeper;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Starting point for the application.
 */
public class Minesweeper extends Application
{
	private static final int NUMBER_OF_ROWS = 20;
	private static final int NUMBER_OF_COLUMNS = 40;
	private static final int PADDING = 10;
	
	/**
	 * Launches the application.
	 * @param pArgs This program takes no argument.
	 */
	public static void main(String[] pArgs) 
	{
        launch(pArgs);
    }
	
    @Override
    public void start(Stage pPrimaryStage) 
    {
    	pPrimaryStage.setTitle("Minesweeper");
    	GridPane root = new GridPane();
    	root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(PADDING));
    	for( int columnIndex = 0; columnIndex < NUMBER_OF_COLUMNS; columnIndex++)
    	{
    		for( int rowIndex = 0; rowIndex < NUMBER_OF_ROWS; rowIndex++)
    		{
    			root.add(new Button(), columnIndex, rowIndex);
    		}
    	}
    	pPrimaryStage.setResizable(false);
        pPrimaryStage.setScene(new Scene(root));
        pPrimaryStage.show();
    }
}
