package Commands;

import Utilities.BuildPermissions;
import Utilities.BuildUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 8/27/16.
 */
public class BClearCommand extends BuildUtils implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(cmd.getName().equalsIgnoreCase("clearinventory") && BuildPermissions.BUILD_COMMAND_CLEAR.checkPermission(sender))
        {
            if(sender instanceof Player)
            {
                Player p =(Player)sender;
                if(args.length < 1)
                {
                    p.sendMessage(color("&7Your inventory has been &ccleared."));
                    p.getInventory().clear();
                }else if(args.length > 0)
                {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if(target == null)
                    {
                        p.sendMessage(color("&cCan't clear an inventory if the player isn't online!"));
                    }else
                    {
                        target.getInventory().clear();
                        target.sendMessage(color("&7Your inventory has been &ccleared."));
                        p.sendMessage(color("&7You have cleared the player, &a" +target.getName() + "'s &7inventory." ));
                    }

                }
            }else
            {
                if(args.length == 0)
                {
                    sender.sendMessage(color("&cYou can't clear your own inventory, " + sender.getName()));
                }else
                {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if(target == null)
                    {
                        sender.sendMessage(color("&cCan't clear an inventory if the player isn't online!"));
                    }else
                    {
                        target.getInventory().clear();
                        target.sendMessage(color("&7Your inventory has been &ccleared."));
                        sender.sendMessage(color("&7You have cleared the player, &a" +target.getName() + "'s &7inventory." ));
                    }
                }
            }
        }else
        {
            sender.sendMessage(color("&cYou don't have permission for /clearinventory"));
        }

        return true;
    }

}
