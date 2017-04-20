package utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by ES359 on 9/23/16.
 */
public class Data extends BuildUtils
{
    private File file;
    private JSONObject json; // org.json.simple
    private JSONParser parser = new JSONParser();
    private HashMap<String, Object> defaults = new HashMap<String, Object>();

    public Data(File f)
    {
        file = f;
        reload();
    }

    public Data(File f, Player p)
    {
        file = f;
        reload(p);
        update(p);
    }


        public void reload(Player p)
        {
            try
            {
                // TODO
                if (!file.getParentFile().exists())
                {
                    file.getParentFile().mkdirs();
                }

                if (!file.exists()) {
                    PrintWriter pw = new PrintWriter(file, "UTF-8");
                    pw.print("{");
                    pw.print("}");
                    pw.flush();
                    pw.close();
                }
                json = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(file), "UTF-8"));

                String fm = p.hasPlayedBefore() ? "Played before." : "Has not played before.";
                String wl = p.isWhitelisted() ? "Player is whitelisted" : "Player is not whitelisted";
    //            JSONObject myObject = new JSONObject();
                defaults.put("UUID", p.getUniqueId().toString());
                defaults.put("Name", p.getName());
                defaults.put("IP",p.getAddress().toString());
                defaults.put("World",p.getWorld().getName());
                defaults.put("FirstJoin",getStamp().toString());
                defaults.put("LastJoined", 0);
                defaults.put("Playedbefore",fm);
                defaults.put("EXPTotal",p.getTotalExperience());
                defaults.put("Health", p.getHealth());
                defaults.put("Whitelisted",wl);
                defaults.put("Permissions", p.getEffectivePermissions().toArray());
    //            JSONArray myArray = new JSONArray();
    //            myArray.add(p.getEffectivePermissions().toArray());
    //            defaults.put("Permissions",myArray);
                save();

            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

    public void update(Player p)
    {
        try
        {
            // TODO
            if (!file.getParentFile().exists())
            {
                file.getParentFile().mkdirs();
            }

            if (!file.exists()) {
                PrintWriter pw = new PrintWriter(file, "UTF-8");
                pw.print("{");
                pw.print("}");
                pw.flush();
                pw.close();
            }
            json = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            String fm = p.hasPlayedBefore() ? "Played before." : "Has not played before.";
            String wl = p.isWhitelisted() ? "Player is whitelisted" : "Player is not whitelisted";
//            JSONObject myObject = new JSONObject();
            defaults.put("UUID", p.getUniqueId().toString());
            defaults.put("Name", p.getName());
            defaults.put("IP",p.getAddress().toString());
            defaults.put("World",p.getWorld().getName());
            defaults.put("EXPTotal",p.getTotalExperience());
            defaults.put("Health", p.getHealth());
            defaults.put("LastJoined",getStamp());
            defaults.put("Whitelisted",wl);
            defaults.put("Permissions", p.getEffectivePermissions().toArray());
//            JSONArray myArray = new JSONArray();
//            myArray.add(p.getEffectivePermissions().toArray());
//            defaults.put("Permissions",myArray);
            save();

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void reload()
    {
        try
        {
            // TODO
            if (!file.getParentFile().exists())
            {
                file.getParentFile().mkdirs();
            }

            if (!file.exists()) {
                PrintWriter pw = new PrintWriter(file, "UTF-8");
                pw.print("{");
                pw.print("}");
                pw.flush();
                pw.close();
            }
            json = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(file), "UTF-8"));


            defaults.put("Author", "&cES's Test String...");

            defaults.put("Permission", "&cNo perms...");

            JSONObject myObject = new JSONObject();
            myObject.put("Test", "test");
            myObject.put("Test2", "test2");
            defaults.put("MyObject", myObject);

            JSONArray myArray = new JSONArray();

            defaults.put("Worlds", myArray);
            save();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public String getRawData(String key) {
        return json.containsKey(key) ? json.get(key).toString() : (defaults.containsKey(key) ? defaults.get(key).toString() : key);
    }

    public String getString(String key) {
        return ChatColor.translateAlternateColorCodes('&', getRawData(key));
    }

    public boolean getBoolean(String key) {
        return Boolean.valueOf(getRawData(key));
    }

    public double getDouble(String key) {
        try {
            return Double.parseDouble(getRawData(key));
        } catch (Exception ex) { }
        return -1;
    }

    public double getInteger(String key) {
        try {
            return Integer.parseInt(getRawData(key));
        } catch (Exception ex) { }
        return -1;
    }

    public JSONObject getObject(String key) {
        return json.containsKey(key) ? (JSONObject) json.get(key)
                : (defaults.containsKey(key) ? (JSONObject) defaults.get(key) : new JSONObject());
    }

    public JSONArray getArray(String key) {
        return json.containsKey(key) ? (JSONArray) json.get(key)
                : (defaults.containsKey(key) ? (JSONArray) defaults.get(key) : new JSONArray());
    }


    //TODO Check command arguments? implement that into the ArrayList in the json file.
    // Location to Json
    @SuppressWarnings("unchecked")
    public JSONObject locToJson(Location loc) {
        JSONObject jso = new JSONObject();

            jso.put("x", loc.getX());
            jso.put("y", loc.getY());
            jso.put("z", loc.getZ());
            jso.put("yaw", loc.getYaw());
            jso.put("pitch", loc.getPitch());

        return jso;
    }


    public void add(Player p, String path)
    {
        JSONArray js = new JSONArray();
        js.add(p.getLocation().getWorld().getName() + "." +locToJson(p.getLocation()));
        json.put(path,js);
        save();
    }

    public void registerUser(Player p)
    {
        JSONObject js = new JSONObject();
        js.put("name",p.getName());
        js.put("UUID",p.getUniqueId().toString());

        json.put(p.getUniqueId(),js);
        save();
    }

    public boolean save() {
        try {
            JSONObject toSave = new JSONObject();

            for (String s : defaults.keySet()) {
                Object o = defaults.get(s);
                if (o instanceof String) {
                    toSave.put(s, getString(s));
                } else if (o instanceof Double) {
                    toSave.put(s, getDouble(s));
                } else if (o instanceof Integer) {
                    toSave.put(s, getInteger(s));
                } else if (o instanceof JSONObject) {
                    toSave.put(s, getObject(s));
                } else if (o instanceof JSONArray) {
                    toSave.put(s, getArray(s));
                }
            }

            // TODO

            TreeMap<String, Object> treeMap = new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
            treeMap.putAll(toSave);

            Gson g = new GsonBuilder().setPrettyPrinting().create();
            String prettyJsonString = g.toJson(treeMap);

            FileWriter fw = new FileWriter(file);
            fw.write(prettyJsonString);
            fw.flush();
            fw.close();


            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}