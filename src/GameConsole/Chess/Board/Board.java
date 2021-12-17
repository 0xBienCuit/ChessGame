package GameConsole.Chess.Board;



/*
╔═╤═╤═╤═╤═╤═╤═╤═╗
║♜│♞│♝│♛│♚│♝│♞│♜║
╟─┼─┼─┼─┼─┼─┼─┼─╢
║♟│♟│♟│♟│♟│♟│♟│♟║
╟─┼─┼─┼─┼─┼─┼─┼─╢
║ │░│ │░│ │░│ │░║
╟─┼─┼─┼─┼─┼─┼─┼─╢
║░│ │░│ │░│ │░│ ║
╟─┼─┼─┼─┼─┼─┼─┼─╢
║ │░│ │░│ │░│ │░║
╟─┼─┼─┼─┼─┼─┼─┼─╢
║░│ │░│ │░│ │░│ ║
╟─┼─┼─┼─┼─┼─┼─┼─╢
║♙│♙│♙│♙│♙│♙│♙│♙║
╟─┼─┼─┼─┼─┼─┼─┼─╢
║♖│♘│♗│♕│♔│♗│♘│♖║
╚═╧═╧═╧═╧═╧═╧═╧═╝
 */


import GameConsole.Chess.Cells.Cell;
import GameConsole.Chess.Cells.CellColor;
import GameConsole.Chess.Coordinates.File;
import GameConsole.Chess.Coordinates.LocationPoint;
import GameConsole.Chess.piece.PieceColor;
import GameConsole.Chess.piece.PieceGenerator;
import GameConsole.Chess.piece.absPiece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//multi dimensional array of cells
public class Board {
    private static final Integer BOARD_LENGTH = 8;
    private final Map<LocationPoint, Cell> locationPointCellMap;
    private final List<absPiece> whitePieces = new ArrayList<>();
    private final List<absPiece> blackPieces = new ArrayList<>();
    Cell[][] boardCells = new Cell[BOARD_LENGTH][BOARD_LENGTH];


    public Board() {
        locationPointCellMap = new HashMap<>();
        Map<LocationPoint, absPiece> pieces = PieceGenerator.getPieces();

        for (int i = 0; i < boardCells.length; i++) {
            int column = 0; // current colluimn
            CellColor currentColor = (i % 2 == 0) ? CellColor.WHITE : CellColor.BLACK;
            for (File file : File.values()) { //create cell for each cell
                Cell newCell = new Cell(currentColor, new LocationPoint(file, BOARD_LENGTH - i));
                if (pieces.containsKey(newCell.getLocationPoint())) {
                    absPiece piece = pieces.get(newCell.getLocationPoint());
                    newCell.setCurrentPiece(piece);
                    newCell.setOccupied(true);
                    piece.setCurrentCell(newCell);
                    if (piece.getPieceColor().equals(PieceColor.BLACK)) {
                        blackPieces.add(piece);
                    } else {
                        whitePieces.add(piece);
                    }
                }
                boardCells[i][column] = newCell;
                //flip colors
                locationPointCellMap.put(newCell.getLocationPoint(), newCell);
                currentColor = (currentColor == CellColor.BLACK) ? CellColor.WHITE : CellColor.BLACK;
                column++;
            }
        }
    }


    public Map<LocationPoint, Cell> getLocationPointCellMap() {
        return locationPointCellMap;
    }

    public List<absPiece> getWhitePieces() {
        return whitePieces;
    }

    public List<absPiece> getBlackPieces() {
        return blackPieces;
    }

    public void printBoard() {
        for (int i = 0; i < boardCells.length; i++) {
            System.out.print(BOARD_LENGTH - i + " ");
            for (int j = 0; j < boardCells[i].length; j++) {
                if (boardCells[i][j].isOccupied()) {
                    absPiece piece = boardCells[i][j].getCurrentPiece();
                    System.out.print(piece.getPieceName().charAt(0) + " ");

                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println();
        for (File file : File.values()) {
            System.out.print(file.name() + " ");
        }
        System.out.println();
    }
}


