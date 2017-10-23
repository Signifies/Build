package utilities;

import org.bukkit.ChatColor;
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
    BUILD_COMMAND_KICK("Build.command.kick"),
    BUILD_COMMAND_SETSPAWN("Build.setspawn"),
    BUILD_COMMAND_SPAWN("Build.spawn"),
    BUILD_ENVIRONMENT_COMMAND("Build.command.environment"),
    BUILD_ENVIRONMENT_WEATHER("Build.command.environment.weather"),
    BUILD_ENVIRONMENT_TIME("Build.command.environment.time"),
    BUILD_ENVIRONMENT_INFO("Build.command.environment.info"),
    BUILD_ENVIRONMENT_WORLDS("Build.command.environment.worlds"),
    BUILD_ENVIRONMENT_TP("Build.command.environment.tp"),
    BUILD_COMMAND_DELSPAWN("Build.delspawn"),
    BUILD_COMMAND_WHITELIST("Build.whitelist"),
    BUILD_COMMAND_WHITELIST_NOTIFY("Build.whitelist.notify"),
    BUILD_COMMAND_STAFFCHAT("Build.staffchat"),
    BUILD_COMMAND_HEAL("Build.heal"),
    BUILD_COMMAND_HELP("Build.help"),
    BUILD_HELP_BOOK("Build.helpbook"),
    BUILD_INVSEE("Build.invsee"),
    BUILD_INVSEE_OTHERS("Build.invsee.others"),
    BUILD_TOGGLE("Build.toggle.buildmode"),
    BUILD_MAP_INFO("Build.map.info"),
    BUILD("Build."),


    BUILD_TOGGLE_NORMAL("Build.toggle"),
    BUILD_MESSAGE_COMMAND("Build.message"),
    BUILD_COMMAND_CLEAR("Build.ci"),
    BUILD_MODE("Build.mode.set"),
    BUILD_MODE_OTHERS("Build.mode.set.others"),
    BUILD_MODE_SURVIVAL("Build.mode.survival"),
    BUILD_MODE_CREATIVE("Build.mode.survival"),
    BUILD_MODE_ADVENTURE("Build.mode.adventure"),
    BUILD_MODE_SPECTATOR("Build.mode.spectator"),
    BUILD_MODE_USERS("Build.users"),
    COMMAND_BYPASS_TOGGLE("Build.command.bypass.toggle"),
    BUILD_ITEM_COMMAND("Build.item"),

    BUILD_MANAGEMENT_LIST("Build.list"),

    COMMAND_BYPASS("Build.bypass.command"),
    BUILD_SPY_COMMAND("Build.socialspy"),
    BUILD_CHAT("Build.chat"),
    BUILD_CHAT_CLEARSELF("Build.chat.clearself"),
    BUILD_CHAT_CLEAR("Build.chat.clear"),
    BUILD_CHAT_PERMS("Build.perms"),
    BUILD_CHAT_CLEAR_OTHERS("Build.chat.clearothers"),

    BUILD_CHAT_COLOR("Build.chat.color"),
    BUILD_CHAT_WORLD("Build.chat.world"),
    BUILD_CHAT_EXP("Build.chat.exp"),
    BUILD_CHAT_LOCATION("Build.chat.location"),
    BUILD_CHAT_TP("Build.chat.tp"),
    BUILD_SET_CHAT("Build.set.chat"),
    BUILD_SET_TNT("Build.set.tnt"),

    BUILD_RELOAD("Build.reload"),

    BUILD_MAP_COMMAND("Build.map"),
    BUILD_MAP_ADD("Build.map.add"),
    BUILD_MAP_REMOVE("Build.map.remove"),

    BUILD_SET_BUILD("Build.set.build"),
    BUILD_SET_BREAK("Build.set.break"),
    BUILD_SET_PLACE("Build.set.place"),
    BUILD_SET_INTERACT("Build.set.interact"),
    BUILD_SET_DROP("Build.set.drop"),
    BUILD_SET_PICKUP("Build.set.pickup"),
    BUILD_SET_WARPS("Build.warp.set"),


    BUILD_BYPASS_CHAT("Build.bypass.chat"),
    BUILD_BYPASS_PLACE("Build.bypass.place"),
    BUILD_BYPASS_BREAK("Build.bypass.break"),
    BUILD_BYPASS_INTERACT("Build.bypass.interact"),
    BUILD_BYPASS_DROP("Build.bypass.drop"),
    BUILD_BYPASS_PICKUP("Build.bypass.pickup"),
    BUILD_BYPASS_TPTOGGLE("Build.bypass.toggle"),
    BUILD_BYPASS_STATUS("Build.status"),
    BUILD_MANGEMENT("Build.manger"),

    PLUGIN_DONATOR_2 ("plugin.donator.2");

    private String key;

     BuildPermissions(String key) {
        this.key = key;
    }

    public boolean checkPermission(Player p){
//       Debug.log(check(p),2);
        return p.hasPermission(getKey());
    }

    public boolean checkPermission(CommandSender sender)
    {
//       Debug.log(check(sender),2);
        return sender.hasPermission(getKey());
    }

    public String getKey() {
        return key;
    }



    public String check(Player p)
    {
        String s = p.hasPermission(getKey()) ? "" : BuildUtils.prefix +"" +ChatColor.RED +"Error, " + p.getName() + " does not have the permission: " + getKey(); //TODO Bug with checking permissions.

        return s;
    }
    public String check(CommandSender p)
    {
        String s = p.hasPermission(getKey()) ? "" : BuildUtils.prefix +"" +ChatColor.RED +"Error, " + p.getName() + " does not have the permission: " + getKey();
        return  s;
    }

}

