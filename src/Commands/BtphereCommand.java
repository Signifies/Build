package Commands;

import Utilities.BuildPermissions;
import Utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 8/13/16.
 */
public class BtphereCommand extends BuildUtils implements CommandExecutor
{
    Build main;
    public BtphereCommand(Build instance)
    {
        main = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {


        if(sender instanceof Player)
        {
            Player p = (Player)sender;

            if(cmd.getName().equalsIgnoreCase("tphere"))
            {
                if(!BuildPermissions.BUILD_COMMAND_TPHERE.checkPermission(p))
                {
                    p.sendMessage(main.getNoPermission());
                }else
                {
                    if(args.length ==0)
                    {
                        p.sendMessage(color("&7You need to specify a player."));
                    }else if(args.length >0)
                    {
                        String arg = args[0];
                        Player target = Bukkit.getServer().getPlayer(arg);
                        if(target == null)
                        {
                            p.sendMessage(color("&7Error - Unable to find the player, &a" + arg + "&7..."));
                        }else
                        {
                            if(main.getToggle().contains(target.getUniqueId()))
                            {
                                p.sendMessage(color("&a" +target.getName() + "&7 has their teleportation disabled..."));
                            }else if(BuildPermissions.BUILD_BYPASS_TPTOGGLE.checkPermission(p))
                            {
//                                p.teleport(target.getLocation());
                                target.teleport(p.getLocation());
                                p.sendMessage(color("&7Teleporting "+target.getName() + "&7..."));
                                target.sendMessage(color("&7Teleporting..."));
                            }else if(!main.getToggle().contains(target.getUniqueId()))
                            {
                                target.teleport(p.getLocation());
                                p.sendMessage(color("&7Teleporting "+target.getName() + "&7..."));
                                target.sendMessage(color("&7Teleporting..."));
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

}
