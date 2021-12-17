package GameConsole.Chess.piece;

import GameConsole.Chess.Board.Board;
import GameConsole.Chess.Cells.Cell;
import GameConsole.Chess.Coordinates.LocationPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Queen extends absPiece implements Movable {
    private Movable bishop;
    private Movable rook;

    public Queen(PieceColor pieceColor) {
        super(pieceColor);
        this.pieceName = "Queen";

    }

    //compose queen's movability by getting movability bishop & rook
    public Queen(PieceColor pieceColor, Movable bishop, Movable rook) {
        super(pieceColor);
        this.bishop = bishop;
        this.rook = rook;

    }

    @Override
    public List<LocationPoint> getValidMoves(Board board) {

        List<LocationPoint> moveCandidates = new ArrayList<>(Collections.emptyList()); //magic
        moveCandidates.addAll(bishop.getValidMoves(board, this.getCurrentCell()));
        moveCandidates.addAll(rook.getValidMoves(board, this.getCurrentCell()));
        return moveCandidates;

    }

    @Override
    public List<LocationPoint> getValidMoves(Board board, Cell cell) {
        return null;
    }

    @Override
    public void doMove(Cell cell) {
        Cell currentCell = this.getCurrentCell();
        this.setCurrentCell(cell);
        currentCell.init();

    }
}
