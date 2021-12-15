
 package utilities;

import com.avaje.ebean.validation.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.ES96.com.Build;

import java.util.ArrayList;
import java.util.UUID;

 /**
 * Created by ES359 on 7/28/16.
 */
public class Debug
{
    static public String FAILED_ACTION = "[FAILED ACTION] ";
    static public String ACTION = "[ACTION] ";
    static public String LOG = "[LOG] ";
    static public String SEVERE = "[SEVERE] &c";
    static public String PREFIX = BuildUtils.prefix;
    private final static String VERSION = "1.4.0";
    public static boolean DEBUG = true; //Create constructor initilized inside main method?? Or variable access?
    private static int priorityLevel = 0;       //using static?

    static public String pluginLog()
    {
        return LOG+ ""+ PREFIX;
    }

   static String color(String s)
    {
        return ChatColor.translateAlternateColorCodes('&',s);
    }


    static public void log(String msg, int level)
    {
        if(priority(level))
        {
           Bukkit.getServer().getConsoleSender().sendMessage(color(msg));
        }
    }

    static public void log(int level, Player p, String msg)
    {
        if(priority(level))
        {
            p.sendMessage(color(msg));
        }
    }

    static public void log(String msg, int priority) {
       if(DEBUG || priority > 0) {
          System.out.println(LOG +"Priority: "+priority+" "+ msg);
       }
    }


    /* //TODO comeback to this later.
    case : //Add an if statement to check certain aspects
            //of a monitor or medium priority.
            //Maybe check instance of player??
    return false;
    break;
    */



    /**
     * Simple debug flag enabler.
     * @param sender
     * @param args
     */
    static public void debugToggle(CommandSender sender, String[] args)
    {
        if(args.length > 1 && args[0].equalsIgnoreCase("debug"))
        {

            /**
             * REPLACE MAINCLASS WITH YOUR MAINCLASS.
             *
             * EXAMPLE: <MAINCLASS>.DEBUG.
             *
             * Make sure to add:
             * static public DEBUG = false; // To your main class.
             *
             *
             */

//            setValue(Boolean.parseBoolean(args[1]));
//            sender.sendMessage(color("[&4DEBUG&f] &c--> &7You have set Debug status to &4&l: " + getValue()));
        }
    }

    private static ArrayList<String> devList()
    {

        ArrayList<String> value = new ArrayList<>();
        //ES
        value.add("9c5dd792-dcb3-443b-ac6c-605903231eb2");
        value.add("347a17b6-5851-46c2-8747-5576443a311a");
        for(String text : value)
        {
            Debug.log(ChatColor.GOLD + text,1);
        }

        return value;
    }

    static public boolean checkAuth(UUID user)
    {
//        Debug.log("[LOG] &cCurrently logging the user, " + user.toString());
        return devList().contains(user.toString());
    }


    /**
     *  Informs author that plugin is being used by server.
     *
     * @param p
     */
    static  public void displayAuthInfo(Player p, String version)
    {
        if(checkAuth(p.getUniqueId()))
        {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a&l&oHello, &7"+ p.getName() +"\n &aThis server is using " +PREFIX + " ") + version);
        }
    }

}
