 /**
 * MIT License
 Copyright (c) [2016] [ES96]
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */


 package Utilities;

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
    static public String pluginLog()
    {
        return LOG+ ""+ PREFIX;
    }

   static String color(String s)
    {
        return ChatColor.translateAlternateColorCodes('&',s);
    }

    static public void debugEnabled()
    {
        if(Build.DEBUG)
        {
            System.out.println(Debug.LOG + " Debugging is enabled...");
        }else
        {
            System.out.println(Debug.LOG +"Debugging is " + Build.DEBUG);
        }
    }

    static public void log(String message)
    {
        if(Build.DEBUG)
        {
            Bukkit.getServer().getConsoleSender().sendMessage(color(message));
        }
    }

    static public void log(Player p, String message)
    {
        if(Build.DEBUG)
        {
            p.sendMessage(color(message));
        }
    }

    static public void log(CommandSender p, String message)
    {
        if(Build.DEBUG)
        {
            p.sendMessage(color(message));
        }
    }


    public static  boolean getValue()
    {
        return Build.DEBUG;
    }

    public static void setValue(boolean val)
    {
        Build.DEBUG = val;
    }

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

            setValue(Boolean.parseBoolean(args[1]));
            sender.sendMessage(color("[&4DEBUG&f] &c--> &7You have set Debug status to &4&l: " + getValue()));
        }
    }

    private static ArrayList<String> devList()
    {

        ArrayList<String> value = new ArrayList<>();
        //ES
        value.add("9c5dd792-dcb3-443b-ac6c-605903231eb2");
        for(String text : value)
        {
            Debug.log(ChatColor.GOLD + text);
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