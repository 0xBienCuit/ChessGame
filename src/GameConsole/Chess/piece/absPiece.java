package GameConsole.Chess.piece;

import GameConsole.Chess.Cells.Cell;

public abstract class absPiece implements Movable {
    protected String pieceName;
    protected PieceColor pieceColor;
    protected Cell currentCell;

    public absPiece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public String getPieceName() {
        return pieceName;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }
    //setter to change status of cell from occupied to free

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    @Override
    public String toString() {
        return "absPiece{" +
                "pieceName='" + pieceName + '\'' +
                ", pieceColor=" + pieceColor +
                ", currentCell=" + currentCell +
                '}';
    }
}
