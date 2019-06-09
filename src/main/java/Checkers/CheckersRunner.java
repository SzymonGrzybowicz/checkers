package Checkers;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;


public class CheckersRunner extends Application {

    Board board = new Board();
    GridPane grid = board.createBoardWithPiece();
    static TextArea wynik = new TextArea();
    FlowPane flowPane = new FlowPane();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        HBox hBox = new HBox();
        hBox.setPadding(new Insets(0, 0, 0, 0));


        Button save = new Button();
        save.setText("Save Game!");
        save.setMinSize(150, 50);
        save.setOnAction(event -> saveGame());

        Button load = new Button();
        load.setText("Load Game!");
        load.setMinSize(150, 50);
        load.setOnAction(event -> loadGame());


        Button newbtn = new Button();
        newbtn.setPrefSize(200, 50);
        newbtn.setText("New Game!");
        newbtn.setOnAction(event -> newGame());


        wynik.setPrefSize(300, 50);
        wynik.setText("Red: " + (0) + "            White: " + (0) + "\nWhite Turn!");
        wynik.setEditable(false);


        hBox.getChildren().addAll(newbtn, load, save, wynik);
        flowPane.getChildren().addAll(hBox);


        flowPane.getChildren().addAll(grid);


        Scene scene = new Scene(flowPane, 800, 850, Color.BLACK);


        primaryStage.setResizable(false);

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void loadGame() {
        try {
            ObjectInputStream stream = stream = new ObjectInputStream(new FileInputStream("save.txt"));
            ArrayList<PieceSaveData> loadData = (ArrayList<PieceSaveData>) stream.readObject();

            flowPane.getChildren().removeAll(grid);
            grid = board.createCleanBoard();
            flowPane.getChildren().addAll(grid);

            for (PieceSaveData data : loadData) {
                if (data.isWhite()) {
                    Piece piece = new WhitePiece();
                    if (data.isQueen()) {
                        piece.setQueen();
                    }
                    ((Field) Utils.getNode(grid, data.getRow(), data.getColumn())).addPiece(piece);
                    Board.whitePieces.add(piece);
                }

                if (!(data.isWhite())) {
                    Piece piece = new RedPiece();
                    if (data.isQueen()) {
                        piece.setQueen();
                    }
                    ((Field) Utils.getNode(grid, data.getRow(), data.getColumn())).addPiece(piece);
                    Board.redPieces.add(piece);
                }

                Board.whiteTurn = data.isWhiteTurn();
                Utils.writeScoreAndTurn();
            }
        } catch (IOException e) {
            wynik.setText("Save file not found!");
        } catch (ClassNotFoundException e) {
            wynik.setText("Save file crashed");
        }


    }


    private void saveGame() {
        ArrayList<PieceSaveData> saveData = new ArrayList<>();

        for (Node node : grid.getChildren()) {
            if (((Field) node).getChildren().size() > 0) {
                PieceSaveData pieceSaveData = new PieceSaveData(
                        GridPane.getRowIndex(node),
                        GridPane.getColumnIndex(node),
                        ((Field) node).getChildren().get(0) instanceof WhitePiece,
                        ((Piece) ((Field) node).getChildren().get(0)).isQueen(),
                        Board.whiteTurn);

                saveData.add(pieceSaveData);

            }
        }

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("save.txt"));
            out.writeObject(saveData);
            out.close();
            wynik.setText("Game saved");
        } catch (IOException ex) {
            wynik.setText("Cannot create save file!");
        }


    }

    void newGame() {
        flowPane.getChildren().removeAll(grid);
        grid = board.createBoardWithPiece();
        flowPane.getChildren().addAll(grid);
        Utils.writeScoreAndTurn();

    }


}
