import ch.zhaw.catan.Config;

public class LongestRoad{
    private static LongestRoad instance = null;
    private Config.Faction owner;

    private LongestRoad(){}

    public static LongestRoad getInstance(){
        if(instance == null){
            instance = new LongestRoad();
        }

        return instance;
    }

    public void changeOwner(Config.Faction newOwner){
        this.owner = newOwner;
    }
}