package de.ender.endercraft.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.ender.endercraft.Main;

public class Spawn implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("EnderCraft.spawn")) {
                if(args.length == 0) {

                    FileConfiguration configset = Main.getPlugin().getConfig();
                    Location loc = (Location) configset.get("utils.spawn");
                    if(loc != null) {
                        player.teleport(loc);
                        player.sendMessage("§aYou are now at Spawn!");
                    } else {
                        player.sendMessage("§6Something went wrong: §4'Try to set Spawn first!'");
                    }

                } else
                    player.sendMessage("§cPlease use §6/setspawn§c!");
            }else
                player.sendMessage("§cYou have no permission for that command!");
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
