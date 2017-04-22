package utilities;

import me.ES96.com.Build;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.logging.Level;

/**
 * Created by Coffee on 4/20/17.
 */
public class WorldConfig extends BuildUtils {

    private FileConfiguration WorldConfig = null;
    private File WorldConfigFile = null;
    private static final String fileName = "Worlds.yml";

    private Build main;

    public WorldConfig(Build instance)
    {
        main = instance;
    }


    public void reloadWorldConfig() {
        if (WorldConfigFile == null) {
            WorldConfigFile = new File(main.getDataFolder(),fileName);
        }
        WorldConfig = YamlConfiguration.loadConfiguration(WorldConfigFile);

        // Look for defaults in the jar
        Reader defConfigStream = null;
        try {
            defConfigStream = new InputStreamReader(main.getResource(fileName), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            WorldConfig.setDefaults(defConfig);
        }
    }

    public FileConfiguration getWorldConfig() {
        if (WorldConfig == null) {
            reloadWorldConfig();
        }
        return WorldConfig;
    }

    public void saveWorldConfig() {
        if (WorldConfig == null || WorldConfigFile == null) {
            return;
        }
        try {
            getWorldConfig().save(WorldConfigFile);
        } catch (IOException ex) {
            main.getLogger().log(Level.SEVERE, "Could not save config to " + WorldConfigFile, ex);
        }
    }

    public void saveDefaultWorldConfig() {
        if (WorldConfigFile == null) {
            WorldConfigFile = new File(main.getDataFolder(), fileName);
        }
        if (!WorldConfigFile.exists()) {
            main.saveResource(fileName, false);
        }
    }

}
