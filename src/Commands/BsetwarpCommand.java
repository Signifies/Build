package Commands;

import Utilities.BuildPermissions;
import Utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 8/14/16.
 */
public class BsetwarpCommand extends BuildUtils implements CommandExecutor
{
    private Build main;

    public BsetwarpCommand(Build instance)
    {
        main = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(!(sender instanceof Player))
        {
            sender.sendMessage(color("%prefix% &2Sorry, the console can't set warps."));
            return true;
        }

        Player p =(Player)sender;

        if (cmd.getName().equalsIgnoreCase("setwarp"))
        {
            if(!BuildPermissions.BUILD_SET_WARPS.checkPermission(p))
            {
                p.sendMessage(color("&2Error you don't have permissions for this."));
            }else
            {
                if (args.length == 0) {
                    p.sendMessage(color("&7What warp name do you want to set?"));
                    return true;
                }
                main.getWarps().getWarpConfig().set("warps." + args[0] + ".world", p.getLocation().getWorld().getName());
                main.getWarps().getWarpConfig().set("warps." + args[0] + ".x", p.getLocation().getX());
                main.getWarps().getWarpConfig().set("warps." + args[0] + ".y", p.getLocation().getY());
                main.getWarps().getWarpConfig().set("warps." + args[0] + ".z", p.getLocation().getZ());
                main.getWarps().saveWarpConfig();
                p.sendMessage(color("&7Set the warp, &e" + args[0] + " &7..."));
            }
        }
        return true;
    }

}

