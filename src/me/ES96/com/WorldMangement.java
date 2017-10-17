package me.ES96.com;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import sun.management.counter.perf.PerfLongArrayCounter;
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

    private String path;
    private String worlds;
    private String worldName;
    private String author;
    private UUID uuid;
    private Timestamp stamp;
    private Location location;
    private String permission;
    private boolean chat;
    private String chatFormat;


    private int mapIDValue = 0;


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
        if(instance.getWConfig().getWorl'dConfig().getConfigurationSection("World-Management.saved-worlds."+world) == null){
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
//    ConfigurationSection path = instance.getWConfig().getWorldConfig().getConfigurationSection("World-Management.saved-worlds");

    private int id = 0;
    private int getMapID()
    {
        return id;
    }

    public void writeWorld(Player player)
    {
        int local = getMapID();

        instance.getWConfig().getWorldConfig().set("World-Management."+player.getWorld().getName() + "", player.getWorld().getName());
        instance.getWConfig().getWorldConfig().set("World-Management." + player.getWorld().getName() +".name", player.getWorld().getName());
        instance.getWConfig().getWorldConfig().set("World-Management." +player.getWorld().getName() + ".ID",String.format("%03d", ++local));
        instance.getWConfig().getWorldConfig().set("World-Management." + player.getWorld().getName() + ".author", player.getName());
        instance.getWConfig().getWorldConfig().set("World-Management." + player.getWorld().getName() + ".uuid", player.getUniqueId().toString());
        instance.getWConfig().getWorldConfig().set("World-Management." + player.getWorld().getName() + ".permission","Build.worlds." +player.getWorld().getName());
        instance.getWConfig().getWorldConfig().set("World-Management." +player.getWorld().getName() + ".game-world", false);
        instance.getWConfig().getWorldConfig().set("World-Management." + player.getWorld().getName() + ".time-created", getStamp());
        instance.getWConfig().getWorldConfig().set("World-Management." + player.getWorld().getName() + ".break",false);
        instance.getWConfig().getWorldConfig().set("World-Management." + player.getWorld().getName() + ".place",false);
        instance.getWConfig().getWorldConfig().set("World-Management." + player.getWorld().getName() + ".drop",false);
        instance.getWConfig().getWorldConfig().set("World-Management." + player.getWorld().getName() + ".pickup",false);
        instance.getWConfig().getWorldConfig().set("World-Management." + player.getWorld().getName() + ".chat.Enabled",true);
        instance.getWConfig().getWorldConfig().set("World-Management." +player.getWorld().getName()  + ".chat.format.enabled", true);
        instance.getWConfig().getWorldConfig().set("World-Management." + player.getWorld().getName() + ".chat.format","&8&l> &b[&7%world%&b] &a%name% &7%msg%");
        instance.getWConfig().getWorldConfig().set("World-Management." +player.getWorld().getName()  + ".chat.use-format", true);
        instance.getWConfig().getWorldConfig().set("World-Management." +player.getWorld().getName() +  ".chat.per-world", false );
        instance.getWConfig().getWorldConfig().set("World-Management." + player.getWorld().getName() + ".interact", true);
        instance.getWConfig().getWorldConfig().set("World-Management." + player.getWorld().getName() + ".explosion", false);
        instance.getWConfig().saveWorldConfig();
        log(Debug.pluginLog() + "&a&lThe world, &n" + player.getWorld().getName() + "&a&l has been added.");
        player.sendMessage(color( "&a&lThe world, &n" + player.getWorld().getName() + "&a&l has been added."));
//        instance.getWConfig().getWorldConfig().set("World-Management." + player.getWorld().getName() + "",);

    }

    public void perWorldChat(Player user, boolean perWorld, String format) {

    }

    void write(String path, Object value)
    {
        instance.getWConfig().getWorldConfig().set(path, value);
    }
        
    public void deleteWorld(Player player)
    {
        //Add a time delay with this method using configuration api and referencing usage from broadcast.
        instance.getWConfig().getWorldConfig().set("World-Management."+player.getWorld().getName() + "", null);
        instance.getWConfig().saveWorldConfig();
        log(Debug.pluginLog() + "&a&lThe world, &n" + player.getWorld().getName() + "&c&l has been removed.");
        player.sendMessage(color( "&a&lThe world, &n" + player.getWorld().getName() + "&c&l has been removed."));

    }

    public void getWorldInfo()
    {

    }




}
