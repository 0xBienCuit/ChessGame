package gameapplication.view.board;

import gameapplication.model.chess.ChessBoard;
import gameapplication.statecontrol.AIChessController;
import gameapplication.statecontrol.ChessController;
import gameapplication.statecontrol.GameController;
import gameapplication.view.filechooser.FileChooserView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Nicolas Bouquiaux
 */
public class BoardPresenter {
    private final BoardView view;
    private GameController model;
    private ChessBoard board;

    public BoardPresenter(GameController model, BoardView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void updateView() {
    }


    private void addEventHandlers() {

        board = view.getBoard();


        view.getReset().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                model = new ChessController();
                view.reset(model);
            }
        });
        view.getPlayAI().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                model = new AIChessController();
                view.reset(model);
            }
        });
        view.getMenuView().getMiSave().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser chooser = new FileChooser();
                FileChooserView fc = new FileChooserView();
                chooser.setTitle("Open Resource File");
                chooser.getExtensionFilters().addAll(fc.getFileChooser().getExtensionFilters());
                File selectedFile = fc.getFileChooser().showOpenDialog(view.getScene().getWindow());
                if ((selectedFile != null) && (Files.isReadable(Paths.get(selectedFile.toURI())))) {
                    try {
                        model = new ChessController();
                        fc.openFile(view, FileChooserView.Category.BOARDS, FileChooserView.Format.FEN);
                        model = new ChessController();
                        model.getNewInstance();
                        view.reset(model);
                    } catch (NullPointerException e) {
                        Alert errorWindow = new Alert(Alert.AlertType.ERROR);
                        errorWindow.setHeaderText("Problem with selected file");
                        errorWindow.setContentText("File is not readable: " + e.getMessage());
                        errorWindow.showAndWait();
                        e.printStackTrace();
                    }
                } else {
                    Alert errorWindow = new Alert(Alert.AlertType.ERROR);
                    errorWindow.setHeaderText("Problem with selected file");
                    errorWindow.setContentText("File is not readable");
                    errorWindow.showAndWait();
                }

            }
        });
//        view.getMenuView().getMiSave().setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                FileChooserView fileChooserView = new FileChooserView();
//                fileChooserView.getFileChooser().showSaveDialog(view.getScene().getWindow());
//                File selectedFile = Paths.get(fileChooserView.getFile().toURI()).toFile();
//
//
//
//                try (DataOutputStream dos = new DataOutputStream(new ObjectOutputStream(new FileOutputStream(selectedFile)))) {
//
//
//                    dos.write(model.getCurrentSide());
//            } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }
//
//
//
//    });
    }
}
