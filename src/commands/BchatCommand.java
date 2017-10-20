package commands;

import org.bukkit.World;
import utilities.BuildPermissions;
import utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 8/13/16.
 */
public class BchatCommand extends BuildUtils implements CommandExecutor
{
    Build main;

    public BchatCommand(Build instance)
    {
        main = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(cmd.getName().equalsIgnoreCase("chat") && !(BuildPermissions.BUILD_CHAT.checkPermission(sender)))
        {
            sender.sendMessage(main.getNoPermission());
        }else if(args.length < 1)
        {
            sender.sendMessage(color("&8/chat permissions &b&l--&b&l> &aLists all permissions.\n" +
                    "&8/chat help &8&l--&b&l> &aLists configuration options/help.\n"));
        }else {
            if(args.length > 0)
            {
                switch (args[0].toLowerCase())
                {
                    case "clear":
                    case "c":
                        if (BuildPermissions.BUILD_CHAT_CLEAR.checkPermission(sender))
                        {
                            if(args.length > 1)
                            {
                                World world = Bukkit.getServer().getWorld(args[1]);

                                if(world == null)
                                {
                                    sender.sendMessage(color("&7>&fSorry, the world, &a" + args[1] + "&f does not exist."));
                                }else
                                {
                                    for(Player p : world.getPlayers())
                                    {
                                        for(int i=0; i < 100; i++)
                                        {
                                            p.sendMessage("");

                                        }
                                        p.sendMessage(color("&7> The &fchat&7 has been &fcleared&7."));
                                    }
                                    sender.sendMessage(color("&7> You have cleared the &fchat &7for the world &f " + args[1] +"&7."));
                                }
                            }else
                            {
                                clear();
                            }
                        }
                        //clear();
//                        Bukkit.getServer().broadcastMessage(ChatColor.GOLD +"Chat has been cleared...");
                        break;

                    case "clearself":
                    case "cs":
                        if(!BuildPermissions.BUILD_CHAT_CLEARSELF.checkPermission(sender));
                        else
                            selfClear(sender);
                        break;

                    case "help":
                    case "?":
                        sender.sendMessage(color(""
                                        + "&7Command usage: &e/chat < [mute] <world> <time> || [un-mute] <world> || [clear] <world> ] || [clearself || [reload] || [clearuser] <user> >\n"
                                        + "&7Permissions: &2Build.cmd &2&l|| &7Build.bypass\n"
                        ));
                        break;

                    case "permissions":
                        if(!BuildPermissions.BUILD_CHAT.checkPermission(sender));
                        else
                            sender.sendMessage(color("&7=======[&ePermissions&7]======="));
                        sender.sendMessage(color("&7Build.* &b&l--&b&l> &bGrants all access to the plugin. \n" +
                                "&7Build.reload &8&l--&b&l> &bGrants permission to reload the plugin. \n" +
                                "&7Build.cmd &8&l--&b&l> &bGrants permission for base command. \n" +
                                "&7Build.enable &8&l--&b&l> &bAllows you to enable Chat.\n" +
                                "&7Build.disable &8&l--&b&l> &bAllows you to disable Chat.\n" +
                                "&7Build.bypass &8&l--&b&l> &bAllows you to bypass chat lock.\n" +
                                "&7Build.clear &8&l--&b&l> &bAllows you to clear Global chat.\n" +
                                "&7Build.clearself &b&l--&b&l> &bAllows you to clear your own chat.\n" +
                                "&7Build.permissions &8&l--&b&l> &bAllows you to see permissions.\n" +
                                "&7Build.clearothers &8&l--&b&l> &bAllows you to clear another users chat."));
                        break;

                    case "clearuser":
                    case "cu":
                        if(!BuildPermissions.BUILD_CHAT_CLEAR_OTHERS.checkPermission(sender));
                        else if(args.length == 1)
                            sender.sendMessage(color("&7Command usage is &a&n/chat clearuser <username>"));
                        else if(args.length > 1)
                        {
                            Player target = Bukkit.getServer().getPlayer(args[1]);
                            if(target == null)
                            {
                                sender.sendMessage(color("&7Warning: The user, &a&o" + args[1] + " &7couldn't be found."));
                            }else {
                                clearPlayer(target);
                                sender.sendMessage(color("&7You have &ocleared &7the chat for the user, &a&o" +target.getName() + "&7."));
                            }
                        }
                        break;

                    case "mute":
                        if(BuildPermissions.BUILD_SET_CHAT.checkPermission(sender))
                        {
                            if (args.length > 1)
                            {
                                World world = Bukkit.getServer().getWorld(args[1]);
                                if(world == null)
                                {
                                    sender.sendMessage(color("&7>&fSorry, the world, &a" + args[1] + "&f does not exist."));
                                }else
                                {
                                    main.getWConfig().getWorldConfig().set("World-Management."+ world.getName() + ".chat.Enabled",false);
                                    main.getWConfig().saveWorldConfig();
                                    for(Player u : world.getPlayers())
                                    {
                                        u.sendMessage(color(main.getBConfig().getBuildConfig().getString("Chat.chat-disabled")));
                                    }
                                    sender.sendMessage(color("&7> You have &fmuted&7 the &fchat &7for the world &f " + world.getName() +"&7."));
                                }
                                //get the world
                                //mute chat here.
                            } else if (args.length >= 2)
                            {
                                //Mute chat, in the specific world, using the specific amount of time as the last argument provided.
                            }else if(args.length < 1)
                            {
                                if((sender instanceof Player))
                                {
                                    Player user = (Player)sender;
                                    main.getWConfig().getWorldConfig().set("World-Management."+ user.getWorld().getName() + ".chat.Enabled",false);
                                    main.getWConfig().saveWorldConfig();
                                    user.sendMessage(color("&7> You have &fmuted&7 the &fchat &7for the world &f " +user.getWorld() +"&7."));
                                }else
                                {
                                    sender.sendMessage(color("&7/chat mute <world>"));
                                }
                            }
                        }
                        break;

                    case "unmute":
                        if(BuildPermissions.BUILD_SET_CHAT.checkPermission(sender))
                        {
                            if (args.length > 1)
                            {
                                World world = Bukkit.getServer().getWorld(args[1]);
                                if(world == null)
                                {
                                    sender.sendMessage(color("&7>&fSorry, the world, &a" + args[1] + "&f does not exist."));
                                }else
                                {
                                    main.getWConfig().getWorldConfig().set("World-Management."+ world.getName() + ".chat.Enabled",true);
                                    main.getWConfig().saveWorldConfig();
                                    for(Player u : world.getPlayers())
                                    {
                                        u.sendMessage(color(main.getBConfig().getBuildConfig().getString("Chat.chat-enabled")));
                                    }
                                    sender.sendMessage(color("&7> You have &fun-muted&7 the &fchat &7for the world &f " + world.getName() +"&7."));
                                }
                                //get the world
                                //mute chat here.
                            }else
                            {
                                if((sender instanceof Player))
                                {
                                   Player user = (Player)sender;
                                    main.getWConfig().getWorldConfig().set("World-Management."+ user.getWorld().getName() + ".chat.Enabled",true);
                                    main.getWConfig().saveWorldConfig();
                                    user.sendMessage(color("&7> You have &fun-muted&7 the &fchat &7for the world &f " +user.getWorld().getName() +"&7."));
                                }else
                                {
                                    sender.sendMessage(color("&7/chat mute <world>"));
                                }
                            }
                        }
                        break;
                        default:
                        sender.sendMessage(color("&7Unknown argument, &a" + args[0] + "&7!"));
                }
            }
        }
        return true;
    }
}
