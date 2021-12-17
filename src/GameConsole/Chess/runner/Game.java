package GameConsole.Chess.runner;

import GameConsole.Chess.Board.Board;
import GameConsole.Chess.Cells.Cell;
import GameConsole.Chess.Coordinates.File;
import GameConsole.Chess.Coordinates.LocationPoint;
import GameConsole.Chess.piece.Movable;

import java.util.Scanner;


public class Game {
    public static void main(String[] args) {
        //Map<LocationPoint, Cell> locationPointCellMap = null;
        Board board = new Board();
        board.printBoard();
        board.getWhitePieces().forEach(System.out::println);

        Scanner sc = new Scanner(System.in);
        while(true){
            String line = sc.nextLine();
            String[] fromTo = line.split("->");
            File fromFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[0].charAt(0))));
            int fromPoint = Integer.parseInt(String.valueOf(fromTo[0].charAt(1)));
            File toPoint = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[1].charAt(0))));
            int toFile = Integer.parseInt(String.valueOf(fromTo[1].charAt(1)));

            Cell SrcDest = board.getLocationPointCellMap().get(new LocationPoint(fromFile, fromPoint));
            Cell dstSrc = board.getLocationPointCellMap().get(new LocationPoint(fromFile, fromPoint));

            SrcDest.getCurrentPiece().doMove(dstSrc);
            SrcDest.init();

            board.printBoard();


        }


    }

    public static void printPiece(Movable piece, Board board) {
        piece.getValidMoves(null);
    }
}


