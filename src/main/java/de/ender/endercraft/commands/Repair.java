package de.ender.endercraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Repair implements CommandExecutor {

    @Deprecated
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("EnderCraft.repair")) {
                if(args.length == 0) {
                    ItemStack item = player.getItemInHand();
                    if(item.getType().getMaxDurability() >= 1) {
                        item.setDurability((short) 0);
                        player.sendMessage("§aYour Item has been Repaired!");
                    }else
                        player.sendMessage("§cYou can't Repair §6this §cItem");
                } else
                    player.sendMessage("§cPlease use §6/repair§c!");
            }else
                player.sendMessage("§cYou have no permission for that command!");
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
