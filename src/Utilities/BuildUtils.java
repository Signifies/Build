package Utilities;

import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by ES359 on 8/13/16.
 */
public class BuildUtils
{
    /**
     * Plugin prefix.
     */
    public static String prefix = ChatColor.translateAlternateColorCodes('&',"&2Build &7->");

    @Deprecated
    protected String permission = color(getPrefix() + "&eSorry, but you are not able to use this command.");

    String author = "9c5dd792-dcb3-443b-ac6c-605903231eb2";

    public boolean checkAuthor(UUID uuid)
    {
        return uuid.toString().equals(author);
    }

    public void displayAuthInfo(Player p)
    {
        if(checkAuthor(p.getUniqueId()))
        {
            p.sendMessage(color("&a&l&oHello, &7"+ p.getUniqueId().toString() +"\n &aThis server is using ") + getPrefix());
        }
    }

    /**
     *  Displays plugin description Information.
     *
     * @param sender
     * @param
     *
     */
    public void desc(CommandSender sender, Build main)
    {
        sender.sendMessage(color("&2========== " + getPrefix().replace(":","") + "&2=========="));
        sender.sendMessage(color("&7[&9" + main.pdfFile.getName() + "&7] &6Created by, &b&l" +main.pdfFile.getAuthors()+"&6."));
        sender.sendMessage(color("&2" + main.pdfFile.getDescription() + "&2."));
        sender.sendMessage(color("&bWebsite: &e&l" + main.pdfFile.getWebsite()));
        sender.sendMessage(color("     &6&l>>>&2&l===============&6&l<<<\t"));
    }

    /**
     *  Display help screen to CMS.
     *
     * @param sender
     * @param title
     * @param command
     * @param info
     */
    public void displayHelp(CommandSender sender, String title, String command, String info)
    {
        sender.sendMessage(color(title));
        sender.sendMessage("");
        sender.sendMessage(color(command));
        sender.sendMessage("");
        sender.sendMessage(color(info));
    }

    /**
     *  Displays help screen to Player
     *
     * @param player
     * @param title
     * @param body
     * @param information
     */
    public void displayHelpMsg(Player player, String title, String body, String information)
    {
        player.sendMessage(color(title));
        player.sendMessage("");
        player.sendMessage(color(body));
        player.sendMessage(" ");
        player.sendMessage(color(information));
    }


    /**
     * Gets the set plugin prefix.
     *
     * @return
     */
    public String getPrefix()
    {
        return this.prefix;
    }


    /**
     *  A method of controlling exception messages in the console.
     *
     * @param e
     */
    public void exceptionDebug(Exception e)
    {
        if(Build.DEBUG)
        {
            log("&cERROR: &3" +e.getMessage());
            e.printStackTrace();
        }else
        {
            log("&cERROR: &3" +e.getMessage());
        }
    }

    /**
     * Method that handles all the color formatting
     *
     * @param message
     * @return
     */
    public String color(String message) {
        String msg =  message;
        msg = msg.replace("&", "§");
        msg = msg.replace("%prefix%",getPrefix());
        return msg;
    }

    public String msg(String format)
    {
        return color(format);
    }


    public String check(boolean value, String name)
    {
        return  value ? name +ChatColor.GREEN+" [Enabled]"  : name + ChatColor.RED +" [Disabled]";
    }

    public ArrayList<String> commandList()
    {
        ArrayList<String> value = new ArrayList<String>();

        value.add("     &f----- &7Build Commands &f-----");
        value.add("&a/build &7<section> [true || false]");
        value.add("&a/build &7status");
        value.add("&a/build &7perms");
        value.add("&a/build &7about");
        value.add("&a/build &7reload");
        value.add("");
        value.add("&a/build &7chat [true || false]");
        value.add("&a/build &7<itemdrops> [true || false]");
        value.add("&a/build &7<itempickup> [true || false]");
        return value;
    }

    public void sendText(ArrayList<String> text, CommandSender sender)
    {
        for(String txt: text)
        {
            txt = txt.replace("%player%",sender.getName());
            sender.sendMessage(color(txt));
        }
    }

    public String getPluginVersion(Build main, CommandSender sender)
    {
        return color("&fHello, &a&n"+sender.getName() +".&r\nYou are currently running version &b&n"+main.pdfFile.getVersion() + "&r of &e&n"+main.pdfFile.getName() +"&r\n \n&6Your server is running version &c&n"+ main.getServer().getBukkitVersion());
    }

    /**
     *  Logging message to the console.
     *
     * @param msg
     */
    public void log(String msg)
    {
        Bukkit.getServer().getConsoleSender().sendMessage(color("&c&l[LOG]&f " + msg));
    }
}
