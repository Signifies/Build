package utilities;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public enum Ranks {

    //TODO See these threads:https://www.spigotmc.org/threads/tutorial-custom-rank-system.109135/#post-1177008
    //TODO https://gist.github.com/ES359/635d9e581f32ac271eab5dda75c2c0fe
    //List<String> for permissions.
    //TODO Hashmap that reads in the permissions from file set as Enum variable? CHeck constructor usage
    //Use ordinal numbering system to dictate the placement and inheritance of each Rank.
    //So permission check could look like:
    /*
    if(user.isRank(Rank.DEFAULT))
    {
        //Apply all permissions for default rank + check for numbering
    }

    if(user.isRank(Rank.Builder) && Rank.ordinal <= 4) // either  a number, or another ranks default placement number.
    {7
        //If true, allow all access to features from less than the rank number or the rank number it self
    }
    */

    GUEST(0),
    MEMBER(1),
    BUILDER(2),
    MOD(3),
    SMOD(4),
    ADMIN(5),
    DEV(6);


    private int weight;

    private List<UUID> players;

    Ranks(int weight) {
        this.weight = weight;
        players = new ArrayList<>();
    }

    public int getWeight() {
        return this.weight;
    }


    public static Ranks getRank(Player p) {

        Ranks ranks = Ranks.; //TODO fix implementation here... check gist... Uses hashmap??

            /*
             Get the players rank
             */
        return ranks;
    }


    public boolean isInherited(Ranks rank)
    {
        return weight <= rank.getWeight();
    }

    public static void setRank(Player p, Ranks rank) {
        rank.getMembers().add(p.getUniqueId());
    }

    public List<UUID> getMembers() {
        return this.players;
    }

    public static void rankUp(Player p) {
        for (Ranks ranks : Ranks.values()) {
            if (ranks.getWeight() == getRank(p).getWeight() + 1) {
                setRank(p, ranks);
                return;
            }
        }
    }

    void test ()
    {
        for(Ranks r : Ranks.values())
        {
            if(getRank().getWeight() == r.weight); //something like this
        }
    }

}