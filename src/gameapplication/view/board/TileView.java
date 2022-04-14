package gameapplication.view.board;

import gameapplication.model.Position;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


/**
 * View class for a tile on a chess board
 * A tile should be able to display a chess piece
 * as well as highlight itself during the game.
 *
 * @author PJ Conde
 */
public class TileView implements Tile {

    private final Position pos;
    private final StackPane node = new StackPane();
    private String symbol;
    private final Color defaultColor;
    private Color activeTile = null;

    /**
     * Creates a TileView with a specified position
     *
     * @param p
     */
    public TileView(Position p) {
        // TODO
        this.pos = p;
        int positionSum = p.getRow() + p.getCol();
        if (positionSum % 2 == 0) {
            defaultColor = Color.WHITE;
        } else {
        defaultColor = Color.BLACK;
        }
        Background bg = new Background(new BackgroundFill(defaultColor,
                new CornerRadii(0), new Insets(0)));
        node.setBackground(bg);
    }

    public void onTileClick(Position click) {

    }

    @Override
    public Position getPosition() {
        // TODO
        return pos;
    }


    @Override
    public Node getRootNode() {
        // TODO
        node.getChildren().clear();
        Color trans = Color.TRANSPARENT;
        Label label = new Label(symbol);
        label.setFont(new Font(50));
        node.getChildren().addAll(new Rectangle(50, 50, trans));
        return node;
    }

    @Override
    public String getSymbol() {
        // TODO
        return symbol;
    }

    @Override
    public void setSymbol(String symbol) {
        // TODO
        node.getChildren().clear();
        this.symbol = symbol;
        Color trans = Color.TRANSPARENT;
        Label label = new Label(symbol);
        label.setFont(new Font(50));
        node.getChildren().addAll(new Rectangle(50, 50, trans), label);
    }

    @Override
    public void highlight(Color s) {
        // TODO

        Background bg = new Background(new BackgroundFill(s,
                new CornerRadii(0), new Insets(0)));
        node.setBackground(bg);
    }

    @Override
    public void unhighlight() {
        // TODO
        node.getChildren().clear();
        Background bg = new Background(new BackgroundFill(defaultColor,
                new CornerRadii(0), new Insets(0)));
        node.setBackground(bg);
    }

    @Override
    public void setHighlighted(Color b) {
        // TODO
        Background bg = new Background(new BackgroundFill(b,
                new CornerRadii(0), new Insets(0)));
        node.setBackground(bg);
    }


    @Override
    public void clear() {
        // TODO
        Background bg = new Background(new BackgroundFill(defaultColor,
                new CornerRadii(0), new Insets(0)));
        node.setBackground(bg);
    }


}
