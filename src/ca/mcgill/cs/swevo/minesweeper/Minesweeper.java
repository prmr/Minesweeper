package ca.mcgill.cs.swevo.minesweeper;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Starting point for the application.
 */
public class Minesweeper extends Application
{
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
    	aMinefield = new Minefield(20, 20, 50);
    	refresh();
    }
    
    private void refresh()
    {
    	for( Position position : aMinefield.getHiddenPositions() )
    	{
    		aRoot.add(createHiddenTile(position), position.getColumn(), position.getRow());
    	}
    	for( Position position : aMinefield.getRevealedPositions() )
    	{
    		aRoot.add(createRevealedTile(position), position.getColumn(), position.getRow());
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
	
	private Button createHiddenTile(Position pPosition)
	{
		Button button = new Button();
		
		button.setMinHeight(0);
		button.setMinWidth(0);
		button.setStyle("-fx-background-radius: 0; -fx-pref-width: 20px; -fx-pref-height: 20px;" +
				"-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-font-size: 12; -fx-text-fill: red; -fx-font-weight: bold");
		if( aMinefield.isMarked(pPosition))
		{
			button.setText("!");
		}
		button.setOnAction(e-> {aMinefield.reveal(pPosition); refresh();}); 
		button.setOnMouseClicked( e-> {
			if( e.getButton() == MouseButton.SECONDARY )
			{
				aMinefield.toggleMark(pPosition); refresh();
			}
		});
		return button;
	}
	
	private Label createRevealedTile(Position pPosition)
	{
		Label tile = new Label();
		tile.setMinSize(0, 0);
		tile.setStyle("-fx-pref-width: 20px; -fx-pref-height: 20px; -fx-border-width: 0; -fx-border-color: black; -fx-background-color: lightgrey;");
		tile.setAlignment(Pos.CENTER);
		tile.setFont(new Font("Arial", 14));
		if( aMinefield.isMined(pPosition))
		{
			tile.setText("X");
		}
		else
		{
			int neighbours = aMinefield.getNumberOfMinedNeighbours(pPosition);
			if( neighbours == 0 )
			{
				tile.setText(" ");
			}
			else
			{
				tile.setText(Integer.toString(neighbours));
			}
		}
		return tile;
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
