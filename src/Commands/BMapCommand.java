package Commands;

import Utilities.Debug;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

/**
 * Created by ES359 on 9/10/16.
 */
public class BMapCommand implements CommandExecutor
{


    private String mapMaker = "Sasha_Waters";
    private static ArrayList<String> Maps()
    {
        ArrayList<String> value = new ArrayList<>();
        value.add("Original");
        value.add("Lipstick");
        value.add("Allocation");
        value.add("Crib");
        for(String text : value)
        {
            Debug.log(ChatColor.GOLD + text);
        }

        return value;
    }
    void format (Player p)
    {
        String map = p.getWorld().getName();
        String val = Maps().contains(map) ? ChatColor.translateAlternateColorCodes('&',map + " &f-- &7Author: &6&l" + mapMaker) :
                ChatColor.translateAlternateColorCodes('&',"&cYou aren't in a map - Join a map for info..");
        p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&9[&eMap&9]&r &7-> &6&l" + val));
    }

    public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String args[])
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.DARK_RED + "Console cannot use /map - Must be (Player)");
            return true;
        }
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
        return true;
    }
}
