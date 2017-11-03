package me.ES96.com;


import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.bukkit.scheduler.BukkitRunnable;
import utilities.BuildUtils;

import java.util.UUID;

/**
 * Created by Evan on 10/23/2017.
 */
public class UserManager extends BuildUtils
{
    private final Build instance;
    private final BiMap<UUID, String> storedUUIDs;

    public BiMap<UUID,String> getStoredUUIDs() //This can be modified to support potential ranks etc.
    {
        return storedUUIDs;
    }


    public UserManager(Build binstance)
    {
        instance = binstance;
        storedUUIDs = HashBiMap.create();
    }

    public void set(String path, Object value)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                instance.getUuidConfig().getUUIDConfig().set(path,"");
                instance.getUuidConfig().getUUIDConfig().set(path+".uuid",path);
                instance.getUuidConfig().getUUIDConfig().set(path+".name",value);
                instance.getUuidConfig().getUUIDConfig().set(path+".state",State.DEFAULT.toString());
                //Add rank maybe??

                instance.getUuidConfig().saveUUIDConfig();
                log("&4The Data &f" + path +" &4and &f"+value + " &4has been saved...");
            }
        }.runTaskAsynchronously(instance);
    }


    public String getNameFromUUID(UUID uuid)
    {
        if(storedUUIDs.containsKey(uuid))
        {
            return storedUUIDs.get(uuid);
        }

        String foundName = instance.getUuidConfig().getUUIDConfig().getString(uuid.toString() +".name",null);
        if(!foundName.equals(null))
        {
            storedUUIDs.forcePut(uuid,foundName);
        }
        log("&6The name, &a"+foundName +" &6has been retrieved from the uuid: &4" +uuid.toString());
        return foundName;
    }

    public UUID getUUIDFromName(String name)
    {
        if(storedUUIDs.containsValue(name))
        {
            return storedUUIDs.inverse().get(name);
        }
        UUID foundUUID = null;
        for(String pUUID : instance.getUuidConfig().getUUIDConfig().getKeys(false))
        {
            String playerName = instance.getUuidConfig().getUUIDConfig().getString(pUUID+".name");
            if(name.equalsIgnoreCase(playerName))
            {
                foundUUID = UUID.fromString(pUUID+".uuid");
                storedUUIDs.forcePut(foundUUID,playerName);
                break;
            }
        }
        log("&6The UUID, &a"+foundUUID +" &6has been retrieved from the name: &4" +name +".");
        return foundUUID;
    }
}
