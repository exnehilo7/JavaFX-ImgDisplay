/*
Programmer: Dan Hopp
Date: 12-MAR-2020
Description: Implement the following in one JavaFx application file (one 
application/file, but make it create two stages):

    Stage 1:  Create and display an 8x8 checkerboard (chess board) grid. 
              Add one chess piece image to any of the squares on the board,
              properly sized and centered within its square. 

    Stage 2:  Randomly choose and display three Hieroglyphs side-by-side.

 */
package imagedisplay;

import java.security.SecureRandom;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class DisplayImages extends Application{
    
    @Override
    public void start(Stage primaryStage){
        
        //Show scene for the chessboard
        primaryStage.setTitle("Greetings, Professor Falken (Stage 1)");
        primaryStage.setScene(ChessBoard());
        primaryStage.show();
        
        //Show scene for the Hieroglyphs
        Stage stage02 = new Stage();
        stage02.setTitle("Walk Like an Egyptian (Stage 2)");
        stage02.setScene(Hieroglyphs());
        stage02.show();
        
    }
    
/*Method to create an array of squares, color the squares like a chess board, 
create an array of stackPanes, and populate the stackPane array with the
squares and a chess piece. Place each stack pane into a single grid pane
coordinate. Return the scene.
Pawn image taken from http://www.pdclipart.org/  */    
    private Scene ChessBoard(){
        //Create the chessboard base
        GridPane chessBoard = new GridPane();
        chessBoard.setAlignment(Pos.CENTER);
        
        //Create an array of Rectangles
        Rectangle[] rectangleArray = new Rectangle[64];
        
        /*Create the "checkers". Make the rectangles square. Start with a white 
        square, and alternate from white to black. After 8 iterations, start 
        with the opposite starting color of the previous iteration*/
        boolean colorToggle = false;
        for (int i = 0; i < rectangleArray.length; i++){
            rectangleArray[i] = new Rectangle(0, 0, 50, 50);

            if (colorToggle){
                rectangleArray[i].setFill(Color.BLACK);
                colorToggle = false;
            }
            else{
                rectangleArray[i].setFill(Color.WHITE);
                colorToggle = true;
            }
            
            //Switch the pattern for the next row
            if ((i + 1) % 8 == 0){ //remmeber them zero-indexing
                if (colorToggle)
                    colorToggle = false;
                else
                    colorToggle = true;
            }         
        }
        
        //The image folder is in the src folder
        Image blackPawnImg = new Image("images/pawn.png");
        ImageView imageView00 = new ImageView(blackPawnImg);

        /*Create a stackPane for each grid, and place a square and the pawn in one
        of the squares */
        StackPane[] stackPaneArray = new StackPane[64];
        
        
        for(int i = 0; i < stackPaneArray.length; i++){
            //Populate array with StackPanes
            stackPaneArray[i] = new StackPane();
            //Add the pawn
            stackPaneArray[i].getChildren().addAll(rectangleArray[i], imageView00);
        }

        //Populate the board with the "checkers" and the piece
        int arrIndex = 0;
        //Row
        for (int row = 0; row < 8; row++){
           //Columns
           for(int col = 0; col < 8; col++){
               chessBoard.add(stackPaneArray[arrIndex], col, row);
               arrIndex++;
           }
        }

        //Make a scene to put in the main stage
        Scene scene = new Scene(chessBoard);
        
        return scene;
        
    }
    
    
/*Method to put three random Hieroglyphs into an horizontal box.*/
    private Scene Hieroglyphs(){
        
        //Create horizontal pane, spacing 50
        HBox hBox = new HBox(50);
        
        //Get a (true) random number generator
        SecureRandom randomInt = new SecureRandom();
      
        //Import 3 random images. The image folder is in the src folder
        //Image 41 removed. Technically it wasn't a Hieroglyph
        Image glyph00 = new Image("images/Hieroglyphs/" + 
                randomInt.nextInt(60) + ".png");
        Image glyph01 = new Image("images/Hieroglyphs/" + 
                randomInt.nextInt(60) + ".png");
        Image glyph02 = new Image("images/Hieroglyphs/" + 
                randomInt.nextInt(60) + ".png");
        
        //Place images in Hbox
        hBox.getChildren().addAll(new ImageView(glyph00), new ImageView( glyph01),
                new ImageView(glyph02));
                
        //Make a scene to put in the second stage
        Scene scene = new Scene(hBox);
        
        return scene;
    }

/*Main method to launch app*/
    public static void main(String[] args) {
        Application.launch(args);
    }    
    
}


