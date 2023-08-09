package de.ender.endercraft.commands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClrCommand implements CommandExecutor {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player) || !sender.hasPermission("endercraft.clr")) return false;
        Player pSender = (Player) sender;
        if(args.length == 0) pSender.getInventory().clear();
        else if(args.length == 1){
            Player aPlayer = Bukkit.getPlayer(args[0]);
            if(args[0].equals("ALL")) Bukkit.getOnlinePlayers().forEach((iPlayer)-> iPlayer.getInventory().clear());
            else if(aPlayer != null) aPlayer.getInventory().clear();
            else pSender.sendMessage(miniMessage.deserialize("<gold>Player isn't online!"));
        } else pSender.sendMessage(miniMessage.deserialize("<gold>Incorrect use of command!"));
        return false;
    }
}
