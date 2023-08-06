package de.ender.endercraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Enderchest implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label,String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                if(player.hasPermission("endercraft.ec")) {

                    Inventory inventory = player.getEnderChest();
                    player.openInventory(inventory);
                }
            }else if(args.length == 1) {

                if(player.hasPermission("endercraft.super_ec")) {

                    Inventory inventory = Bukkit.getPlayer(args[0]).getEnderChest();
                    player.openInventory(inventory);

                }

            }


        } else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
