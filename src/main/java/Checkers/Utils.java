package Checkers;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;


public class Utils {


    public static Node getNode(GridPane gridPane, int row, int column) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return node;
            }
        }

        return null;
    }

    public static void setBoardDefaultColours(GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            ((Field) node).paintDefaultColour();

        }
    }

    public static boolean canKick(Piece piece) {
        GridPane grid = (GridPane) piece.getParent().getParent();
        int pieceRow = GridPane.getRowIndex(piece.getParent());
        int piececolumn = GridPane.getColumnIndex(piece.getParent());

        Field up = (Field) getNode(grid, pieceRow + 1, piececolumn);
        Field down = (Field) getNode(grid, pieceRow - 1, piececolumn);
        Field left = (Field) getNode(grid, pieceRow, piececolumn - 1);
        Field right = (Field) getNode(grid, pieceRow, piececolumn + 1);

        if (up != null && up.getChildren().size() > 0) {
            if (getNode(grid, pieceRow + 2, piececolumn) != null && ((Field) getNode(grid, pieceRow + 2, piececolumn)).getChildren().size() == 0) {
                return true;
            }
        }else if (down != null && down.getChildren().size() > 0) {
            if (getNode(grid, pieceRow - 2, piececolumn) != null &&((Field) getNode(grid, pieceRow - 2, piececolumn)).getChildren().size() == 0) {
                return true;
            }
        }else if (left != null && left.getChildren().size() > 0) {
            if (getNode(grid, pieceRow, piececolumn - 2) != null && ((Field) getNode(grid, pieceRow, piececolumn - 2)).getChildren().size() == 0) {
                return true;
            }
        }else if (right != null && right.getChildren().size() > 0) {
            if (getNode(grid, pieceRow, piececolumn + 2) != null && ((Field) getNode(grid, pieceRow, piececolumn + 2)).getChildren().size() == 0) {
                return true;
            }
        }

        return false;
    }

    public static List<Piece> checkWhichPieceCanKick(GridPane gridPane) {
        List<Piece> list = new ArrayList<>();
        for (Node node : gridPane.getChildren()) {
            if(Board.whiteTurn) {
                if (((Field) node).getChildren().size() > 0 && ((Field) node).getChildren().get(0) instanceof WhitePiece) {
                    if(canKick((Piece)((Field) node).getChildren().get(0))){
                        list.add((Piece)((Field) node).getChildren().get(0));
                    }

                }
            }

            if(!Board.whiteTurn) {
                if (((Field) node).getChildren().size() > 0 && ((Field) node).getChildren().get(0) instanceof BlackPiece) {
                    if(canKick((Piece)((Field) node).getChildren().get(0))){
                        list.add((Piece)((Field) node).getChildren().get(0));
                    }

                }
            }
        }

        return list;
    }

}
