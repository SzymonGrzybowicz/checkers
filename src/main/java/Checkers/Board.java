package Checkers;

import javafx.event.Event;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Control;
import javafx.scene.layout.*;

public class Board {

    public GridPane createBoard() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(0, 0, 0, 0));

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                StackPane square = new StackPane();
                String colour;


                if ((row + column) % 2 == 0) {
                    colour = "white";

                } else {
                    colour = "black";
                }
                square.setStyle("-fx-background-color: " + colour + ";");
                grid.add(square, column, row);


                //Dodawanie pionków na pola startowe

                if (row < 3 && (row + column) % 2 == 0) {
                    square.getChildren().add(new Piece(0, false));
                }

                if (row >= 5 && (row + column) % 2 != 0) {
                    square.getChildren().add(new Piece(1, false));
                }


            }
        }

        for (int i = 0; i < 8; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true));
            grid.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE, Priority.ALWAYS, VPos.CENTER, true));

        }
        return grid;
    }

    public void readMouseEvent(Event event){
        if(event.getTarget() instanceof Piece){
            ((Piece) event.getTarget()).mouseClickedOnPiece();
        }
    }

}
