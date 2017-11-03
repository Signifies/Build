package me.ES96.com;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import utilities.BuildUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evan on 10/24/2017.
 */
public class PlayerAttributes extends BuildUtils
{
    public static final PlayerAttributes BLANK;

    private ItemStack[] armor;
    private ItemStack[] items;
    private List<PotionEffect> effects;
    private double health;
    private int level;
    private float exp;
    private int foodLevel;
    private float saturation;
    private float exhaustion;
    private GameMode gameMode;
    private Location location;

    public PlayerAttributes(Player player)
    {
        this(player.getInventory().getArmorContents(),
                player.getInventory().getContents(),
                new ArrayList<>(player.getActivePotionEffects()),
                player.getHealth(),
                player.getLevel(),
                player.getExp(),
                player.getFoodLevel(),
                player.getSaturation(),
                player.getExhaustion(),
                player.getGameMode(),
                player.getLocation());
    }

    //Check this one later on, I think it's used already with this keyword

    private PlayerAttributes(ItemStack[] armor,
                        ItemStack[] items,
                        List<PotionEffect> effects,
                        double health,
                        int level,
                        float exp,
                        int foodLevel,
                        float saturation,
                        float exhaustion,

                        GameMode gameMode,
                        Location location) {

        this.armor = armor;
        this.items = items;
        this.effects = effects;
        this.health = health;
        this.level = level;
        this.exp = exp;
        this.foodLevel = foodLevel;
        this.saturation = saturation;
        this.exhaustion = exhaustion;
        this.gameMode = gameMode;
        this.location = location;
    }

    public ItemStack[] getArmor() {
        return armor;
    }

    public ItemStack[] getItems() {
        return items;
    }

    public List<PotionEffect> getEffects() {
        return effects;
    }

    public double getHealth() {
        return health;
    }

    public int getLevel() {
        return level;
    }

    public float getExp() {
        return exp;
    }

    public int getFoodLevel() {
        return foodLevel;
    }

    public float getSaturation() {
        return saturation;
    }

    public float getExhaustion() {
        return exhaustion;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public Location getLocation() {
        return location;
    }

    public void setArmor(ItemStack[] armor) {
        this.armor = armor;
    }

    public void setItems(ItemStack[] items) {
        this.items = items;
    }

    public void setEffects(List<PotionEffect> effects) {
        this.effects = effects;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExp(float exp) {
        this.exp = exp;
    }

    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }

    public void setExhaustion(float exhaustion) {
        this.exhaustion = exhaustion;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void reset(Player player) {
        player.getInventory().setArmorContents(this.armor);
        player.getInventory().setContents(this.items);
        player.updateInventory();
        player.setLevel(this.level);
        player.setExp(this.exp);
        player.setFoodLevel(this.foodLevel);
        player.setSaturation(this.saturation);
        player.setExhaustion(this.exhaustion);
        for(PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
        player.addPotionEffects(this.effects);
        player.setGameMode(this.gameMode);
        player.setHealth(this.health);
        if(this.location != null) {
            player.teleport(this.location);
        }
    }

    static {
        BLANK = new PlayerAttributes(new ItemStack[4], new ItemStack[4], new ArrayList<>(), 20.0,
                0, 0, 20, 0, 0, Bukkit.getDefaultGameMode(), null);
    }
}
