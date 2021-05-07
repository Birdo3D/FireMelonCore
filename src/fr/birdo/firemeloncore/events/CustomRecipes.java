package fr.birdo.firemeloncore.events;

import fr.birdo.firemeloncore.FireMelonCore;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class CustomRecipes implements Listener {
    private Plugin plugin = FireMelonCore.getPlugin(FireMelonCore.class);

    public void customRecipe() {
        ItemStack item = new ItemStack(Material.QUARTZ_BLOCK, 4);
        ShapedRecipe r = new ShapedRecipe(item);
        r.shape("##", "##");
        r.setIngredient('#', Material.QUARTZ);

        plugin.getServer().addRecipe(r);
    }
}