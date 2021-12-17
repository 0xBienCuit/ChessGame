package GameConsole.Chess.piece;

import GameConsole.Chess.Board.Board;
import GameConsole.Chess.Cells.Cell;
import GameConsole.Chess.Coordinates.LocationPoint;

import java.util.List;

public class Rook extends absPiece implements Movable{

    public Rook(PieceColor pieceColor){
        super(pieceColor);
        this.pieceName = "Rook";
    }
    @Override
    public List<LocationPoint> getValidMoves(Board board) {
        return null;
    }

    @Override
    public List<LocationPoint> getValidMoves(Board board, Cell cell) {
        return null;
    }

    @Override
    public void doMove(Cell cell) {
        cell.setOccupied(true);
        cell.setCurrentPiece(this);

    }
}
