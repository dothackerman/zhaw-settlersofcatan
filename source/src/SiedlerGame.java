import ch.zhaw.catan.Config;
import ch.zhaw.catan.Config.Faction;
import ch.zhaw.catan.Config.Resource;
import ch.zhaw.catan.SiedlerBoard;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SiedlerGame {
    private Map<Resource, Integer> bank;
    private ArrayList<Player> players = new ArrayList<>();
    private int requiredWinPoints;

    public SiedlerGame(int winPoints, int players) {
        // TODO: Implement

        for (int value = 0; value < players; value++) {
            this.players.add(new Player(Config.Faction.values()[value]));
        }

        requiredWinPoints = winPoints;
        bank = Config.INITIAL_RESOURCE_CARDS_BANK;
    }

    public void switchToNextPlayer() {
        // TODO: Implement
    }

    public void switchToPreviousPlayer() {
        // TODO: Implement
    }

    public List<Faction> getPlayer() {
        // TODO: Implement
        return null;
    }

    public SiedlerBoard getBoard() {
        // TODO: Implement
        return null;
    }

    public Faction getCurrentPlayer() {
        // TODO: Implement
        return null;
    }

    public int getCurrentPlayerResourceStock(Resource resource) {
        // TODO: Implement
        return 0;
    }

    public boolean placeInitialSettlement(Point position, boolean payout) {
        // TODO: Implement
        return false;
    }

    public boolean placeInitialRoad(Point roadStart, Point roadEnd) {
        // TODO: Implement
        return false;
    }

    public Map<Faction, List<Resource>> throwDice(int dicethrow) {
        // TODO: Implement
        return null;
    }

    public boolean buildSettlement(Point position) {
        // TODO: Implement
        return false;
    }

    public boolean buildCity(Point position) {
        // TODO: OPTIONAL task - Implement
        return false;
    }

    public boolean buildRoad(Point roadStart, Point roadEnd) {
        // TODO: Implement
        return false;
    }

    public boolean tradeWithBankFourToOne(Resource offer, Resource want) {
        //TODO validate players resources

        if (bank.get(want) < 1) {
            return false;
        }

        bank.put(offer, bank.get(offer) + 4);
        bank.put(want, bank.get(want) - 1);

      return true;
    }

    public Faction getWinner() {
        // TODO: Implement
        return null;
    }
}
