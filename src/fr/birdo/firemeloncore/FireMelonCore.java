package fr.birdo.firemeloncore;

import fr.birdo.firemeloncore.events.*;
import fr.birdo.firemeloncore.item.Bedrock_Hammer;
import fr.birdo.firemeloncore.item.NetheriteStick;
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
        cmd.add("give bedrock_hammer");
        cmd.add("give netherite_stick");
        cmd.add("test");
        cmd.add("osu");

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[FireMelonCore] Plugin Enable !");
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Commands(this), this);
        pm.registerEvents(new NightVision_Upgrade(this), this);
        pm.registerEvents(new Bedrock_Hammer(this), this);
        pm.registerEvents(new NetheriteStick(this), this);
        pm.registerEvents(new CustomRecipes(this), this);
        pm.registerEvents(new DragonDeath(this), this);
        pm.registerEvents(new RecipesGuiEvents(this), this);
        pm.registerEvents(new OsuEvents(this), this);
        this.getCommand("fm").setTabCompleter(new FMCCompleter());
        CustomRecipes recipes = new CustomRecipes(this);
        recipes.customRecipe();
        recipes.unshaped();
    }

    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[FireMelonCore] Plugin Disable !");

    }
}

