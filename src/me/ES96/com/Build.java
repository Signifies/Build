package me.ES96.com;

import commands.*;
import events.*;
import SQLAPI.SQL;
import utilities.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by ES359 on 8/13/16.
 */
public class Build extends JavaPlugin
{

    public static boolean DEBUG = true;
    private Warps warp = new Warps(this);
    private BuildConfig conf = new BuildConfig(this);
    private WorldConfig wconf = new WorldConfig(this);
    public PluginDescriptionFile pdfFile = this.getDescription();
    private ArrayList<UUID> toggle = new ArrayList<>();
    private ArrayList<UUID> buildMode = new ArrayList<>();
    private ArrayList<UUID> notify = new ArrayList<>();
    private ArrayList<String> messages = new ArrayList<>();
    private ArrayList<UUID> spy = new ArrayList<>();
    public static ArrayList<String> staff = new ArrayList<>();
    public WorldMangement worldMangement = new WorldMangement(this);


    public Menu menu;
    BuildMode mode;
    private SQL sql;
    public void onEnable()
    {
        conf = new BuildConfig(this);
        mode = new BuildMode(this);

        configuration();
        loadWarps(); //TODO
        loadEvents();
        loadWorlds();
        commands();
        menu = new Menu(this);
//        sql = new SQL(getConfig().getString("Database.host"), getConfig().getString("Database.username"), getConfig().getString("Database.password"), getConfig().getString("Database.database"));
    }

    void configuration()
    {
        Debug.log(Debug.pluginLog() +"&6Loading configuration...",1);
        conf.saveDefaultBuildConfig();
        conf.saveBuildConfig();

    }

    void loadEvents()
    {
        Debug.log(Debug.pluginLog() + "&bLoading events...",1);
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new BuildEvents(this), this);
        pm.registerEvents(new CommandRestrict(this), this);
//        pm.registerEvents(new Invsee(this), this);
    }

    void loadWarps()
    {
        Debug.log(Debug.pluginLog() + "&aLoading Warps...",1);
        warp.saveDefaultWarpConfig();
        warp.saveWarpConfig();
    }

    void loadWorlds()
    {
        Debug.log(Debug.pluginLog() + "&6Loading Worlds...",1);
        wconf.saveDefaultWorldConfig();
        wconf.saveWorldConfig();
    }


    void commands()
    {
        Debug.log(Debug.pluginLog() + "&2Loading commands...",1);
        registerCmd("build", new BuildCommand(this,mode));
        registerCmd("warp",new BwarpCommand(this));
        registerCmd("setwarp", new BsetwarpCommand(this));
        registerCmd("deletewarp", new BdeleteWarpCommand(this));
        registerCmd("setspawn", new BsetspawnCommand(this));
        registerCmd("spawn", new BspawnCommand(this));
        registerCmd("tp", new BtpCommand(this));
        registerCmd("tptoggle",new BtpToggleCommand(this));
        registerCmd("tphere", new BtphereCommand(this));
        registerCmd("chat", new BchatCommand(this));
        registerCmd("kick", new BkickCommand(this));
        registerCmd("help",new BhelpCommand(this));
        registerCmd("item", new BitemCommand(this));
        registerCmd("clearinventory", new BClearCommand());
        registerCmd("cmddisable", new CommandRestrict(this));
        registerCmd("message", new BmessageCommand(this));
        registerCmd("whitelist", new BwhitelistCommand(this));
        registerCmd("mode", new BmodeCommand(this));
        registerCmd("inventory", new BinvseeCommand(this));
//        registerCmd("invsee",new Invsee(this));
        registerCmd("map",new BMapCommand(this));
        registerCmd("list", new ListCommand(this));
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
    public ArrayList<UUID> getToggle()
    {
        return toggle;
    }
    public ArrayList<UUID> getBuildMode()
    {
        return buildMode;
    }
    public BuildMode gettMode()
    {
        return mode;
    }
    public ArrayList<UUID> getNotifications()
    {
        return notify;
    }
    public ArrayList<String> getMessages()
    {
        return messages;
    }
    public ArrayList<UUID> getSpy() {return spy;}
    public ArrayList<String> getStaff()
    {
        return staff;
    }
    BuildUtils u = new BuildUtils();
    public String getNoPermission()
    {
        return u.color(conf.getBuildConfig().getString("no-permission"));
    }
    public String modeMsg()
    {
        return u.color(getBConfig().getBuildConfig().getString("Mode.Build"));
    }
    public WorldConfig getWConfig()
    {
        return wconf;
    }
    public WorldMangement getWorldMangement()
    {
        return worldMangement;
    }
}
