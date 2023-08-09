package de.ender.endercraft.commands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Repair implements CommandExecutor {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    @Deprecated
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.repair")) {
                if(args.length == 0) {
                    ItemStack item = player.getItemInHand();
                    if(item.getType().getMaxDurability() >= 1) {
                        item.setDurability((short) 0);
                        player.sendMessage(miniMessage.deserialize("<green>Your Item has been Repaired!"));
                    }else
                        player.sendMessage(miniMessage.deserialize("<red>You can't Repair <gold>this <red>Item"));
                } else
                    player.sendMessage(miniMessage.deserialize("<red>Please use <gold>/repair<red>!"));
            }else
                player.sendMessage(miniMessage.deserialize("<red>You have no permission for that command!"));
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
