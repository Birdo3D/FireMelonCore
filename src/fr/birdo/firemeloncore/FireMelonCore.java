package fr.birdo.firemeloncore;

import fr.birdo.firemeloncore.events.*;
import fr.birdo.firemeloncore.item.NightVision_Upgrade;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class FireMelonCore extends JavaPlugin implements Listener {

    public void onEnable() {

        /* Commands */
        List cmd = Commands.FMC_COMMANDS;
        cmd.add("give nv_upgrade");
        cmd.add("test");

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[FireMelonCore] Plugin Enable !");
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Commands(this), this);
        pm.registerEvents(new NightVision_Upgrade(this), this);
        pm.registerEvents(new CustomRecipes(this), this);
        pm.registerEvents(new DragonDeath(this), this);
        pm.registerEvents(new RecipesGuiEvents(this), this);
        this.getCommand("fm").setTabCompleter(new FMCCompleter());
        CustomRecipes recipes = new CustomRecipes(this);
        recipes.customRecipe();
    }

    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[FireMelonCore] Plugin Disable !");

    }
}

