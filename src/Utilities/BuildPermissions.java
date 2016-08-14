package Utilities;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ES359 on 8/13/16.
 */
public enum  BuildPermissions
{
    BUILD_COMMAND("Build.set"),
    BUILD_COMMAND_TP("Build.tp"),
    BUILD_COMMAND_TPHERE("Build.tphere"),
    BUILD_COMMAND_TOGGLE("Build.tptoggle"),
    BUILD_COMMAND_WARP("Build.warp"),
    BUILD_COMMAND_WARP_LIST("Build.warp.list"),
    BUILD_COMMAND_DELWARP("Build.warp.delete"),
    BUILD_COMMAND_WORLD_KICK("Build.world.kick"),
    BUILD_COMMAND_KICK(""),
    BUILD_COMMAND_SETSPAWN("Build.setspawn"),
    BUILD_COMMAND_DELSPAWN("Build.delspawn"),
    BUILD_COMMAND_WHITELIST(""),
    BUILD_COMMAND_WHITELIST_NOTIFY("Build.whitelist.notify"),
    BUILD_COMMAND_STAFFCHAT("Build.staffchat"),
    BUILD_COMMAND_HEAL("Build.heal"),
    BUILD_COMMAND_HELP("Build.help"),

    BUILD_CHAT("Build.chat"),
    BUILD_CHAT_CLEARSELF("Build.chat.clearself"),
    BUILD_CHAT_CLEAR("Build.chat.clear"),
    BUILD_RELOAD("Build.reload"),

    BUILD_SET_BUILD("Build.set.build"),
    BUILD_SET_BREAK("Build.set.break"),
    BUILD_SET_PLACE("Build.set.break"),
    BUILD_SET_INTERACT("Build.set.interact"),
    BUILD_SET_DROP("Build.set.drop"),
    BUILD_SET_PICKUP("Build.set.pickup"),
    BUILD_SET_WARPS("Build.warp.set"),
    BUILD_SET_CHAT("Build.set.chat"),
    BUILD_SET_TNT("Build.set.tnt"),

    BUILD_BYPASS_CHAT("Build.bypass.chat"),
    BUILD_BYPASS_PLACE("Build.bypass.place"),
    BUILD_BYPASS_BREAK("Build.bypass.break"),
    BUILD_BYPASS_INTERACT("Build.bypass.interact"),
    BUILD_BYPASS_DROP("Build.bypass.drop"),
    BUILD_BYPASS_PICKUP("Build.bypass.pickup"),
    BUILD_BYPASS_TPTOGGLE("Build.bypass.toggle"),
    BUILD_BYPASS_STATUS("Build.status"),

    PLUGIN_DONATOR_2 ("plugin.donator.2");

    private String key;

    BuildPermissions(String key) {
        this.key = key;
    }

    public boolean checkPermission(Player p){
        return p.hasPermission(getKey());
    }

    public boolean checkPermission(CommandSender sender)
    {
        return sender.hasPermission(getKey());
    }

    public String getKey() {
        return key;
    }
}

