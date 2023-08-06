package de.ender.endercraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Invsee implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label,String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.invsee")) {
                if(args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target != player) {

                        Inventory inventory = target.getInventory();
                        player.openInventory(inventory);

                    } else
                        player.sendMessage("§cYou can't open your own Inventory!");
                }
            }else
                player.sendMessage("§cYou have no permission for that command!");
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
