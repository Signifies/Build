package events;

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
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import utilities.BuildPermissions;
import utilities.BuildUtils;
import utilities.ItemUtill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Invsee extends BuildUtils implements CommandExecutor, Listener
{

    private Build instance;

    public Invsee(Build value)
    {
        instance = value;
    }

    public HashMap<UUID, Integer> TID;
    public Invsee() {
        this.TID = new HashMap<UUID, Integer>();
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return false;
        }
        final Player p = (Player)sender;
        if (!BuildPermissions.BUILD_INVSEE.checkPermission(p)) {
            p.sendMessage(color(instance.getBConfig().getBuildConfig().getString("no-permission")));
            return false;
        }
        if (args.length != 1) {
            p.sendMessage(color("&aUsage: &7/invsee <player>"));
            return false;
        }
        if (Bukkit.getPlayer(args[0]) == null) {
            p.sendMessage(ChatColor.RED + args[0] + " Is offline or is a invalid player.");
            return false;
        }
        this.startInvsee(p, Bukkit.getPlayer(args[0]));
        return false;
    }
    
    public void startInvsee(final Player p, final Player target) {
        final Inventory inv = Bukkit.createInventory(p, 45, color("&a" + target.getName()));
        final int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(instance,new Runnable() {
            @Override
            public void run() {
                if (target != null) {
                    inv.setContents(target.getInventory().getContents());
                    inv.setItem(36, target.getInventory().getHelmet());
                    inv.setItem(37, target.getInventory().getChestplate());
                    inv.setItem(38, target.getInventory().getLeggings());
                    inv.setItem(39, target.getInventory().getBoots());
                    inv.setItem(43, ItemUtill.createItem(Material.COOKED_BEEF, target.getFoodLevel(), color("§6Hunger"), color("&c&l") + target.getFoodLevel()));
                    final ItemStack powder = ItemUtill.createItem(Material.GLASS_BOTTLE, color("&bPotion Effects"));
                    final ItemMeta im = powder.getItemMeta();
                    final ArrayList<String> lore = new ArrayList<String>();
                    if (p.getActivePotionEffects().size() > 0) {
                        for (final PotionEffect pe : p.getActivePotionEffects()) {
                            lore.add(color("&c") + ItemUtill.getPotionName(pe.getType()) + " " + ItemUtill.getPotionAmplifier(pe) + color("&c: &l ") + Invsee.convertSecondsToMinutes(ItemUtill.getPotionDuration(pe)));
                        }
                    }
                    else {
                        lore.add(color("&cNone"));
                    }
                    im.setLore(lore);
                    powder.setItemMeta(im);
                    inv.setItem(44, powder);
                }
            }
        }, 0L, 3L);
        p.openInventory(inv);
        this.TID.put(p.getUniqueId(), id);
    }
    
    public void stopScheduler(final Player p) {
        if (this.TID.containsKey(p.getUniqueId())) {
            final int id = this.TID.get(p.getUniqueId());
            Bukkit.getScheduler().cancelTask(id);
            this.TID.remove(p.getUniqueId());
        }
    }
    
    @EventHandler
    public void onClose(final InventoryCloseEvent e) {
        if (this.TID.containsKey(e.getPlayer().getUniqueId())) {
            this.stopScheduler((Player)e.getPlayer());
        }
    }
    
    public static String convertSecondsToMinutes(final int time) {
        final int minutes = time / 60;
        final int seconds = time % 60;
        final String disMinu = new StringBuilder().append(minutes).toString();
        final String disSec = String.valueOf((seconds < 10) ? "0" : "") + seconds;
        final String formattedTime = String.valueOf(disMinu) + ":" + disSec;
        return formattedTime;
    }
}