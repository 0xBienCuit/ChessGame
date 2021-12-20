package GameConsole.Chess.piece;

import GameConsole.Chess.Board.Board;
import GameConsole.Chess.Cells.Cell;
import GameConsole.Chess.Cells.LocationPointGenerator;
import GameConsole.Chess.Coordinates.LocationPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Knight extends absPiece implements Movable{
    public Knight(PieceColor pieceColor){
        super(pieceColor);
        this.pieceName="Knight";

    }

    @Override
    public List<LocationPoint> getValidMoves(Board board) {
        List<LocationPoint> moveCandidates = Collections.emptyList();
        Map<LocationPoint, Cell> cellMap = board.getLocationPointCellMap();
        LocationPoint current = this.getCurrentCell().getLocationPoint();
        getMoves(moveCandidates, cellMap, current, 2,1);
        getMoves(moveCandidates, cellMap, current, 2,-1);
        getMoves(moveCandidates, cellMap, current, -2,1);
        getMoves(moveCandidates, cellMap, current, -2,-1);
        return moveCandidates;

    }

    private void getMoves(List<LocationPoint> candidates, Map<LocationPoint, Cell> cellMap, LocationPoint current, int pointOffSet, int fileOffSet){
        LocationPoint next = LocationPointGenerator.build(current, fileOffSet, pointOffSet);
        if (cellMap.containsKey(next)){
            if(cellMap.get(next).isOccupied()){
                if (cellMap.get(next).getCurrentPiece().pieceColor.equals(this.pieceColor)) return;
                candidates.add(next);
                return;
            }
            candidates.add(next);
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
