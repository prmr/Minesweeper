package ca.mcgill.cs.swevo.minesweeper;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Starting point for the application.
 */
public class Minesweeper extends Application
{
	private static final int NUMBER_OF_ROWS = 20;
	private static final int NUMBER_OF_COLUMNS = 30;
	private static final int PADDING = 1;
	
	private Minefield aMinefield;
	private GridPane aRoot;
	
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
    	createScene();
        newGame();
        refresh();
    	aMinefield.addObserver(()-> refresh());
        pStage.setScene(new Scene(aRoot));
        pStage.show();
       	setFocus(pStage);
    }
    
    private void prepareStage(Stage pStage) 
	{
    	pStage.setTitle("Minesweeper");
    	pStage.setResizable(false);
	}
    
    private void newGame()
    {
    	aMinefield = new Minefield(20, 10, 10);
    	refresh();
    }
    
    private void refresh()
    {
    	for( int columnIndex = 0; columnIndex < aMinefield.getNumberOfColumns(); columnIndex++)
    	{
    		for( int rowIndex = 0; rowIndex < aMinefield.getNumberOfRows(); rowIndex++)
    		{
    			aRoot.add(createTile(aMinefield.getView(columnIndex, rowIndex), columnIndex, rowIndex), columnIndex, rowIndex);
    		}
    	}
    }
	
	private void createScene() 
	{
		aRoot = new GridPane();
		aRoot.setHgap(1);
		aRoot.setVgap(1);
		aRoot.setAlignment(Pos.CENTER);
		aRoot.setPadding(new Insets(PADDING));
	}
	
	private Node createTile(CellView pView, int pColumn, int pRow)
	{
		if( pView == CellView.Hidden )
		{
			Button button = new Button();
			button.setMinHeight(0);
			button.setMinWidth(0);
			button.setStyle("-fx-background-radius: 0; -fx-pref-width: 20px; -fx-pref-height: 20px;" +
					"-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
			button.setOnAction(e-> aMinefield.reveal(pColumn, pRow));
			return button;
		}
		else if( pView == CellView.Bomb )
		{
			Label tile = new Label("X");
			tile.setMinSize(0, 0);
			tile.setStyle("-fx-pref-width: 20px; -fx-pref-height: 20px; -fx-border-width: 0; -fx-border-color: black; -fx-background-color: lightgrey;");
			tile.setAlignment(Pos.CENTER);
			tile.setFont(new Font("Arial", 14));
			return tile;
		}
		else if( pView == CellView.Zero )
		{
			Label tile = new Label(" ");
			tile.setMinSize(0, 0);
			tile.setStyle("-fx-pref-width: 20px; -fx-pref-height: 20px; -fx-border-width: 0; -fx-border-color: black; -fx-background-color: lightgrey;");
			tile.setAlignment(Pos.CENTER);
			tile.setFont(new Font("Arial", 14));
			tile.setText(Integer.toString(pView.ordinal()-2));
			return tile;
		}
		else
		{
			Label tile = new Label();
			tile.setMinSize(0, 0);
			tile.setStyle("-fx-pref-width: 20px; -fx-pref-height: 20px; -fx-border-width: 0; -fx-border-color: black; -fx-background-color: lightgrey;");
			tile.setAlignment(Pos.CENTER);
			tile.setFont(new Font("Arial", 14));
			tile.setText(Integer.toString(pView.ordinal()-2));
			return tile;
		}

//		else if( pView == CellView.Bomb )
//		{
//			Text text = new Text();
//			text.setFill(Color.RED);
//			return text;
//		}
//		else
//		{
//			Text text = new Text(Integer.toString(pView.ordinal()-2));
//			return text;
//		}
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
