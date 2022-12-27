package de.ender.endercraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Chat implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label,String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length >= 1) {
                player.chat("String.join(\" \", Arrays.copyOfRange(args, 0, args.length))");
            } else
                player.sendMessage("§cPlease use §6/c <Massage>§c!");
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
