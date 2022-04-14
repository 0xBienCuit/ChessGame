package gameapplication.view.board;


import gameapplication.model.*;
import gameapplication.model.chess.ChessBoard;
import gameapplication.model.chess.ChessPiece;
import gameapplication.statecontrol.*;
import gameapplication.view.menu.MenuView;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


/**
 * A class for a view for a chess board. This class must have a reference
 * to a GameController for chess playing chess
 *
 * @author Nicolas Bouquiaux
 */
public class BoardView extends BorderPane {

    /* You may add more instance data if you need it */
    protected GameController controller;
    private GridPane gridPane;
    private Tile[][] tiles = new Tile[8][8];

    public MenuView getMenuView() {
        return menuView;
    }

    private MenuView menuView;
    private Text sideStatus;
    private Text state;
    private boolean isRotated;
    private boolean click = false;
    private Tile origin;
    public ChessBoard board;
    private Button reset;

    private Button playAI;
    private HBox play;

    public BoardView() {
        initialiseNodes();
        layoutNodes();

    }

    private void initialiseNodes() {
        this.controller = new ChessController();
        this.state = new Text();

        this.sideStatus = new Text();
        reset = new Button("Reset");
        playAI = new Button("Play AI");
        play = new HBox(10);
        tiles = new Tile[8][8];
        gridPane = new GridPane();
        menuView = new MenuView();


    }

    public Button getReset() {
        return reset;
    }

    public Button getPlayAI() {
        return playAI;
    }

    public HBox getPlay() {
        return play;
    }
    public ChessBoard getBoard() {
        return board;
    }
    private void layoutNodes() {
        play.getChildren().addAll(reset, playAI);
        super.setBottom(play);
        super.setTop(getMenuView());
        gridPane.setStyle("-fx-background-color : goldenrod;");


        VBox root = new VBox(10);

        super.setCenter(gridPane);


    }

    /**
     * Listener for clicks on a tile
     *
     * @param tile The tile attached to this listener
     * @return The event handler for all tiles.
     */

    private EventHandler<? super MouseEvent> tileListener(Tile tile) {
        return event -> {
            //not your turn!
            if (controller instanceof NetworkedChessController) {
                controller.getCurrentSide();
            }

            // Don't change the code above this :)
            // call firstClick or secondClick, depending on which it is
            try {
                if (!this.click) {
                    this.click = true;
                    firstClick(tile);
                } else if (this.click) {
                   event = null;
                    this.click = false;
                    secondClick(tile);
                }
            } catch (IllegalMoveException e) {
                System.out.println("Temp");
            }
        };
    }

    /**
     * Perform the first click functions, like displaying
     * which are the valid moves for the piece you clicked.
     *
     * @param tile The TileView that was clicked
     */
    private void firstClick(Tile tile) {
        // TODO
        this.origin = tile;
        Color color = Color.LIGHTGREEN;
        Color capture = Color.DARKSALMON;
        Set<Move> moves = controller.getMovesForPieceAt(tile.getPosition());
        if (moves.size() > 0) {
            for (Move m : moves) {
                Position pos = m.getDestination();
                Tile x = this.getTileAt(pos);
                x.highlight(capture);
                if (controller.moveResultsInCapture(m)) {
                    x.highlight(capture);
                }
            }
            Color highlight = Color.PALEGOLDENROD;
            tile.highlight(highlight);
        }
    }

    /**
     * Perform the second click functions, like
     * sending moves to the controller but also
     * checking that the user clicked on a valid position.
     * If they click on the same piece they clicked on for the first click
     * then you should reset to click state back to the first click and clear
     * the highlighting effected on the board.
     *
     * @param tile the TileView at which the second click occurred
     */
    private void secondClick(Tile tile) throws IllegalMoveException {
        // TODO
        Set<Move> moves = controller.getMovesForPieceAt(origin.getPosition());
        Position destination = tile.getPosition();
        if (origin.getPosition().equals(destination)) {
            origin.clear();
            for (Move x : moves) {
                Position pos = x.getDestination();
                this.getTileAt(pos).clear();
            }
        } else {
            for (Move m : moves) {
                Position pos = m.getDestination();
                if (destination.equals(pos)) {
                    try {
                        controller.makeMove(m);
                        controller.endTurn();
                        controller.beginTurn();
                    } catch (IllegalMoveException e) {
                        throw new IllegalMoveException(m);
                    }
                }
            }
        }
    }

    /**
     * This method should be called any time a move is made on the back end.
     * It should update the tiles' highlighting and symbols to reflect the
     * change in the board state.
     *
     * @param moveMade          the move to show on the view
     * @param capturedPositions a list of positions where pieces were captured
     */
    public void updateView(Move moveMade, List<Position> capturedPositions) {
        // TODO
        Color previous = Color.NAVAJOWHITE;
        Position start = moveMade.getStart();
        Position end = moveMade.getDestination();

        for (Tile[] tile : tiles) {
            for (Tile t : tile) {
                t.clear();
                Position current = t.getPosition();
                this.getTileAt(start).highlight(previous);
                this.getTileAt(end).highlight(previous);
                t.setSymbol(controller.getSymbolForPieceAt(current));
            }
        }
    }

    /**
     * Asks the user which PieceType they want to promote to
     * (suggest using Alert). Then it returns the Piecetype user selected.
     *
     * @return the PieceType that the user wants to promote their piece to
     */
    private ChessPiece.ChessPieceType handlePromotion() {
        // TODO
        ChessPiece.ChessPieceType type = ChessPiece.ChessPieceType.PAWN;
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Promotion");
        alert.setHeaderText("Your pawn has earned a promotion!");
        alert.setContentText("What would you like to promote to?");
        ButtonType queen = new ButtonType("Queen");
        ButtonType knight = new ButtonType("Knight");
        ButtonType rook = new ButtonType("Rook");
        ButtonType bishop = new ButtonType("Bishop");
        alert.getButtonTypes().setAll(queen, knight, rook, bishop);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == queen) {
            type = ChessPiece.ChessPieceType.QUEEN;
        } else if (result.isPresent() && result.get() == knight) {
            type = ChessPiece.ChessPieceType.KNIGHT;
        } else if (result.isPresent() && result.get() == rook) {
            type = ChessPiece.ChessPieceType.ROOK;
        } else if (result.isPresent() && result.get() == bishop) {
            type = ChessPiece.ChessPieceType.BISHOP;
        }
        return type;
    }

    /**
     * Handles a change in the GameState (ie someone in check or stalemate).
     * If the game is over, it should open an Alert and ask to keep
     * playing or exit.
     *
     * @param s The new Game State
     */
    public void handleGameStateChange(GameState s) {
        // TODO
        if (s.equals(ChessState.BLACK_IN_CHECK)) {
            s = ChessState.WHITE_WINS;
        } else if (s.equals(ChessState.WHITE_IN_CHECK)) {
            s = ChessState.BLACK_WINS;
        }
        this.state.setText(s.toString());
        if (s.isGameOver()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText("Result:  " + s.toString());
            alert.setContentText("Continue playing or Exit?");

            ButtonType cont = new ButtonType("Continue");
            ButtonType exit = new ButtonType("Exit");
            alert.getButtonTypes().setAll(cont, exit);
            alert.showAndWait().ifPresent(response -> {
                if (response == cont) {
                    this.controller = new ChessController();
                    this.reset(this.controller);
                } else {
                    Alert alertToSave = new Alert(AlertType.INFORMATION);
                    alertToSave.setTitle("Would you like to save your game?");
                    alertToSave.setHeaderText("Save Game");
                    alertToSave.setContentText("Would you like to save your game?");

                    ButtonType save = new ButtonType("Save");
                    ButtonType noSave = new ButtonType("Don't Save");
                    alert.getButtonTypes().setAll(save, noSave);
                    alertToSave.showAndWait().ifPresent(responseToSave -> {
                        if (responseToSave == save) {
                            //TODO
                        } else {
                            Platform.exit();
                        }
                    });


                }
            });
        }
    }

    /**
     * Updates UI that depends upon which Side's turn it is
     *
     * @param s The new Side whose turn it currently is
     */
    public void handleSideChange(Side s) {
        // TODO
        this.sideStatus.setText(s.toString() + "'s" + " Turn");
    }

    /**
     * Resets this BoardView with a new controller.
     * This moves the chess pieces back to their original configuration
     * and calls startGame() at the end of the method
     *
     * @param newController The new controller for this BoardView
     */
    public void reset(GameController newController) {
        if (controller instanceof NetworkedChessController) {
            ((NetworkedChessController) controller).close();
        }
        controller = newController;
        isRotated = false;
        if (controller instanceof NetworkedChessController) {
            Side mySide = ((NetworkedChessController) controller).getLocalSide();
            if (mySide == Side.BLACK) {
                isRotated = true;
            }
        }
        sideStatus.setText(controller.getCurrentSide() + "'s Turn");

        // controller event handlers
        // We must force all of these to run on the UI thread
        controller.addMoveListener(
                (Move move, List<Position> capturePositions) ->
                        Platform.runLater(
                                () -> updateView(move, capturePositions)));

        controller.addCurrentSideListener(
                (Side side) -> Platform.runLater(
                        () -> handleSideChange(side)));

        controller.addGameStateChangeListener(
                (GameState state) -> Platform.runLater(
                        () -> handleGameStateChange(state)));

        controller.setPromotionListener(() -> handlePromotion());


        addPieces();
        controller.startGame();
        if (isRotated) {
            setBoardRotation(180);
        } else {
            setBoardRotation(0);
        }
    }

    /**
     * Initializes the gridPane object with the pieces from the GameController.
     * This method should only be called once before starting the game.
     */
    private void addPieces() {
        gridPane.getChildren().clear();
        Map<Piece, Position> pieces = controller.getAllActivePiecesPositions();
        /* Add the tiles */
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Tile tile = new TileView(new Position(row, col));
                gridPane.add(tile.getRootNode(),
                        1 + tile.getPosition().getCol(),
                        1 + tile.getPosition().getRow());
                GridPane.setHgrow(tile.getRootNode(), Priority.ALWAYS);
                GridPane.setVgrow(tile.getRootNode(), Priority.ALWAYS);
                getTiles()[row][col] = tile;
                tile.getRootNode().setOnMouseClicked(
                        tileListener(tile));
                tile.clear();
                tile.setSymbol("");
            }
        }
        /* Add the pieces */
        for (Piece p : pieces.keySet()) {
            Position placeAt = pieces.get(p);
            getTileAt(placeAt).setSymbol(p.getType().getSymbol(p.getSide()));
        }
        /* Add the coordinates around the perimeter */
        for (int i = 1; i <= 8; i++) {
            Text coord1 = new Text((char) (64 + i) + "");
            GridPane.setHalignment(coord1, HPos.CENTER);
            gridPane.add(coord1, i, 0);

            Text coord2 = new Text((char) (64 + i) + "");
            GridPane.setHalignment(coord2, HPos.CENTER);
            gridPane.add(coord2, i, 9);

            Text coord3 = new Text(9 - i + "");
            GridPane.setHalignment(coord3, HPos.CENTER);
            gridPane.add(coord3, 0, i);

            Text coord4 = new Text(9 - i + "");
            GridPane.setHalignment(coord4, HPos.CENTER);
            gridPane.add(coord4, 9, i);
        }
    }

    private void setBoardRotation(int degrees) {
        gridPane.setRotate(degrees);
        for (Node n : gridPane.getChildren()) {
            n.setRotate(degrees);
        }
    }

    /**
     * Gets the view to add to the scene graph
     *
     * @return A pane that is the node for the chess board
     */
    public Pane getView() {
        return gridPane;
    }

    /**
     * Gets the tiles that belong to this board view
     *
     * @return A 2d array of TileView objects
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getTileAt(int row, int col) {
        return getTiles()[row][col];
    }

    private Tile getTileAt(Position p) {
        return getTileAt(p.getRow(), p.getCol());
    }

}
