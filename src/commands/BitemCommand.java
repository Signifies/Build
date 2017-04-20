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
                p.sendMessage(color("&cNot enough arguments were passed."));
            }else
            {
                String[] frmt = args[0].split(":");
                Material mat = Material.getMaterial(Integer.parseInt(frmt[0]));
                int amt = (args.length == 2) ? Integer.parseInt(args[1]) : mat.getMaxStackSize();
                ItemStack is = (frmt.length == 1) ? new ItemStack(mat,amt) : new ItemStack(mat);
                p.sendMessage(color("&7Adding &6" +amt + "&7 of &c" + args[0]));
                p.getInventory().addItem(is);
            }
        }else
        {
            p.sendMessage(instance.getNoPermission());
        }
        return true;
    }
}
