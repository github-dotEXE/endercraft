package de.ender.endercraft.commands;

import de.ender.core.CConfig;
import de.ender.endercraft.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Vanish implements CommandExecutor, Listener {

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        CConfig cconfig = new CConfig("vanish.yml", Main.getPlugin());
        FileConfiguration config = cconfig.getCustomConfig();
        if(!(sender instanceof Player)||!sender.hasPermission("endercraft.vanish")) return false;
        Player player = ((Player) sender);
        List<Player> players = (List<Player>) config.getList("inVanish",new ArrayList<>());

        if(!players.contains(player)) {
            players.add(player);
            player.sendMessage(ChatColor.GOLD+"You are now in vanish!");
            for (Player p:
                    Bukkit.getOnlinePlayers()) {
                p.hidePlayer(Main.getPlugin(),player);
            }
        } else {
            players.remove(player);
            player.sendMessage(ChatColor.GOLD+"You are no longer in vanish!");
            for (Player p:
                    Bukkit.getOnlinePlayers()) {
                p.showPlayer(Main.getPlugin(),player);
            }
        }

        config.set("inVanish", players);
        cconfig.save();
        return true;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        CConfig cconfig = new CConfig("vanish.yml", Main.getPlugin());
        FileConfiguration config = cconfig.getCustomConfig();

        Player player = event.getPlayer();
        if(config.getList("inVanish",new ArrayList<>()).contains(player)){
            player.sendMessage(ChatColor.GOLD+"You are still in vanish!");
            for (Player p:
                    Bukkit.getOnlinePlayers()) {
                p.hidePlayer(Main.getPlugin(),player);
            }
        }
        for (Object pivo:
            config.getList("inVanish",new ArrayList<>())) {
            if(pivo instanceof Player){
                player.hidePlayer(Main.getPlugin(),((Player) pivo));
            }
        }
    }
}
