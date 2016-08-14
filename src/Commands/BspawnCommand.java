package Commands;

import Utilities.BuildPermissions;
import Utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 8/13/16.
 */
public class BspawnCommand extends BuildUtils implements CommandExecutor
{
    Build main;
    public BspawnCommand(Build instance)
    {
        main = instance;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(!(sender instanceof Player))
        {
            sender.sendMessage(color("%prefix% &2Error the console can't use."));
            return true;
        }

        Player p=(Player)sender;

        if (cmd.getName().equalsIgnoreCase("setspawn")) {
            if(!BuildPermissions.BUILD_COMMAND_SETSPAWN.checkPermission(p))
            {
                p.sendMessage(color("&2Error - you do not have permission for this."));
            }else
            {
                main.getWarps().getWarpConfig().set("spawn.world", p.getLocation().getWorld().getName());
                main.getWarps().getWarpConfig().set("spawn.x", p.getLocation().getX());
                main.getWarps().getWarpConfig().set("spawn.y", p.getLocation().getY());
                main.getWarps().getWarpConfig().set("spawn.z", p.getLocation().getZ());
                main.getWarps().saveWarpConfig();
                p.sendMessage(color("&7Spawn has been &aset."));
                return true;
            }
        }
        return true;
    }
}
