package fr.birdo.firemeloncore.events;

import fr.birdo.firemeloncore.FireMelonCore;
import fr.birdo.firemeloncore.gui.RecipesGui;
import fr.birdo.firemeloncore.item.NightVision_Upgrade;
import fr.birdo.firemeloncore.utils.Random;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class Commands implements Listener {

    // create Main class instance
    private FireMelonCore instance;

    public Commands(FireMelonCore pluginInstance) {
        this.instance = pluginInstance;
    }

    public static List<String> FMC_COMMANDS = new ArrayList<>();

    ItemStack nv_upgrade = NightVision_Upgrade.getItem();

    @EventHandler
    public void OnCommandSend(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        String msg = e.getMessage();
        String[] args = msg.split(" ");

        if (args[0].equalsIgnoreCase("/fm")) {
            if (player.isOp()) {
                if (args[1].equalsIgnoreCase("give")) {
                    if (args[2].equalsIgnoreCase("nv_upgrade")) {
                        player.getInventory().addItem(nv_upgrade);
                        player.sendMessage(ChatColor.DARK_GREEN + "Vous avez bien reçu 1 FMSMP:NV_UPGRADE");
                    } else {
                        player.sendMessage(ChatColor.DARK_RED + "Item inconnu !");
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "Vous n'avez pas la permission de faire ça !");
            }
            if (args[1].equalsIgnoreCase("test")) {
                player.sendMessage(ChatColor.GREEN + "Le plugin fonctionne correctement, bon jeu ! :enjoy:");
            }
            e.setCancelled(true);
        }
        if (args[0].equalsIgnoreCase("/recipes")) {
            RecipesGui.selectRecipe(e.getPlayer());
        }
    }
}
