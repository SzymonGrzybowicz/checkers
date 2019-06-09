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
        int pieceColumn = GridPane.getColumnIndex(piece.getParent());

        Field leftUp = (Field) getNode(grid, pieceRow - 1, pieceColumn - 1);
        Field leftDown = (Field) getNode(grid, pieceRow + 1, pieceColumn - 1);
        Field rightUp = (Field) getNode(grid, pieceRow - 1, pieceColumn + 1);
        Field rightDown = (Field) getNode(grid, pieceRow + 1, pieceColumn + 1);

        if (leftUp != null && leftUp.getChildren().size() > 0 && leftUp.getChildren().get(0).getClass() != piece.getClass() && leftUp.getChildren().get(0) instanceof Piece) {
            if (getNode(grid, pieceRow - 2, pieceColumn - 2) != null && ((Field) getNode(grid, pieceRow - 2, pieceColumn - 2)).getChildren().size() == 0) {
                System.out.println("1");
                return true;
            }
        }
        if (leftDown != null && leftDown.getChildren().size() > 0 && leftDown.getChildren().get(0).getClass() != piece.getClass() && leftDown.getChildren().get(0) instanceof Piece) {
            if (getNode(grid, pieceRow + 2, pieceColumn - 2) != null && ((Field) getNode(grid, pieceRow + 2, pieceColumn - 2)).getChildren().size() == 0) {
                System.out.println("2");
                return true;
            }
        }
        if (rightUp != null && rightUp.getChildren().size() > 0 && rightUp.getChildren().get(0).getClass() != piece.getClass() && rightUp.getChildren().get(0) instanceof Piece) {
            if (getNode(grid, pieceRow - 2, pieceColumn + 2) != null && ((Field) getNode(grid, pieceRow - 2, pieceColumn + 2)).getChildren().size() == 0) {
                System.out.println("3");
                return true;
            }
        }
        if (rightDown != null && rightDown.getChildren().size() > 0 && rightDown.getChildren().get(0).getClass() != piece.getClass() && rightDown.getChildren().get(0) instanceof Piece) {
            if (getNode(grid, pieceRow + 2, pieceColumn + 2) != null && ((Field) getNode(grid, pieceRow + 2, pieceColumn + 2)).getChildren().size() == 0) {
                System.out.println("4");
                return true;


            }
        }


        return false;
    }

    public static List<Piece> checkWhichPieceCanKick(GridPane gridPane) {
        List<Piece> list = new ArrayList<>();
        for (Node node : gridPane.getChildren()) {
            if (Board.whiteTurn) {
                if (((Field) node).getChildren().size() > 0 && ((Field) node).getChildren().get(0) instanceof WhitePiece) {
                    if (canKick((Piece) ((Field) node).getChildren().get(0))) {
                        list.add((Piece) ((Field) node).getChildren().get(0));
                    }

                }
            }

            if (!Board.whiteTurn) {
                if (((Field) node).getChildren().size() > 0 && ((Field) node).getChildren().get(0) instanceof RedPiece) {
                    if (canKick((Piece) ((Field) node).getChildren().get(0))) {
                        list.add((Piece) ((Field) node).getChildren().get(0));
                    }

                }
            }
        }

        return list;
    }


    public static void writeScoreAndTurn() {
        if (Board.whiteTurn) {
            CheckersRunner.wynik.setText("Red: " + (12 - Board.whitePieces.size()) + "    White: " + (12 - Board.redPieces.size()) + "\nWhite Turn!");
        }
        if (!(Board.whiteTurn)) {
            CheckersRunner.wynik.setText("Red: " + (12 - Board.whitePieces.size()) + "    White: " + (12 - Board.redPieces.size()) + "\nRed Turn!");
        }

        checkAndEndGame();


    }

    public static void checkCanMove(GridPane gridPane) {
        if (Board.whiteTurn) {
            Board.whiteCantMove = true;
            for (Node field : gridPane.getChildren()) {
                for (Node piece : ((Field) field).getChildren()) {
                    if (piece instanceof WhitePiece && ((Piece) piece).canMoveOrKick()) {
                        Board.whiteCantMove = false;
                    }
                }
            }
        }

        if (!(Board.whiteTurn)) {
            Board.redCantMove = true;
            for (Node field : gridPane.getChildren()) {
                for (Node piece : ((Field) field).getChildren()) {
                    if (piece instanceof RedPiece && ((Piece) piece).canMoveOrKick()) {
                        Board.redCantMove = false;
                    }
                }
            }
        }
    }


    private static void checkAndEndGame() {
        if (Board.whitePieces.size() == 0) {
            CheckersRunner.wynik.setText("Winner is Red!");
        }
        if (Board.redPieces.size() == 0) {
            CheckersRunner.wynik.setText("Winner is White!");
        }
    }

}
