package fr.birdo.firemeloncore.events;

import fr.birdo.firemeloncore.FireMelonCore;
import fr.birdo.firemeloncore.gui.OsuGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OsuEvents implements Listener {

    public OsuEvents(FireMelonCore fireMelonCore) {
    }

    int score;

    @EventHandler
    public void onRecipeIsClicked(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("Play :")) {
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                    String item = e.getCurrentItem().getItemMeta().getDisplayName();
                    if(item.equalsIgnoreCase(ChatColor.GREEN+"OSU!")){
                        OsuGui.openGui(Bukkit.getServer().getPlayer(e.getWhoClicked().getName()));
                        score++;
                        e.getWhoClicked().sendMessage("Score : " + score);
                    }
                }
                e.setCancelled(true);
            }
        }
    }
}
