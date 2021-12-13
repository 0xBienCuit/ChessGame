package GameConsole.Chess.piece.pieces;

import GameConsole.Chess.piece.Piece;
import GameConsole.Chess.piece.PieceColor;
import GameConsole.Chess.spot.Spot;

public class Knight extends Piece {

    public Knight(PieceColor pieceColor, Spot pieceLocation) {
        super(pieceColor, pieceLocation);
    }

    public Piecetype getPieceType(){
        return Piecetype.KNIGHT;
    }

    public Spot validMoves(){
        //todo

        //change later to initialize the spots to the number of valid moves
        Spot[][] spot = new Spot[1][1];
        return spot[3][4];
    }
}
