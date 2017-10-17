package commands;

import utilities.BuildUtils;
import utilities.Debug;
import me.ES96.com.Build;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
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
//    private  inventory;
//private ItemStack is;



    @SuppressWarnings("Duplicates")
    public ItemStack createItem(Material mat, String name, String key, Player p) {
        ItemStack is = new ItemStack(mat);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName( color(name));
        List<String> lore = instance.getBConfig().getBuildConfig().getStringList("Help." +key + ".lore");
        for (int i = 0; i < lore.size(); i++)
        {
            String var = color(lore.get(i));
            var = var.replace("{player}",p.getName());
            var = var.replace("{gold}", "Bae gold.");
            lore.add(var);
        }
        meta.setLore(lore);
        is.setItemMeta(meta);
        return is;
    }

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

    public void test(Inventory inv)
    {

    }

    public BhelpCommand(Build main)
    {
        instance = main;
        Bukkit.getServer().getPluginManager().registerEvents(this,instance);
    }


    /*
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
*/

    @SuppressWarnings("Duplicates")
    public void path(Inventory inv, Player p)
    {
        for(String itemKey : instance.getBConfig().getBuildConfig().getConfigurationSection("Help").getKeys(false)) {
            ItemStack A =  createItem(Material.valueOf(itemKey.replace("Help.","")),instance.getBConfig().getBuildConfig().getString("Help." +itemKey + ".name"), itemKey, p);
            inv.setItem(2,A);
        }
    }

    void test1(ItemStack is)
    {
        List<String> items = instance.getBConfig().getBuildConfig().getStringList("Items");

        for(String i : items)
        {
            Debug.log(Debug.pluginLog()+"&c BAEHELP gui Debug statement.",0);
            ItemStack isx= new ItemStack(Material.getMaterial(instance.getBConfig().getBuildConfig().getString(i +".item")));
            String path = i+".item" + ".slot";
            Debug.log(Debug.pluginLog() + "ITEM: " + is.getType(),0);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(i);
            is.setItemMeta(im);
//            inventory.setItem(instance.getBConfig().getBuildConfig().getInt(path),is);
            Debug.log(Debug.pluginLog()+"&c BAEHELP gui Debug statement.",0);
        }
    }

    //// TODO: 8/30/16 For each??  
    public void getItem(Inventory inv,Player value)
    {
        List<String> items = instance.getBConfig().getBuildConfig().getStringList("Items");
        for(String s : items)
        {
            ItemStack is = new ItemStack(Material.valueOf(s));
//            Debug.log(Debug.pluginLog() + "ITEM: " + is.getType());
//            Debug.log(Debug.pluginLog() + "What is the variable s? : " + s);
            int slot = instance.getBConfig().getBuildConfig().getInt(s +".slot");
            String name = instance.getBConfig().getBuildConfig().getString(s+".Name");
            name = name.replace("{player}",value.getName());
            name = name.replace("{display_name}",value.getDisplayName());
            name = name.replace("{gold}", "GOLD");
            name = name.replace("{uuid}",value.getUniqueId().toString());
            if(name.equals(null))
            {
                Debug.log(Debug.SEVERE + "&cERROR. Name is null.",0);
            }
//            Debug.log(Debug.pluginLog() + "&bName variable data : " + name);
//            Debug.log(Debug.pluginLog() + "&cName variable path : " + s+".Name");
            String path = s +".set.lore";
            List<String> lore = instance.getBConfig().getBuildConfig().getStringList(path);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(ChatColor.translateAlternateColorCodes('&',name));
            for (int i = 0; i < lore.size(); i++)
            {
                String var = lore.remove(0);
                var = var.replace("{player}",value.getName());
                var = var.replace("{uuid}",value.getUniqueId().toString());
                var = var.replace("{gold}", "Bae gold.");
                lore.add(ChatColor.translateAlternateColorCodes('&',var));
            }
            im.setLore(lore);
            is.setItemMeta(im);
            inv.setItem(slot,is);
        }
    }

    /*
    public void  no()
    {
        List<String> lore = instance.getBConfig().getBuildConfig().getStringList(s + ".lore");
        for (int i = 0; i < lore.size(); i++)
        {
            String var = lore.get(i);
//                var = var.replace("{player}",p.getName());
//                var = var.replace("{gold}", "Bae gold.");
            lore.add(var);
        }
//        im.setLore(lore);
    }
    */
    Inventory inventory = null;
    int hash;
    public void BAEHelp(Player p)
    {
        Debug.log(Debug.pluginLog()+"&c BAEHELP gui Debug statement.",0);
        inventory  = Bukkit.getServer().createInventory(p, instance.getBConfig().getBuildConfig().getInt("Inventory-settings.size"), ChatColor.translateAlternateColorCodes('&',instance.getBConfig().getBuildConfig().getString("Inventory-settings.name")));

        hash = inventory.hashCode();

        Debug.log(Debug.pluginLog() + "&ccreateInventory hash data: " + hash,0);

//        inventory.setItem(7, new ItemStack(Material.CAKE));
        getItem(inventory,p);
        p.openInventory(inventory);
    }


    public String getInvName()
    {
        String msg = instance.getBConfig().getBuildConfig().getString("Inventory-settings.name");


        msg = msg.replace("§","");
        msg = msg.replace("&","");
        Debug.log(Debug.pluginLog() + "&6Testing message: &b" + msg,0);

        return msg;
    }

    public Inventory getBAEInv()
    {
        return this.inventory;
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

    @EventHandler
    public void OCE(InventoryClickEvent event) {

        Player p = (Player) event.getWhoClicked();
//        Debug.log("\n");
//        Debug.log(Debug.pluginLog() + "Event inventory hash: " + event.getInventory().hashCode());
        try
        {
            if(getBAEInv().getViewers().contains(p))
            {
                Debug.log("Testing...",0);
                event.setCancelled(true);
            }else
            {
                event.setCancelled(false);
            }
        }catch (NullPointerException e)
        {
            e.getMessage();
        }
    }

}
