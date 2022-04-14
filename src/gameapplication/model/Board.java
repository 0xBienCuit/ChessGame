package gameapplication.model;

import gameapplication.model.chess.SideHasNoMovesException;

import java.util.Map;
import java.util.Set;

/**
 * Interface for a board game board.
 *
 * @author Joe
 * @version 1.0
 */
public interface Board {

    /**
     * Moves Piece p using Move m. This method assumes the move is legal.
     *
     * @param p
     * @param m
     */
    void movePiece(Piece p, Move m);

    /**
     * Answers if a move is valid/legal or not given the state of the board
     *
     * @param m
     * @param movingSide
     * @return
     */
    boolean pieceCanMove(Move m, Side movingSide);


    Map<Piece, Set<Move>> generateAllMovesForSide(Side s)
            throws SideHasNoMovesException;

    /**
     * Returns the positions of all active on the board pieces
     *
     * @return
     */
    Map<Piece, Position> getAllActivePiecesPositions();

    /**
     * Get the piece at a particular position
     *
     * @param p
     * @return
     */
    Piece getPieceAt(Position p);

    /**
     * Changes the piece at some position to a different piece. This is useful
     * for promotion type moves like Pawn Promotion in chess, or 'king-ing' in
     * checkers
     *
     * @param pos
     * @param newPiece
     */
    void replacePieceAt(Position pos, Piece newPiece);
}
