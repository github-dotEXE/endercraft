package de.ender.endercraft.commands;

import de.ender.core.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GiveCMD implements CommandExecutor,TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player) || !sender.hasPermission("endercraft.i") || args.length == 0) return false;
        Player player = (Player) sender;
        Material material  = Material.getMaterial(args[0]);
        if(material == null) return false;
        int amount = material.getMaxStackSize();
        if(args.length >= 2) amount = Integer.parseInt(args[1]);
        Collection<ItemStack> leftOvers = player.getInventory().addItem(new ItemBuilder(material,amount).build()).values();
        for (ItemStack item: leftOvers) player.getWorld().dropItemNaturally(player.getLocation(), item);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if (args.length == 1) {
            for (Material m:
                Material.values()) {
                commands.add(m.toString());
            }
        }

        StringUtil.copyPartialMatches(args[args.length-1], commands, completions); //copy matches of first argument

        Collections.sort(completions);//sort the list
        return completions;
    }
}
