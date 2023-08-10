package de.ender.endercraft.listeners;

import de.ender.core.CConfig;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.ender.endercraft.Main;

public class OnPlayerJoinListener implements Listener {
    FileConfiguration config = Main.getPlugin().getConfig();

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {

        Player player = event.getPlayer();

        FileConfiguration configset = new CConfig(Main.getPlugin().SPAWNCONFIG, Main.getPlugin()).getCustomConfig();
        Location loc = configset.getLocation("utils.spawn");
        if(loc != null) {
            player.teleport(loc);
        }

        //player.sendMessage("§2Welcome " + player.getName() + "!");

    }

}