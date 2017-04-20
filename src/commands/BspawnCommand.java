package commands;

import utilities.BuildPermissions;
import utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
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
            sender.sendMessage(color("%prefix% &cConsole cannot go to spawn!"));
            return true;
        }

        Player p =(Player)sender;


        if (cmd.getName().equalsIgnoreCase("spawn")) {
           if(!BuildPermissions.BUILD_COMMAND_SPAWN.checkPermission(p))
           {
               p.sendMessage(main.getNoPermission());
           }else
           {
               if (main.getWarps().getWarpConfig().getConfigurationSection("spawn") == null) {
                   p.sendMessage(color("&7Error, the spawn hasn't been &cset&7!"));
                   return true;
               }
               World w = Bukkit.getServer().getWorld(main.getWarps().getWarpConfig().getString("spawn.world"));
               double x = main.getWarps().getWarpConfig().getDouble("spawn.x");
               double y = main.getWarps().getWarpConfig().getDouble("spawn.y");
               double z = main.getWarps().getWarpConfig().getDouble("spawn.z");
               p.teleport(new Location(w, x, y, z));
               p.sendMessage(color("&7Welcome to &aSpawn."));
               return true;
           }
        }

        return true;
    }

}
