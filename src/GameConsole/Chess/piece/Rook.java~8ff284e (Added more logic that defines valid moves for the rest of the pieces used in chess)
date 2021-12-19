package GameConsole.Chess.piece;

import GameConsole.Chess.Board.Board;
import GameConsole.Chess.Cells.Cell;
import GameConsole.Chess.Cells.LocationPointGenerator;
import GameConsole.Chess.Coordinates.LocationPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rook extends absPiece implements Movable {

    public Rook(PieceColor pieceColor) {
        super(pieceColor);
        this.pieceName = "Rook";
    }

    @Override
    public List<LocationPoint> getValidMoves(Board board) {
        List<LocationPoint> moveCandidates = new ArrayList<>();
        Map<LocationPoint, Cell> cellMap = board.getLocationPointCellMap();
        LocationPoint current = this.getCurrentCell().getLocationPoint();
        getFileCandidates(moveCandidates, cellMap, current, -1); // rook can only move up and down or latterly
        getFileCandidates(moveCandidates, cellMap, current, 1); //
        getPointCandidates(moveCandidates, cellMap, current, -1);
        getPointCandidates(moveCandidates, cellMap, current, 1);

        return moveCandidates;
    }

    private void getPointCandidates(List<LocationPoint> moveCandidates, Map<LocationPoint, Cell> cellMap, LocationPoint current, int offset) {
        LocationPoint next = LocationPointGenerator.build(current, current.getFile().ordinal(), 0); //keep file fixed, but add offset on each iteration of loop
        while (cellMap.containsKey(next)) {
            // --> if occupied == true, check to see piece that its occupying the cell is same color, if true we break out of loop, else we add to list of potential moves
            if (cellMap.get(next).isOccupied()) {
                if (cellMap.get(next).getCurrentPiece().pieceColor.equals(this.pieceColor)) {
                    break; // break because we can't move past another cell
                }
                moveCandidates.add(next);
                break;
            }
            moveCandidates.add(next);
            next = LocationPointGenerator.build(next, next.getFile().ordinal(), 0); //update next to include new current position and adding the new offset to file | point stays constant
        }
    }

    private void getFileCandidates(List<LocationPoint> moveCandidates, Map<LocationPoint, Cell> cellMap, LocationPoint current, int offset) {
        LocationPoint next = LocationPointGenerator.build(current, offset, 0);
        while (cellMap.containsKey(next)) {
            if (cellMap.get(next).isOccupied()) {
                if (cellMap.get(next).getCurrentPiece().pieceColor.equals(this.pieceColor)) {
                    break;
                }
                moveCandidates.add(next);
                break;
            }
            moveCandidates.add(next);
            next = LocationPointGenerator.build(next, offset, 0);
        }

    }

    @Override
    public List<LocationPoint> getValidMoves(Board board, Cell cell) {
        List<LocationPoint> moveCandidates = new ArrayList<>();
        Map<LocationPoint, Cell> cellMap = board.getLocationPointCellMap();
        LocationPoint current = cell.getLocationPoint();
        getFileCandidates(moveCandidates, cellMap, current, -1);
        getFileCandidates(moveCandidates, cellMap, current, 1);
        getPointCandidates(moveCandidates, cellMap, current, -1);
        getPointCandidates(moveCandidates, cellMap, current, 1);
        return moveCandidates;

    }

    @Override
    public void doMove(Cell cell) {
        cell.setOccupied(true);
        cell.setCurrentPiece(this);

    }
}
