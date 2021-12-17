package GameConsole.Chess.piece;


import GameConsole.Chess.Board.Board;
import GameConsole.Chess.Cells.Cell;
import GameConsole.Chess.Cells.LocationPointGenerator;
import GameConsole.Chess.Coordinates.LocationPoint;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Pawn extends absPiece implements Movable {
    private boolean isFirstMove = true;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
        this.pieceName = "pawn";
    }

    @Override
    public List<LocationPoint> getValidMoves(Board board) {
        List<LocationPoint> moveCandidates = new ArrayList<>(Collections.emptyList());
        LocationPoint current = this.getCurrentCell().getLocationPoint();
        if (isFirstMove) {
        moveCandidates.add(LocationPointGenerator
                .build(this.getCurrentCell().getLocationPoint(), 0, 1));
            moveCandidates.add(LocationPointGenerator
                    .build(this.getCurrentCell().getLocationPoint(), 0, 2));
        }

        moveCandidates.add(LocationPointGenerator.build(current, 1, 1));
        moveCandidates.add(LocationPointGenerator.build(this.getCurrentCell().getLocationPoint(), -1, 1));




        List<LocationPoint> validMoves = moveCandidates.stream().filter((candidate) -> {
            return (board.getLocationPointCellMap().containsKey(candidate));
        }).collect(Collectors.toList());

        return moveCandidates;
    }

    @Override
    public List<LocationPoint> getValidMoves(Board board, Cell cell) {
        return null;
    }


    @Override
    public void doMove(Cell cell) {


    }

}
