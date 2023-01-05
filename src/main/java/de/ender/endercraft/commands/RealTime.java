package de.ender.endercraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.ender.endercraft.Main;

public class RealTime implements CommandExecutor {

    @Deprecated
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("EnderCraft.realtime")) {
                if(args.length == 0) {
                    FileConfiguration config = Main.getPlugin().getConfig();
                    if(config.getBoolean("realtime")) {
                        config.set("realtime", false);
                        Main.getPlugin().saveConfig();
                        Bukkit.broadcastMessage("§aRealTime §4OFF");
                    } else {
                        config.set("realtime", true);
                        Main.getPlugin().saveConfig();
                        Bukkit.broadcastMessage("§aRealTime §2ON");
                        player.sendMessage("§eYou have to restart or reload your Server to make the changes");
                        player.sendMessage("§eIt works best if the doDaylightCycle gamerule is set to false");
                    }

                } else
                    player.sendMessage("§cPlease use §6/realtime§c!");
            }else
                player.sendMessage("§cYou have no permission for that command!");
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
