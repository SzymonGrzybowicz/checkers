package Checkers;


import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static Checkers.Utils.getNode;

abstract class Piece extends Circle {

    private boolean isQueen = false;

    public Piece(Color pieceColour) {
        super(30, pieceColour);

    }

    abstract void mouseClicked();

    abstract void paintMovePossible();

    abstract boolean canMoveOrKick();


    public void setQueen() {

        isQueen = true;
        super.setStroke(Color.GOLD);
        super.setStrokeWidth(6);

    }

    public boolean isQueen() {
        return isQueen;
    }


    public void paintKickPossible() {
        Board.alreadyClickedPiece = this;

        GridPane grid = (GridPane) this.getParent().getParent();
        int pieceRow = GridPane.getRowIndex(this.getParent());
        int piececolumn = GridPane.getColumnIndex(this.getParent());

        Field leftup = (Field) getNode(grid, pieceRow + 1, piececolumn - 1);
        Field leftdown = (Field) getNode(grid, pieceRow - 1, piececolumn - 1);
        Field rightup = (Field) getNode(grid, pieceRow + 1, piececolumn + 1);
        Field rightdown = (Field) getNode(grid, pieceRow - 1, piececolumn + 1);

        if (leftup != null && leftup.getChildren().size() > 0 && Board.alreadyClickedPiece.getClass() != leftup.getChildren().get(0).getClass()) {
            if (getNode(grid, pieceRow + 2, piececolumn - 2) != null && ((Field) getNode(grid, pieceRow + 2, piececolumn - 2)).getChildren().size() == 0) {

                leftup.setColour("purple");
                ((Field) getNode(grid, pieceRow + 2, piececolumn - 2)).setColour("green");
                Board.possibleKickMoves.add(((Field) getNode(grid, pieceRow + 2, piececolumn - 2)));

            }
        }
        if (leftdown != null && leftdown.getChildren().size() > 0 && Board.alreadyClickedPiece.getClass() != leftdown.getChildren().get(0).getClass()) {
            if (getNode(grid, pieceRow - 2, piececolumn - 2) != null && ((Field) getNode(grid, pieceRow - 2, piececolumn - 2)).getChildren().size() == 0) {

                leftdown.setColour("purple");
                ((Field) getNode(grid, pieceRow - 2, piececolumn - 2)).setColour("green");
                Board.possibleKickMoves.add(((Field) getNode(grid, pieceRow - 2, piececolumn - 2)));
            }

        }
        if (rightup != null && rightup.getChildren().size() > 0 && Board.alreadyClickedPiece.getClass() != rightup.getChildren().get(0).getClass()) {
            if (getNode(grid, pieceRow + 2, piececolumn + 2) != null && ((Field) getNode(grid, pieceRow + 2, piececolumn + 2)).getChildren().size() == 0) {

                rightup.setColour("purple");
                ((Field) getNode(grid, pieceRow + 2, piececolumn + 2)).setColour("green");
                Board.possibleKickMoves.add(((Field) getNode(grid, pieceRow + 2, piececolumn + 2)));

            }

        }
        if (rightdown != null && rightdown.getChildren().size() > 0 && Board.alreadyClickedPiece.getClass() != rightdown.getChildren().get(0).getClass()) {
            if (getNode(grid, pieceRow - 2, piececolumn + 2) != null && ((Field) getNode(grid, pieceRow - 2, piececolumn + 2)).getChildren().size() == 0) {

                rightdown.setColour("purple");
                ((Field) getNode(grid, pieceRow - 2, piececolumn + 2)).setColour("green");
                Board.possibleKickMoves.add(((Field) getNode(grid, pieceRow - 2, piececolumn + 2)));

            }
        }
    }


}
