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
        getFileCandidates(moveCandidates, cellMap, current, -1);
        getFileCandidates(moveCandidates, cellMap, current, 1);
        getPointCandidates(moveCandidates, cellMap, current, -1);
        getPointCandidates(moveCandidates, cellMap, current, 1);

        return moveCandidates;
    }

    private void getPointCandidates(List<LocationPoint> moveCandidates, Map<LocationPoint, Cell> cellMap, LocationPoint current, int offset){
        LocationPoint next = LocationPointGenerator.build(current,current.getFile().ordinal(), 0);
        while (cellMap.containsKey(next)){
            if (cellMap.get(next).isOccupied()){
                if (cellMap.get(next).getCurrentPiece().pieceColor.equals(this.pieceColor)){
                    break;
                }
                moveCandidates.add(next);
                break;
            }
            moveCandidates.add(next);
            next = LocationPointGenerator.build(next, offset, 0);
        }
    }

    private void getFileCandidates(List<LocationPoint> moveCandidates, Map<LocationPoint, Cell> cellMap, LocationPoint current, int offset){
        LocationPoint next = LocationPointGenerator.build(current, offset, 0);
        while (cellMap.containsKey(next)){
            if (cellMap.get(next).isOccupied()){
                if (cellMap.get(next).getCurrentPiece().pieceColor.equals(this.pieceColor)){
                    break;
                }
                moveCandidates.add(next);
                break;
            }
            moveCandidates.add(next);
            next = LocationPointGenerator.build(next, -1, 0);
        }

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
