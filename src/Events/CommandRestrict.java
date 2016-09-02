package Events;

import Utilities.BuildPermissions;
import Utilities.BuildUtils;
import Utilities.Debug;
import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;
import java.util.UUID;

/**
 * Created by ES359 on 8/29/16.
 */
public class CommandRestrict extends BuildUtils implements CommandExecutor, Listener
{
    Build instance;

   public CommandRestrict(Build main)
    {
        instance = main;
    }

    @EventHandler
    public void onCmd(PlayerCommandPreprocessEvent event)
    {
        Debug.log(getPrefix() + " &aCommand pre-process event.");
        Player p = event.getPlayer();
        UUID uuid = p.getUniqueId();

        String denied = this.instance.getBConfig().getBuildConfig().getString("restriction-msg");
        denied = denied.replace("%player%", p.getName());
        String message[] = event.getMessage().split(" ");
        String command = message[0];
        List<String> denyList = this.instance.getBConfig().getBuildConfig().getStringList("blocked-cmds");

       if(denyList.contains(command))
       {
           if(p.isOp() || !BuildPermissions.COMMAND_BYPASS.checkPermission(p))
           {
               event.setCancelled(true);
               p.sendMessage(color(denied));
               for(Player staff : Bukkit.getServer().getOnlinePlayers())
               {
                   if(instance.getNotifications().contains(staff.getUniqueId()))
                   {
                       staff.sendMessage(color("&c&l> &8[&4Warning&8] &c" + p.getName() + " &7used the &4blocked cmd: &c" + command));
                   }
               }
           }
       }else
       {
           event.setCancelled(false);
       }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(!(sender instanceof Player))
        {
            sender.sendMessage(color("%prefix% &cNot for use by the console."));
            return true;
        }

        Player p =(Player)sender;

        if(cmd.getName().equalsIgnoreCase("cmddisable") && !BuildPermissions.COMMAND_BYPASS_TOGGLE.checkPermission(p))
        {
           p.sendMessage(instance.getNoPermission());
        }else if(args.length < 1)
        {
            p.sendMessage(color("&a&l>> &7Command usage: /cmd <[reload] &b&l|| &a[notifications]&f>"));
        }else if(args.length > 0)
        {
            switch (args[0])
            {
                case "notifications":
                case "notify":
                    UUID uuid = p.getUniqueId();
                    if(!instance.getNotifications().contains(uuid))
                    {
                        instance.getNotifications().add(uuid);
                        p.sendMessage(color("&7You are now receiving command &anotifications"));
                    }else
                    {
                        instance.getNotifications().remove(uuid);
                        p.sendMessage(color("&7You are no longer receiving command &anotifications"));
                    }
                    break;
                default:
                    p.sendMessage(color("&7Unknown argument..."));
                    p.sendMessage(color("&7Command usage: /cmd <&b&l|| &a[notifications]&f>"));
            }
        }

        return true;
    }

}
