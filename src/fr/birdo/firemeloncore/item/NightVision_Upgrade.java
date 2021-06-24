package fr.birdo.firemeloncore.item;

import fr.birdo.firemeloncore.FireMelonCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class NightVision_Upgrade implements Listener {

    // create Main class instance
    private static FireMelonCore instance;

    public NightVision_Upgrade(FireMelonCore pluginInstance) {
        this.instance = pluginInstance;
    }

    public static String itemName = "Night Vision Upgrade";
    public static String itemLore = "Apply this on helmet in anvil.";
    public static Material itemMat = Material.STRUCTURE_VOID;
    private static int count;

    public static ItemStack getItem() {

        ItemStack item = new ItemStack(itemMat, 1);
        ItemMeta itemM = item.getItemMeta();
        itemM.setDisplayName(ChatColor.AQUA + itemName);
        itemM.addEnchant(Enchantment.DURABILITY, 1, true);
        itemM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemM.setLore(Arrays.asList(itemLore));
        item.setItemMeta(itemM);

        return item;
    }

    @EventHandler
    public static void onInteract(PlayerInteractEvent e) {
        if (e.getPlayer().getInventory().getItem(EquipmentSlot.HAND) != null) {
            if (e.getPlayer().getInventory().getItem(EquipmentSlot.HAND).getType() == itemMat) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public static void isEquiped(PlayerJoinEvent e) {
        count = Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, () -> {
            ItemStack helmet = e.getPlayer().getInventory().getItem(EquipmentSlot.HEAD);
            if (helmet != null) {
                if (helmet.hasItemMeta() && helmet.getItemMeta().hasLore() && helmet.getItemMeta().getLore().toString().contains(ChatColor.GRAY + "Night Vision I")) {
                    e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 18 * 20, 9));
                }else{
                    if(e.getPlayer().hasPotionEffect(PotionEffectType.NIGHT_VISION) && e.getPlayer().getPotionEffect(PotionEffectType.NIGHT_VISION).getAmplifier() == 9){
                        e.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
                    }
                }
            }else if(e.getPlayer().hasPotionEffect(PotionEffectType.NIGHT_VISION) && e.getPlayer().getPotionEffect(PotionEffectType.NIGHT_VISION).getAmplifier() == 9){
                e.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
            }
            if (!e.getPlayer().isOnline()) {
                Bukkit.getScheduler().cancelTask(count);
            }
        }, 0L, 4 * 20L);
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
