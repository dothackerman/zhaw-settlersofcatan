package ch.zhaw.catan;

import ch.zhaw.catan.Config.Land;
import ch.zhaw.catan.components.Road;
import ch.zhaw.catan.components.Settlement;
import ch.zhaw.hexboard.HexBoardTextView;
import ch.zhaw.hexboard.Label;

import java.awt.*;

/**
 * The SiedlerBoardTextView class is responsible for displaying the board.
 *
 * @author Patrick Egli
 * @version 1.0.0
 */
public class SiedlerBoardTextView extends HexBoardTextView<Land, Settlement, Road, String> {

    public SiedlerBoardTextView(SiedlerBoard board) {
        super(board);
    }

    /**
     * Sets the number chip label.
     *
     * @param center The position of the field.
     * @param number The number of the chip.
     */
    public void setNumberChipLabel(Point center, int number) {
        String numberAsString = Integer.toString(number);
        char firstChar, secondChar;

        if(numberAsString.length() > 1) {
            firstChar = numberAsString.charAt(0);
            secondChar = numberAsString.charAt(1);
        } else {
            firstChar = '0';
            secondChar = numberAsString.charAt(0);
        }

        setLowerFieldLabel(center, new Label(firstChar, secondChar));
    }
}
