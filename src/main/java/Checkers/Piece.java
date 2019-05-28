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


    public void setQueen() {

        isQueen = true;
        super.setStroke(Color.GOLD);
        super.setStrokeWidth(6);

    }

    public boolean isQueen() {
        return isQueen;
    }


    public void paintKickPossible(){
        Board.alreadyClickedPiece = this;

        GridPane grid = (GridPane) this.getParent().getParent();
        int pieceRow = GridPane.getRowIndex(this.getParent());
        int piececolumn = GridPane.getColumnIndex(this.getParent());

        Field up = (Field) getNode(grid, pieceRow + 1, piececolumn);
        Field down = (Field) getNode(grid, pieceRow - 1, piececolumn);
        Field left = (Field) getNode(grid, pieceRow, piececolumn - 1);
        Field right = (Field) getNode(grid, pieceRow, piececolumn + 1);

        if (up != null && up.getChildren().size() > 0) {
            if (((Field) getNode(grid, pieceRow + 2, piececolumn)).getChildren().size() == 0) {

                up.setColour("red");
                ((Field) getNode(grid, pieceRow + 2, piececolumn)).setColour("green");
                Board.possibleKickMoves.add(((Field) getNode(grid, pieceRow + 2, piececolumn)));

            }
        }
        if (down != null && down.getChildren().size() > 0) {
            if (((Field) getNode(grid, pieceRow - 2, piececolumn)).getChildren().size() == 0) {

                down.setColour("red");
                ((Field) getNode(grid, pieceRow - 2, piececolumn)).setColour("green");
                Board.possibleKickMoves.add(((Field) getNode(grid, pieceRow - 2, piececolumn)));
            }

        }
        if (left != null && left.getChildren().size() > 0) {
            if (((Field) getNode(grid, pieceRow, piececolumn - 2)).getChildren().size() == 0) {

                left.setColour("red");
                ((Field) getNode(grid, pieceRow, piececolumn - 2)).setColour("green");
                Board.possibleKickMoves.add(((Field) getNode(grid, pieceRow, piececolumn - 2)));

            }

        }
        if (right != null && right.getChildren().size() > 0) {
            if (((Field) getNode(grid, pieceRow, piececolumn + 2)).getChildren().size() == 0) {

                right.setColour("red");
                ((Field) getNode(grid, pieceRow, piececolumn + 2)).setColour("green");
                Board.possibleKickMoves.add(((Field) getNode(grid, pieceRow, piececolumn + 2)));

            }
        }
    }




}
