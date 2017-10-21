package commands;

import org.bukkit.event.inventory.ClickType;
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
            String name = instance.getBConfig().get555555555555555555555555BuildConfig().getString("Help."+itemKey + ".name");
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

    //// TODO: 8/30/16 For each??  
    public void getItem(Inventory inv,Player value)
    {
        List<String> items = instance.getBConfig().getBuildConfig().getStringList("Items");
        String location =  color("&7X:&a"+value.getLocation().getBlockX() +" &7Y&a:" +value.getLocation().getBlockY() + " &7Z&a:" + value.getLocation().getBlockZ() +"&r" );

        for(String s : items)
        {
            ItemStack is = new ItemStack(Material.valueOf(s));
//            Debug.log(Debug.pluginLog() + "ITEM: " + is.getType());
//            Debug.log(Debug.pluginLog() + "What is the variable s? : " + s);
            int slot = instance.getBConfig().getBuildConfig().getInt(s +".slot");
            String name = instance.getBConfig().getBuildConfig().getString(s+".Name");
            name = name.replace("{player}",value.getName());
            name = name.replace("{display_name}",value.getDisplayName());
            name = name.replace("{uuid}",value.getUniqueId().toString());
            if(name == null)
            {
                Debug.log(Debug.SEVERE + "&cERROR. Name is null.",2);
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
                var = var.replace("{time}", getStamp().toString());
                var = var.replace("{location}", location);
                var = var.replace("{world}",value.getWorld().getName());
                lore.add(ChatColor.translateAlternateColorCodes('&',var));
            }
            im.setLore(lore);
            is.setItemMeta(im);
            inv.setItem(slot,is);
        }
    }

    Inventory inventory = null;
    int hash;
    public void BAEHelp(Player p)
    {
        Debug.log(Debug.pluginLog()+"&c BAEHELP gui Debug statement.",0);
        inventory  = Bukkit.getServer().createInventory(p, instance.getBConfig().getBuildConfig().getInt("Inventory-settings.size"), ChatColor.translateAlternateColorCodes('&',instance.getBConfig().getBuildConfig().getString("Inventory-settings.name")));
        hash = inventory.hashCode();

        Debug.log(Debug.pluginLog() + "&ccreateInventory hash data: " + hash,0);

        getItem(inventory,p);
        p.openInventory(inventory);
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
                event.setCancelled(true);
//                Debug.log("Testing...",0);
//                event.setCancelled(true);

                /*
                List<String> items = instance.getBConfig().getBuildConfig().getStringList("Items");

                for(String s : items)
                {
                    ItemStack is = new ItemStack(Material.valueOf(s));
                    boolean clickable = instance.getBConfig().getBuildConfig().getBoolean(s+".clickable");
                    String perm = instance.getBConfig().getBuildConfig().getString(s+".permission");
                    List<String> commands = instance.getConfig().getStringList(s+".functions");
                    if(event.getCurrentItem() == is && clickable && event.isRightClick())
                    {
                        Debug.log(1,p,Debug.LOG+"Inventory check passed, moving towards permission.");
                        //Cancel, check perms, run function or help method.
                        //TODO.. DO we want a specific function or do we want to check the item meta??
                        if(p.hasPermission(perm))
                        {
                            Debug.log(Debug.LOG + "&6Checking permission output: " + perm,1);
                            runCommands(commands,p,"Commands run.");
                            Debug.log(Debug.LOG + "&6Command path check: " + commands,1);

                        }else
                        {
                            p.sendMessage(color("&7> You don't have &fpermission&7 to click that&f."));
                        }
                    }else
                    {
                        event.setCancelled(true);
                    }
                }
                */
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
