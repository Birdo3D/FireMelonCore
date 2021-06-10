package fr.birdo.firemeloncore.events;

import fr.birdo.firemeloncore.FireMelonCore;
import fr.birdo.firemeloncore.utils.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DragonDeath implements Listener {

    // create Main class instance
    private FireMelonCore instance;

    public DragonDeath(FireMelonCore pluginInstance) {
        this.instance = pluginInstance;
    }

    private int chance = 4;
    private int wait = 9;

    @EventHandler
    public void onDragonDeath(EntityDeathEvent e) {
        if (e.getEntity().getType() == EntityType.ENDER_DRAGON) {
            if (Random.roll(1, chance) == 1) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(instance, () -> {
                    e.getEntity().getLocation().getWorld().getBlockAt(0, 80, 0).setType(Material.DRAGON_EGG);
                }, (wait * 20));
            }
        }
    }
}
