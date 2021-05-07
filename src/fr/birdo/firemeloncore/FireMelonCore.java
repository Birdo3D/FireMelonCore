package fr.birdo.firemeloncore;

import fr.birdo.firemeloncore.events.Commands;
import fr.birdo.firemeloncore.events.CustomRecipes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("all")
public class FireMelonCore extends JavaPlugin implements Listener {

    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[FireMelonCore] Plugin Enable !");
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Commands(this), this);
        CustomRecipes recipes = new CustomRecipes();
        recipes.customRecipe();
    }

    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[FireMelonCore] Plugin Disable !");

    }
}

