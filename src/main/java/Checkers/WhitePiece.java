package Checkers;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import static Checkers.Utils.getNode;

public class WhitePiece extends Piece {


    public WhitePiece() {
        super(Color.WHITE);

    }


    @Override
    public void mouseClicked() {
        if (Board.alreadyClickedPiece != null) {
            Utils.setBoardDefaultColours((GridPane) this.getParent().getParent());
            Board.possibleMoves.clear();
            Board.alreadyClickedPiece = null;
        }
        if (Board.whiteTurn) {
            Board.alreadyClickedPiece = this;
            if (Utils.canKick(this)) {
                paintKickPossible();
            } else if (Utils.checkWhichPieceCanKick((GridPane) this.getParent().getParent()).size() == 0) {
                paintMovePossible();
            }
        }

    }


    public void paintMovePossible() {
        {


            int row = GridPane.getRowIndex(this.getParent());
            int column = GridPane.getColumnIndex(this.getParent());

            Field leftUp = (Field) Utils.getNode((GridPane) this.getParent().getParent(), (row - 1), (column + 1));
            Field rightUp = (Field) Utils.getNode((GridPane) this.getParent().getParent(), (row - 1), (column - 1));

            if (leftUp != null && leftUp.getChildren().size() == 0) {
                Board.possibleMoves.add(leftUp);
            }
            if (rightUp != null && rightUp.getChildren().size() == 0) {
                Board.possibleMoves.add(rightUp);
            }


            if (isQueen()) {
                Field leftDown = (Field) Utils.getNode((GridPane) this.getParent().getParent(), (row + 1), (column + 1));
                Field rightDown = (Field) Utils.getNode((GridPane) this.getParent().getParent(), (row + 1), (column - 1));

                if (leftDown != null && leftDown.getChildren().size() == 0) {
                    Board.possibleMoves.add(leftDown);
                }

                if (rightDown != null && rightDown.getChildren().size() == 0) {
                    Board.possibleMoves.add(rightDown);
                }
            }

            for (Field field : Board.possibleMoves) {
                field.setColour("green");
            }
        }


    }

    public boolean canMoveOrKick() {
        if (Utils.canKick(this)) {
            return true;
        }
        int row = GridPane.getRowIndex(this.getParent());
        int column = GridPane.getColumnIndex(this.getParent());

        Field leftUp = (Field) getNode((GridPane) this.getParent().getParent(), (row - 1), (column + 1));
        Field rightUp = (Field) getNode((GridPane) this.getParent().getParent(), (row - 1), (column - 1));

        if (leftUp != null && leftUp.getChildren().size() == 0) {
            return true;
        }
        if (rightUp != null && rightUp.getChildren().size() == 0) {
            return true;
        }

        if (isQueen()) {
            Field leftDown = (Field) Utils.getNode((GridPane) this.getParent().getParent(), (row + 1), (column + 1));
            Field rightDown = (Field) Utils.getNode((GridPane) this.getParent().getParent(), (row + 1), (column - 1));

            if (leftDown != null && leftDown.getChildren().size() == 0) {
                return true;
            }
            if (rightDown != null && rightDown.getChildren().size() == 0) {
                return true;
            }
        }
        return false;
    }

}
