package commands;

import utilities.BuildPermissions;
import utilities.BuildUtils;
import me.ES96.com.Build;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ES359 on 8/27/16.
 */
public class BitemCommand extends BuildUtils implements CommandExecutor
{
    Build instance;

    public BitemCommand(Build main)
    {
        instance = main;
    }

    ItemStack item = getItemStack("SAPLING:5",1);

    public ItemStack getItemStack(String value,int amt) {

        Material mat = null;
        int data = 0;

        String[] obj = value.split(":");

        if (obj.length == 2) {
            try {
                mat = Material.matchMaterial(obj[0]);
            } catch (Exception e) {
                return null; // material name doesn't exist
            }

            try {
                data = Integer.valueOf(obj[1]);
            } catch (NumberFormatException e) {
                return null; // data not a number
            }
        } else {
            try {
                mat = Material.matchMaterial(value);
            } catch (Exception e) {
                return null; // material name doesn't exist
            }
        }

        ItemStack item = new ItemStack(mat, amt);
        item.setDurability((short) data);
        return item;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {

        if(!(sender instanceof Player))
        {
            /// TODO: 8/27/16
            sender.sendMessage(color("&cWill add console support on a later date. TODO: 8/27/16"));
            return true;
        }

        Player p=(Player)sender;

        if(cmd.getName().equalsIgnoreCase("item") && BuildPermissions.BUILD_ITEM_COMMAND.checkPermission(p))
        {
            if(args.length < 1)
            {
                p.sendMessage(color("&7/item [id] <amount> &f|| &7/item [player] [id] <amount> &f|| &7/item [id] [amount] <display_name> <lore> <enchantment>"));
            }else if(args.length >0)
            {
               ItemStack is = getItemStack(args[0],Integer.parseInt(args[1]));
               p.getPlayer().getInventory().addItem(is);
            }
        }else
        {
            p.sendMessage(instance.getNoPermission());
        }
        return true;
    }
}
