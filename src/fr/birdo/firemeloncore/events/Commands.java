package fr.birdo.firemeloncore.events;

import fr.birdo.firemeloncore.FireMelonCore;
import fr.birdo.firemeloncore.gui.OsuGui;
import fr.birdo.firemeloncore.gui.RecipesGui;
import fr.birdo.firemeloncore.item.Bedrock_Hammer;
import fr.birdo.firemeloncore.item.NetheriteStick;
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

    @EventHandler
    public void OnCommandSend(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        String msg = e.getMessage();
        String[] args = msg.split(" ");

        if (args[0].equalsIgnoreCase("/fm")) {
            if (player.isOp()) {
                if (args[1].equalsIgnoreCase("give")) {
                    if (args[2].equalsIgnoreCase("nv_upgrade")) {
                        player.getInventory().addItem(NightVision_Upgrade.getItem());
                        player.sendMessage(ChatColor.DARK_GREEN + "Vous avez bien reçu 1 FMSMP:NV_UPGRADE");
                    }else if(args[2].equalsIgnoreCase("bedrock_hammer")){
                        player.getInventory().addItem(Bedrock_Hammer.getItem());
                        player.sendMessage(ChatColor.DARK_GREEN + "Vous avez bien reçu 1 FMSMP:BEDROCK_HAMMER");
                    }else if(args[2].equalsIgnoreCase("netherite_stick")){
                        player.getInventory().addItem(NetheriteStick.getItem());
                        player.sendMessage(ChatColor.DARK_GREEN + "Vous avez bien reçu 1 FMSMP:BEDROCK_HAMMER");
                    }else{
                        player.sendMessage(ChatColor.DARK_RED + "Item inconnu !");
                    }
                }
                if (args[1].equalsIgnoreCase("osu")) {
                    OsuGui.openGui(player);
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
