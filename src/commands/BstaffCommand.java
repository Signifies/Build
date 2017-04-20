package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import utilities.BuildUtils;

/**
 * Created by ES359 on 8/13/16.
 */

//TODO Download lyuten decompiler...
public class BstaffCommand extends BuildUtils implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(args.length < 1)
        {
            //Send Help menu/message
        }else
        {

        }
        return true;
    }

}
