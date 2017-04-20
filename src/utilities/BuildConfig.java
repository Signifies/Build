package utilities;

import me.ES96.com.Build;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.logging.Level;

public class BuildConfig
{
    private FileConfiguration BuildConfig = null;
    private File BuildConfigFile = null;
    private static final String fileName = "Config.yml";

    private Build main;

    public BuildConfig(Build instance)
    {
        main = instance;
    }


    public void reloadBuildConfig() {
        if (BuildConfigFile == null) {
            BuildConfigFile = new File(main.getDataFolder(),fileName);
        }
        BuildConfig = YamlConfiguration.loadConfiguration(BuildConfigFile);

        // Look for defaults in the jar
        Reader defConfigStream = null;
        try {
            defConfigStream = new InputStreamReader(main.getResource(fileName), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            BuildConfig.setDefaults(defConfig);
        }
    }

    public FileConfiguration getBuildConfig() {
        if (BuildConfig == null) {
            reloadBuildConfig();
        }
        return BuildConfig;
    }

    public void saveBuildConfig() {
        if (BuildConfig == null || BuildConfigFile == null) {
            return;
        }
        try {
            getBuildConfig().save(BuildConfigFile);
        } catch (IOException ex) {
            main.getLogger().log(Level.SEVERE, "Could not save config to " + BuildConfigFile, ex);
        }
    }

    public void saveDefaultBuildConfig() {
        if (BuildConfigFile == null) {
            BuildConfigFile = new File(main.getDataFolder(), fileName);
        }
        if (!BuildConfigFile.exists()) {
            main.saveResource(fileName, false);
        }
    }
}