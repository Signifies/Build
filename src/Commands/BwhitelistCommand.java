package Commands;

import Utilities.BuildPermissions;
import Utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by ES359 on 9/1/16.
 */
public class BwhitelistCommand extends BuildUtils implements CommandExecutor
{

    Build instance;

    public BwhitelistCommand(Build main)
    {
        instance = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(cmd.getName().equalsIgnoreCase("whitelist"))
        {
            if(!BuildPermissions.BUILD_COMMAND_WHITELIST.checkPermission(sender))
            {
                sender.sendMessage(instance.getNoPermission());
            }else
            {
                if(args.length < 1)
                {
                    sender.sendMessage(color("&7> &f/whitelist &7<[add] [remove] [list] [on] [off]>"));
                }else
                {
                    switch (args[0].toLowerCase())
                    {
                        case "list":
                            sender.sendMessage(color("&7    ----- &aWhitelisted Players &7-----"));
                            sender.sendMessage(color("&a" + Bukkit.getServer().getWhitelistedPlayers().toString()));
                            break;
                        case "on":
                        case "enabled":
                            Bukkit.getServer().setWhitelist(true);
                            sender.sendMessage(color("&7You have turned on the &awhitelist&7."));
                            break;
                        case "off":
                        case "disabled":
                            Bukkit.getServer().setWhitelist(false);
                            sender.sendMessage(color("&7You have turned off the &awhitelist&7."));
                            break;

                        case "add":
                            if(args.length >0)
                            {
                                OfflinePlayer target = Bukkit.getServer().getPlayer(args[0]);
                                Bukkit.getServer().getWhitelistedPlayers().add(target);
                                sender.sendMessage(color("&7The player, &a"+ target.getName() + "&7 has been added to the whitelist."));
                            }else
                            {
                                sender.sendMessage(color("&7You have used incorrect arguments."));
                            }
                            break;
                        case "remove":
                        case "rm":

                            if(args.length >0)
                            {
                                OfflinePlayer target = Bukkit.getServer().getPlayer(args[0]);
                                Bukkit.getServer().getWhitelistedPlayers().remove(target);
                                sender.sendMessage(color("&7The player, &a"+ target.getName() + "&7 has been removed to the whitelist."));
                            }else
                            {
                                sender.sendMessage(color("&7You have used incorrect arguments."));
                            }
                            break;

                        default:
                            sender.sendMessage(color("&7/whitelist"));
                    }
                }
            }
        }

        return true;
    }
}
