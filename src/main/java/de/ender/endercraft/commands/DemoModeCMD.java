package de.ender.endercraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DemoModeCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player)||!sender.hasPermission("endercraft.demomode")) return false;
        if(args.length != 1) ((Player) sender).showDemoScreen();
        else if (args[0].equals("ALL")) {
            Bukkit.getOnlinePlayers().forEach(Player::showDemoScreen);
        } else {
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) return false;
            player.showDemoScreen();
        }
        return true;
    }
}
