package Commands;

import Events.Menu;
import Utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ES359 on 8/13/16.
 */
public class BhelpCommand extends BuildUtils implements CommandExecutor, Listener
{
    Build instance;
//    Player player;
    private Inventory inventory;
    private ItemStack is;

    @SuppressWarnings("Duplicates")
    public ItemStack createItem(Material mat, String name, List<String> path, Player p) {
        ItemStack is = new ItemStack(mat);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName( color(name));
        List<String> lore = new ArrayList<>();
        for (int i = 0; i < path.size(); i++)
        {
            String var = color(path.get(i));
            var = var.replace("{player}",p.getName());
            var = var.replace("{gold}", "Bae gold.");
            lore.add(var);
        }
        meta.setLore(lore);
        is.setItemMeta(meta);
        return is;
    }
    @SuppressWarnings("Duplicates")
    public ItemStack createItem(ItemStack is, String name, List<String> path) {
//        ItemStack is = new ItemStack(mat);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName( color(name));
        List<String> lore = new ArrayList<>();
        for (int i = 0; i < path.size(); i++)
        {
            String var = color(path.get(i));
            var = var.replace("{player}","");
            var = var.replace("{gold}", "Bae gold.");
            lore.add(var);
        }
        meta.setLore(lore);
        is.setItemMeta(meta);
        return is;
    }

    public BhelpCommand(Build main)
    {
        instance = main;
        Bukkit.getServer().getPluginManager().registerEvents(this,main);
    }
    @SuppressWarnings("Duplicates")
    public void path(Inventory inv, Player p)
    {
        for(String itemKey : instance.getBConfig().getBuildConfig().getConfigurationSection("Help").getKeys(false)) {
            ItemStack is = instance.getBConfig().getBuildConfig().getItemStack("Help." + itemKey);
            ItemMeta meta = is.getItemMeta();
            String name = instance.getBConfig().getBuildConfig().getString("Help."+itemKey + ".name");
            List<String> lore = instance.getBConfig().getBuildConfig().getStringList("Help." +itemKey +".lore");
            int pos = instance.getBConfig().getBuildConfig().getInt("Help." +itemKey + ".item-pos");
            meta.setDisplayName( color(name));
            for (int i = 0; i < lore.size(); i++)
            {
                String var = color(lore.get(i));
                var = var.replace("{player}",p.getName());
                var = var.replace("{gold}", "Bae gold.");
                lore.add(var);
            }
            meta.setLore(lore);
            is.setItemMeta(meta);
            inv.setItem(pos,is);
        }
    }


    public void BAEHelp(Player p)
    {

        inventory  = Bukkit.getServer().createInventory(null, 18, color("&6Battleaxe &aHelp&b&l>"));

//        inventory.setItem(2, createItem(Material.DIAMOND, "&6Help",instance.getBConfig().getBuildConfig().getStringList("help.display"), p));
//        inventory.setItem(4, createItem(Material.COMPASS, "&nTesting: ",instance.getBConfig().getBuildConfig().getStringList("help.display"), p));

        path(inventory,p);
        p.openInventory(inventory);
    }


    public void openInv(Player p)
    {
        p.openInventory(inventory);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {
        if(!(sender instanceof Player)) return true;


       Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("help"))
        {
            if(args.length == 0)
            {

                BAEHelp(p);
//                instance.menu.openInv(player);
                //TODO implement vote menu here.
            }
        }
        return true;
    }


}
