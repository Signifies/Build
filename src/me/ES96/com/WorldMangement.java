package me.ES96.com;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import utilities.BuildUtils;
import utilities.Debug;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by Coffee on 4/20/17.
 */
public class WorldMangement extends BuildUtils
{

    private Build instance;

    public WorldMangement(Build value)
    {
        instance = value;
    }


    /*
    public void writeWorld(Player player)
    {
        String path = instance.getWConfig().getWorldConfig().getString("World-Management.saved-worlds.");
        String world = player.getWorld().getName();
        String author = player.getName();
        UUID uuid = player.getUniqueId();
        Timestamp ts = getStamp();
        Location location = player.getWorld().getSpawnLocation();

        if(instance.getWConfig().getWorldConfig().getConfigurationSection("World-Management.saved-worlds."+world) == null){
            write("World-Management.saved-worlds",world);
            write(path+""+world+".name",world);
            write(path+""+world+".author",author);
//            write(path+""+world+".uuid",uuid);
            write(path+""+world+".permission", "Build.worlds."+world);
            write(path+""+world+".time-created",ts.toString());
//            write(path+""+world+".world-location",location.toString());
            write(path+""+world+".place", false);
            write(path+""+world+".break", false);
            write(path+""+world+".drop", true);
            write(path+""+world+".pickup", true);
            write(path+""+world+".chat.chat-enabled", true);
            write(path+""+world+".chat.chat-format", color("&8&l> &b[&7%world%&b] &a%name% &7%msg%").toString());
            write(path+""+world+".interact", true);
            write(path+""+world+".explosion", false);
            log(Debug.pluginLog() + "&a&lThe world, &n" + world + "&a&l has been added.");
            player.sendMessage(color( "&a&lThe world, &n" + world + "&a&l has been added."));
            instance.getWConfig().saveWorldConfig();
        }else
        {
            log("&cError, this world has already been created or exists.");
        }
    }
    */

    public void writeWorld(Player player)
    {
        ConfigurationSection path = instance.getWConfig().getWorldConfig().getConfigurationSection("World-Management.saved-worlds");
        String world = player.getWorld().getName();
        String author = player.getName();
        UUID uuid = player.getUniqueId();
        Timestamp ts = getStamp();
        Location location = player.getWorld().getSpawnLocation();

        if(path != null){
            Debug.log(Debug.pluginLog() + "Path isn't null");
        write("World-Management.saved-worlds",world);
        return;
    }

        Debug.log(Debug.pluginLog() + "The path was null...");
        path = path.createSection(world) ;
        path.set("name", world);
        path.set("author", author);
        //path.set("uuid", uuid);
        path.set("permission", "Build.worlds."+world );
        path.set("time-created", ts.toString());
        //path.set("world-location", location.toString());
        path.set("place", false);
        path.set("break", false);
        path.set("drop", true);
        path.set("pickup", true);
        path.set("chat.chat-enabled", true);
        path.set("chat.chat-format", color("&8&l> &b[&7%world%&b] &a%name% &7%msg%").toString());
        path.set("interact", true);
        path.set("explosion", false);
        log(Debug.pluginLog() + "&a&lThe world, &n" + world + "&a&l has been added.");
        player.sendMessage(color( "&a&lThe world, &n" + world + "&a&l has been added."));
        instance.getWConfig().saveWorldConfig();
    }

    void write(String path, Object value)
    {
        instance.getWConfig().getWorldConfig().set(path, value);
    }
        
    public void deleteWorld(Player player)
    {}

    public void getWorldInfo()
    {

    }




}
