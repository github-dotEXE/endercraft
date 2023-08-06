package de.ender.endercraft.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class Rename implements CommandExecutor {

    @SuppressWarnings("deprecation")
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.rename")) {
                if(args.length >= 1) {
                    ItemStack item = player.getItemInHand();
                    ItemMeta itemMeta = item.getItemMeta();
                    String name = String.join(" ", Arrays.copyOfRange(args, 0, args.length)).replace("&", "§");
                    itemMeta.setDisplayName(name);
                    item.setItemMeta(itemMeta);

                } else
                    player.sendMessage("§cPlease use §6/rename <Name...>§c!");
            }else
                player.sendMessage("§cYou have no permission for that command!");
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
