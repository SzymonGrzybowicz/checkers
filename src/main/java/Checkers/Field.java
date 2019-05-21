package Checkers;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


public class Field extends StackPane {

    private Piece piece;
    private boolean pieceExist = false;

    public Field() {
        this.setMaxSize(100, 100);
        this.setMinSize(100, 100);
    }

    public void setColour(String colour) {
        this.setStyle("-fx-background-color: " + colour + ";");
    }

    public void addPiece(Color pieceColor) {
        piece = new Piece(30, pieceColor, false);
        this.getChildren().addAll(piece);
        pieceExist = true;


    }

    public void removePiece() {
        this.getChildren().removeAll(piece);
        pieceExist = false;

    }

    public boolean isPieceExist() {
        return pieceExist;
    }
}
