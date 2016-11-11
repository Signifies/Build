package Utilities;

import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.*;

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
//        msg = msg.replace("&", "§");
//        ChatColor.translateAlternateColorCodes('&',msg);
        msg = msg.replace("%prefix%",getPrefix());
        return ChatColor.translateAlternateColorCodes('&',msg);
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
        value.add("&a/build &7warps");
        value.add("");
        value.add("&a/build &7chat [true || false]");
        value.add("&a/build &7<itemdrops> [true || false]");
        value.add("&a/build &7<itempickup> [true || false]");
        return value;
    }
    public ArrayList<String> usage()
    {
        ArrayList<String> value = new ArrayList<>();

        value.add("&7   ----- &bMode Usage &7-----");
        value.add("&7/mode <&c0,1,2,3 &8- &cs,c,a,spec &8- &csurvival,creative,adventure,spectator, build, builder&7>");

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
    public void sendText(ArrayList<String> text, Player sender)
    {
        for(String txt: text)
        {
            txt = txt.replace("%player%",sender.getName());
            sender.sendMessage(color(txt));
        }
    }

    public void sendText(List<String> text, Player sender)
    {
        for(String txt: text)
        {
            txt = txt.replace("%player%",sender.getName());
            txt = txt.replace("%uuid%",sender.getUniqueId().toString());
            txt = txt.replace("%display_name%",sender.getDisplayName());
            txt = txt.replace("%IP%", sender.getAddress().toString());

            sender.sendMessage(color(txt));
        }
    }

    public String getPlugins()
    {

        String value = "";
        for(Plugin plugins : Bukkit.getServer().getPluginManager().getPlugins())
        {
            value = plugins.toString();
        }
        return value;
    }

    public void getAllCommands (CommandSender sender)
    {

       List<String> value = new ArrayList<>();
        for(Plugin plugins : Bukkit.getServer().getPluginManager().getPlugins())
        {
            for (String commands : Bukkit.getServer().getPluginManager().getPlugin(plugins.getName()).getDescription().getCommands().keySet())
            {
                sender.sendMessage(Bukkit.getServer().getPluginManager().getPlugin(plugins.getName()).getDescription().getCommands().get(commands).get("description").toString());
            }
        }
    }



    public void allCommands(CommandSender sender)
    {
        for(Plugin plugins : Bukkit.getServer().getPluginManager().getPlugins())
        {
            Bukkit.getServer().getPluginManager().getPlugin(plugins.getName()).getDescription().getCommands().keySet().forEach(s -> sender.sendMessage(s));
        }
    }


    public void getBuildCommands(Player p)
    {
       Bukkit.getServer().getPluginManager().getPlugin("Build").getDescription().getCommands().keySet().forEach(s -> p.sendMessage(s));
    }

    public void getBuildCommands(CommandSender p)
    {
        Bukkit.getServer().getPluginManager().getPlugin("Build").getDescription().getCommands().keySet().forEach(s -> p.sendMessage(color("&a"+s)));
    }

    public String getStaff(String format)
    {
//        Bukkit.getServer().getOnlinePlayers().forEach(s -> BuildPermissions.BUILD_MANGEMENT.checkPermission(s));
        StringBuilder sb = new StringBuilder();
        for(Player p : Bukkit.getServer().getOnlinePlayers())
        {
            if(BuildPermissions.BUILD_MANGEMENT.checkPermission(p))
            {
                sb.append(p.getName() + ", ");
                Build.staff.add(p.getName());
            }
        }
        if(sb.length() < 1)
            return color(format);
            //return color("&cError: No staff members online. &b&o.-.");
        else
            return sb.toString();
    }


    public void sendText(List<String> text, CommandSender sender)
    {
        for(String txt: text)
        {
            txt = txt.replace("%player%",sender.getName());
            sender.sendMessage(color(txt));
        }
    }

    public void sendText(List<String> text, CommandSender sender, String s)
    {
        int amt = Bukkit.getServer().getOnlinePlayers().size();

        int max = Bukkit.getServer().getMaxPlayers();

        for(String txt: text)
        {
            txt = txt.replace("%online_players%", ""+amt);
            txt = txt.replace("%max_players%", ""+max);
            txt = txt.replace("%player%",sender.getName());
            txt = txt.replace("%staff%",getStaff(s));
            txt =txt.replace("%time%",getStamp().toString());
            sender.sendMessage(color(txt));
        }
    }



    public ArrayList<String> warps(List<String> s)
    {
        ArrayList<String> value = new ArrayList<>();
        value.add("     &f----- &bWarps &f-----");
        value.add("&6"+s);
        value.add("&7------------------");
        return value;
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

    public void clearPlayer(Player p)
    {
        for(int i=0; i < 100; i++)
        {
            p.sendMessage("");
        }
        p.sendMessage(color("&7Your chat has been &7&nCleared&c, by an Admin, &a&n" + p.getName()));
    }

    public void selfClear(CommandSender sender) {
        for(int i=0; i <100; i++) {
            sender.sendMessage("");
        }
        sender.sendMessage( ChatColor.GRAY + "You have cleared your own chat, "+ ChatColor.GREEN +sender.getName());
    }

    public void clear() {
        for(Player p :Bukkit.getServer().getOnlinePlayers())
        {
            for(int i=0; i <100; i ++)
            {
                p.sendMessage("");
            }
        }
        Bukkit.broadcastMessage(color("&7&lThe chat has been &a&lcleared&7&l."));
    }

    public ItemStack createItem(Material mat, int amount, String name) {
        ItemStack is = new ItemStack(mat,amount);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(color(name));
        is.setItemMeta(meta);
        return is;
    }

    Calendar cal = Calendar.getInstance();
    Date now = cal.getTime();
    public java.sql.Timestamp stamp = new java.sql.Timestamp(now.getTime());
    public java.sql.Timestamp getStamp() {
        return stamp;
    }

    public void setArmor(Player p)
    {
        p.getInventory().setHelmet(createItem(Material.CHAINMAIL_HELMET,1,"&cHat"));
        p.getInventory().setChestplate(createItem(Material.CHAINMAIL_CHESTPLATE,1,"&cChestplate"));
        p.getInventory().setLeggings(createItem(Material.CHAINMAIL_LEGGINGS,1,"&cLeggings"));
        p.getInventory().setBoots(createItem(Material.CHAINMAIL_BOOTS,1,"&cBoots"));
    }


    public void clearArmor(Player p)
    {
        p.getInventory().setHelmet(null);
        p.getInventory().setChestplate(null);
        p.getInventory().setLeggings(null);
        p.getInventory().setBoots(null);
    }

}
