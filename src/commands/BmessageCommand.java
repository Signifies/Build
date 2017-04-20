package commands;

import utilities.BuildPermissions;
import utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 9/3/16.
 */
public class BmessageCommand extends BuildUtils implements CommandExecutor
{
    Build instance;
    public BmessageCommand(Build main)
    {
        instance = main;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(cmd.getName().equalsIgnoreCase("message"))
        {
            if(!BuildPermissions.BUILD_MESSAGE_COMMAND.checkPermission(sender))
            {
                sender.sendMessage(instance.getNoPermission());
            }else if(args.length < 1)
            {
                sender.sendMessage(color("&7/message <playername> <message>"));
            }else
            {
                Player target = Bukkit.getServer().getPlayer(args[0]);

                if(target == null)
                {
                    sender.sendMessage(color("&7Unable to send a message to " + args[0] + "&7 are they online?"));
                    return true;
                }

                StringBuilder str = new StringBuilder();

                for (String arg : args) {
                    str.append(arg + " ");
                }

                String msg = str.toString().replace(args[0], "");
                String to = instance.getBConfig().getBuildConfig().getString("messaging.format.to");
                to = to.replace("%sender%",sender.getName());
                to = to.replace("%target%", target.getName());
                to = to.replace("%msg%",msg);

                String from = instance.getBConfig().getBuildConfig().getString("messaging.format.from");
                from = from.replace("%sender%",sender.getName());
                from = from.replace("%target%", target.getName());
                from = from.replace("%msg%",msg);


                sender.sendMessage(color(to));
                target.sendMessage(color(from));
            }
        }

        return true;
    }
}
