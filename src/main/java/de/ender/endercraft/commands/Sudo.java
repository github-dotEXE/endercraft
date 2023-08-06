package de.ender.endercraft.commands;

import java.util.Arrays;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sudo implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label,String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.sudo")) {
                if(args.length == 1) {

                } else if(args.length >= 2) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target != null) {
                        target.performCommand(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
                        player.sendMessage("§aYou've used sudo on §6" + target.getName());
                    } else
                        player.sendMessage("§cThe Player §6" + args[0] + " §c isn't online!");
                } else
                    player.sendMessage("§cPlease use §6/sudo <Player> <Massage>§c!");

            } else
                player.sendMessage("§cYou have no permission for that command!");

        } else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
