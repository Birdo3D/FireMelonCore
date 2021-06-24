package fr.birdo.firemeloncore.events;

import fr.birdo.firemeloncore.FireMelonCore;
import fr.birdo.firemeloncore.gui.RecipesGui;
import fr.birdo.firemeloncore.item.Bedrock_Hammer;
import fr.birdo.firemeloncore.item.NetheriteStick;
import fr.birdo.firemeloncore.item.NightVision_Upgrade;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class RecipesGuiEvents implements Listener {

    public RecipesGuiEvents(FireMelonCore fireMelonCore) {
    }

    @EventHandler
    public void onRecipeIsClicked(InventoryClickEvent e) {

        if (e.getView().getTitle().equalsIgnoreCase("Select Recipe") || e.getView().getTitle().equalsIgnoreCase("Recipe :")) {
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                    String item = e.getCurrentItem().getItemMeta().getDisplayName();
                    if(item.equalsIgnoreCase(ChatColor.AQUA+NightVision_Upgrade.itemName)){
                        RecipesGui.nv_upgradeRecipeGui(Bukkit.getServer().getPlayer(e.getWhoClicked().getName()));
                    }
                    if(item.equalsIgnoreCase(ChatColor.AQUA+ Bedrock_Hammer.itemName)){
                        RecipesGui.bedrock_hammerRecipeGui(Bukkit.getServer().getPlayer(e.getWhoClicked().getName()));
                    }
                    if(item.equalsIgnoreCase(ChatColor.AQUA+ NetheriteStick.itemName)){
                        RecipesGui.netherite_stickRecipeGui(Bukkit.getServer().getPlayer(e.getWhoClicked().getName()));
                    }
                    if(item.equalsIgnoreCase(ChatColor.RED+"Back")){
                        RecipesGui.selectRecipe(Bukkit.getServer().getPlayer(e.getWhoClicked().getName()));
                    }
                    if(item.equalsIgnoreCase(ChatColor.RED+"Close")){
                        Bukkit.getServer().getPlayer(e.getWhoClicked().getName()).closeInventory();
                    }
                }
                e.setCancelled(true);
            }
        }
    }
}