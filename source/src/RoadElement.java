import java.awt.*;

public class RoadElement {
    private Point start;
    private Point end;

    public RoadElement(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public char getName() {
        return 'r';
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }
}
