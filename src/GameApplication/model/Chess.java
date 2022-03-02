package GameApplication.model;

import GameApplication.model.chess.Board;
import GameApplication.model.chess.piece.Piece;

import java.util.Scanner;

public class Chess {

    private  Piece[][] piecesOnBoard;
    private Board board;
    Scanner sc = new Scanner(System.in);

    public Chess(){
        System.out.println("Chess game ");
//        piece[0][0] sizeX = 8;
//        piece[0][1] sizeY = 8;
         board = new Board();
        // board.drawBoard();

      piecesOnBoard = board.getPieceIntern();
    }

    public Board getBoard() {
        return board;
    }

    /*
    public static void main(String[] args) {
        System.out.println("Chess game ");
//        piece[0][0] sizeX = 8;
//        piece[0][1] sizeY = 8;
        Board brd = new Board();
       // brd.drawBoard();

         piecesOnBoard = brd.getPieceIntern();
    }

     */


    public  Piece[][] getPiecesOnBoard() {
        piecesOnBoard = getBoard().getPieceIntern();
        return piecesOnBoard;
    }


}