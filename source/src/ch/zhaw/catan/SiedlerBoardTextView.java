package ch.zhaw.catan;

import ch.zhaw.catan.Config.Land;
import ch.zhaw.catan.components.Road;
import ch.zhaw.catan.components.Settlement;
import ch.zhaw.hexboard.HexBoardTextView;

public class SiedlerBoardTextView extends HexBoardTextView<Land, Settlement, Road, String> {

    public SiedlerBoardTextView(SiedlerBoard board) {
        super(board);
    }

}
