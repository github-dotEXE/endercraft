package de.ender.endercraft.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.ender.endercraft.Main;

public class OnPlayerLeaveListener implements Listener {
    FileConfiguration config = Main.getPlugin().getConfig();
    @Deprecated
    @EventHandler
    public void onPlayerQuit (PlayerQuitEvent event) {

        if(config.getBoolean("endercraft.BInOut")) {
            event.setQuitMessage("[§c-§r] " + event.getPlayer().getName());
        }
        config.set("endercraft.logout", event.getQuitMessage());
        Main.getPlugin().saveConfig();


    }
}
