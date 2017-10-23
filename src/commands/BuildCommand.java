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
                        case "helpbook":
                        case "book":
                        case "guide":
                            if((sender instanceof Player))
                           {
                               Player staff = (Player)sender;
                               if(BuildPermissions.BUILD_HELP_BOOK.checkPermission(staff))
                               {
                                   staff.getPlayer().getInventory().addItem(helpBook(main,staff));
                               }else
                               {
                                   staff.sendMessage(color("&fBuild.helpbook &7permission is required to receive help book&f!"));
                               }
                           }else
                            {
                                sender.sendMessage(color("&cSorry, the console can't read!"));
                            }
                           break;


                        default:
                            sender.sendMessage(color("&7You've used incorrect arguments with this command, use &a/build&7."));
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
