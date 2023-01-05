package de.ender.endercraft.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnDeathListener implements Listener {


    @EventHandler
    public void onDeath (PlayerDeathEvent event) {

        Player player = event.getEntity();
        Location location = player.getLocation();

        //Death Location
        player.sendMessage("§4You're Death location is: " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ());



    }
}
