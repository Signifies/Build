package me.ES96.com;

import Commands.BuildCommand;
import Commands.BwarpCommand;
import Events.BuildEvents;
import Utilities.BuildConfig;
import Utilities.BuildPermissions;
import Utilities.Debug;
import Utilities.Warps;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ES359 on 8/13/16.
 */
public class Build extends JavaPlugin
{

    public static boolean DEBUG = true;
    private Warps warp = new Warps(this);
    private BuildConfig conf = new BuildConfig(this);
    public PluginDescriptionFile pdfFile = this.getDescription();
    public void onEnable()
    {
        conf = new BuildConfig(this);
        configuration();
//        loadWarps(); TODO
        loadEvents();
        commands();

    }

    void configuration()
    {
        Debug.log(Debug.pluginLog() +"&6Loading configuration...");
        conf.saveDefaultBuildConfig();
        conf.saveBuildConfig();

    }

    void loadEvents()
    {
        Debug.log(Debug.pluginLog() + "&bLoading Events...");
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new BuildEvents(this), this);
    }

    void loadWarps()
    {
        Debug.log(Debug.pluginLog() + "&aLoading Warps...");
        warp.saveDefaultWarpConfig();
        warp.saveWarpConfig();
    }


    void commands()
    {
        Debug.log(Debug.pluginLog() + "&2Loading commands...");
        registerCmd("build", new BuildCommand(this));
        registerCmd("warp",new BwarpCommand(this));
    }

    private void registerCmd(String command, CommandExecutor commandExecutor) {
        Bukkit.getServer().getPluginCommand(command).setExecutor(commandExecutor);
    }

    public Warps getWarps()
    {
        return warp;
    }

    public BuildConfig getBConfig()
    {
        return conf;
    }


}
