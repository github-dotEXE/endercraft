package de.ender.endercraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Godmode implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label,String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("EnderCraft.godmode")) {
                if(!player.isInvulnerable()) {
                    player.setInvulnerable(true);
                    player.sendMessage("Godmode: §aON");
                } else {
                    player.setInvulnerable(false);
                    player.sendMessage("Godmode: §cOFF");
                }
            }else
                player.sendMessage("§cYou have no permission for that command!");
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
