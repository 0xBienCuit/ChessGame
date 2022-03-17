package GameApplication.model.chess.piece;

import GameApplication.model.chess.piece.pieces.*;
import GameApplication.model.chess.spot.Spot;

import java.util.ArrayList;
import java.util.List;

public class PieceSets {
    public List<Piece> getPieceSets() {
        return pieceSets;
    }

    //hier gaan we alle Piece's inzetten
    List<Piece> pieceSets = new ArrayList<>();

    public void setPieceSets(List<Piece> pieceSets) {
        this.pieceSets = pieceSets;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    private PieceColor color;


    public PieceSets(PieceColor color) {

        //TODO generate 16 pieces
        setPieceStartPositions(color);

        this.color = color;
    }


    public void setPieceStartPositions(PieceColor color) {
        //If color is white then row should be 0 (bottom) else 7 (top of board)
        int valueForRow = PieceColor.WHITE == color ? 0 : 7;

        //TODO Set each piece on its position based on piece color.
        //Sets King an d Queen
        pieceSets.add(new King(color, new Spot(4, valueForRow)));
        pieceSets.add(new Queen(color, new Spot(3, valueForRow)));


        //sets the initial value for ROOK , KNIGHT and BISHOP
        for (int i = 0; i < 2; i++) {
            //TODO uitleg geven voor i*7 of i*3. waarom?
            pieceSets.add(new Rook(color, new Spot(i * 7, valueForRow)));
            pieceSets.add(new Knight(color, new Spot(1 + i * 5, valueForRow)));
            pieceSets.add(new Bishop(color, new Spot(2 + i * 3, valueForRow)));
        }
        for (int i = 0; i < 8; i++) {
            pieceSets.add(new Pawn(color, new Spot(i, valueForRow == 0 ? 1 : 6)));
        }

        //add piece to spot
        for (Piece piece : pieceSets) {
            piece.getPieceLocation().setPiece(piece);
        }


    }

    public List<Piece> getPieces() {
        return pieceSets;
    }

    public PieceColor getColor() {
        return color;
    }

    public String getColorName() {
        return color.getColorName();
    }

    public void removePiece(Piece piece) {
        //boolean p = pieceSets.contains(piece);

        pieceSets.remove(piece);

        /*
        for (Iterator<Piece> iterator = pieceSets.iterator(); iterator.hasNext(); ) {
            Piece next = iterator.next();
            System.out.println(piece.equals(next));
        }

         */
    }


    @Override
    public String toString() {
        StringBuilder pieces = new StringBuilder("");
        for (Piece ps :
                pieceSets) {
            pieces.append(String.format("%s%n", ps.toString()));
        }
        return pieces.toString();
    }
}