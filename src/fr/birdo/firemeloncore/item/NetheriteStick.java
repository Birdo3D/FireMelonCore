package fr.birdo.firemeloncore.item;

import fr.birdo.firemeloncore.FireMelonCore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class NetheriteStick implements Listener {

    // create Main class instance
    private static FireMelonCore instance;

    public NetheriteStick(FireMelonCore pluginInstance) {
        this.instance = pluginInstance;
    }

    public static String itemName = "Netherite Stick";
    public static Material itemMat = Material.STICK;

    public static ItemStack getItem() {

        ItemStack item = new ItemStack(itemMat, 1);
        ItemMeta itemM = item.getItemMeta();
        itemM.setDisplayName(ChatColor.AQUA + itemName);
        itemM.addEnchant(Enchantment.DURABILITY, 1, true);
        itemM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemM);

        return item;
    }

    public static ItemStack getItemCraft() {

        ItemStack item = new ItemStack(itemMat, 4);
        ItemMeta itemM = item.getItemMeta();
        itemM.setDisplayName(ChatColor.AQUA + itemName);
        itemM.addEnchant(Enchantment.DURABILITY, 1, true);
        itemM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemM);

        return item;
    }

    @EventHandler
    public void noRename(InventoryClickEvent e) {
        Inventory clickedInventory = e.getClickedInventory();
        if (clickedInventory instanceof AnvilInventory) {
            AnvilInventory anvilInventory = (AnvilInventory) clickedInventory;
            if (anvilInventory.getContents()[0] != null) {
                if (anvilInventory.getContents()[0].getType().equals(itemMat)) {
                    if (anvilInventory.getContents()[0].hasItemMeta() && anvilInventory.getContents()[0].getItemMeta().hasDisplayName() && anvilInventory.getContents()[0].getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + itemName)) {
                        if (e.getSlot() == 2) {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}
