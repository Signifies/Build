package me.ES96.com;

import utilities.BuildUtils;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by ES359 on 11/9/16.
 */
public class User extends BuildUtils
{

    private Player user;
    private Timestamp time;
    private String name;
    private UUID uuid;

    private HashMap<UUID,String> container = new HashMap<>();


    public User()
    {}

    public User(Player player)
    {
        user = player;
        time = getStamp();
        name = player.getName();
        uuid = player.getUniqueId();
        check();
    }


    void check()
    {
        if(newUser())
        {
            container.put(user.getUniqueId(),user.getName());
        }else
        {
            if(container.containsKey(user.getUniqueId()))
            {

            }
        }
    }


    public String getName()
    {
        return name;
    }

    public Timestamp getTimeStamp()
    {
        return time;
    }

    public UUID getID()
    {
        return uuid;
    }

    public boolean newUser()
    {
        return user.hasPlayedBefore();
    }



}
