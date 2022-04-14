package gameapplication.model.chess;

import gameapplication.model.Move;
import gameapplication.model.Position;
import gameapplication.model.Side;

import java.util.HashSet;
import java.util.Set;


public class Bishop extends ChessPiece {

    public Bishop(Side side) {
        super(ChessPieceType.BISHOP, side);
    }

    @Override
    public Set<Move> generateMoves(Position curPos) {
        Set<Move> moves = new HashSet<Move>();
        int curCol = curPos.getCol();
        int curRow = curPos.getRow();

        for (int dx = -1; dx <= 1; dx += 2) {
            for (int dy = -1; dy <= 1; dy += 2) {
                int destCol = curCol + dx;
                int destRow = curRow + dy;
                while (ChessUtils.posBoundsTest(destRow, destCol)) {
                    Position destPos = new Position(destRow, destCol);
                    moves.add(new Move(curPos, destPos));

                    destCol = destCol + dx;
                    destRow = destRow + dy;
                }
            }
        }
        return moves;
    }
}
