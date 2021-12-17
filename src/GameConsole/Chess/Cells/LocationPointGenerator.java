package GameConsole.Chess.Cells;

import GameConsole.Chess.Coordinates.File;
import GameConsole.Chess.Coordinates.LocationPoint;

public class LocationPointGenerator {
    private static final File[] files = File.values();

    public static LocationPoint build(LocationPoint current, Integer fileOffSet, Integer pointOffSet) {
        Integer currentFile = current.getFile().ordinal();
        return new LocationPoint(files[currentFile + fileOffSet], current.getPoint() + pointOffSet);
    }
}
