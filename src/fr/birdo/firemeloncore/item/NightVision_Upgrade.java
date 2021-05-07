package fr.birdo.firemeloncore.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class NightVision_Upgrade {

    public static String itemName = "CustomName";
    public static String itemLore = "CustomLore";

    public static ItemStack getItem() {

        ItemStack item = new ItemStack(Material.CHAINMAIL_HELMET, 1);
        ItemMeta itemM = item.getItemMeta();
        itemM.setDisplayName(ChatColor.AQUA+itemName);
        itemM.addEnchant(Enchantment.DURABILITY, 1, true);
        itemM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemM.setLore(Arrays.asList(itemLore));
        item.setItemMeta(itemM);

        return item;
    }
}
