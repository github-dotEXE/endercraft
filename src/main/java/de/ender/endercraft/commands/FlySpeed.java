package de.ender.endercraft.commands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlySpeed implements CommandExecutor {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    public boolean onCommand(CommandSender sender, Command command, String label,String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.flyspeed")) {
                if(args.length == 1) {
                    if(player.getAllowFlight()) {
                        String speed = args[0];
                        float flyspeed = Float.parseFloat(speed);
                        player.setFlySpeed(flyspeed/100 );
                        player.sendMessage(miniMessage.deserialize("<green>Your Flyingspeed is now " + flyspeed + "!"));
                        player.sendMessage(miniMessage.deserialize("<aqua>The Default is 10"));
                    } else {
                        player.sendMessage(miniMessage.deserialize("<red>Please use <gold>/flyspeed <1-100>!"));
                    }
                }else
                    player.sendMessage(miniMessage.deserialize("<red>You have no permission to Fly!"));
            } else
                player.sendMessage(miniMessage.deserialize("<red>You have no permission for that command!"));
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }
}
