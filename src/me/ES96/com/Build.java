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

    private static ImplementedAPI implementedAPI;

    private Warps warp = new Warps(this);
    private BuildConfig conf = new BuildConfig(this);
    private UUIDConfig uuidConfig = new UUIDConfig(this);
    private WorldConfig wconf = new WorldConfig(this);
    public PluginDescriptionFile pdfFile = this.getDescription();
    private ArrayList<UUID> toggle = new ArrayList<>();
    private ArrayList<UUID> buildMode = new ArrayList<>();
    private ArrayList<UUID> notify = new ArrayList<>();
    private ArrayList<String> messages = new ArrayList<>();
    private ArrayList<UUID> spy = new ArrayList<>();
    public static ArrayList<String> staff = new ArrayList<>();
    private WorldMangement worldMangement = new WorldMangement(this);
    private UserManager userManager = new UserManager(this);


    public Menu menu;
    BuildMode mode;
    private SQL sql;

    public void onEnable()
    {

        conf = new BuildConfig(this);
        mode = new BuildMode(this);

        configuration();
        loadUUIDs();
        loadWarps();
        loadEvents();
        loadWorlds();
        commands();
//        checkDependencies();

        try {
            implementedAPI = ImplementedAPI.boot(this);
        }catch (Exception e)
        {
            Debug.log(Debug.FAILED_ACTION+"Exception message: "+e.getMessage() ,1);
            Debug.log(Debug.LOG+"Exception:  "+e.getStackTrace().toString(),1);
        }

        menu = new Menu(this);
//        sql = new SQL(getConfig().getString("Database.host"), getConfig().getString("Database.username"), getConfig().getString("Database.password"), getConfig().getString("Database.database"));
//        getBConfig().getBuildConfig().getString("Database.host"), getBConfig().getBuildConfig().getString("Database.username"), getBConfig().getBuildConfig().getString("Database.password"), getBConfig().getBuildConfig().getString("Database.database");

    }

    void configuration()
    {
        Debug.log(Debug.pluginLog() +"&6Loading configuration...",1);
        conf.saveDefaultBuildConfig();
        conf.saveBuildConfig();
    }

    void loadUUIDs()
    {
        Debug.log(Debug.pluginLog()+"&b&nLoading UUID's...",1);
        uuidConfig.saveDefaultUUIDConfig();
        uuidConfig.saveUUIDConfig();
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
       // registerCmd("inventory", new BinvseeCommand(this));
        registerCmd("invsee",new Invsee(this));
        registerCmd("map",new BMapCommand(this));
        registerCmd("list", new ListCommand(this));
        registerCmd("environment", new BEnvironment(this));
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
    public boolean isSQLEnabled()
    {
        return getBConfig().getBuildConfig().getBoolean("Database.enabled");
    }

    public UUIDConfig getUuidConfig()
    {
        return uuidConfig;
    }

    public UserManager getUserManager()
    {
        return userManager;
    }


    public void onDisable()
    {
        if(implementedAPI !=null)
        {
           try {
               Debug.log(Debug.LOG+"&4Shutting down API....",1);
               implementedAPI.trash();
           }catch (IllegalAccessException e)
           {
               Debug.log(Debug.SEVERE+"API was already trashed.",1);
           }catch (Exception e)
           {
               Debug.log(Debug.SEVERE + "Error while trashing API.",1);
           }
        }
    }

    public boolean pexLoaded()
    {
        return Bukkit.getServer().getPluginManager().getPlugin("PermissionsEx").isEnabled();
    }

    public boolean vaultLoaded()
    {
        return Bukkit.getServer().getPluginManager().getPlugin("Vault").isEnabled();
    }

    boolean usingVault()
    {
        return getBConfig().getBuildConfig().getBoolean("dependencies.vault");
    }

    boolean usingPEX()
    {
        return getBConfig().getBuildConfig().getBoolean("dependencies.PermissionsEx");
    }
    private void checkDependencies()
    {
        if(!pexLoaded() && !usingPEX())
        {
            //Add an instance thingy here maybe? or just a message
            Debug.log(Debug.FAILED_ACTION + "&4PermissionsEx failed to load or is disabled in build config!",1);
            return;
        }

        if(!vaultLoaded() && !usingVault())
        {
            Debug.log(Debug.FAILED_ACTION + "&4Vault failed to load or is disabled in build config!",1);
            return;
        }
    }

}
