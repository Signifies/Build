package commands;

import utilities.BuildPermissions;
import utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 8/17/16.
 */
public class BtpToggleCommand extends BuildUtils implements CommandExecutor
{
    Build main;

    public BtpToggleCommand(Build instance)
    {
        main = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(!(sender instanceof Player))
        {
            sender.sendMessage(color("%prefix% &cYou cannot use this."));
        }else
        {
            Player p =(Player)sender;

            if(cmd.getName().equalsIgnoreCase("tptoggle"))
            {
                if(!BuildPermissions.BUILD_COMMAND_TOGGLE.checkPermission(p))
                {
                    p.sendMessage(main.getNoPermission());
                }else if(args.length == 0)
                {

                    if(!main.getToggle().contains(p.getUniqueId()))
                    {
                        main.getToggle().add(p.getUniqueId());
                        p.sendMessage(color("&7You have toggled teleportation &aoff&7."));
                    }else
                    {
                        main.getToggle().remove(p.getUniqueId());
                        p.sendMessage(color("&7You have toggled teleportation &aon&7."));
                    }
                }
            }
        }
        return true;
    }
}
