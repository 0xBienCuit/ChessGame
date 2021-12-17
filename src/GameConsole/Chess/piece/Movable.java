package GameConsole.Chess.piece;

import GameConsole.Chess.Board.Board;
import GameConsole.Chess.Cells.Cell;
import GameConsole.Chess.Coordinates.LocationPoint;

import java.util.List;

public interface Movable {
    List<LocationPoint> getValidMoves(Board board);
    List<LocationPoint> getValidMoves(Board board, Cell cell);
    void doMove(Cell cell);
}
