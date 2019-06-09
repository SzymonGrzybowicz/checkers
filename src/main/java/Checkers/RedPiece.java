package Checkers;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.List;

import static Checkers.Utils.getNode;

public class RedPiece extends Piece {


    public RedPiece() {

        super(Color.RED);
    }

    @Override
    void mouseClicked() {

        if (Board.alreadyClickedPiece != null) {
            Utils.setBoardDefaultColours((GridPane) this.getParent().getParent());
            Board.possibleMoves.clear();
            Board.alreadyClickedPiece = null;
        }
        if (!(Board.whiteTurn)) {
            Board.alreadyClickedPiece = this;
            if (Utils.canKick(this)) {
                paintKickPossible();
            } else if (Utils.checkWhichPieceCanKick((GridPane) this.getParent().getParent()).size() == 0) {
                this.paintMovePossible();
            }
        }


    }

    public boolean canMoveOrKick() {

        if (Utils.canKick(this)) {
            return true;
        }
        int row = GridPane.getRowIndex(this.getParent());
        int column = GridPane.getColumnIndex(this.getParent());

        Field leftDown = (Field) getNode((GridPane) this.getParent().getParent(), (row + 1), (column + 1));
        Field rightDown = (Field) getNode((GridPane) this.getParent().getParent(), (row + 1), (column - 1));

        if (leftDown != null && leftDown.getChildren().size() == 0) {
            return true;
        }
        if (rightDown != null && rightDown.getChildren().size() == 0) {
            return true;
        }

        if (isQueen()) {
            Field leftUp = (Field) Utils.getNode((GridPane) this.getParent().getParent(), (row - 1), (column + 1));
            Field rightUp = (Field) Utils.getNode((GridPane) this.getParent().getParent(), (row - 1), (column - 1));

            if (leftUp != null && leftUp.getChildren().size() == 0) {
                return true;
            }
            if (rightUp != null && rightUp.getChildren().size() == 0) {
                return true;
            }
        }
        return false;

    }

    public void paintMovePossible() {


        int row = GridPane.getRowIndex(this.getParent());
        int column = GridPane.getColumnIndex(this.getParent());

        Field leftDown = (Field) getNode((GridPane) this.getParent().getParent(), (row + 1), (column + 1));
        Field rightDown = (Field) getNode((GridPane) this.getParent().getParent(), (row + 1), (column - 1));

        if (leftDown != null && leftDown.getChildren().size() == 0) {
            Board.possibleMoves.add(leftDown);
        }
        if (rightDown != null && rightDown.getChildren().size() == 0) {
            Board.possibleMoves.add(rightDown);
        }

        if (isQueen()) {
            Field leftUp = (Field) Utils.getNode((GridPane) this.getParent().getParent(), (row - 1), (column + 1));
            Field rightUp = (Field) Utils.getNode((GridPane) this.getParent().getParent(), (row - 1), (column - 1));

            if (leftUp != null && leftUp.getChildren().size() == 0) {
                Board.possibleMoves.add(leftUp);
            }
            if (rightUp != null && rightUp.getChildren().size() == 0) {
                Board.possibleMoves.add(rightUp);
            }
        }

        for (Field field : Board.possibleMoves) {
            field.setColour("green");
        }


    }

    public boolean movePossible() {
        int row = GridPane.getRowIndex(this.getParent());
        int column = GridPane.getColumnIndex(this.getParent());

        Field leftDown = (Field) getNode((GridPane) this.getParent().getParent(), (row + 1), (column + 1));
        Field rightDown = (Field) getNode((GridPane) this.getParent().getParent(), (row + 1), (column - 1));

        if (leftDown != null && leftDown.getChildren().size() == 0) {
            return true;
        }
        if (rightDown != null && rightDown.getChildren().size() == 0) {
            return true;
        }

        if (isQueen()) {
            Field leftUp = (Field) Utils.getNode((GridPane) this.getParent().getParent(), (row - 1), (column + 1));
            Field rightUp = (Field) Utils.getNode((GridPane) this.getParent().getParent(), (row - 1), (column - 1));

            if (leftUp != null && leftUp.getChildren().size() == 0) {
                return true;
            }
            if (rightUp != null && rightUp.getChildren().size() == 0) {
                return true;
            }
        }
        return false;
    }
}
