package Checkers;


import javafx.scene.image.Image;
import javafx.scene.layout.*;




public class Piece extends StackPane {

    private int pieceColour;
    private boolean isQueen;
    private Image white = new Image("white_piece.png");
    private Image black = new Image("black_piece.png");



    public Piece(int pieceColour, boolean isQueen) {
        this.pieceColour = pieceColour;
        this.isQueen = isQueen;
        if (pieceColour == 1){

            BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage backgroundImage = new BackgroundImage(white, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            Background background = new Background(backgroundImage);
            this.setBackground(background);

        }else{

            BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage backgroundImage = new BackgroundImage(black, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            Background background = new Background(backgroundImage);

            this.setBackground(background);
        }
    }

    public void mouseClickedOnPiece(){
        System.out.println("x: " + GridPane.getColumnIndex(this.getParent()));
        System.out.println("y: " + GridPane.getRowIndex(this.getParent()));
    }

    public void setQueen(boolean queen) {
        isQueen = queen;
    }

    public int getPieceColour() {
        return pieceColour;
    }

    public boolean isQueen() {
        return isQueen;
    }

}
