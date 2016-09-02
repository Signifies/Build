package Utilities;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class ItemBuilder
{
    private Material type;
    private final int amount;
    private String name;
    private Color color;
    
    public ItemBuilder(final Material type, final int amount) {
        this.type = type;
        this.amount = amount;
    }
    
    public ItemBuilder(final Material type) {
        this(type, 1);
    }
    
    public final ItemBuilder setType(final Material type) {
        this.type = type;
        return this;
    }
    
    public final ItemBuilder setName(final String name) {
        this.name = name;
        return this;
    }
    
    public final ItemBuilder setColor(final Color color) {
        this.color = color;
        return this;
    }
    
    public final ItemStack build() {
        final ItemStack item = new ItemStack(this.type, this.amount);
        final ItemMeta meta = item.getItemMeta();
        if (this.name != null) {
            meta.setDisplayName(this.name);
        }
        item.setItemMeta(meta);
        return item;
    }
}