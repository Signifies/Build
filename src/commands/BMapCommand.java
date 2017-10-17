package commands;

import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import utilities.BuildPermissions;
import utilities.BuildUtils;
import utilities.Debug;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;

/**
 * Created by ES359 on 9/10/16.
 */
public class BMapCommand extends BuildUtils implements CommandExecutor {

    private Build instance;

    public BMapCommand(Build value) {
        instance = value;
    }


    private String mapMaker = ""; //Will be getting this from Worldmamangement configuration.

    private static ArrayList<String> Maps() {
        ArrayList<String> value = new ArrayList<>();

        for (String text : value) {
            Debug.log(ChatColor.GOLD + text, 0);
        }

        return value;
    }

    void format(Player p) {
        String map = p.getWorld().getName();
        String val = Maps().contains(map) ? ChatColor.translateAlternateColorCodes('&', map + " &f-- &7Author: &6&l" + mapMaker) :
                ChatColor.translateAlternateColorCodes('&', "&cYou aren't in a map - Join a map for info..");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9[&eMap&9]&r &7-> &6&l" + val));
    }

    public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String args[])
    {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.DARK_RED + "Console cannot use /map - Must be (Player)");
            return true;
        }
        Player p = (Player) sender;
        if (!BuildPermissions.BUILD_MAP_COMMAND.checkPermission(p))
        {
            p.sendMessage(color("%prefix% &cYou don't have permission to use /map"));
        } else if (args.length == 0)
        {
            p.sendMessage(color("&7> &f/map &7[add] [remove] [<remove cancel>]"));
        } else if (args.length > 0)
        {
            switch (args[0])
            {
                case "add":
                case "+":
                    if (BuildPermissions.BUILD_MAP_ADD.checkPermission(p)) {
                        //TODO implement add method inside WorldManagement.
                        instance.getWorldMangement().writeWorld(p);
                        p.sendMessage(color("&7> Map added &fsuccessfully&7!"));

                    } else {
                        p.sendMessage(color("&cYou don't have permission to add maps!"));
                    }
                    break;

                case "remove":
                case "-":

                    if (BuildPermissions.BUILD_MAP_REMOVE.checkPermission(p))
                    {

                        BukkitScheduler scheduler = getServer().getScheduler();

                        if(args.length == 1 )
                        {
                            p.sendMessage(color("&7> &f10 &7second delay before map data is &cdeleted&f! &2&l/map cancel&f?"));
                            scheduler.scheduleSyncDelayedTask(instance, new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    instance.getWorldMangement().deleteWorld(p);
                                    p.sendMessage(color("&7> Map data has been &cremoved&7!"));
                                }
                            }, 20*10L);
                        }else if(args[1].equalsIgnoreCase("cancel"))//TODO add cancel check here.
                        {
                            Bukkit.getServer().getScheduler().cancelTasks(instance);
                            p.sendMessage(color("&7You have &4&ncanceled&r &7the removal of the current map&f!"));
                        }
                    }else
                    {
                        p.sendMessage(color("&cYou don't have permission to remove maps."));
                    }
                    break;


            }
        }
        return true;
    }
}


/*
Player player = (Player)sender;
        if(cmd.getName().equalsIgnoreCase("map"))
        {
            if(!player.hasPermission("paccraft.map"))
            {
                player.sendMessage("[PacCraft]" + ChatColor.RED + "You don't have permission for /map. Ask an Admin about permission.");
            }else if(args.length >=0)
            {
                format(player);
            }
        }
 */