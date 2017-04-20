package commands;

import utilities.BuildPermissions;
import utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by ES359 on 11/9/16.
 */
public class ListCommand extends BuildUtils implements CommandExecutor
{


    private Build instance;
    public ListCommand(Build main)
    {
        instance = main;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(BuildPermissions.BUILD_MANAGEMENT_LIST.checkPermission(sender))
        {

            if(args.length < 1)
            {
                sendText(instance.getBConfig().getBuildConfig().getStringList("Build.list.format"),sender,instance.getBConfig().getBuildConfig().getString("Build.list.messages"));
            }

        }else
        {
            sender.sendMessage(instance.getNoPermission());
        }

        return true;
    }
}
