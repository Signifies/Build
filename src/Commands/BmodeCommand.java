package Commands;

import Utilities.BuildPermissions;
import Utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 9/4/16.
 */
public class BmodeCommand extends BuildUtils implements CommandExecutor
{
    Build instance;
    public BmodeCommand(Build main)
    {
        instance = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {
        if(cmd.getName().equalsIgnoreCase("mode"))
        {
            if(BuildPermissions.BUILD_MODE.checkPermission(sender))
            {
                if(sender instanceof Player)
                {
                    Player p = (Player)sender;
                    if(args.length < 1)
                    {
                        p.sendMessage(color("&7Usage: /mode &7<&amode&7>"));
                    }else if(args.length > 0)
                    {
                        switch (args[0].toLowerCase())
                        {
                            case "survival":
                            case "0":
                            case "s":
                                if(!BuildPermissions.BUILD_MODE_SURVIVAL.checkPermission(p))
                                {
                                    p.sendMessage(color("&7You don't have permission for &6survival&7."));
                                }else
                                {
                                    if(args.length > 1)
                                    {

                                        if(BuildPermissions.BUILD_MODE_OTHERS.checkPermission(p))
                                        {
                                            Player t = Bukkit.getPlayer(args[1]);
                                            if(t == null) {p.sendMessage(color("&cError - The player, &f" + args[1] + "&c is null!"));return true;}
                                            t.setGameMode(GameMode.SURVIVAL);
                                            p.sendMessage(color("&7You set &f"+t.getName() + "'s &7gamemode to &6" +t.getGameMode().toString().toLowerCase() + "&7."));
                                        }else
                                        {
                                            p.sendMessage(color(instance.getBConfig().getBuildConfig().getString("Mode.set-others")));
                                        }
                                    }else
                                    {
                                        p.setGameMode(GameMode.SURVIVAL);
                                        p.sendMessage(color("&7Your gamemode has been set to, &6" + p.getGameMode().toString().toLowerCase() + "&7."));
                                    }
                                }
                                break;
                            case "creative":
                            case "1":
                            case "c":
                                if(!BuildPermissions.BUILD_MODE_CREATIVE.checkPermission(p))
                                {
                                    p.sendMessage(color("&7You don't have permission for &6creative."));
                                }else
                                {
                                    if(args.length > 1)
                                    {
                                        if(BuildPermissions.BUILD_MODE_OTHERS.checkPermission(p))
                                        {
                                            Player t = Bukkit.getPlayer(args[1]);
                                            if(t == null) {p.sendMessage(color("&cError - The player, &f" + args[1] + "&c is null!"));return true;}
                                            t.setGameMode(GameMode.CREATIVE);
                                            p.sendMessage(color("&7You set &f"+t.getName() + "'s &7gamemode to &6" +t.getGameMode().toString().toLowerCase() + "&7."));
                                        }else
                                        {
                                            p.sendMessage(color(instance.getBConfig().getBuildConfig().getString("Mode.set-others")));
                                        }
                                    }else
                                    {
                                        p.setGameMode(GameMode.CREATIVE);
                                        p.sendMessage(color("&7Your gamemode has been set to, &6" + p.getGameMode().toString().toLowerCase() + "&7."));
                                    }
                                }
                                break;

                            case "adventure":
                            case "2":
                            case "a":
                                if(!BuildPermissions.BUILD_MODE_ADVENTURE.checkPermission(p))
                                {
                                    p.sendMessage(color("&7You don't have permission for &6adventure"));
                                }else
                                {
                                    if(args.length > 1)
                                    {
                                        if(BuildPermissions.BUILD_MODE_OTHERS.checkPermission(p))
                                        {
                                            Player t = Bukkit.getPlayer(args[1]);
                                            if(t == null) {p.sendMessage(color("&cError - The player, &f" + args[1] + "&c is null!"));return true;}
                                            t.setGameMode(GameMode.ADVENTURE);
                                            p.sendMessage(color("&7You set &f"+t.getName() + "'s &7gamemode to &6" +t.getGameMode().toString().toLowerCase() + "&7."));
                                        }else
                                        {
                                            p.sendMessage(color(instance.getBConfig().getBuildConfig().getString("Mode.set-others")));
                                        }
                                    }else
                                    {
                                        p.setGameMode(GameMode.ADVENTURE);
                                        p.sendMessage(color("&7Your gamemode has been set to, &6" + p.getGameMode().toString().toLowerCase()));
                                    }
                                }
                                break;

                            case "spectator":
                            case "3":
                            case "spec":
                                if(!BuildPermissions.BUILD_MODE_SPECTATOR.checkPermission(p))
                                {
                                    p.sendMessage(color("&7You don't have permission for &6spectator"));
                                }
                                break;

                            case "build":
                                if(args.length > 1)
                                {
                                    if(BuildPermissions.BUILD_MODE_OTHERS.checkPermission(p))
                                    {
                                        Player t = Bukkit.getPlayer(args[1]);
                                        if(t == null) {p.sendMessage(color("&cError - The player, &f" + args[1] + "&c is null!"));return true;}
                                        instance.gettMode().setBuildMode(t);
                                    }else
                                    {
                                        p.sendMessage(color(instance.getBConfig().getBuildConfig().getString("Mode.set-others")));
                                    }
                                }else
                                {
                                    instance.gettMode().setBuildMode(p);
                                }
                                break;

                            case "builder":
                                if(args.length > 1)
                                {
                                    if(BuildPermissions.BUILD_MODE_OTHERS.checkPermission(p))
                                    {
                                        Player t = Bukkit.getPlayer(args[1]);
                                        if(t == null) {p.sendMessage(color("&cError - The player, &f" + args[1] + "&c is null!"));return true;}
                                        instance.gettMode().setMode(t);
                                    }else
                                    {
                                        p.sendMessage(color(instance.getBConfig().getBuildConfig().getString("Mode.set-others")));
                                    }
                                }else
                                {
                                    instance.gettMode().setMode(p);
                                }
                                break;

                            case  "users":
                                if(BuildPermissions.BUILD_MODE_USERS.checkPermission(p))
                                {
                                    p.sendMessage(color("&a" + instance.gettMode().builders()));
                                }else
                                {
                                    p.sendMessage(instance.getNoPermission());
                                }
                                break;

                            case "usage":
                                sendText(usage(),p);
                                break;

                            default:
                                p.sendMessage(color("&7Unknown arguments have been passed. See usage."));
                        }
                    }
                }else
                {
                    if(args.length < 1)
                    {
                        sender.sendMessage(color("&7Usage: /mode &7<&amode&7>"));
                    }else if(args.length >0)
                    {
                        switch (args[0].toLowerCase())
                        {
                            case "survival":
                            case "0":
                            case "s":
                                if(args.length > 1)
                                {
                                    Player t = Bukkit.getPlayer(args[1]);
                                    if(t == null) {sender.sendMessage(color("&cError - The player, &f" + args[1] + "&c is null!"));return true;}
                                    t.setGameMode(GameMode.SURVIVAL);
                                    sender.sendMessage(color("&7You set &f"+t.getName() + "'s &7gamemode to &6" +t.getGameMode().toString().toLowerCase() + "&7."));
                                }else
                                {
                                    sender.sendMessage(use());
                                }
                                break;
                            case "creative":
                            case "1":
                            case "c":
                                if(args.length > 1)
                                {
                                    Player t = Bukkit.getPlayer(args[1]);
                                    if(t == null) {sender.sendMessage(color("&cError - The player, &f" + args[1] + "&c is null!"));return true;}
                                    t.setGameMode(GameMode.CREATIVE);
                                    sender.sendMessage(color("&7You set &f"+t.getName() + "'s &7gamemode to &6" +t.getGameMode().toString().toLowerCase() + "&7."));
                                }else
                                {
                                    sender.sendMessage(use());
                                }
                                break;

                            case "adventure":
                            case "2":
                            case "a":
                                if(args.length > 1)
                                {
                                    Player t = Bukkit.getPlayer(args[1]);
                                    if(t == null) {sender.sendMessage(color("&cError - The player, &f" + args[1] + "&c is null!"));return true;}
                                    t.setGameMode(GameMode.ADVENTURE);
                                    sender.sendMessage(color("&7You set &f"+t.getName() + "'s &7gamemode to &6" +t.getGameMode().toString().toLowerCase() + "&7."));
                                }else
                                {
                                    sender.sendMessage(use());
                                }
                                break;

                            case "spectator":
                            case "3":
                            case "spec":
                                if(args.length > 1)
                                {
                                    Player t = Bukkit.getPlayer(args[1]);
                                    if(t == null) {sender.sendMessage(color("&cError - The player, &f" + args[1] + "&c is null!"));return true;}
                                    t.setGameMode(GameMode.SPECTATOR);
                                    sender.sendMessage(color("&7You set &f"+t.getName() + "'s &7gamemode to &6" +t.getGameMode().toString().toLowerCase() + "&7."));
                                }else
                                {
                                    sender.sendMessage(use());
                                }
                                break;

                            case "build":
                                if(args.length > 1)
                                {
                                    Player t = Bukkit.getPlayer(args[1]);
                                    if(t == null) {sender.sendMessage(color("&cError - The player, &f" + args[1] + "&c is null!"));return true;}
                                    instance.gettMode().setBuildMode(t);
                                }else
                                {
                                    sender.sendMessage(use());
                                }
                                break;

                            case "builder":
                                if(args.length > 1)
                                {
                                    Player t = Bukkit.getPlayer(args[1]);
                                    if(t == null) {sender.sendMessage(color("&cError - The player, &f" + args[1] + "&c is null!"));return true;}
                                    instance.gettMode().setMode(t);
                                }else
                                {
                                    sender.sendMessage(use());
                                }
                                break;


                            case "users":
                                sender.sendMessage(color("&a" + instance.gettMode().builders()));
                                break;

                            case "usage":
                                sendText(usage(),sender);
                                break;

                            default:
                                sender.sendMessage(color("&7Unknown arguments have been passed. See usage."));
                        }
                    }
                }
            }else
            {
                sender.sendMessage(instance.getNoPermission());
            }
        }
        return true;
    }

    String use()
    {
        return color("&7/mode &7<&cmode&7> <&fplayername&7>");
    }

}
