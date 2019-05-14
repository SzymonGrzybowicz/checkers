package Checkers;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class CheckersRunner extends Application {

    private Image white = new Image("white_piece.png", 100, 100, true, true);
    private Image black = new Image("black_piece.png");
    private Pane pieces = new FlowPane(Orientation.HORIZONTAL);



    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        Board board = new Board();
        GridPane grid = board.createBoard();


        Scene scene = new Scene(grid, 850, 850, Color.BLACK);

        primaryStage.setResizable(false);

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();

    }



}
