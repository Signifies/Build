package commands;

import events.BuildMode;
import utilities.BuildPermissions;
import utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by ES359 on 8/13/16.
 */
public class BuildCommand extends BuildUtils implements CommandExecutor
{

    private Build main;
    BuildMode build;
    public BuildCommand(Build instance, BuildMode val)
    {
        main = instance;
        build = val;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {
        if (cmd.getName().equalsIgnoreCase("build")) {
            if (!BuildPermissions.BUILD_COMMAND.checkPermission(sender)) {
                sender.sendMessage(color(main.getBConfig().getBuildConfig().getString("no-permission")));
            } else {

//                logCommand(sender, main);

                if (args.length < 1) {
                    //TODO implement Command help menu Array.
                    sendText(commandList(), sender);
                } else if (args.length > 0) {
                    switch (args[0].toLowerCase()) {

                        case "rl":
                        case "reload":
                            if (!BuildPermissions.BUILD_RELOAD.checkPermission(sender)) {
                                sender.sendMessage(color("&7You don't have permission to &areload &7the config!"));
                            } else {
                                main.getBConfig().reloadBuildConfig();
                                sender.sendMessage(color("&7The &aconfig.yml &7has been reloaded."));
                            }
                            break;

                        case "permissions":
                        case "perms":
//                            sendText(PermissionsSettings.permissionList(), sender);
                            break;

                        case "version":
                        case "ver":
                            sender.sendMessage(getPluginVersion(main, sender));
                            break;

                        case "warps":
//                            warpList(main,sender);
                            List<String> list = main.getWarps().getWarpConfig().getStringList("Warp-list");
                            sendText(warps(list),sender);
                            break;

                        case "about":
                            desc(sender, main);
                            break;

                        case "chat":
                            if(BuildPermissions.BUILD_SET_CHAT.checkPermission(sender))
                            {
                                if (args.length > 1) {
                                    boolean value = Boolean.parseBoolean(args[1]);

                                    String msg = color(main.getBConfig().getBuildConfig().getString("Chat.msg"));
                                    msg = msg.replace("%value%", "" + value);
                                    main.getBConfig().getBuildConfig().set("Chat.Enabled", value);
                                    save();
                                    Bukkit.getServer().broadcastMessage(msg);
                                } else {
                                    sender.sendMessage(color("&a/set &7<chat> [true || false]"));
                                }
                            }else {
                                sender.sendMessage(msg("&7You don't have permissions for chat."));
                            }
                            break;

                        case "itemdrops":
                        case "id":
                        case "idrop":
                            if(BuildPermissions.BUILD_SET_DROP.checkPermission(sender))
                            {
                                if (args.length > 1) {
                                    boolean value = Boolean.parseBoolean(args[1]);
//                                String msg = color(main.getBConfig().getBuildConfig().getString("Item-drop.msg"));

                                    main.getBConfig().getBuildConfig().set("Item-drop.Enabled", value);
                                    save();
                                    sender.sendMessage(color("&7You set item drops to &a" + value + "&7."));
                                } else {
                                    sender.sendMessage(color("&a/set &7<itemdrops> [true || false]"));
                                }
                            }else
                            {
                                sender.sendMessage(msg("&7You don't have permissions for drops."));
                            }
                            break;

                        case "itempickup":
                        case "pickup":
                            if(BuildPermissions.BUILD_SET_PICKUP.checkPermission(sender))
                            {
                                if (args.length > 1) {
                                    boolean value = Boolean.parseBoolean(args[1]);
//                                String msg = color(main.getBConfig().getBuildConfig().getString("Item-pickup.msg"));

                                    main.getBConfig().getBuildConfig().set("Item-pickup.Enabled", value);
                                    save();
                                    sender.sendMessage(color("&7You set item pickup to &a" + value + "&7."));
                                } else {
                                    sender.sendMessage(color("&a/set &7<itempickup> [true || false]"));
                                }
                            }else
                            {
                                sender.sendMessage(msg("&7You don't have permissions for item pickup."));
                            }
                            break;
                        case "blockbreak":
                        case "bb":
                        case "blockbreaking":
                            if(BuildPermissions.BUILD_SET_BREAK.checkPermission(sender))
                            {
                                if (args.length > 1) {
                                    boolean value = Boolean.parseBoolean(args[1]);
//                                String msg = color(main.getBConfig().getBuildConfig().getString("Block-break.msg"));

                                    main.getBConfig().getBuildConfig().set("Block-break.Enabled", value);
                                    save();
                                    sender.sendMessage(color("&7You set Block breaking to &a" + value + "&7."));
                                } else {
                                    sender.sendMessage(color("&a/set &7<blockbreaking> [true || false]"));
                                }
                            }else
                            {
                                sender.sendMessage(msg("&7You don't have permissions for Block breaking."));
                            }
                            break;

                        case "blockplace":
                        case "place":
                        case "bp":
                            if(BuildPermissions.BUILD_SET_BUILD.checkPermission(sender))
                            {
                                if (args.length > 1) {
                                    boolean value = Boolean.parseBoolean(args[1]);
                                    String msg = color(main.getBConfig().getBuildConfig().getString("Block-place.msg"));

                                    main.getBConfig().getBuildConfig().set("Block-place.Enabled", value);
                                    save();
                                    sender.sendMessage(color("&7You set Block placing to &a" + value + "&7."));
                                } else {
                                    sender.sendMessage(color("&a/set &7<blockplace> [true || false]"));
                                }
                            }else
                            {
                                sender.sendMessage(msg("&7You don't have permissions for Block placing."));
                            }
                            break;

                        case "interact":
                        case "Interaction":
                        case "it":
                            if(BuildPermissions.BUILD_SET_INTERACT.checkPermission(sender))
                            {
                                if (args.length > 1) {
                                    boolean value = Boolean.parseBoolean(args[1]);
//                                String msg = color(main.getBConfig().getBuildConfig().getString("Interact.msg"));

                                    main.getBConfig().getBuildConfig().set("Interact.Enabled", value);
                                    save();
                                    sender.sendMessage(color("&7You set interaction to &a" + value + "&7."));
                                } else {
                                    sender.sendMessage(color("&a/set &7<interaction> [true || false]"));
                                }
                            }else
                            {
                                sender.sendMessage(msg("&7You don't have permissions for Interaction"));
                            }
                            break;


                        case "explosion":
                        case "tnt-entity":
                        case "tnt":
                            if(BuildPermissions.BUILD_SET_TNT.checkPermission(sender))
                            {
                                if (args.length > 1) {
                                    boolean value = Boolean.parseBoolean(args[1]);
//                                String msg = color(main.getBConfig().getBuildConfig().getString("Interact.msg"));

                                    main.getBConfig().getBuildConfig().set("Explosion.Disabled", value);
                                    save();
                                    sender.sendMessage(color("&7You set tnt to &a" + value + "&7."));
                                } else {
                                    sender.sendMessage(color("&a/set &7<tnt-entity> [true || false]"));
                                }
                            }else
                            {
                                sender.sendMessage(msg("&7You don't have permissions for explosions."));
                            }
                            break;

                        case "status":
                        case "stats":
                            if(BuildPermissions.BUILD_BYPASS_STATUS.checkPermission(sender))
                            {
                                configStats(sender);
                            }else
                            {
                                sender.sendMessage(msg("&7You don't have permissions for status"));
                            }
                            break;

                        case "mode":
                            if(!(sender instanceof Player))
                            {
                                sender.sendMessage(color("%prefix% &cYou cannot use build mode console."));
                            }else
                            {
                                Player builder = (Player)sender;
                                build.setMode(builder);
                            }
                            break;

                        case "?":
                            sender.sendMessage(color("&b---- &7Commands &b----"));
                            getBuildCommands(sender);
                            break;

                        case "pl":
                            if(!BuildPermissions.BUILD_MANGEMENT.checkPermission(sender))
                                return true;
                            sender.sendMessage(color("&b---- &aPlugins &b----"));
                            sender.sendMessage(getPlugins());
                            break;

                        case "allcmds":
                        case  "commands":
                            if(!BuildPermissions.BUILD_MANGEMENT.checkPermission(sender))
                                return true;
                            sender.sendMessage(color("&4---- &7ALL server commands &4----"));
//                            getAllCommands(sender);
                            allCommands(sender);
                        break;

                        default:
                            sender.sendMessage(color("&7You've used incorrect arguments with this command, use &a/set&7."));
                    }
                }
            }
        }
        return true;
    }

    private void configStats(CommandSender sender)
    {
        sender.sendMessage(check(main.getBConfig().getBuildConfig().getBoolean("Chat.Enabled"), "Chat"));
        sender.sendMessage(check(main.getBConfig().getBuildConfig().getBoolean("Item-drop.Enabled"),"Item drops"));
        sender.sendMessage(check(main.getBConfig().getBuildConfig().getBoolean("Item-pickup.Enabled"),"Item pickup"));
        sender.sendMessage(check(main.getBConfig().getBuildConfig().getBoolean("Block-place.Enabled"),"Block Placing"));
        sender.sendMessage(check(main.getBConfig().getBuildConfig().getBoolean("Block-break.Enabled"),"Block Breaking"));
        sender.sendMessage(check(main.getBConfig().getBuildConfig().getBoolean("Explosion.Enabled"),"TNT"));
//   TODO     sender.sendMessage(check(main.getBConfig().getBuildConfig().getBoolean(""),""));
    }

    public void save()
    {
        main.getBConfig().saveBuildConfig();
    }

}
