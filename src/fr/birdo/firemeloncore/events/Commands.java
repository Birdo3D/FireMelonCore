package fr.birdo.firemeloncore.events;

import fr.birdo.firemeloncore.FireMelonCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

@SuppressWarnings("all")
public class Commands implements Listener {

    // create Main class instance
    private FireMelonCore instance;

    public Commands(FireMelonCore pluginInstance) {
        this.instance = pluginInstance;
    }

    @EventHandler
    public void OnCommandSend(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        String msg = e.getMessage();
        String[] args = msg.split(" ");

        if (args[0].equalsIgnoreCase("/fm")) {
        }
    }
}
