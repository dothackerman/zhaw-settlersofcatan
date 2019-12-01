import ch.zhaw.catan.Config;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private Config.Faction faction;
    private Map<Config.Resource, Integer> availableResources = new HashMap<>();
    private Map<Config.Structure, ArrayList<Point>> structures = new HashMap<>();
    private Map<Point, Road> roads = new HashMap<>();

    public Player(Config.Faction faction) {
        this.faction = faction;

        structures.put(Config.Structure.SETTLEMENT, new ArrayList<>());
        structures.put(Config.Structure.CITY, new ArrayList<>());
    }

    public Config.Faction getFaction() {
        return faction;
    }

    public boolean tryAddSettlement(Point newSettlement) {
        ArrayList<Point> points = structures.get(Config.Structure.SETTLEMENT);

        if (points.contains(newSettlement)) {
            return false;
        }

        points.add(newSettlement);
        return true;
    }

    public boolean tryAddCity(Point newCity) {
        ArrayList<Point> points = structures.get(Config.Structure.CITY);

        if (points.contains(newCity)) {
            return false;
        }

        points.add(newCity);
        return true;
    }

    public void startRoad(Point startStructure, Point roadEnd) {
        RoadElement roadElement = new RoadElement(startStructure, roadEnd);
        Road road = new Road(startStructure, roadElement);

        roads.put(startStructure, road);
    }


    //TODO updateRoad
    //determine from which startStructure the new Point (either new structure or roadelement) started

    public int getLongestStreet() {
        //TODO calculate longest continuous road count
        return -1;
    }

}
