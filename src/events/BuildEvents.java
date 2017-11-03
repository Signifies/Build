package events;

import me.ES96.com.PlayerAttributes;
import me.ES96.com.PlayerState;
import me.ES96.com.State;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import utilities.BuildPermissions;
import utilities.BuildUtils;
import utilities.Data;
import utilities.Debug;
import me.ES96.com.Build;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created by ES359 on 8/13/16.
 */
public class BuildEvents extends BuildUtils implements Listener
{
    Build main;
    private Data data;
    public BuildEvents(Build instance)
    {
        main = instance;
    }


    @EventHandler
    public void onPlace(BlockPlaceEvent event)
    {

        Player p = event.getPlayer();

        boolean placement = main.getWConfig().getWorldConfig().getBoolean("World-Management." +p.getWorld().getName() + ".place");
        boolean mode = main.gettMode().isInBuildMode(p.getUniqueId());

        if(placement)
        {
            if(mode)
            {
                event.setCancelled(false);
//                p.sendMessage(inform);
            }else
            {
                event.setCancelled(true);
                p.sendMessage(main.modeMsg());
            }
        }else if(BuildPermissions.BUILD_BYPASS_PLACE.checkPermission(p))
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
    }

    @EventHandler
    public void onDist(BlockBreakEvent event)
    {
        Player p = event.getPlayer();

        boolean breaking = main.getWConfig().getWorldConfig().getBoolean("World-Management."+p.getWorld().getName() + ".break");
        boolean mode = main.gettMode().isInBuildMode(p.getUniqueId());

        if(breaking)
        {
            if(mode)
            {
                event.setCancelled(false);
//                p.sendMessage(inform);
            }else
            {
                event.setCancelled(true);
                p.sendMessage(main.modeMsg());
            }
        }else if(BuildPermissions.BUILD_BYPASS_BREAK.checkPermission(p))
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

    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent event)
    {
        Player p = event.getPlayer();


        boolean chat = main.getWConfig().getWorldConfig().getBoolean("World-Management."+p.getWorld().getName() + ".chat.Enabled") ;
//        boolean chat = main.getBConfig().getBuildConfig().getBoolean("Chat.Enabled"); //TODO remove later. use this one ^
        boolean perm = BuildPermissions.BUILD_CHAT.checkPermission(p);

        if(chat)
        {
            if(perm)
            {
                event.setCancelled(false);
            }else
            {
                event.setCancelled(true);
                p.sendMessage(color(main.getBConfig().getBuildConfig().getString("Chat.no-permission")));
            }
        }else if(BuildPermissions.BUILD_BYPASS_CHAT.checkPermission(p))
        {
            event.setCancelled(false);
        }else
        {
            event.setCancelled(true);
            p.sendMessage(color(main.getBConfig().getBuildConfig().getString("Chat.chat-disabled")));
            return;
        }

        String location =  color("&7X:&a"+p.getLocation().getBlockX() +" &7Y&a:" +p.getLocation().getBlockY() + " &7Z&a:" + p.getLocation().getBlockZ() +"&r" );

        String message = event.getMessage();

        if(BuildPermissions.BUILD_CHAT_COLOR.checkPermission(p))
        {
            message = message.replace("&","§");
        }

        if(BuildPermissions.BUILD_CHAT_WORLD.checkPermission(p))
        {
            message = message.replace("#world", p.getWorld().getName());
        }

        if(BuildPermissions.BUILD_CHAT_EXP.checkPermission(p))
        {
            message = message.replace("#exp", ""+p.getExpToLevel());
        }

        if(BuildPermissions.BUILD_CHAT_LOCATION.checkPermission(p))
        {
            message = message.replace("#location", location);
            message = message.replace("#loc", location);
        }

        if(BuildPermissions.BUILD_CHAT_TP.checkPermission(p))
        {
            if(message.contains("#tp"))
            {
                message = message.replace("#tp", "");
//                message = message.replace("#tp", "¯\\_ツ_/¯");
                TextComponent m = new TextComponent("Click to teleport to "+p.getName());

                m.setColor(ChatColor.GREEN);

                String format = color("&bTeleports to player when clicked!");

                m.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(format).create()));

                m.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " +p.getName()));

                for(Player player : Bukkit.getServer().getOnlinePlayers())
                {
                    player.spigot().sendMessage(m);
                }

            }
        }

        if(message.contains("@" +p.getName()))
        {
            for(Player users : event.getRecipients())
            {
                message = message.replace("@"+p.getName(), color(" @&b&n"+p.getName()+"&r "));
                users.playSound(p.getLocation(), Sound.NOTE_PLING, 10.0f, 10.0f);

                String f = color(main.getBConfig().getBuildConfig().getString("mention-format"));
                f = f.replace("%player%",p.getName());
                users.sendMessage(f);
            }
        }

        if(!main.getWConfig().getWorldConfig().getBoolean("World-Management."+p.getWorld().getName() + ".chat.use-format")) return;
        String format = main.getWConfig().getWorldConfig().getString("World-Management." +p.getWorld().getName() + ".chat.format");

        format = format.replace("%name%", p.getName());
        format = format.replace("%msg%", message);
        format = format.replace("%world%", p.getWorld().getName());
        format = format.replace("%UUID%", p.getUniqueId().toString());
        format = format.replace("%location%",location);
//                format = format.replaceAll("%IP%", "" + player.getAddress());

        if(main.getBConfig().getBuildConfig().getBoolean("Chat.per-world"))
        {
            event.getRecipients().clear();
            for(Player plrs : event.getPlayer().getWorld().getPlayers())
            {
                event.getRecipients().add(plrs);
            }
        }

        event.setFormat(color(format));
    }

    @EventHandler
    public void onDamage(final EntityDamageByEntityEvent paramEntityDamageByEntityEvent) {
        if (paramEntityDamageByEntityEvent.getDamager() instanceof Player && paramEntityDamageByEntityEvent.getEntity() instanceof Player) {
            final Player localPlayer = (Player)paramEntityDamageByEntityEvent.getDamager();
            if (localPlayer.getGameMode() == GameMode.CREATIVE || main.gettMode().isInBuildMode(localPlayer.getUniqueId())) { //TODO fix so build mode applys too.
                paramEntityDamageByEntityEvent.setCancelled(true);
                localPlayer.sendMessage("§6§l(!)§r§c You are not allowed to pvp whilst in creative");
            }
        }
    }

    @EventHandler
    public void onIntereact(PlayerInteractEvent event)
    {

        Player p = event.getPlayer();

        boolean interact = main.getWConfig().getWorldConfig().getBoolean("World-Management." + p.getWorld().getName()+ ".interact") ;
        boolean mode = main.gettMode().isInBuildMode(p.getUniqueId());

        if(interact)
        {
            if(mode)
            {
                event.setCancelled(false);
//                p.sendMessage(inform);
            }else
            {
                event.setCancelled(true);
                p.sendMessage(main.modeMsg());
            }
        }else if(BuildPermissions.BUILD_BYPASS_INTERACT.checkPermission(p))
        {
            event.setCancelled(false);
        }else
        {
            event.setCancelled(true);
            if(main.getWConfig().getWorldConfig().getBoolean("Interact.use-msg"))
            {
                p.sendMessage(color(main.getBConfig().getBuildConfig().getString("Interact.msg")));
            }
        }

    }

    @EventHandler
    public void drop(PlayerDropItemEvent event) {
        Player p = event.getPlayer();

        boolean drop = main.getWConfig().getWorldConfig().getBoolean("World-Management." + p.getWorld().getName() + ".drop");
        boolean mode = main.gettMode().isInBuildMode(p.getUniqueId());
        //p.getItemInHand().getItemMeta().getDisplayName().contains(color(main.getBConfig().getBuildConfig().getString("help-book.name"))) idfuckingk
        if (drop) {
            if (mode) {
                event.setCancelled(false);
//                p.sendMessage(inform);
            } else {
                event.setCancelled(true);
                p.sendMessage(main.modeMsg());
            }
        } else if (BuildPermissions.BUILD_BYPASS_DROP.checkPermission(p)) {
           event.setCancelled(false);
        } else {
            event.setCancelled(true);
            if (main.getBConfig().getBuildConfig().getBoolean("Item-drop.use-msg")) {
                p.sendMessage(color(main.getBConfig().getBuildConfig().getString("Item-drop.msg")));
            }
        }
    }

    @EventHandler
    public void pickup(PlayerPickupItemEvent event)
    {
        Player p = event.getPlayer();

        boolean pickup = main.getWConfig().getWorldConfig().getBoolean("World-Management." +p.getWorld().getName() + ".pickup");
        boolean mode = main.gettMode().isInBuildMode(p.getUniqueId());

        if (pickup) {
            if (mode) {
                event.setCancelled(false);
//                p.sendMessage(inform);
            } else {
                event.setCancelled(true);
                if(main.getBConfig().getBuildConfig().getBoolean("Item-pickup.use-msg"))
                {
                    p.sendMessage(main.modeMsg());
                }
            }
        } else if (BuildPermissions.BUILD_BYPASS_PICKUP.checkPermission(p)) {
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
            if (main.getBConfig().getBuildConfig().getBoolean("Item-pickup.use-msg")) {
                p.sendMessage(color(main.getBConfig().getBuildConfig().getString("Item-pickup.msg")));
            }
        }

    }


    @EventHandler
    public void onExplosionPrime(ExplosionPrimeEvent event)
    {

        if(event.getEntity() instanceof TNTPrimed)
        {

            if(main.getWConfig().getWorldConfig().getBoolean("World-Management." +event.getEntity().getWorld().getName() + ".explosion"))
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
        Debug.log(Debug.pluginLog() + "&9Server Ping event called.",0);
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
    public void join(PlayerJoinEvent event)
    {
        Player p = event.getPlayer();
       //TODO STATE
        PlayerState state = new PlayerState();
        state.setPlayerState(p,State.DEFAULT);
        event.setJoinMessage(null);
        String format = main.getBConfig().getBuildConfig().getString("Messages.join");
        format = format.replace("{player}", p.getName());
        format = format.replace("{display_name}",p.getDisplayName());
        format = format.replace("{uuid}",p.getUniqueId().toString());
        Bukkit.getServer().broadcastMessage(color(format));
        List<String> motd = main.getBConfig().getBuildConfig().getStringList("Build.MOTD.motd");
        sendText(motd,p);

//        data = new Data(new File("plugins/Build/PlayerData/" + p.getUniqueId() + ".json"),p);

    }

    @EventHandler
    public void quit(PlayerQuitEvent event)
    {
        Player p = event.getPlayer();
        event.setQuitMessage(null);
        String format = main.getBConfig().getBuildConfig().getString("Messages.quit");
        format = format.replace("{player}", p.getName());
        format = format.replace("{display_name}",p.getDisplayName());
        format = format.replace("{uuid}",p.getUniqueId().toString());
        Bukkit.getServer().broadcastMessage(color(format));
    }


    @EventHandler
    public void preLogin(AsyncPlayerPreLoginEvent event)
    {
        main.getUserManager().set(event.getUniqueId().toString(), event.getName());
        main.getUserManager().getStoredUUIDs().forcePut(event.getUniqueId(),event.getName());
    }


    @EventHandler
    public void onLogin(PlayerLoginEvent event) {

        Debug.log(Debug.pluginLog() + "&aWhitelist event called.",0);
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
