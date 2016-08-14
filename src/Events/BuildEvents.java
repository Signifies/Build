package Events;

import Utilities.BuildPermissions;
import Utilities.BuildUtils;
import Utilities.Debug;
import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.UUID;

/**
 * Created by ES359 on 8/13/16.
 */
public class BuildEvents extends BuildUtils implements Listener
{
    Build main;
    public BuildEvents(Build instance)
    {
        main = instance;
    }
    /*
    @EventHandler
    public void WCE(final WeatherChangeEvent event) {
        event.setCancelled(true);
        World world = event.getWorld();
        world.setStorm(false);
        world.setThundering(false);
    }
    */

    @EventHandler
    public void onPlace(BlockPlaceEvent event)
    {

        Player p = event.getPlayer();

        if(!main.getBConfig().getBuildConfig().getBoolean("Block-place.Enabled"))
        {
            if(BuildPermissions.BUILD_BYPASS_PLACE.checkPermission(p))
            {
                event.setCancelled(false);
            }else
            {
                event.setCancelled(true);
                if(main.getBConfig().getBuildConfig().getBoolean("Block-place.use-msg"))
                {
                    p.sendMessage(color(main.getBConfig().getBuildConfig().getString("Block-place.msg")));
                }
            }
        }else
        {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onDist(BlockBreakEvent event)
    {
        Player p = event.getPlayer();

        if(!main.getBConfig().getBuildConfig().getBoolean("Block-break.Enabled"))
        {
            if(BuildPermissions.BUILD_BYPASS_BREAK.checkPermission(p))
            {
                event.setCancelled(false);
            }else
            {
                event.setCancelled(true);
                if(main.getBConfig().getBuildConfig().getBoolean("Block-break.use-msg"))
                {
                    p.sendMessage(color(main.getBConfig().getBuildConfig().getString("Block-break.msg")));
                }
            }
        }else
        {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent event)
    {
        Player p = event.getPlayer();

        if(!main.getBConfig().getBuildConfig().getBoolean("Chat.Enabled"))
        {
            if(BuildPermissions.BUILD_BYPASS_CHAT.checkPermission(p))
            {
                event.setCancelled(false);
            }else
            {
                event.setCancelled(true);
                p.sendMessage(color(main.getBConfig().getBuildConfig().getString("Chat.chat-disabled")));
            }
        }else
        {
            if(main.getBConfig().getBuildConfig().getBoolean("Chat.custom-chat.Enabled"))
            {
                if(BuildPermissions.BUILD_CHAT.checkPermission(p))
                {
                    event.setCancelled(false);
                    String location =  color("&7X:&a"+p.getLocation().getBlockX() +" &7Y&a:" +p.getLocation().getBlockY() + " &7Z&a:" + p.getLocation().getBlockZ() +"&r" );
                    String format = main.getBConfig().getBuildConfig().getString("Chat.custom-chat.Format");
                    format = format.replace("%name%", p.getName());
                    format = format.replace("%msg%", event.getMessage());
                    format = format.replace("%world%", p.getWorld().getName());
                    format = format.replace("%UUID%", p.getUniqueId().toString());
                    format = format.replace("%location%",location);
//                format = format.replaceAll("%IP%", "" + player.getAddress());

                    //TODO: Add vault to get prefix from PermissionsEX...

                    event.setFormat(color(format));
                }else
                {
                    event.setCancelled(true);
                    p.sendMessage(color("&2You don't have permission to speak here. &eMessage an Administrator about permission."));
                }
            }
        }
    }

    @EventHandler
    public void onIntereact(PlayerInteractEvent event)
    {

        Player p = event.getPlayer();

        if(!main.getBConfig().getBuildConfig().getBoolean("Interact.Enabled"))
        {
            if(BuildPermissions.BUILD_BYPASS_INTERACT.checkPermission(p))
            {
                event.setCancelled(false);
            }else
            {
                event.setCancelled(true);
                if(main.getBConfig().getBuildConfig().getBoolean("Interact.use-msg"))
                {
                    p.sendMessage(color(main.getBConfig().getBuildConfig().getString("Interact.msg")));
                }
            }
        }else
        {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void drop(PlayerDropItemEvent event) {
        Player p = event.getPlayer();

        if (!main.getBConfig().getBuildConfig().getBoolean("Item-drop.Enabled")) {
            if (BuildPermissions.BUILD_BYPASS_DROP.checkPermission(p)) {
                event.setCancelled(false);
            } else {
                event.setCancelled(true);
                if (main.getBConfig().getBuildConfig().getBoolean("Item-drop.use-msg")) {
                    p.sendMessage(color(main.getBConfig().getBuildConfig().getString("Item-drop.msg")));
                }
            }
        } else {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onExplosionPrime(ExplosionPrimeEvent event)
    {
        if(event.getEntity() instanceof TNTPrimed)
        {

            if(main.getBConfig().getBuildConfig().getBoolean("Explosion.Enabled"))
            {
                event.setCancelled(false);
            }else
            {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void serverPing(ServerListPingEvent event)
    {
        Debug.log(Debug.pluginLog() + "&9Server Ping event called.");
        String pingmessage = color(main.getBConfig().getBuildConfig().getString("Build.MOTD.server-list"));

        pingmessage = pingmessage.replaceAll("&", "\u00A7");
        pingmessage = pingmessage.replace("#nl", "\n");
//        pingmessage = pingmessage.replace("%staff%",getStaff(main));
//        pingmessage = pingmessage.replace("%users%",getUsers());
//        pingmessage = pingmessage.replace("%time%",getStamp().toString());
        event.setMotd(pingmessage);
        //uitl.logToConsole("TEST: " +event.getAddress());
    }

    public boolean checkWhitelist()
    {
        return Bukkit.getServer().hasWhitelist();
    }


    @EventHandler
    public void onLogin(PlayerLoginEvent event) {

        Debug.log(Debug.pluginLog() + "&aWhitelist event called.");
        Player p = event.getPlayer();
        UUID uuid = p.getUniqueId();
        String config= this.main.getBConfig().getBuildConfig().getString("Whitelist.kick-message");
        config = config.replace("%playername%",p.getName());
        config = config.replace("%uuid%",uuid.toString());
        config = config.replace("#nl", "\n");

        String alert = this.main.getBConfig().getBuildConfig().getString("Whitelist.whitelist-alert");
        alert = alert.replace("%playername%",p.getName());
        alert = alert.replace("%uuid%",uuid.toString());
        alert = alert.replace("#nl", "\n");

        if(checkWhitelist()) {
            if(!p.isWhitelisted()) {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, color(config));
                Bukkit.getServer().getConsoleSender().sendMessage(color(alert));
                for(Player staff : Bukkit.getServer().getOnlinePlayers()){
                    if(BuildPermissions.BUILD_COMMAND_WHITELIST_NOTIFY.checkPermission(staff)) {
                        if(!p.isWhitelisted()){
                            staff.sendMessage(color(alert));
                        }else {
                            return;
                        }
                    }
                }
            }
        }
    }
}
