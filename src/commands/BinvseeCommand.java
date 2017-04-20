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
 * Created by ES359 on 9/7/16.
 */
public class BinvseeCommand extends BuildUtils implements CommandExecutor
{
    Build instance;
    public BinvseeCommand(Build main)
    {
        instance = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage(color("&7This hasn't been implemented for console yet."));
            return true;
        }

        Player p =(Player)sender;
        if(cmd.getName().equalsIgnoreCase("inventory"))
        {
            if(!BuildPermissions.BUILD_INVSEE.checkPermission(p))
            {
                p.sendMessage(instance.getNoPermission());
            }else
            {
                if(args.length < 1)
                {
                    /// TODO: 9/7/16  Implement usage message.
                    p.sendMessage(color("&7/invsee &a<type> &f<player>"));
                }else if(args.length > 0)
                {
                    switch (args[0].toLowerCase())
                    {
                        case "usage":
                        case "help":

                            break;

                        case "inventory":
                        case "inv":
                        case "iv":
                            if(args.length > 1)
                            {
                                Player target = Bukkit.getServer().getPlayer(args[1]);
                                if(target == null)
                                {
                                    p.sendMessage(color("&7Error, unable to open inventory for player, &a" + args[1] + "&7."));
                                    return true;
                                }else
                                {
//                                    Inventory t  = Bukkit.getServer().createInventory(target,target.getInventory().getSize(), color("&a&l"+target.getName()));
//                                    t.setContents(target.getInventory().getContents());
                                    p.openInventory(target.getInventory());
                                    p.sendMessage(color("&7Opening the inventory for, &a" + target.getName() + "&7."));
                                }
                            }else
                            {
                                p.openInventory(p.getInventory());
                                p.sendMessage(color("&7Opening inventory for: &f" +p.getName() + "&7."));
                            }
                            break;

                        case "enderchest":
                        case "ender":
                        case "ec":
                        case "echest":
                            if(args.length > 1)
                            {
                                Player target = Bukkit.getServer().getPlayer(args[1]);
                                if(target == null)
                                {
                                    p.sendMessage(color("&7Error, unable to open Enderchest for player, &a" + args[1] + "&7."));
                                    return true;
                                }else
                                {
//                                    Inventory t  = Bukkit.getServer().createInventory(target.getInventory().getHolder(),target.getInventory().getSize(), color("&a&l"+target.getName()));
                                    p.openInventory(target.getEnderChest());
                                    p.sendMessage(color("&7Opening the Ender Chest for, &a" + target.getName() + "&7."));
                                }
                            }else
                            {
                                p.openInventory(p.getEnderChest());
                                p.sendMessage(color("&7Opening Ender Chest for: &f" +p.getName() + "&7."));
                            }
                            break;

                        default:
                            p.sendMessage(color("&7Unknown parameters " +args[0] + "."));

                    }
                }
            }
        }

        return true;
    }
}
