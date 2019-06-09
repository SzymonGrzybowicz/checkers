package Checkers;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;


public class Field extends StackPane {

    private Piece piece;
    String defaultColour;
    private boolean nextKickPossible;
    Computer computer = new Computer();

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

    public void addRedPiece() {
        piece = new RedPiece();
        this.getChildren().addAll(piece);
        Board.redPieces.add(piece);
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

            nextKickPossible = false;
            kickPiece();
            System.out.println("zbito");
            System.out.println(nextKickPossible);

            if (!(nextKickPossible)) {
                blockedMoveLogic();
            }

        } else if (Board.possibleMoves.contains(this)) {

            movePiece();

            blockedMoveLogic();

        }

        Utils.setBoardDefaultColours((GridPane) this.getParent());


        if (!(Board.whiteTurn)) {
            computer.computerLogic((GridPane) this.getParent());
        }

    }


    private void blockedMoveLogic() {

        Board.whiteTurn = !(Board.whiteTurn);
        Utils.checkCanMove((GridPane) this.getParent());
        Utils.writeScoreAndTurn();
        if (!(Board.whitePieces.isEmpty()) && !(Board.redPieces.isEmpty())) {
            if (Board.whiteTurn && Board.whiteCantMove) {
                CheckersRunner.wynik.setText("White player Blocked! \nWinner is Red!");
            } else if (!(Board.whiteTurn) && Board.redCantMove) {
                CheckersRunner.wynik.setText("Red player Blocked! \nWinner is White!");
            }
        }

        Board.alreadyClickedPiece = null;

    }


    private void movePiece() {
        ((Field) Board.alreadyClickedPiece.getParent()).removePiece();
        this.addPiece(Board.alreadyClickedPiece);

        checkAndSetQueenLogic();

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
        Board.redPieces.remove(kickedPiece);

        checkAndSetQueenLogic();
        System.out.println("zaczynamy sprawdzac");
        if (Utils.canKick(Board.alreadyClickedPiece)) {
            nextKickPossible = true;
        }

        Board.possibleMoves.clear();
        Board.possibleKickMoves.clear();


    }

    private void checkAndSetQueenLogic() {
        if (Board.alreadyClickedPiece instanceof WhitePiece && GridPane.getRowIndex(this) == 0) {
            Board.alreadyClickedPiece.setQueen();
        }

        if (Board.alreadyClickedPiece instanceof RedPiece && GridPane.getRowIndex(this) == 7) {
            Board.alreadyClickedPiece.setQueen();
        }
    }
}
