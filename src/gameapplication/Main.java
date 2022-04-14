package gameapplication;


import gameapplication.statecontrol.GameController;
import gameapplication.view.board.BoardPresenter;
import gameapplication.view.board.BoardView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Main class that stages our mainView & model to scenes
 *
 * @author Nicolas Bouquiaux
 * @version 1.0
 * @author nicolas Bouquiaux
 * @author nicolas Bouquiaux
 * @author nicolas Bouquiaux
 * @author nicolas Bouquiaux
 */

/**
 * @author nicolas Bouquiaux
 */

/**
 * The main class is the entry point for the program. It creates a new chess game and a new scene. It then creates a new
 * start view and a new start presenter. It then sets the scene to the start view
 */
public class Main extends Application {

    private GameController controller;
    private BoardView board;
    private Text state;
    private Text sideStatus;
    private VBox root;
//    private final Logic logic = new Logic();
    private final int size = 8;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane border = new BorderPane();
        HBox control = new HBox();
        control.setPrefHeight(40);
        control.setSpacing(10.0);
        control.setAlignment(Pos.BASELINE_CENTER);
        Button start = new Button("start");
        //start.setOnMouseClicked(event -> refresh(border));



        BoardView boardview = new BoardView();
        BoardPresenter presenter = new BoardPresenter(controller, boardview);

        Scene scene = new Scene(boardview, 800, 800);

        scene.getStylesheets().add("resources/stylesheet.css");
        primaryStage.setScene(scene);


        primaryStage.setTitle("Ultimate Chess");
        primaryStage.show();


    }
}
