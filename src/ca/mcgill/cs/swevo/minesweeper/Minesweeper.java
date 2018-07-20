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
	private static final int NUMBER_OF_COLUMNS = 30;
	private static final int PADDING = 1;
	
	/**
	 * Launches the application.
	 * @param pArgs This program takes no argument.
	 */
	public static void main(String[] pArgs) 
	{
        launch(pArgs);
    }
	
    @Override
    public void start(Stage pStage) 
    {
    	prepareStage(pStage);
        pStage.setScene(createScene());
        pStage.show();
       	setFocus(pStage);
    }
    
    private void prepareStage(Stage pStage) 
	{
    	pStage.setTitle("Minesweeper");
    	pStage.setResizable(false);
	}
	
	private Scene createScene() 
	{
		GridPane root = new GridPane();
		root.setHgap(1);
		root.setVgap(1);
    	root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(PADDING));
    	for( int columnIndex = 0; columnIndex < NUMBER_OF_COLUMNS; columnIndex++)
    	{
    		for( int rowIndex = 0; rowIndex < NUMBER_OF_ROWS; rowIndex++)
    		{
    			root.add(createCellButton(), columnIndex, rowIndex);
    		}
    	}
		return new Scene(root);
	}
	
	private Button createCellButton()
	{
		Button button = new Button();
		button.setMinHeight(0);
		button.setMinWidth(0);
		button.setStyle("-fx-background-radius: 0; -fx-pref-width: 20px; -fx-pref-height: 20px;" +
				"-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
		return button;
	}
	
	/*
	 * By default the GUI focus highlight is set on the top-left button.
	 * This method fixes this by setting the focus on the entire layout.
	 */
	private void setFocus(Stage pStage)
	{
        pStage.getScene().getRoot().requestFocus();
	}
}
