package me.ES96.com;

import com.google.common.collect.BiMap;

import java.util.UUID;

/**
 * Created by Evan on 10/23/2017.
 */
public class UserManager
{
    private Build instance;
    private BiMap<UUID, String> storedUUIDs;

    public UserManager(Build binstance)
    {
        instance = binstance;

    }

}
