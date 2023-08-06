package de.ender.endercraft.commands;

import de.ender.core.CConfig;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.ender.endercraft.Main;
import org.jetbrains.annotations.NotNull;

public class SetSpawn implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.setspawn")) {
                if(args.length == 0) {

                    CConfig cconfig = new CConfig(Main.getPlugin().SPAWNCONFIG, Main.getPlugin());
                    FileConfiguration configset = cconfig.getCustomConfig();
                    Location loc = player.getLocation();
                    configset.set("utils.spawn", loc);
                    player.sendMessage("§aYou set the Spawn to §b" + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + "§a!");
                    cconfig.save();

                } else
                    player.sendMessage("§cPlease use §6/setspawn§c!");
            }else
                player.sendMessage("§cYou have no permission for that command!");
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
