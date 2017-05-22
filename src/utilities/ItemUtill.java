package utilities;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class ItemUtill
{
    public static ItemStack createItem(final Material material, final String displayname) {
        final ItemStack item = new ItemStack(material);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        item.setItemMeta(meta);
        return item;
    }
    
    public static ItemStack createItem(final Material material, final int amount, final String displayname) {
        final ItemStack item = new ItemStack(material, amount);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        item.setItemMeta(meta);
        return item;
    }
    
    public static ItemStack createItem(final Material material, final short data, final String displayname) {
        final ItemStack item = new ItemStack(material);
        item.setDurability(data);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        item.setItemMeta(meta);
        return item;
    }
    
    public static ItemStack createItem(final Material material, final String displayname, final String... lore) {
        final ItemStack item = new ItemStack(material);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        final ArrayList<String> llore = new ArrayList<String>();
        for (final String loreString : lore) {
            llore.add(loreString);
        }
        meta.setLore(llore);
        item.setItemMeta(meta);
        return item;
    }
    
    public static ItemStack createItem(final Material material, final int amount, final String displayname, final String... lore) {
        final ItemStack item = new ItemStack(material, amount);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        final ArrayList<String> Lore = new ArrayList<String>();
        for (final String loreString : lore) {
            Lore.add(loreString);
        }
        meta.setLore(Lore);
        item.setItemMeta(meta);
        return item;
    }
    
    public static ItemStack createItem(final Material material, final short data, final int amount, final String displayname, final String... lore) {
        final ItemStack item = new ItemStack(material, amount);
        item.setDurability(data);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        final ArrayList<String> Lore = new ArrayList<String>();
        for (final String loreString : lore) {
            Lore.add(loreString);
        }
        meta.setLore(Lore);
        item.setItemMeta(meta);
        return item;
    }
    
    public static String getPotionName(final PotionEffectType pet) {
        if (pet.equals(PotionEffectType.SPEED)) {
            return "Speed";
        }
        if (pet.equals(PotionEffectType.SLOW)) {
            return "Slowness";
        }
        if (pet.equals(PotionEffectType.FAST_DIGGING)) {
            return "Haste";
        }
        if (pet.equals(PotionEffectType.SLOW_DIGGING)) {
            return "Fatigue";
        }
        if (pet.equals(PotionEffectType.INCREASE_DAMAGE)) {
            return "Strenght";
        }
        if (pet.equals(PotionEffectType.JUMP)) {
            return "Jump Boost";
        }
        if (pet.equals(PotionEffectType.CONFUSION)) {
            return "Confusion";
        }
        if (pet.equals(PotionEffectType.REGENERATION)) {
            return "Regeneration";
        }
        if (pet.equals(PotionEffectType.DAMAGE_RESISTANCE)) {
            return "Resistance";
        }
        if (pet.equals(PotionEffectType.FIRE_RESISTANCE)) {
            return "Fire Resistance";
        }
        if (pet.equals(PotionEffectType.WATER_BREATHING)) {
            return "Water Breathing";
        }
        if (pet.equals(PotionEffectType.INVISIBILITY)) {
            return "Invisibility";
        }
        if (pet.equals(PotionEffectType.BLINDNESS)) {
            return "Blindness";
        }
        if (pet.equals(PotionEffectType.NIGHT_VISION)) {
            return "Night Vision";
        }
        if (pet.equals(PotionEffectType.HUNGER)) {
            return "Hunger";
        }
        if (pet.equals(PotionEffectType.WEAKNESS)) {
            return "Weakness";
        }
        if (pet.equals(PotionEffectType.POISON)) {
            return "Poison";
        }
        if (pet.equals(PotionEffectType.WITHER)) {
            return "Wither";
        }
        return null;
    }
    
    public static String getPotionAmplifier(final PotionEffect pe) {
        if (pe.getAmplifier() == 0) {
            return "I";
        }
        if (pe.getAmplifier() == 1) {
            return "II";
        }
        if (pe.getAmplifier() == 2) {
            return "III";
        }
        if (pe.getAmplifier() == 3) {
            return "IV";
        }
        if (pe.getAmplifier() == 4) {
            return "V";
        }
        return null;
    }
    
    public static int getPotionDuration(final PotionEffect pe) {
        return pe.getDuration() / 20;
    }
}