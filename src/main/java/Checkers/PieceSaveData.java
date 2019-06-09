package Checkers;

import java.io.Serializable;

public class PieceSaveData implements Serializable {
    private int row;
    private int column;
    private boolean isWhite;
    private boolean isQueen;
    private boolean whiteTurn;

    public PieceSaveData(int row, int column, boolean isWhite, boolean isQueen, boolean whiteTurn) {
        this.row = row;
        this.column = column;
        this.isWhite = isWhite;
        this.isQueen = isQueen;
        this.whiteTurn = whiteTurn;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isQueen() {
        return isQueen;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }
}
