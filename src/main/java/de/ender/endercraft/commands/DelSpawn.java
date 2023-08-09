package de.ender.endercraft.commands;

import de.ender.core.CConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.ender.endercraft.Main;
import org.jetbrains.annotations.NotNull;

public class DelSpawn implements CommandExecutor {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.delspawn")) {
                if(args.length == 0) {
                    CConfig cconfig = new CConfig(Main.getPlugin().SPAWNCONFIG, Main.getPlugin());
                    FileConfiguration configset = cconfig.getCustomConfig();
                    configset.set("utils.spawn", null);
                    player.sendMessage(miniMessage.deserialize("<red>You Deleted the Spawn!"));
                    cconfig.save();

                } else
                    player.sendMessage(miniMessage.deserialize("<red>Please use <gold>/delspawn<red>!"));
            }else
                player.sendMessage(miniMessage.deserialize("<red>You have no permission for that command!"));
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
