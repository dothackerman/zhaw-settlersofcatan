import ch.zhaw.catan.Config;
import ch.zhaw.catan.Config.Land;
import ch.zhaw.catan.SiedlerBoard;
import ch.zhaw.catan.SiedlerBoardTextView;
import ch.zhaw.hexboard.Label;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class Dummy {
    private static Random random = new Random();

    public enum Actions {
        SHOW, QUIT, PLAY
    }

    public enum SubActions {
        DEAL, BUILD
    }

    private void run() {
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> textTerminal = textIO.getTextTerminal();

        //Spielaufbau
        SiedlerBoard board = new SiedlerBoard();

        Map<Point, Land> fields = Config.getStandardLandPlacement();

        for (Map.Entry<Point, Land> e : fields.entrySet()) {
            board.addField(e.getKey(), e.getValue());
        }

        SiedlerBoardTextView view = new SiedlerBoardTextView(board);

        Map<Point, Integer> lowerFieldLabel = Config.getStandardDiceNumberPlacement();

        for (Map.Entry<Point, Integer> e : lowerFieldLabel.entrySet()) {
            view.setLowerFieldLabel(e.getKey(), getLabel(getDigits(e.getValue())));
        }

        //Gründungsphase
        //board.setCorner(new Point(6, 6), "RR");
        //board.setEdge(new Point(5, 7), new Point(6, 6), "r");
        //board.addFieldAnnotation(new Point(2, 2), new Point(3, 1), "AA"); ???

        ArrayList<Player> players = new ArrayList<>();
        var playerOne = new Player(Config.Faction.BLUE);
        var playerTwo = new Player(Config.Faction.GREEN);
        players.add(playerOne);
        players.add(playerTwo);

        for (int turns = 1; turns <= 2; turns++) {
            for (Player player : players) {
                //TODO dynamically set initial settlements
            }
        }

        var playerOneFirstSettlement = new Point(5, 7);
        var playerOneFirstRoadEndpoint = new Point(6, 6);
        playerOne.tryAddSettlement(playerOneFirstSettlement);
        playerOne.startRoad(playerOneFirstSettlement, playerOneFirstRoadEndpoint);

        board.setCorner(playerOneFirstSettlement, playerOne.getFaction().toString());
        board.setEdge(playerOneFirstSettlement, playerOneFirstRoadEndpoint, "r");

        var playerOneSecondSettlement = new Point(8, 16);
        var playerOneSecondRoadEndpoint = new Point(7, 15);
        playerOne.tryAddSettlement(playerOneSecondSettlement);
        playerOne.startRoad(playerOneSecondSettlement, playerOneSecondRoadEndpoint);

        board.setCorner(playerOneSecondSettlement, playerOne.getFaction().toString());
        board.setEdge(playerOneSecondSettlement, playerOneSecondRoadEndpoint, "r");

        boolean running = true;
        while (running) {
            switch (getEnumValue(textIO, Actions.class)) {
                case SHOW:
                    textTerminal.println(view.toString());
                    break;
                case PLAY:
                    int number = random.nextInt(12) + 1;
                    //TODO if 7 handle special 7-rule

                    //TODO SiedlerGame.throwDice(number)

                    switch (getEnumValue(textIO, SubActions.class)) {
                        case DEAL:
                            //TODO 4-to-1 system
                            //TODO check if player has 4 cards
                            //TODO check if bank has 1 card

                            break;
                        case BUILD:
                            //TODO check if player has enough resources
                            //TODO [road] check if empty road
                            //TODO [settlement] check that neighbour corners are empty
                            //TODO [city] check that field is settlement
                            break;
                    }

                case QUIT:
                    running = false;
                    break;
                default:
                    throw new IllegalStateException("Internal error found - Command not implemented.");
            }
        }
        textIO.dispose();
    }

    private Label getLabel(ArrayList<Integer> digits) {
        if (digits.size() == 1) {
            return new Label('0', digits.get(0).toString().toCharArray()[0]);
        }

        return new Label(digits.get(0).toString().toCharArray()[0], digits.get(1).toString().toCharArray()[0]);
    }

    private ArrayList<Integer> getDigits(int i) {
        ArrayList<Integer> digits = new ArrayList<>();
        while (i > 0) {
            digits.add(i % 10);
            i /= 10;
        }
        return digits;
    }


    public static <T extends Enum<T>> T getEnumValue(TextIO textIO, Class<T> commands) {
        return textIO.newEnumInputReader(commands).read("What would you like to do?");
    }

    public static void main(String[] args) {
        new Dummy().run();
    }
}
