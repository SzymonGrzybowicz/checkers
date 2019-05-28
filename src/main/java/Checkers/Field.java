package Checkers;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;


public class Field extends StackPane {

    private Piece piece;
    String defaultColour;
    private boolean nextKickPossible;

    public Field(String defaultColour) {
        this.setMaxSize(100, 100);
        this.setMinSize(100, 100);
        this.defaultColour = defaultColour;
        this.setStyle("-fx-background-color: " + defaultColour + ";");
    }

    public void setColour(String colour) {
        this.setStyle("-fx-background-color: " + colour + ";");
    }

    public void paintDefaultColour() {
        this.setStyle("-fx-background-color: " + defaultColour + ";");
    }

    public void addWhitePiece() {
        piece = new WhitePiece();
        this.getChildren().addAll(piece);
        Board.whitePieces.add(piece);
    }

    public void addBlackPiece() {
        piece = new BlackPiece();
        this.getChildren().addAll(piece);
        Board.blackPieces.add(piece);
    }

    public void removePiece() {
        this.getChildren().removeAll(piece);
    }

    public void addPiece(Piece piece) {
        this.getChildren().addAll(piece);
        this.piece = piece;
    }

    public void mouseClicked() {


        if (Board.possibleKickMoves.contains(this)) {

            kickPiece();

            if (!(nextKickPossible)) {
                Board.whiteTurn = !(Board.whiteTurn);
            }

        } else if (Board.possibleMoves.contains(this)) {

            movePiece();

            Board.whiteTurn = !(Board.whiteTurn);
        }

        Utils.setBoardDefaultColours((GridPane) this.getParent());


    }

    private void movePiece() {
        ((Field) Board.alreadyClickedPiece.getParent()).removePiece();
        this.addPiece(Board.alreadyClickedPiece);

        checkAndSetQueenLogic();

        Board.alreadyClickedPiece = null;
        Board.possibleMoves.clear();
    }


    private void kickPiece() {
        int row = GridPane.getRowIndex(this);
        int column = GridPane.getColumnIndex(this);
        int rowOfAlreadyClickedPiece = GridPane.getRowIndex(Board.alreadyClickedPiece.getParent());
        int columnOfAlreadyClickedPiece = GridPane.getColumnIndex(Board.alreadyClickedPiece.getParent());
        int rowOfKickedPiece = (row + rowOfAlreadyClickedPiece) / 2;
        int columnOfKickedPiece = (column + columnOfAlreadyClickedPiece) / 2;
        Piece kickedPiece = ((Field) Utils.getNode(((GridPane) this.getParent()), rowOfKickedPiece, columnOfKickedPiece)).piece;

        ((Field) Board.alreadyClickedPiece.getParent()).removePiece();
        this.addPiece(Board.alreadyClickedPiece);


        ((Field) Utils.getNode(((GridPane) this.getParent()), rowOfKickedPiece, columnOfKickedPiece)).removePiece();

        Board.whitePieces.remove(kickedPiece);
        Board.blackPieces.remove(kickedPiece);

        checkAndSetQueenLogic();

        if (Utils.checkWhichPieceCanKick((GridPane) this.getParent()).contains(Board.alreadyClickedPiece)) {
            Board.alreadyClickedPiece.paintKickPossible();
            nextKickPossible = true;

        }

        Board.alreadyClickedPiece = null;
        Board.possibleMoves.clear();
        Board.possibleKickMoves.clear();
    }

    private void checkAndSetQueenLogic() {
        if (Board.alreadyClickedPiece instanceof WhitePiece && GridPane.getRowIndex(this) == 0) {
            Board.alreadyClickedPiece.setQueen();
        }

        if (Board.alreadyClickedPiece instanceof BlackPiece && GridPane.getRowIndex(this) == 7) {
            Board.alreadyClickedPiece.setQueen();
        }
    }
}
