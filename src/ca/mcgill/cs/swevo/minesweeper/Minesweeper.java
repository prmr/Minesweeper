package ca.mcgill.cs.swevo.minesweeper;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
        aRoot.requestFocus();
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
    	aRoot.getChildren().clear();
    	aRoot.requestFocus();
    	for( Position position : aMinefield.getAllPositions() )
    	{
    		aRoot.add(createTile(position), position.getColumn(), position.getRow());
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
	
	private Node createTile(Position pPosition)
	{
		CellStatus status = aMinefield.getStatus(pPosition);
		if( status == CellStatus.MARKED )
		{
			return createHiddenTile(pPosition, true);
		}
		else if( status == CellStatus.HIDDEN )
		{
			return createHiddenTile(pPosition, false);
		}
		else
		{
			return createRevealedTile(pPosition, status);
		}
	}
	
	private Button createHiddenTile(Position pPosition, boolean pMarked)
	{
		Button button = new Button();
		
		button.setMinHeight(0);
		button.setMinWidth(0);
		button.setStyle("-fx-background-radius: 0; -fx-pref-width: 20px; -fx-pref-height: 20px;" +
				"-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-font-size: 12; -fx-text-fill: red; -fx-font-weight: bold");
		if( pMarked)
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
	
	private Label createRevealedTile(Position pPosition, CellStatus pStatus)
	{
		Label tile = new Label();
		tile.setMinSize(0, 0);
		tile.setStyle("-fx-pref-width: 20px; -fx-pref-height: 20px; -fx-border-width: 0; -fx-border-color: black; -fx-background-color: lightgrey;");
		tile.setAlignment(Pos.CENTER);
		tile.setFont(new Font("Arial", 14));
		if( pStatus == CellStatus.MINE )
		{
			tile.setText("X");
		}
		else if( pStatus == CellStatus.CLEAR )
		{
			tile.setText(" ");
		}
		else
		{
			tile.setText(Integer.toString(aMinefield.getNumberOfMinedNeighbours(pPosition)));
		}
		return tile;
	}
}
