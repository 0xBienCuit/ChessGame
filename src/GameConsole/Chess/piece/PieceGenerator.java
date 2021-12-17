package GameConsole.Chess.piece;

import GameConsole.Chess.Coordinates.File;
import GameConsole.Chess.Coordinates.LocationPoint;

import java.util.HashMap;
import java.util.Map;

public final class PieceGenerator {
    private PieceGenerator() { }
    public static Map<LocationPoint, absPiece> getPieces(){
        Map<LocationPoint, absPiece > pieces = new HashMap<>();

        //rooks
        pieces.put(new LocationPoint(File.A, 1), new Rook(PieceColor.WHITE));
        pieces.put(new LocationPoint(File.H, 1), new Rook(PieceColor.WHITE));
        pieces.put(new LocationPoint(File.A, 8), new Rook(PieceColor.BLACK));
        pieces.put(new LocationPoint(File.H, 8), new Rook(PieceColor.BLACK));

        //knights
        pieces.put(new LocationPoint(File.B, 1), new Knight(PieceColor.WHITE));
        pieces.put(new LocationPoint(File.G, 1), new Knight(PieceColor.WHITE));
        pieces.put(new LocationPoint(File.B, 8), new Knight(PieceColor.BLACK));
        pieces.put(new LocationPoint(File.G, 8), new Knight(PieceColor.BLACK));

        //bishops
        pieces.put(new LocationPoint(File.C, 1), new Bishop(PieceColor.WHITE));
        pieces.put(new LocationPoint(File.F, 1), new Bishop(PieceColor.WHITE));
        pieces.put(new LocationPoint(File.C, 8), new Bishop(PieceColor.BLACK));
        pieces.put(new LocationPoint(File.F, 8), new Bishop(PieceColor.BLACK));

        //Queen --> always on d-file
        pieces.put(new LocationPoint(File.D, 1), new Queen(PieceColor.WHITE));
        pieces.put(new LocationPoint(File.D, 8), new Queen(PieceColor.BLACK));

        //King
        pieces.put(new LocationPoint(File.E, 1), new King(PieceColor.WHITE));
        pieces.put(new LocationPoint(File.E, 8), new King(PieceColor.WHITE));

        //pawns

        for(File file: File.values()){
            pieces.put(new LocationPoint(file, 2), new Pawn(PieceColor.WHITE));
            pieces.put(new LocationPoint(file, 7), new Pawn(PieceColor.BLACK));
        }

        return pieces;







    }

    //add pieces to tha thing


}
