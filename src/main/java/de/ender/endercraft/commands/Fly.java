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
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Fly implements CommandExecutor, Listener {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(!(sender instanceof Player)||!sender.hasPermission("endercraft.fly")) return false;

        Player player = ((Player) sender);

        if(!player.getAllowFlight()) {
            add(player);
            player.sendMessage(ChatColor.GOLD+"You can fly now!");
            player.setAllowFlight(true);
        } else {
            remove(player);
            player.sendMessage(ChatColor.GOLD+"You can no longer fly!");
            player.setAllowFlight(false);
        }
        return true;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        CConfig cconfig = new CConfig("fly.yml", Main.getPlugin());
        FileConfiguration config = cconfig.getCustomConfig();
        player.setAllowFlight(config.getList("inFly").contains(player));
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        if(!event.getPlayer().getAllowFlight()) remove(event.getPlayer());
        else add(event.getPlayer());
    }

    private void add(Player player){
        CConfig cconfig = new CConfig("fly.yml", Main.getPlugin());
        FileConfiguration config = cconfig.getCustomConfig();
        List<Player> players = (List<Player>) config.getList("inFly",new ArrayList<>());
        players.add(player);
        config.set("inFly", players);
        cconfig.save();
    }
    private void remove(Player player){
        CConfig cconfig = new CConfig("fly.yml", Main.getPlugin());
        FileConfiguration config = cconfig.getCustomConfig();
        List<Player> players = (List<Player>) config.getList("inFly",new ArrayList<>());
        players.remove(player);
        config.set("inFly", players);
        cconfig.save();
    }
}
