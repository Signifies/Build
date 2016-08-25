package Commands;

import Utilities.BuildPermissions;
import Utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by ES359 on 8/13/16.
 */
public class BwarpCommand extends BuildUtils implements CommandExecutor
{
    private Build main;
    public BwarpCommand(Build instance)
    {
        main = instance;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(!(sender instanceof Player))
        {
            sender.sendMessage(color("%prefix% &2Sorry, the console cannot use this command."));
            return true;
        }

        Player p =(Player)sender;

        if(cmd.getName().equalsIgnoreCase("warp"))
        {
            if(!BuildPermissions.BUILD_COMMAND_WARP.checkPermission(p))
            {
                p.sendMessage(main.getNoPermission());
//                p.sendMessage(color("&2Error you don't have permissions for this."));
            }else
            {
                if (args.length == 0)
                {
                    p.sendMessage(color("&7Where do you need to warp to?"));
                }else
                {
                    if(args[0].equalsIgnoreCase("list"))
                    {
                        if(BuildPermissions.BUILD_COMMAND_WARP_LIST.checkPermission(p))
                        {
                            List<String> list = main.getWarps().getWarpConfig().getStringList("Warp-list");
                            sendText(warps(list),p);
//                        p.sendMessage(color("&aTesting to see if this is working.."));
//                        warpList(main,p);
                        }else
                        {
                            p.sendMessage(color("&2Error - &7You don't have permission to list warps."));
                        }
                    }else
                    {
                        if(main.getWarps().getWarpConfig().getConfigurationSection("warps." + args[0]) == null)
                        {
                            p.sendMessage(color("&2Error &7- The warp, &e" + args[0] + " &7doesn't exist. "));
                        }else
                        {
                            World w = Bukkit.getServer().getWorld(main.getWarps().getWarpConfig().getString("warps." + args[0] + ".world"));
                            double x = main.getWarps().getWarpConfig().getDouble("warps." + args[0] + ".x");
                            double y = main.getWarps().getWarpConfig().getDouble("warps." + args[0] + ".y");
                            double z = main.getWarps().getWarpConfig().getDouble("warps." + args[0] + ".z");
                            p.teleport(new Location(w, x, y, z));
                            p.sendMessage(color("&7Warping to &6" + args[0] + "&7..."));
                        }
                    }
                }
            }
        }

        return true;
    }
}
