package fr.birdo.firemeloncore.item;

import fr.birdo.firemeloncore.FireMelonCore;
import fr.birdo.firemeloncore.utils.ItemDurability;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Bedrock_Hammer implements Listener {

    // create Main class instance
    private static FireMelonCore instance;

    public Bedrock_Hammer(FireMelonCore pluginInstance) {
        this.instance = pluginInstance;
    }

    public static String itemName = "Bedrock Hammer";
    public static String itemLore = "Left Click on bedrock block for destruct it.";
    public static Material itemMat = Material.DIAMOND_HOE;

    public static ItemStack getItem() {

        ItemStack item = new ItemStack(itemMat, 1);
        ItemMeta itemM = item.getItemMeta();
        itemM.setDisplayName(ChatColor.AQUA + itemName);
        itemM.addEnchant(Enchantment.SILK_TOUCH, 10, true);
        itemM.setLore(Arrays.asList(itemLore));
        item.setItemMeta(itemM);

        return item;
    }

    @EventHandler
    public static void onUse(PlayerInteractEvent e) {
        if (e.getItem() != null) {
            if (e.getItem().getType() == itemMat) {
                if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (e.getClickedBlock() != null) {
                        if (e.getClickedBlock().getType() == Material.BEDROCK) {
                            if (e.getPlayer().getFoodLevel() == 20) {
                                e.getClickedBlock().setType(Material.AIR);
                                hasEnchant(e.getPlayer());
                                e.getPlayer().setFoodLevel(1);
                            }
                        } else {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

    private static void hasEnchant(Player player) {
        ItemStack ItemInHand = player.getInventory().getItem(EquipmentSlot.HAND);
        if (ItemInHand.hasItemMeta() && ItemInHand.getItemMeta().hasEnchants() && ItemInHand.getItemMeta().hasEnchant(Enchantment.DURABILITY)) {
            getDurability(player, ItemInHand.getItemMeta().getEnchantLevel(Enchantment.DURABILITY) + 3);
        } else {
            getDurability(player, 3);
        }
    }

    private static void getDurability(Player player, int lvlmax) {
        int nb = ItemDurability.getMaxDurability(itemMat) / lvlmax;
        int durability = player.getInventory().getItem(EquipmentSlot.HAND).getDurability();
        if (durability == 0) {
            player.getInventory().getItem(EquipmentSlot.HAND).setDurability((short) (nb));
        } else if (durability == nb) {
            player.getInventory().getItem(EquipmentSlot.HAND).setDurability((short) (nb * 2));
        } else if (durability == nb * 2 && durability != nb * (lvlmax - 1)) {
            player.getInventory().getItem(EquipmentSlot.HAND).setDurability((short) (nb * 3));
        } else if (durability == nb * 3 && durability != nb * (lvlmax - 1)) {
            player.getInventory().getItem(EquipmentSlot.HAND).setDurability((short) (nb * 4));
        } else if (durability == nb * 4 && durability != nb * (lvlmax - 1)) {
            player.getInventory().getItem(EquipmentSlot.HAND).setDurability((short) (nb * 5));
        } else if (durability == nb * (lvlmax - 1)) {
            player.getInventory().setItem(EquipmentSlot.HAND, null);
        }
    }

    @EventHandler
    public void inAnvil(InventoryClickEvent e) {
        Inventory clickedInventory = e.getClickedInventory();
        if (clickedInventory instanceof AnvilInventory) {
            AnvilInventory anvilInventory = (AnvilInventory) clickedInventory;
            if (anvilInventory.getContents()[0] != null) {
                if (anvilInventory.getContents()[0].getType().equals(itemMat)) {
                    if (anvilInventory.getContents()[0].hasItemMeta() && anvilInventory.getContents()[0].getItemMeta().hasDisplayName() && anvilInventory.getContents()[0].getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + itemName)) {
                        if (anvilInventory.getContents()[1] != null && anvilInventory.getContents()[1].getType().equals(Material.ENCHANTED_BOOK)) {
                            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) anvilInventory.getContents()[1].getItemMeta();
                            if (meta.getStoredEnchants().toString().contains("unbreaking")) {
                                if (anvilInventory.getContents()[0].getItemMeta().hasEnchant(Enchantment.DURABILITY)) {
                                    if (e.getSlot() == 2) {
                                        e.setCancelled(true);
                                    }
                                } else {
                                    if (anvilInventory.getContents()[0].getDurability() != 0) {
                                        if (e.getSlot() == 2) {
                                            e.setCancelled(true);
                                        }
                                    }
                                }
                            } else {
                                if (e.getSlot() == 2) {
                                    e.setCancelled(true);
                                }
                            }
                        } else {
                            if (e.getSlot() == 2) {
                                e.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}