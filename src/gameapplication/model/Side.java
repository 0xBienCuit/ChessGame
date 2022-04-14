package gameapplication.model;

/**
 * Represent playing sides for a game of chess.
 * I guess this should be in application.model.chess... oh well
 *
 * @author Joe
 */
public enum Side {

    WHITE(6, 7, "White"),
    BLACK(1, 0, "Black");

    private final int frontRow;
    private final int backRow;
    private final String sideName;

    Side(int frontRow, int backRow, String sideName) {
        this.frontRow = frontRow;
        this.backRow = backRow;
        this.sideName = sideName;
    }

    public int getFrontRow() {
        return frontRow;
    }

    public int getBackRow() {
        return backRow;
    }

    @Override
    public String toString() {
        return sideName;
    }
}
