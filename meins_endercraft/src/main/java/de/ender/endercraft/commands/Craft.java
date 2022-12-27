package de.ender.endercraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Craft implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label,String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(player.hasPermission("endercraft.craft")) {
                player.openWorkbench(null, true);
            }

        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }
}
