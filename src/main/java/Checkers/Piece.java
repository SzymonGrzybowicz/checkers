package Checkers;


import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Piece extends Circle {

    private Color pieceColour;
    private boolean isQueen;

    public Piece(double radius, Color pieceColour, boolean isQueen) {
        super(radius, pieceColour);
        this.pieceColour = pieceColour;
        this.isQueen = isQueen;
    }


    public void setQueen(boolean queen) {
        isQueen = queen;
    }

    public boolean isQueen() {
        return isQueen;
    }

    public void mouseClicked() {
         ((Field) this.getParent()).setColour("green");
         movePossible();

    }

    private boolean movePossible() {


    return false;
    }


}
