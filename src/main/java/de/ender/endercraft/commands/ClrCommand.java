package de.ender.endercraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClrCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player) || !sender.hasPermission("endercraft.clr")) return false;
        Player pSender = (Player) sender;
        if(args.length == 0) pSender.getInventory().clear();
        else if(args.length == 1){
            Player aPlayer = Bukkit.getPlayer(args[0]);
            if(args[0].equals("ALL")) Bukkit.getOnlinePlayers().forEach((iPlayer)-> iPlayer.getInventory().clear());
            else if(aPlayer != null) aPlayer.getInventory().clear();
            else pSender.sendMessage(ChatColor.GOLD+"Player isn't online!");
        } else pSender.sendMessage(ChatColor.GOLD+"Incorrect use of command!");
        return false;
    }
}
