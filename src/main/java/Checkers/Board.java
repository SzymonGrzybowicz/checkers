package Checkers;

import javafx.event.Event;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Control;
import javafx.scene.layout.*;

import java.util.ArrayList;


public class Board {

    static Piece alreadyClickedPiece;
    static ArrayList<Field> possibleMoves = new ArrayList<>();
    static ArrayList<Field> possibleKickMoves = new ArrayList<>();
    static boolean whiteTurn;
    static ArrayList<Piece> whitePieces = new ArrayList<>();
    static ArrayList<Piece> blackPieces = new ArrayList<>();

    public GridPane createBoard() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(0, 0, 0, 0));

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {

                Field field;

                if ((row + column) % 2 == 0) {
                    field = new Field("white");

                } else {
                    field = new Field("black");
                }

                grid.add(field, column, row);


                if (row < 3 && (row + column) % 2 == 0) {
                    field.addBlackPiece();
                }

                if (row >= 5 && (row + column) % 2 != 0) {
                    field.addWhitePiece();
                }
                whiteTurn = true;


            }
        }

        for (int i = 0; i < 8; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true));
            grid.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE, Priority.ALWAYS, VPos.CENTER, true));

        }
        return grid;
    }

    public void readMouseEvent(Event event) {
        if (event.getTarget() instanceof Piece) {
            ((Piece) event.getTarget()).mouseClicked();
        }
        if (event.getTarget() instanceof Field){
            ((Field) event.getTarget()).mouseClicked();
        }

    }
}