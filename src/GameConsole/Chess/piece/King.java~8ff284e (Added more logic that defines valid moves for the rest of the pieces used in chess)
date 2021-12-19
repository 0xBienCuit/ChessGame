package GameConsole.Chess.piece;

import GameConsole.Chess.Board.Board;
import GameConsole.Chess.Cells.Cell;
import GameConsole.Chess.Coordinates.LocationPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class King extends absPiece implements Movable {
    private Movable bishop;
    private Movable rook;

    public King(PieceColor pieceColor) {
        super(pieceColor);
        this.pieceName = "King";

    }

    //compose queen's movability by getting movability bishop & rook
    public King(PieceColor piececolor, Movable bishop, Movable rook) {
        super(piececolor);
        this.rook = rook;
        this.bishop = bishop;

    }

    @Override
    public List<LocationPoint> getValidMoves(Board board) {
        List<LocationPoint> moveCandidates = new ArrayList<>(Collections.emptyList()); //magic
        moveCandidates.addAll(bishop.getValidMoves(board, this.getCurrentCell()));
        moveCandidates.addAll(rook.getValidMoves(board, this.getCurrentCell()));
        LocationPoint current = this.getCurrentCell().getLocationPoint();
        return moveCandidates.stream()
                .filter(candidate ->
                        Math.abs(candidate.getFile().ordinal() - current.getFile().ordinal()) == 1 &&
                                Math.abs(candidate.getPoint() - current.getPoint()) == 1)
                .collect(Collectors.toList());
        //filter all candidates that are more than 1 cell away
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
