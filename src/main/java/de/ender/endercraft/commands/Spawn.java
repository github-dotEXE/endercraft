package de.ender.endercraft.commands;

import de.ender.core.CConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.ender.endercraft.Main;
import org.jetbrains.annotations.NotNull;

public class Spawn implements CommandExecutor {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.spawn")) {
                if(args.length == 0) {

                    FileConfiguration configset = new CConfig(Main.getPlugin().SPAWNCONFIG, Main.getPlugin()).getCustomConfig();
                    Location loc = (Location) configset.get("utils.spawn");
                    if(loc != null) {
                        player.teleport(loc);
                        player.sendMessage(miniMessage.deserialize("<green>You are now at Spawn!"));
                    } else {
                        player.sendMessage(miniMessage.deserialize("<gold>Something went wrong: <yellow>'Try to set Spawn first!'"));
                    }

                } else
                    player.sendMessage(miniMessage.deserialize("<red>Please use <gold>/setspawn<red>!"));
            }else
                player.sendMessage(miniMessage.deserialize("<red>You have no permission for that command!"));
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
