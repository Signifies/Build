package commands;

import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utilities.BuildPermissions;
import utilities.BuildUtils;
import utilities.Debug;

/**
 * Created by Evan on 10/21/2017.
 */
public class BEnvironment extends BuildUtils implements CommandExecutor
{


    public Build instance;
    public BEnvironment(Build value)
    {
        instance = value;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {
        if(args.length < 1 && BuildPermissions.BUILD_ENVIRONMENT_COMMAND.checkPermission(sender))
        {
            sender.sendMessage(color("&7/environment <flag> [world] <setting>"));
        }else if(args.length >0)
        {
            switch (args[0].toLowerCase())
            {
                //TODO add a world info command.
                case "weather":
                    if(BuildPermissions.BUILD_ENVIRONMENT_WEATHER.checkPermission(sender))
                    {
                        if(args.length > 1)
                        {

                            World world = Bukkit.getServer().getWorld(args[1]);
                            if(world == null)
                            {
                                sender.sendMessage(color("&7The world &f"+args[1] +"&7 doesn't exist."));
                            }else if(args[2].equalsIgnoreCase("thunder") &&args.length >=3)
                            {
                                boolean setting = Boolean.parseBoolean(args[3]);
                                Debug.log(Debug.LOG + "Logging setting value: " +setting,1);
                                world.setThundering(setting);
                                sender.sendMessage(color("&7You have set &fthunder &7to &f"+setting+"&7."));
                            }else if(args[2].equalsIgnoreCase("rain") && args.length >=3)
                            {
                                boolean duration = Boolean.parseBoolean(args[3]);
//                                Debug.log(Debug.LOG + "Logging setting value: " +setting,1);
                                world.setStorm(duration);
                                sender.sendMessage(color("&7You have set the weather duration to &f"+duration+"&7."));
                            }
                        }
                    }
                    break;

                case "daycycle":
                case "time":
                case "value": //TODO set this one later for per world permant settings.
                    if(BuildPermissions.BUILD_ENVIRONMENT_TIME.checkPermission(sender))
                    {
                        if(args.length > 1)
                        {
                            World world = Bukkit.getServer().getWorld(args[1]);
                            if(world == null)
                            {
                                sender.sendMessage(color("&7The world &f"+args[1] +"&7 doesn't exist."));
                            }else if(args[2].equalsIgnoreCase("day"))
                            {
                                world.setTime(1000);
                                sender.sendMessage(color("&7You have set the &ftime &7to &fday&7."));
                            }else if(args.length > 2)
                            {
                                int value = Integer.parseInt(args[3]);
                                world.setTime(value);
                                sender.sendMessage(color("&7You have set the &ftime &7to &f"+value));
                            }
                        }
                    }
                    break;
                case "info":
                    if(BuildPermissions.BUILD_ENVIRONMENT_INFO.checkPermission(sender))
                    {
                        if(args.length > 1)
                        {
                            World world = Bukkit.getServer().getWorld(args[1]);
                            if(world == null)
                            {
                                sender.sendMessage(color("&7The world &f"+args[1] +"&7 doesn't exist."));
                            }else
                            {
                                //todo later. not essential to gamecore.
                            }
                        }
                    }
                    break;

                case "tp":
                    if(BuildPermissions.BUILD_ENVIRONMENT_TP.checkPermission(sender))
                    {

                        if(sender instanceof Player)
                        {
                            Player p= (Player)sender;
                            if(args.length < 1)
                            {
                                p.sendMessage(color("&7See /environment help"));
                            }else
                            {
                                if(args.length > 1)
                                {
                                    World world = Bukkit.getServer().getWorld(args[1]);
                                    if(world == null)
                                    {
                                        p.sendMessage(color("&7The world &f"+args[1] +"&7 doesn't exist&f."));
                                    }else
                                    {
                                        p.teleport(world.getSpawnLocation());
                                        p.sendMessage(color("&fTeleporting &7to the world &f"+world.getName()+"&7..."));
                                    }
                                }else if(args.length > 2)
                                {
                                    World l = Bukkit.getServer().getWorld(args[1]);
                                    if(l ==null)
                                    {
                                        p.sendMessage(color("&7The world &f"+args[1] +"&7 doesn't exist&f."));
                                    }else
                                    {
                                        Player target = Bukkit.getServer().getPlayer(args[2]);
                                        if(target ==null)
                                        {
                                            p.sendMessage(color("&7The player &f"+args[2] +"&7 doesn't exist&f."));
                                        }else
                                        {
                                            target.teleport(l.getSpawnLocation());
                                            p.sendMessage(color("&7You &fteleported &7the player &f"+target.getName() + "&7 to &f"+l.getName()+"&7."));
                                        }
                                    }
                                }
                            }
                       }else
                        {
                           if(args.length < 1)
                           {
                               sender.sendMessage(color("&7See &f/environment [tp] [world] <player>"));
                           }else if(args.length > 1)
                           {
                               World l = Bukkit.getServer().getWorld(args[1]);
                               if(l==null)
                               {
                                   sender.sendMessage(color("&7The world &f "+args[1]+"&7 doesn't exist&f."));
                               }else if(args.length >2)
                               {
                                   Player target = Bukkit.getServer().getPlayer(args[2]);
                                   if(target ==null)
                                   {
                                       sender.sendMessage(color("&7The player &f "+args[2]+"&7 doesn't exist&f."));
                                   }else
                                   {
                                       target.teleport(l.getSpawnLocation());
                                       target.sendMessage(color("&fTeleporting &7to &f"+l.getName()+"&7."));
                                       sender.sendMessage(color("&fTeleporting &7the player&f "+target.getName()+"&7 to &f"+l.getName()+"&7."));
                                   }
                               }
                           }
                        }
                    }
                    break;

                case "worlds":
                    if(BuildPermissions.BUILD_ENVIRONMENT_WORLDS.checkPermission(sender))
                    {
                        sender.sendMessage(color("&f---- &7List of Worlds &f----"));
//                        sender.sendMessage(color("&7List of worlds: &f") + Bukkit.getServer().getWorlds().toString());
                        for(World l : Bukkit.getServer().getWorlds())
                        {
                            sender.sendMessage(color("&b"+l.getName()));
                        }
                    }
                    break;

                    default:
                        sender.sendMessage(color("&7/environment <flag> [world] <setting>"));
                        sender.sendMessage(color("&7/environment [tp] <player>"));
                        sender.sendMessage(color("&f----- &7Example&f -----"));
                        sender.sendMessage(color("&7/environment time day world 1000"));
                        sender.sendMessage(color("&7Flags: &f<info> <worlds> <daycycle> <weather>"));
                        sender.sendMessage(color("&7Settings: &f<true || false>"));
                        sender.sendMessage(color(""));
                        break;
            }
        }
        return true;
    }
}
