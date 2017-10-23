package me.ES96.com;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
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

    private int id = 0;
    private int getMapID()
    {
        return id;
    }

    public void writeWorld(Player player)
    {
        int local = getMapID();

       if(instance.getWConfig().getWorldConfig().getConfigurationSection("World-Management").contains(player.getWorld().getName()))
       {
           player.sendMessage(color("&cThis world has already been added!"));
       }else
       {
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
//        instance.getWConfig().getWorldConfig().set("World-Management." +player.getWorld().getName() +  ".chat.per-world", false );
           instance.getWConfig().getWorldConfig().set("World-Management." + player.getWorld().getName() + ".interact", true);
           instance.getWConfig().getWorldConfig().set("World-Management." + player.getWorld().getName() + ".explosion", false);
           instance.getWConfig().saveWorldConfig();
           log(Debug.pluginLog() + "&a&lThe world, &n" + player.getWorld().getName() + "&a&l has been added.");
           player.sendMessage(color( "&a&lThe world, &n" + player.getWorld().getName() + "&a&l has been added."));
           player.sendMessage(color("&7> Map added &fsuccessfully&7!"));
//        instance.getWConfig().getWorldConfig().set("World-Management." + player.getWorld().getName() + "",);

       }
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

    public void getWorldInfo(String world, CommandSender sender) //TODO make the format pretty and it's good to go!
    {
        sender.sendMessage(color("&7--- &fWorld Data&7 ---"));
        instance.getWConfig().getWorldConfig().getString("World-Management."+world+".name");
        sender.sendMessage(color(instance.getWConfig().getWorldConfig().getString("World-Management."+world+".ID")));
        sender.sendMessage(color(instance.getWConfig().getWorldConfig().getString("World-Management."+world+".author")));
        sender.sendMessage(color(instance.getWConfig().getWorldConfig().getString("World-Management."+world+".uuid")));
        sender.sendMessage(color(instance.getWConfig().getWorldConfig().getString("World-Management."+world+".permission")));
        sender.sendMessage(color(instance.getWConfig().getWorldConfig().getString("World-Management."+world+".time-created")));
        sender.sendMessage(check(instance.getWConfig().getWorldConfig().getBoolean("World-Management."+world+".game-world"), "Game World" ));
        sender.sendMessage(check(instance.getWConfig().getWorldConfig().getBoolean("World-Management."+world+".break"), "Block Breaking"));
        sender.sendMessage(check(instance.getWConfig().getWorldConfig().getBoolean("World-Management."+world+".place"), "Block Placing"));
        sender.sendMessage(check(instance.getWConfig().getWorldConfig().getBoolean("World-Management."+world+".drop"), "Item Dropping"));
        sender.sendMessage(check(instance.getWConfig().getWorldConfig().getBoolean("World-Management."+world+".pickup"), "Item Pickup"));
        sender.sendMessage(check(instance.getWConfig().getWorldConfig().getBoolean("World-Management."+world+".interact"), "Interaction"));
        sender.sendMessage(check(instance.getWConfig().getWorldConfig().getBoolean("World-Management."+world+".explosion"), "Explosion"));
        sender.sendMessage(check(instance.getWConfig().getWorldConfig().getBoolean("World-Management."+world+".chat.Enabled"), "Chat Enabled"));
        sender.sendMessage(check(instance.getWConfig().getWorldConfig().getBoolean("World-Management."+world+".chat.use-format"), "Using Chat Format"));
        sender.sendMessage(check(instance.getWConfig().getWorldConfig().getBoolean("World-Management."+world+".chat.format.enabled"), "Format Enabled"));
        sender.sendMessage(instance.getWConfig().getWorldConfig().getString("World-Management."+world+".chat.format"));
//        sender.sendMessage(color(instance.getWConfig().getWorldConfig().getString("")));
//        sender.sendMessage(color(instance.getWConfig().getWorldConfig().getString("")));

    }




}
