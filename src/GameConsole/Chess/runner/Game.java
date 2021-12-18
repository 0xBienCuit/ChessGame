package GameConsole.Chess.runner;

import GameConsole.Chess.Board.Board;
import GameConsole.Chess.Cells.Cell;
import GameConsole.Chess.Coordinates.File;
import GameConsole.Chess.Coordinates.LocationPoint;
import GameConsole.Chess.piece.Movable;

import java.io.IOException;
import java.util.Scanner;

/**
 * TODO (deadline today)
 * - upload pdf-file consisting of a representation of program UML-diagrams
 * <p>
 * TODO (Deadline 24/12)
 * - fix bug at doMove() that doesn't place piece at moved spot
 * - implement validMoves() Logic --> ASAP
 */


public class Game {
    public static void main(String[] args) throws IOException {
        //Map<LocationPoint, Cell> locationPointCellMap = null;
        System.out.println("Play a game of chess! Only caveat is that it's running on MS-DOS");
        System.out.println("Each player takes turn by typing in their starting point followed by '-->' and their desired destination point \n Example usage: \t a2->a4 \n");
        Board board = new Board();

        board.printBoard();
        board.getWhitePieces().forEach(System.out::println);

        Scanner sc = new Scanner(System.in);



        while(true){
        //clear console
        String line = sc.nextLine();

        String[] fromTo = line.split("->");
        File fromFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[0].charAt(0))));
        int fromPoint = Integer.parseInt(String.valueOf(fromTo[0].charAt(1)));
        File toFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[1].charAt(0))));
        int toPoint = Integer.parseInt(String.valueOf(fromTo[1].charAt(1)));


        Cell fromCl = board.getLocationPointCellMap().get(new LocationPoint(fromFile, fromPoint));
        Cell toCl = board.getLocationPointCellMap().get(new LocationPoint(toFile, toPoint));

        fromCl.getCurrentPiece().doMove(toCl);
        ClearConsole();
        fromCl.reset();
        fromCl.setCurrentPiece(null);


        board.printBoard();


    }
}

    public static void printPiece(Movable piece) {
        piece.getValidMoves(null);
    }

    public static void ClearConsole() {
        try {
            String OS = System.getProperty("os.name");
            if (OS.contains(("Windows"))) {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                ProcessBuilder startProcess = pb.inheritIO();
                startProcess.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}




