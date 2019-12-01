package ch.zhaw.catan;

import ch.zhaw.catan.Config.Land;
import ch.zhaw.catan.components.Road;
import ch.zhaw.catan.components.Settlement;
import ch.zhaw.hexboard.HexBoard;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SiedlerBoard extends HexBoard<Land, Settlement, Road, String> {

    /**
     * The number chips on the board.
     */
    private Map<Point, Integer> numberChips;

    /**
     * Creates a {@link SiedlerBoard} object instance.
     */
    public SiedlerBoard() {
        this.numberChips = new HashMap<>();
    }

    /**
     * Returns the map of the number chips.
     *
     * @return The map of the number chips.
     */
    public Map<Point, Integer> getNumberChips() {
        return numberChips;
    }
}
