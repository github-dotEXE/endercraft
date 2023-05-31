package de.ender.endercraft.commands;

import de.ender.endercraft.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class Vanish implements CommandExecutor {

    ArrayList<String> Players = new ArrayList<>();
    FileConfiguration config = Main.getPlugin().getConfig();
    @SuppressWarnings("deprecation")
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.vanish")) {
                if(args.length == 0) {
                    if(Players.contains(player.getName())) {
                        Players.remove(player.getName());
                        for(Player all : Bukkit.getOnlinePlayers()) {
                            all.showPlayer(player);
                        }
                        player.sendMessage("§aYou are §cno §alonger in §6vanish§a!!!");
                    } else {
                        Players.add(player.getName());
                        for(Player all : Bukkit.getOnlinePlayers()) {
                            all.hidePlayer(player);
                        }
                        player.sendMessage("§aYou are §2now §ain §6vanish§a!!!");
                    }
                } else if(args.length == 1) {
                    String silence = args[0];
                    if(Objects.equals(args[0], silence)) {
                        if(Players.contains(player.getName())) {
                            Players.remove(player.getName());
                            for(Player all : Bukkit.getOnlinePlayers()) {
                                all.showPlayer(player);
                            }
                            Bukkit.broadcastMessage(config.getString("endercraft.login"));
                            player.sendMessage("§aYou are §cno §alonger in §6vanish§a!!!");
                        } else {
                            Players.add(player.getName());
                            for(Player all : Bukkit.getOnlinePlayers()) {
                                all.hidePlayer(player);
                            }
                            Bukkit.broadcastMessage(config.getString("endercraft.logout"));
                            player.sendMessage("§aYou are §2now §ain §6vanish§a!!!");
                        }

                    } else
                        player.sendMessage("§cPlease use §6/vanish§c!");

                } else
                    player.sendMessage("§cPlease use §6/vanish§c!");
            } else
                player.sendMessage("§cYou have no permission for that command!");

        }else
            sender.sendMessage("That Command is only for Players");
        return false;


    }
}
