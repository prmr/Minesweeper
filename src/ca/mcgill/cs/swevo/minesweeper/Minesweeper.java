package ca.mcgill.cs.swevo.minesweeper;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
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
	private GridPane aGrid;
	private Label aStatusBar;
	
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
        newGame();
        refresh();
        pStage.show();
        aGrid.requestFocus();
    }
    
    private void prepareStage(Stage pStage) 
	{
    	pStage.setTitle("Minesweeper");
    	pStage.setResizable(false);
	}
    
    private void newGame()
    {
    	aMinefield = new Minefield(16, 16, 20);
    	refresh();
    }
    
    private void refresh()
    {
    	aGrid.getChildren().clear();
    	aGrid.requestFocus();
    	for( Position position : aMinefield.getAllPositions() )
    	{
    		aGrid.add(createTile(position), position.getColumn(), position.getRow());
    	}
    	GameStatus status = aMinefield.getGameStatus();
    	if( status == GameStatus.WON )
    	{
    		aStatusBar.setText("You won! Press Enter to play again");
    	}
    	else if( status == GameStatus.LOST )
    	{
    		aStatusBar.setText("You lost. Press Enter to try again");
    	}
    	else
    	{
    		aStatusBar.setText(aMinefield.getNumberOfMinesLeft() + " mines left");
    	}
    }
	
	private Scene createScene() 
	{
		BorderPane root = new BorderPane();
		aStatusBar = new Label();
		aStatusBar.setFont(new Font("Arial", 16));
		BorderPane.setMargin(aStatusBar, new Insets(5, 0, 0, 8));
		root.setTop(aStatusBar);
		aGrid = new GridPane();
		root.setCenter(aGrid);
		aGrid.setHgap(1);
		aGrid.setVgap(1);
		aGrid.setAlignment(Pos.CENTER);
		aGrid.setPadding(new Insets(PADDING));
		
		root.setOnKeyTyped(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(final KeyEvent pEvent)
			{
				if( pEvent.getCharacter().equals("\r"))
				{
					newGame();
					refresh();
				}
				pEvent.consume();
			}
		});
		 
		return new Scene(root);
	}
	
	private Node createTile(Position pPosition)
	{
		CellView status = aMinefield.getStatus(pPosition);
		if( status == CellView.MARKED )
		{
			return createHiddenTile(pPosition, true);
		}
		else if( status == CellView.HIDDEN )
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
	
	private Label createRevealedTile(Position pPosition, CellView pStatus)
	{
		Label tile = new Label();
		tile.setMinSize(0, 0);
		tile.setStyle("-fx-pref-width: 20px; -fx-pref-height: 20px; -fx-border-width: 0; -fx-border-color: black; -fx-background-color: lightgrey;");
		tile.setAlignment(Pos.CENTER);
		tile.setFont(new Font("Arial", 14));
		if( pStatus == CellView.MINE )
		{
			tile.setText("X");
		}
		else if( pStatus == CellView.CLEAR )
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
