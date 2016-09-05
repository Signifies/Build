package Events;

import Utilities.BuildPermissions;
import Utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 * Created by ES359 on 8/19/16.
 */
public class BuildMode extends BuildUtils
{

    Build build;
    public BuildMode(Build instance) { build = instance;}


    public boolean isInBuildMode(UUID uuid)
    {
        return build.getBuildMode().contains(uuid);
    }


    public String builders()
    {
       String b = "";
        for(UUID s : build.getBuildMode())
        {
            b = s.toString();
        }
        return b;
    }

    public void clearUsers()
    {
        build.getBuildMode().clear();
    }

    public void setBuildMode(Player p)
    {
        if(BuildPermissions.BUILD_TOGGLE_NORMAL.checkPermission(p) && !p.getWorld().getName().equalsIgnoreCase("Build-C"))
        {
            if(!isInBuildMode(p.getUniqueId()))
            {
                build.getBuildMode().add(p.getUniqueId());
                p.sendMessage(color("%prefix% &7You have &aenabled &7building for yourself."));
            }else
            {
                build.getBuildMode().remove(p.getUniqueId());
                p.sendMessage(color("%prefix% &7You have &cdisabled &7building for yourself."));
            }
        }else
        {
            p.sendMessage(color("&7You don't have permission to change to this mode."));
        }
    }


    public void setMode(Player player)
    {
        if(BuildPermissions.BUILD_TOGGLE.checkPermission(player))
        {

            if(!isInBuildMode(player.getUniqueId()))
            {
                build.getBuildMode().add(player.getUniqueId());

                player.setGameMode(GameMode.CREATIVE);
                setArmor(player);
                player.setPlayerListName(color("&bBuilder&7> &r"+ player.getName()));
                player.setFlySpeed(0.5f);
                player.setPlayerWeather(WeatherType.CLEAR);
                player.resetPlayerTime();
                player.getInventory().addItem(createItem(Material.COMPASS,1,"&bBuilder compass."));
                player.getInventory().addItem(createItem(Material.WOOD_AXE,1,"&cBuilder Axe."));

                player.sendMessage(color("%prefix% &7You have been set into &aBuild Mode&7."));
            }else
            {
                if(player.isFlying())
                {
                    player.setGameMode(GameMode.CREATIVE);
                }
                clearArmor(player);
                player.setDisplayName(player.getName());
                player.setFlySpeed(0.1f);
                player.setPlayerWeather(WeatherType.CLEAR);
                player.getInventory().clear();
                build.getBuildMode().remove(player.getUniqueId());
                player.setPlayerListName(player.getName());
                player.sendMessage(color("%prefix% &7You have been set out of &aBuild Mode&7."));
            }
        }else
        {
            player.sendMessage(color("&7You don't have permission to change to this mode."));
        }
    }

}
