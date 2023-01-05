package de.ender.endercraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlySpeed implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label,String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("EnderCraft.flySpeed")) {
                if(args.length == 1) {
                    if(player.getAllowFlight() == true) {
                        String speed = args[0];
                        float flyspeed = Float.parseFloat(speed);
                        player.setFlySpeed(flyspeed/100 );
                        player.sendMessage("§aYour Flyingspeed is now " + flyspeed + "!");
                        player.sendMessage("§bThe Default is 10");
                    } else if(args.length == 0) {
                        if(player.getFlySpeed() == 10) {
                            player.setFlySpeed(1);
                            player.sendMessage("§aYour Flyingspeed is now 100!");
                            player.sendMessage("§bThe Default is 10");
                        } else if(player.getFlySpeed() != 10) {
                            player.setFlySpeed((float) 0.1);
                            player.sendMessage("§aYour Flyingspeed is now 10!");
                            player.sendMessage("§bThe Default is 10");
                        } else {
                            player.sendMessage("§cPlease use §6/flyspeed <1-100>!");
                        }
                    } else {
                        player.sendMessage("§cPlease use §6/flyspeed <1-100>!");
                    }
                }else
                    player.sendMessage("§cYou have no permission to Fly!");
            } else
                player.sendMessage("§cYou have no permission for that command!");
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }
}
