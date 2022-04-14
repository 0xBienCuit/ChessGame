package gameapplication.model.chess;


import gameapplication.model.Move;
import gameapplication.model.Position;

/**
 * Created by joe on 10/22/15.
 */
public class CastlingMove extends Move {
    private final CastlingType castlingType;

    public CastlingMove(Position start, Position destination, CastlingType t) {
        super(start, destination);
        this.castlingType = t;
    }

    public CastlingType getCastlingType() {
        return castlingType;
    }

    public enum CastlingType {
        QUEEN_SIDE(0, 3), KING_SIDE(7, 5);

        private final int startCol;
        private final int destCol;

        CastlingType(int startCol, int destCol) {
            this.startCol = startCol;
            this.destCol = destCol;
        }

        public int getStartCol() {
            return startCol;
        }

        public int getDestCol() {
            return destCol;
        }
    }


}
