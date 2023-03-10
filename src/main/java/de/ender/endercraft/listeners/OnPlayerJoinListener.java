package de.ender.endercraft.listeners;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.ender.endercraft.Main;

public class OnPlayerJoinListener implements Listener {
    FileConfiguration config = Main.getPlugin().getConfig();
    @Deprecated
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {

        Player player = event.getPlayer();
        if(config.getBoolean("endercraft.BInOut")) {
            event.setJoinMessage("[§a+§r] " + player.getPlayer().getName());
        }
        config.set("endercraft.login", event.getJoinMessage());
        Main.getPlugin().saveConfig();

        FileConfiguration configget = Main.getPlugin().getConfig();
        Location loc = configget.getLocation("utils.spawn");
        if(loc != null) {
            player.teleport(loc);
        }

        player.sendMessage("§2Welcome " + player.getName() + "!");

        player.sendMessage(config.getString("messages.joinEXTRA"));

    }

}
