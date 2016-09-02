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
public class BkickCommand extends BuildUtils implements CommandExecutor
{
    Build main;
    public BkickCommand(Build instance)
    {
        main = instance;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(cmd.getName().equalsIgnoreCase("kick"))
        {
            if(!BuildPermissions.BUILD_COMMAND_KICK.checkPermission(sender))
            {
                sender.sendMessage(main.getNoPermission());
            }else
            {
                if(args.length < 1)
                {
                    sender.sendMessage(color("&7You need to add a player..."));
                }else if(args.length > 0)
                {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target== null)
                    {
                        sender.sendMessage(color("&7Error - Unable to find the player &a"+args[0]));
                    }else
                    {
                        if(args.length > 1)
                        {
                            StringBuilder str = new StringBuilder();

                            for (String arg : args) {
                                str.append(arg + " ");
                            }
                            String msg = str.toString().replace(args[0], "");
                            String format = main.getBConfig().getBuildConfig().getString("kick-format");

                            format = format.replace("%staff%",sender.getName());
                            format = format.replace("%player%", target.getName());
                            format = format.replace("%msg%",msg);

                            target.kickPlayer(color(format));
                            Bukkit.broadcastMessage(color(format));
                        }else
                        {
                            Bukkit.broadcastMessage(color("&a" + target.getName() +"&7 has been kicked."));
                            target.kickPlayer(color("&7You have been kicked from the &cserver."));
                        }
                    }
                }
            }
        }

        return true;
    }

}
