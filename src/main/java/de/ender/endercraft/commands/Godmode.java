package de.ender.endercraft.commands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Godmode implements CommandExecutor {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    public boolean onCommand(CommandSender sender, Command command, String label,String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.godmode")) {
                if(!player.isInvulnerable()) {
                    player.setInvulnerable(true);
                    player.sendMessage(miniMessage.deserialize("Godmode: <green>ON"));
                } else {
                    player.setInvulnerable(false);
                    player.sendMessage(miniMessage.deserialize("Godmode: <red>OFF"));
                }
            }else
                player.sendMessage(miniMessage.deserialize("<red>You have no permission for that command!"));
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
