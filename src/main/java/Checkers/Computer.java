package Checkers;


import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Computer {
    Random random = new Random();
    ArrayList<Piece> canMove = new ArrayList<>();
    List<Piece> canKick;
    Field selectedField;


    public void computerLogic(GridPane gridPane) {

        Board.alreadyClickedPiece = new RedPiece();
        canKick = Utils.checkWhichPieceCanKick(gridPane);


        if (canKick.size() > 0) {
            Board.alreadyClickedPiece = canKick.get(random.nextInt(canKick.size()));
            Board.alreadyClickedPiece.paintKickPossible();

            Task<Void> pause = new Task<Void>() {
                @Override
                protected Void call() throws Exception {

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                    }
                    return null;
                }
            };
            pause.setOnSucceeded(event -> {
                if (Board.possibleKickMoves.size() > 0) {
                    selectedField = Board.possibleKickMoves.get(random.nextInt(Board.possibleKickMoves.size()));
                    selectedField.mouseClicked();
                }


            });
            new Thread(pause).start();


        } else {

            checkWhichPieceCanMove(gridPane);

            if (canMove.size() > 0) {
                Board.alreadyClickedPiece = canMove.get(random.nextInt(canMove.size()));
                Board.alreadyClickedPiece.paintMovePossible();


                Task<Void> pause = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {

                        }
                        return null;
                    }
                };
                pause.setOnSucceeded(event -> {
                    if (Board.possibleMoves.size() > 0) {
                        assignSelectedField();
                        selectedField.mouseClicked();
                    }


                });
                new Thread(pause).start();

            }


        }
        selectedField = null;
        canMove.clear();
        canKick.clear();
    }


    public void checkWhichPieceCanMove(GridPane gridPane) {
        {

            for (Node node : gridPane.getChildren()) {
                if (((Field) node).getChildren().size() > 0 && ((Field) node).getChildren().get(0) instanceof RedPiece) {

                    if (((RedPiece) ((Field) node).getChildren().get(0)).movePossible()) {
                        canMove.add((Piece) ((Field) node).getChildren().get(0));
                    }
                }
            }
        }

    }

    private void assignSelectedField() {
        selectedField = Board.possibleMoves.get(random.nextInt(Board.possibleMoves.size()));
    }


}
