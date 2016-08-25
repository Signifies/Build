package Events;

import Commands.BhelpCommand;
import Utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ES359 on 8/23/16.
 */
public class Menu extends BuildUtils implements Listener
{

    private Inventory inventory;
    private ItemStack is;
    private Build build;
    private BhelpCommand cmd;

    @SuppressWarnings("Duplicates")
    public ItemStack createItem(Material mat, String name) {
        ItemStack is = new ItemStack(mat);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(name);
        is.setItemMeta(meta);
        return is;
    }

    @SuppressWarnings("Duplicates")
    public ItemStack createItem(Material mat, String name, List<String> path) {
        ItemStack is = new ItemStack(mat);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName( color(name));
        List<String> lore = new ArrayList<>();
        for (int i = 0; i < path.size(); i++)
        {
            String var = color(path.get(i));
//            var = var.replace("{player}",cmd.getUser().getName());
//            var = var.replace("{uuid}", cmd.getUser().getUniqueId().toString());
            lore.add(var);
        }
        meta.setLore(lore);
        is.setItemMeta(meta);
        return is;
    }

    public Menu(Build instance)
    {
        build = instance;
        cmd = new BhelpCommand(instance);
        Bukkit.getServer().getPluginManager().registerEvents(this,instance);
        inventory  = Bukkit.getServer().createInventory(null, 18, color("&6Battleaxe &aHelp&b&l>"));

        inventory.setItem(2, createItem(Material.DIAMOND, "&6Help",build.getBConfig().getBuildConfig().getStringList("help.display")));
        inventory.setItem(4, createItem(Material.COMPASS, "&nTesting: ",build.getBConfig().getBuildConfig().getStringList("help.display")));

    }


    public void closeInv(Player p )
    {
        p.closeInventory();
    }

    public void openInv(Player p)
    {
        p.openInventory(inventory);
    }

    @EventHandler
    public void IE(InventoryClickEvent event)
    {
        Inventory inv = event.getInventory();
        ItemStack clicked = event.getCurrentItem();

        if(inv.getName().equals(inventory))
        {
            event.setCancelled(true);
        }
    }


}
