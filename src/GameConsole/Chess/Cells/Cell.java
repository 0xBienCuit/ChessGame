package GameConsole.Chess.Cells;

import GameConsole.Chess.Coordinates.LocationPoint;
import GameConsole.Chess.piece.absPiece;

public class Cell {
    private final CellColor cellColor;
    private final LocationPoint locationPoint;
    private boolean isOccupied;
    private absPiece currentPiece;

    public Cell(CellColor cellColor, LocationPoint locationPoint) {
        this.cellColor = cellColor;
        this.locationPoint = locationPoint;
        this.isOccupied = false;
    }
    public void reset(){
        this.isOccupied = false;


    }



    public absPiece getCurrentPiece() {
        return currentPiece;
    }

    public void setCurrentPiece(absPiece currentPiece) {
        this.currentPiece = currentPiece;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied; //bij aanroep is false en vice versa
    }

    public LocationPoint getLocationPoint() {
        return locationPoint;
    }

    public CellColor getCellColor() {
        return cellColor;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "cellColor=" + cellColor +
                ", locationPoint=" + locationPoint +
                ", isOccupied=" + isOccupied +
                '}';
    }
}
