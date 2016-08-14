package Utilities;

import me.ES96.com.Build;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.logging.Level;

public class Warps
{
    private FileConfiguration WarpConfig = null;
    private File WarpConfigFile = null;
    private static final String fileName = "Warps.yml";

    private Build main;

    public Warps(Build instance)
    {
        main = instance;
    }


    public void reloadWarpConfig() {
        if (WarpConfigFile == null) {
            WarpConfigFile = new File(main.getDataFolder(),fileName);
        }
        WarpConfig = YamlConfiguration.loadConfiguration(WarpConfigFile);

        // Look for defaults in the jar
        Reader defConfigStream = null;
        try {
            defConfigStream = new InputStreamReader(main.getResource(fileName), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            WarpConfig.setDefaults(defConfig);
        }
    }

    public FileConfiguration getWarpConfig() {
        if (WarpConfig == null) {
            reloadWarpConfig();
        }
        return WarpConfig;
    }

    public void saveWarpConfig() {
        if (WarpConfig == null || WarpConfigFile == null) {
            return;
        }
        try {
            getWarpConfig().save(WarpConfigFile);
        } catch (IOException ex) {
            main.getLogger().log(Level.SEVERE, "Could not save config to " + WarpConfigFile, ex);
        }
    }

    public void saveDefaultWarpConfig() {
        if (WarpConfigFile == null) {
            WarpConfigFile = new File(main.getDataFolder(), fileName);
        }
        if (!WarpConfigFile.exists()) {
            main.saveResource(fileName, false);
        }
    }
}