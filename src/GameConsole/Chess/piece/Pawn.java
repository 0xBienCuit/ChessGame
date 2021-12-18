package GameConsole.Chess.piece;


import GameConsole.Chess.Board.Board;
import GameConsole.Chess.Cells.Cell;
import GameConsole.Chess.Cells.LocationPointGenerator;
import GameConsole.Chess.Coordinates.LocationPoint;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Pawn extends absPiece implements Movable {
    private final boolean isFirstMove = true;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
        this.pieceName = "pawn";
    }

    @Override
    public List<LocationPoint> getValidMoves(Board board) {
        List<LocationPoint> moveCandidates = new ArrayList<>(Collections.emptyList());
        Map<LocationPoint, Cell> cellMap = board.getLocationPointCellMap();
        LocationPoint current = this.getCurrentCell().getLocationPoint();
        moveCandidates.add(LocationPointGenerator.build(current, 0, 1));
        if (isFirstMove) {
            moveCandidates.add(LocationPointGenerator.build(current, 0, 2));
            return moveCandidates;
        }

        moveCandidates.add(LocationPointGenerator.build(current, 1, 1));
        moveCandidates.add(LocationPointGenerator.build(current, -1, 1));

        List<LocationPoint> validMoves = moveCandidates.stream().filter((candidate) -> (board.getLocationPointCellMap().containsKey(candidate))).collect(Collectors.toList());
        //if piece other color --> cancapture
        //piece directly in front of it !! toCapture

        return validMoves.stream().filter((candidate) -> {
            //make sure candidate move ne piece color
            //verify no piece in front of pawn
            if (candidate
                    .getFile()
                    .equals(this.getCurrentCell()
                            .getLocationPoint()
                            .getFile()) && cellMap.get(candidate)
                    .isOccupied()) {
                return false;

            }

            return !cellMap.get(candidate)
                    .getCurrentPiece().pieceColor
                    .equals(this.getPieceColor());
        }).collect(Collectors.toList());


        /**
         * in Essence:
         *  -Calculating all possible Moves
         *  -Filtering out moves non existant on board
         *  -Filtering even more based on piece specific logic
         */

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
