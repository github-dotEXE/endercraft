package de.ender.endercraft.commands;

import java.util.Arrays;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class Rename implements CommandExecutor {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.rename")) {
                if(args.length >= 1) {
                    ItemStack item = player.getItemInHand();
                    ItemMeta itemMeta = item.getItemMeta();
                    Component name = miniMessage.deserialize(String.join(" ", Arrays.copyOfRange(args, 0, args.length)));
                    itemMeta.displayName(name);
                    item.setItemMeta(itemMeta);

                } else
                    player.sendMessage(miniMessage.deserialize("<red>Please use <gold>/rename <Name...><red>!"));
            }else
                player.sendMessage(miniMessage.deserialize("<red>You have no permission for that command!"));
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
