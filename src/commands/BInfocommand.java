package commands;

import utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by ES359 on 10/5/16.
 */
public class BInfocommand extends BuildUtils implements CommandExecutor
{
    Build instance;

    public BInfocommand(Build main)
    {
        instance = main;
        instance.getCommand(CMD).setExecutor(this);
    }

    private final static String CMD = "data";

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(args.length == 0)
            sender.sendMessage(color("&cYou need to use more arguments..."));
        else if(args.length > 0)
        {
            Player user = Bukkit.getServer().getPlayer(args[0]);
            if(user == null)
            {
                sender.sendMessage(color("&cSorry, offline data transactions aren't implemented yet!"));
                return true;
            }

            UUID uuid = user.getUniqueId();

        }
        return true;
    }

}
