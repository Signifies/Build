package Commands;

import Utilities.BuildPermissions;
import Utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Created by ES359 on 8/14/16.
 */
public class BdeleteWarpCommand extends BuildUtils implements CommandExecutor
{
     Build main;
    public BdeleteWarpCommand(Build instance)
    {
        main = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {
        if (cmd.getName().equalsIgnoreCase("deletewarp")) {
            if(!BuildPermissions.BUILD_COMMAND_DELWARP.checkPermission(sender))
            {
                sender.sendMessage(main.getNoPermission());
            }else
            {
                if (args.length == 0) {
                    sender.sendMessage(color("&7What warp do you want to delete?"));
                    return true;
                }
                if (main.getWarps().getWarpConfig().getConfigurationSection("warps." + args[0]) == null) {
                    sender.sendMessage(color("&2Error &7- The warp, &e" + args[0] + " &7doesn't exist. "));
                    return true;
                }
                String warp = args[0];
                List<String> value = main.getWarps().getWarpConfig().getStringList("Warp-list");

                    value.remove(warp);
                main.getWarps().getWarpConfig().set("Warp-list",value);
                main.getWarps().getWarpConfig().set("warps." + args[0], null);
                main.getWarps().saveWarpConfig();
//                deleteWarp(main, args[0]);
                sender.sendMessage(color("&7Deleted the warp, &e" + args[0] + " &7..."));
            }
        }
        return true;
    }
}
