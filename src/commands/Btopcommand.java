package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import utilities.BuildUtils;

/**
 * Created by Evan on 10/22/2017.
 */
public class Btopcommand extends BuildUtils implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[])
    {
        if(!(sender instanceof Player) && args.length < 0)
        {
            sender.sendMessage(color("&7The console is already on &ftop&7! Use a &fplayer instead!"));
        }else if(args.length > 0)
        {
            Player p=(Player)sender;
            if(p==null)
            {
                sender.sendMessage(color("&7Sorry, couldn't find the player, &f" +p.getName() + "&7."));
            }else
            {

            }
        }
        return true;
    }
}
