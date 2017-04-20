package commands;

import utilities.BuildPermissions;
import utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 9/5/16.
 */
public class BspyCommand extends BuildUtils implements CommandExecutor
{
    Build instance;
    public BspyCommand(Build main)
    {
        instance = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(!(sender instanceof Player))
        {
            sender.sendMessage(color("&cThe console doesn't need /spy..."));
            return true;
        }

        Player p =(Player)sender;
        if(cmd.getName().equalsIgnoreCase("socialspy"))
        {

            if(BuildPermissions.BUILD_SPY_COMMAND.checkPermission(p))
            {
                if(args.length < 1)
                {

                }
            }else
            {
                p.sendMessage(instance.getNoPermission());
            }
        }
        return true;
    }
}
