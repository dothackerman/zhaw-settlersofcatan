package ch.zhaw.catan;

import ch.zhaw.catan.Config.Land;
import ch.zhaw.catan.components.Road;
import ch.zhaw.catan.components.Settlement;
import ch.zhaw.hexboard.HexBoard;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * The SiedlerBoard class is responsible for the board of the game.
 *
 * @author Patrick Egli
 * @version 1.0.0
 */
public class SiedlerBoard extends HexBoard<Land, Settlement, Road, String> {

    /**
     * The definition of all cardinal points.
     */
    public enum CardinalPoint {
        NORTH("N"),
        SOUTH("S"),
        NORTH_EAST("NE"),
        NORTH_WEST("NW"),
        SOUTH_EAST("SE"),
        SOUTH_WEST("SW");

        private String name;

        CardinalPoint(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * The number chips on the board.
     */
    private Map<Point, Integer> numberChips;

    /**
     * The field names.
     */
    private Map<Point, Character> fieldNames;

    /**
     * Creates a {@link SiedlerBoard} object instance.
     */
    public SiedlerBoard() {
        this.numberChips = new HashMap<>();

        setupFieldNames();
    }

    /**
     * Sets up the annotations.
     */
    public void setupAnnotations() {
        List<Point> points = getFields();

        for(Point point : points) {
            if(isFieldAnnotated(point)) {
                for (Map.Entry<Point, String> entry : getCornerCoordinatesOfField(point).entrySet()) {
                    Point cornerPosition = entry.getKey();
                    String annotation = entry.getValue();
                    addFieldAnnotation(point, cornerPosition, annotation);
                }
            }
        }
    }

    /**
     * Adds a number chip to the specific field.
     *
     * @param center The position of the field.
     * @param number The number of the field.
     */
    public void addNumberChipToField(Point center, int number) {
        if(hasField(center) && !numberChips.containsKey(center)) {
            numberChips.put(center, number);
        }
    }

    /**
     * Returns the map of the number chips.
     *
     * @return The map of the number chips.
     */
    public Map<Point, Integer> getNumberChips() {
        return numberChips;
    }

    /**
     * Returns the adjacent edges of the given {@code player}.
     *
     * @param corner The position of the corner.
     * @param player The {@link Player} object instance.
     * @return A list of {@link Road} object instances.
     */
    public List<Road> getAdjacentEdgesOfPlayer(Point corner, Player player) {
        List<Road> roads = getAdjacentEdges(corner);
        List<Road> result = new ArrayList<>();

        for(Road road : roads) {
            if(road.getOwner() == player) {
                result.add(road);
            }
        }

        return result;
    }

    /**
     * Returns a boolean state containing information about whether the corner is occupied
     * by the given {@code player} or not.
     *
     * @param location The location of the corner.
     * @param player The {@link Player} object instance.
     * @return A boolean state containing information about whether the corner is occupied by the given {@code} player or not.
     */
    public boolean isCornerOccupiedByPlayer(Point location, Player player) {
        Settlement settlement = getCorner(location);
        return (settlement != null) ? settlement.getOwner() == player : false;
    }

    /**
     * Returns the corner coordinates of a specific field.
     *
     * @param position The center position of the field.
     * @return A list containing the corner coordinates.
     */
    private Map<Point, String> getCornerCoordinatesOfField(Point position) {
        Map<Point, String> map = new HashMap<>();
        Character fieldName = getFieldName(position);

        map.put(new Point(position.x, position.y - 2), CardinalPoint.NORTH.toString() + fieldName);
        map.put(new Point(position.x + 1, position.y - 1), CardinalPoint.NORTH_WEST.toString());
        map.put(new Point(position.x + 1, position.y + 1), CardinalPoint.SOUTH_WEST.toString());
        map.put(new Point(position.x, position.y + 2), CardinalPoint.SOUTH.toString());
        map.put(new Point(position.x - 1, position.y - 1), CardinalPoint.NORTH_EAST.toString());
        map.put(new Point(position.x - 1, position.y + 1), CardinalPoint.SOUTH_EAST.toString());

        return map;
    }

    /**
     * Returns the name of a specific field.
     *
     * @param center The center of the field.
     * @return The name of the field.
     * @throws IllegalArgumentException If the field does not exist.
     */
    public Character getFieldName(Point center) {
        if(fieldNames.containsKey(center)) {
            return fieldNames.get(center);
        }

        String message = String.format("The given field does not exist '%s, %s'", center.x, center.y);
        throw new IllegalArgumentException(message);
    }

    /**
     * Returns a field position by its field name.
     *
     * @param fieldName The field name to look for.
     * @return The field position.
     * @throws IllegalArgumentException If the field does not exist.
     */
    public Point getFieldPositionByName(char fieldName) {
        fieldName = Character.toUpperCase(fieldName);
        for(Map.Entry<Point, Character> entry : fieldNames.entrySet()) {
            if(entry.getValue().equals(fieldName)) {
                return entry.getKey();
            }
        }

        String message = String.format("The field with the name '%s' does not exist.", fieldName);
        throw new IllegalArgumentException(message);
    }

    /**
     * Returns a corner position by the given {@code cardinalPoint} and {@code center}.
     *
     * @param center The center of the field.
     * @param cardinalPoint The cardinal point.
     * @return The corner position.
     * @throws IllegalArgumentException If the cardinal point is invalid.
     */
    public Point getCornerByCardinalPoint(Point center, CardinalPoint cardinalPoint) {
        switch(cardinalPoint) {
            case NORTH:
                return validateCornerPosition(new Point(center.x, center.y - 2));
            case NORTH_WEST:
                return validateCornerPosition(new Point(center.x + 1, center.y - 1));
            case SOUTH_WEST:
                return validateCornerPosition(new Point(center.x + 1, center.y + 1));
            case SOUTH:
                return validateCornerPosition(new Point(center.x, center.y + 2));
            case NORTH_EAST:
                return validateCornerPosition(new Point(center.x - 1, center.y - 1));
            case SOUTH_EAST:
                return validateCornerPosition(new Point(center.x - 1, center.y + 1));
            default:
                throw new IllegalArgumentException("The given cardinal point does not exist.");
        }
    }

    /**
     * Returns the field names.
     *
     * @return The map of field names.
     */
    public Map<Point, Character> getFieldNames() {
        return fieldNames;
    }

    /**
     * Returns a boolean state containing information about whether the given field is annotated or not.
     *
     * @param center The center of the field.
     * @return A boolean state containing information about whether the given field is annotated or not.
     */
    private boolean isFieldAnnotated(Point center) {
        return fieldNames.containsKey(center);
    }

    /**
     * Validates the given {@code cornerPosition}.
     *
     * @param cornerPosition The corner position that is being validated.
     * @return The corner position if the position is valid.
     * @throws IllegalArgumentException If the corner does not exist at the given {@code cornerPosition}.
     */
    private Point validateCornerPosition(Point cornerPosition) {
        if(hasCorner(cornerPosition)) {
            return cornerPosition;
        }

        String message = String.format("The corner at position '%s, %s' does not exist.", cornerPosition.x, cornerPosition.y);
        throw new IllegalArgumentException(message);
    }

    /**
     * Sets up the field names.
     */
    private void setupFieldNames() {
        Map<Point, Character> fieldNames = new HashMap<>();

        fieldNames.put(new Point(5, 5), 'A');
        fieldNames.put(new Point(7, 5), 'B');
        fieldNames.put(new Point(9, 5), 'C');

        fieldNames.put(new Point(4, 8), 'D');
        fieldNames.put(new Point(6, 8), 'E');
        fieldNames.put(new Point(8, 8), 'F');
        fieldNames.put(new Point(10, 8), 'G');

        fieldNames.put(new Point(3, 11), 'H');
        fieldNames.put(new Point(5, 11), 'I');
        fieldNames.put(new Point(7, 11), 'J');
        fieldNames.put(new Point(9, 11), 'K');
        fieldNames.put(new Point(11, 11), 'L');

        fieldNames.put(new Point(4, 14), 'M');
        fieldNames.put(new Point(6, 14), 'N');
        fieldNames.put(new Point(8, 14), 'O');
        fieldNames.put(new Point(10, 14), 'P');

        fieldNames.put(new Point(5, 17), 'Q');
        fieldNames.put(new Point(7, 17), 'R');
        fieldNames.put(new Point(9, 17), 'T');

        this.fieldNames = Collections.unmodifiableMap(fieldNames);
    }
}
