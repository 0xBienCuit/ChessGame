package gameapplication.view.board;

import gameapplication.model.Position;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Tile interface
 *
 * @author Gustavo
 * @date Nov 2, 2015
 */
public interface Tile {

    /**
     * Get the Position object this Tile logically exists at
     *
     * @return This TileView's Position
     */
    Position getPosition();

    /**
     * Get the Node that represents this Tile
     *
     * @return The Node object
     */
    Node getRootNode();

    /**
     * Get the symbol currently displayed at this Tile
     *
     * @return
     */
    String getSymbol();

    /**
     * Set the symbol to be displayed on this Tile, should
     * be a Unicode Chess symbol
     *
     * @param symbol
     */
    void setSymbol(String symbol);

    /**
     * Highlight this tile with a particular color
     *
     * @param s
     */
    void highlight(Color s);

    void unhighlight();

    void setHighlighted(Color b);

    /**
     * Return this tile to its normal appearance.
     */
    void clear();
}
