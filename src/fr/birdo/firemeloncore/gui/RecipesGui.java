package fr.birdo.firemeloncore.gui;

import fr.birdo.firemeloncore.item.NightVision_Upgrade;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RecipesGui implements Listener {

    public RecipesGui() {}

    static ItemStack nv_upgrade = NightVision_Upgrade.getItem();
    private static int deco_patern[]= new int[]{0,1,2,3,4,5,6,7,8,9,13,14,15,16,17,18,22,23,25,26,27,31,32,33,34,35,36,37,38,39,40,41,42,43};

    private static void setInventoryPatern(int[] patern, Inventory inventory){
        for (int i: patern){
            ItemStack deco = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
            ItemMeta decoM = deco.getItemMeta();
            decoM.setDisplayName(" ");
            deco.setItemMeta(decoM);
            inventory.setItem(i, deco);
        }
    }

    public static void selectRecipe(Player p) {

        Inventory inv = Bukkit.createInventory(null, 9, "Select Recipe");

        inv.setItem(0, nv_upgrade);

        p.openInventory(inv);
    }

    public static void nv_upgradeRecipeGui(Player p) {

        Inventory inv = Bukkit.createInventory(null, 5 * 9, "Recipe :");

        inv.setItem(24, nv_upgrade);

        inv.setItem(10, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
        inv.setItem(11, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
        inv.setItem(12, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
        inv.setItem(19, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
        inv.setItem(20, new ItemStack(Material.GOLDEN_CARROT, 1));
        inv.setItem(21, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
        inv.setItem(28, new ItemStack(Material.DIAMOND, 1));
        inv.setItem(29, new ItemStack(Material.REDSTONE, 1));
        inv.setItem(30, new ItemStack(Material.DIAMOND, 1));

        ItemStack closeGui = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta closeGuiM = closeGui.getItemMeta();
        closeGuiM.setDisplayName(ChatColor.RED+"Back");
        closeGui.setItemMeta(closeGuiM);
        inv.setItem(44, closeGui);

        setInventoryPatern(deco_patern, inv);

        p.openInventory(inv);
    }
}