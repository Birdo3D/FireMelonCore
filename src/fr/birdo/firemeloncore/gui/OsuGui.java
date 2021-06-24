package fr.birdo.firemeloncore.gui;

import fr.birdo.firemeloncore.utils.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class OsuGui implements Listener {

    public OsuGui() {}

    private static int deco_patern[]= new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53};

    private static void setInventoryPatern(int[] patern, int selected, Inventory inventory){
        for (int i: patern){
            inventory.setItem(i, getGrayGlassPane());
        }
        if(selected != 0) {
            inventory.setItem(selected, getGreenGlassPane());
        }
    }

    public static void openGui(Player p) {

        Inventory inv = Bukkit.createInventory(null, 6*9, "Play :");

        setInventoryPatern(deco_patern, Random.roll(0,53), inv);

        p.openInventory(inv);
    }

    public static ItemStack getGreenGlassPane() {

        ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta itemM = item.getItemMeta();
        itemM.setDisplayName(ChatColor.GREEN+"OSU!");
        itemM.setLore(Arrays.asList("Click!"));
        item.setItemMeta(itemM);

        return item;
    }

    public static ItemStack getGrayGlassPane() {

        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta itemM = item.getItemMeta();
        itemM.setDisplayName(" ");
        item.setItemMeta(itemM);

        return item;
    }
}
