package Checkers;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class CheckersRunner extends Application {

    Board board = new Board();


    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane grid = board.createBoard();


        Scene scene = new Scene(grid, 800, 800, Color.BLACK);

        grid.setOnMouseClicked(e -> board.readMouseEvent(e));


        primaryStage.setResizable(false);

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


}
