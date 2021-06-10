package fr.birdo.firemeloncore.events;

import fr.birdo.firemeloncore.FireMelonCore;
import fr.birdo.firemeloncore.item.NightVision_Upgrade;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

@SuppressWarnings("deprecation")
public class CustomRecipes implements Listener {
    private Plugin plugin = FireMelonCore.getPlugin(FireMelonCore.class);

    public CustomRecipes(FireMelonCore fireMelonCore) {
    }

    ItemStack nv_upgrade_item = NightVision_Upgrade.getItem();
    String nv_upgrade_name = NightVision_Upgrade.itemName;

    public void customRecipe() {
        ItemStack quartz = new ItemStack(Material.QUARTZ_BLOCK, 4);
        ShapedRecipe quartzR = new ShapedRecipe(quartz);
        quartzR.shape("##", "##");
        quartzR.setIngredient('#', Material.QUARTZ);

        ItemStack nv_upgrade = nv_upgrade_item;
        ShapedRecipe nv_upgradeR = new ShapedRecipe(nv_upgrade);
        nv_upgradeR.shape("###", "#?#", "!/!");
        nv_upgradeR.setIngredient('#', Material.GREEN_STAINED_GLASS);
        nv_upgradeR.setIngredient('?', Material.GOLDEN_CARROT);
        nv_upgradeR.setIngredient('!', Material.DIAMOND);
        nv_upgradeR.setIngredient('/', Material.REDSTONE);

        plugin.getServer().addRecipe(quartzR);
        plugin.getServer().addRecipe(nv_upgradeR);
    }

    @EventHandler
    public void customAnvilRecipe(PrepareAnvilEvent e) {
        ItemStack[] contents = e.getInventory().getContents();
        ItemStack firstSlot = contents[0];
        ItemStack secondSlot = contents[1];

        if (firstSlot != null && secondSlot != null) {

            Material firstSlotMat = firstSlot.getType();

            if (firstSlotMat.equals(Material.LEATHER_HELMET) || firstSlotMat.equals(Material.CHAINMAIL_HELMET) || firstSlotMat.equals(Material.IRON_HELMET) || firstSlotMat.equals(Material.GOLDEN_HELMET) || firstSlotMat.equals(Material.DIAMOND_HELMET) || firstSlotMat.equals(Material.NETHERITE_HELMET)) {
                if (secondSlot.getType().equals(Material.STRUCTURE_VOID)) {
                    if (secondSlot.hasItemMeta() && secondSlot.getItemMeta().hasDisplayName() && secondSlot.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + nv_upgrade_name)) {
                        if (firstSlot.hasItemMeta() && firstSlot.getItemMeta().hasLore() && firstSlot.getItemMeta().getLore().toString().contains(ChatColor.GRAY + "Night Vision I")) {
                        }else{
                            ItemStack result = firstSlot.clone();
                            ItemMeta resultM = result.getItemMeta();
                            resultM.setLore(Arrays.asList("", ChatColor.DARK_GREEN+"Upgrades :", ChatColor.GRAY + "Night Vision I"));
                            result.setItemMeta(resultM);
                            e.setResult(result);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void anvilRecipeValided(InventoryClickEvent e) {
        Inventory clickedInventory = e.getClickedInventory();
        if (clickedInventory instanceof AnvilInventory) {
            AnvilInventory anvilInventory = (AnvilInventory) clickedInventory;
            if (e.getSlot() == 2) {
                if (anvilInventory.getContents()[1] != null) {
                    if (anvilInventory.getContents()[1].getType().equals(Material.STRUCTURE_VOID)) {
                        ItemStack stack = anvilInventory.getItem(2);
                        if (stack != null) {
                            int nb = anvilInventory.getItem(1).getAmount();
                            e.setCursor(stack);
                            anvilInventory.setItem(0, null);
                            ItemStack itemSlot1 = anvilInventory.getItem(1).clone();
                            itemSlot1.setAmount(nb - 1);
                            anvilInventory.setItem(1, itemSlot1);
                            anvilInventory.setItem(2, null);
                        }
                    }
                }
            }
        }
    }
}