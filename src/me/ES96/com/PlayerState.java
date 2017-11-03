package me.ES96.com;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import utilities.Debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PlayerState
{
	private HashMap<UUID,State> statedUsers;
	private Build instance; //If needed.

	public PlayerState()
	{
		statedUsers = new HashMap<>();
	}

	public HashMap getUsers()
	{
		return statedUsers;
	}




	public void setPlayerState(Player player, State state)
	{
		switch (state)
		{
			case DEFAULT:

				if(statedUsers.containsKey(player.getUniqueId()))
				{
					statedUsers.remove(player.getUniqueId());
				}else
				{
					statedUsers.put(player.getUniqueId(),State.DEFAULT);
					Debug.log("Added the player, "+player.getName() +" with the state of "+State.DEFAULT,1);
				}

				break;
			case STAFF:

				if(statedUsers.containsKey(player.getUniqueId()))
				{
					statedUsers.remove(player.getUniqueId());
				}else
				{
					statedUsers.put(player.getUniqueId(),State.STAFF);
					Debug.log("Added the player, "+player.getName() +" with the state of "+State.STAFF,1);
				}
				//helpbook.
				//Chainmail armor for the fun of it.
				break;
			case BUILDER:
				//Build mode...
				//etc...
				if(statedUsers.containsKey(player.getUniqueId()))
				{
					statedUsers.remove(player.getUniqueId());
				}else
				{
					statedUsers.put(player.getUniqueId(),State.BUILDER);
					Debug.log("Added the player, "+player.getName() +" with the state of "+State.BUILDER,1);
				}
				break;
		}
	}
}
