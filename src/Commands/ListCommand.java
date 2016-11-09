package Commands;

import Utilities.BuildPermissions;
import Utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by ES359 on 11/9/16.
 */
public class ListCommand extends BuildUtils
{


    private Build instance;
    public ListCommand(Build main)
    {
        instance = main;
        instance.getCommand("list").setExecutor(instance);
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
