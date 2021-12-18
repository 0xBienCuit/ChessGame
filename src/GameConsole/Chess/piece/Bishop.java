package GameConsole.Chess.piece;

import GameConsole.Chess.Board.Board;
import GameConsole.Chess.Cells.Cell;
import GameConsole.Chess.Cells.LocationPointGenerator;
import GameConsole.Chess.Coordinates.LocationPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bishop extends absPiece implements Movable{

    public Bishop(PieceColor pieceColor){
        super(pieceColor);
        this.pieceName = "Bishop";
    }

    @Override
    public List<LocationPoint> getValidMoves(Board board) {
        List<LocationPoint> moveCandidates = new ArrayList<>();
        Map<LocationPoint, Cell> cellMap = board.getLocationPointCellMap();
        LocationPoint current = this.getCurrentCell().getLocationPoint();
        getMoves(moveCandidates, cellMap, current, 1, 1);
        getMoves(moveCandidates, cellMap, current, 1, -1);
        getMoves(moveCandidates, cellMap, current, -1, -1);
        getMoves(moveCandidates, cellMap, current, -1, 1);

        return moveCandidates;


    }

    private void getMoves(List<LocationPoint> candidates, Map<LocationPoint, Cell> cellMap, LocationPoint current, int fileOffSet, int pointOffSet){
        LocationPoint next = LocationPointGenerator.build(current, fileOffSet, pointOffSet);
        while (cellMap.containsKey(next)){
            if (cellMap.get(next).isOccupied()) break;
            if (cellMap.get(next).getCurrentPiece().pieceColor.equals(this.pieceColor)) break;
            candidates.add(next);
            break;
        }
        candidates.add(next);
        next = LocationPointGenerator.build(next, fileOffSet, pointOffSet);
    }

    @Override
    public List<LocationPoint> getValidMoves(Board board, Cell cell) {
        List<LocationPoint> moveCandidates = new ArrayList<>();
        Map<LocationPoint, Cell> cellMap = board.getLocationPointCellMap();
        LocationPoint current = cell.getLocationPoint();
        getMoves(moveCandidates, cellMap, current, 1, 1);
        getMoves(moveCandidates, cellMap, current, 1, -1);
        getMoves(moveCandidates, cellMap, current, -1, -1);
        getMoves(moveCandidates, cellMap, current, -1, 1);
        return moveCandidates;
    }

    @Override
    public void doMove(Cell cell) {
        cell.setOccupied(true);
        cell.setCurrentPiece(this);


    }
}
