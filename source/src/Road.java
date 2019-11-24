import java.awt.*;
import java.util.ArrayList;

public class Road {
    private ArrayList<RoadElement> roadElements = new ArrayList<>();
    private Point startStructure;

    public Road(Point startStructure, RoadElement firstRoad) {
        this.startStructure = startStructure;
        roadElements.add(firstRoad);
    }

    public Road(Point startStructure) {
        this.startStructure = startStructure;
    }

    public boolean tryAddRoadElement(RoadElement roadElement) {
        if (roadElements.contains(roadElement)) {
            return false;
        }

        roadElements.add(roadElement);
        return true;
    }

    //Beginning with startStructure (Point) calculating whole road determines separate roads and their length

}
