package GameConsole.Chess.Coordinates;

import java.util.Objects;

public class LocationPoint {
    //final because map is set at construction time
    private final File file;
    private final Integer point;

    public LocationPoint(File file, Integer point) {
        this.file = file;
        this.point = point;
    }

    public File getFile() {
        return file;
    }

    public Integer getPoint() {
        return point;
    }

    //required to compare locations to each other
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationPoint)) return false;
        LocationPoint that = (LocationPoint) o;
        return getFile() == that.getFile() && Objects.equals(getPoint(), that.getPoint());
    }

    @Override
    public String toString() {
        return "LocationPoint{" +
                "file=" + file +
                ", point=" + point +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFile(), getPoint());
    }
}
