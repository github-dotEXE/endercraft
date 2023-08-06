package de.ender.endercraft.customItems;

import de.ender.core.ItemBuilder;
import de.ender.core.customItems.CustomFoodItem;
import de.ender.endercraft.Main;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;

public class Sandwich extends CustomFoodItem {
    public Sandwich() {
        super("Sandwich", Main.getPlugin(), 12, 15);
    }

    @Override
    protected ItemStack getItemStack() {
        return new ItemBuilder(Material.BREAD,1).setName(getName()).build();
    }

    @Override
    protected Recipe getRecipe() {
        return new ShapelessRecipe(getNamespacedKey(),getItem())
                .addIngredient(2,Material.BREAD).addIngredient(Material.COOKED_BEEF);
    }
}
