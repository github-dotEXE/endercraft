package de.ender.endercraft.commands;

import de.ender.endercraft.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Glide implements Listener, CommandExecutor {
    private final FileConfiguration config = Main.getPlugin().getConfig();
    private List<UUID> uuids = new ArrayList<>();


    @EventHandler
    public void onFall(PlayerMoveEvent event) {
        if(config.get("glide.uuids") !=null) this.uuids = (List<UUID>) config.get("glide.uuids");
        Player player = event.getPlayer();
        if(uuids.contains(player.getUniqueId())) {
            if(player.getFallDistance() >= 1.5) {
                player.setGliding(true);
            }
        }
    }
    @EventHandler
    public void onToggleGlide(EntityToggleGlideEvent event) {
        if(config.get("glide.uuids") !=null) this.uuids = (List<UUID>) config.get("glide.uuids");
        if(event.getEntity() instanceof Player && !event.isGliding()) {
            Player player = (Player) event.getEntity();
            if(uuids.contains(player.getUniqueId())) {
                if (!player.isOnGround() && (player.getInventory().getChestplate() == null || player.getInventory().getChestplate().getType() != Material.ELYTRA)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(config.get("glide.uuids") !=null) this.uuids = (List<UUID>) config.get("glide.uuids");
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.glide")) {
                UUID uuid = player.getUniqueId();
                if(uuids.contains(uuid)) {
                    uuids.remove(uuid);
                    player.sendMessage(ChatColor.GOLD + "Glide Off");
                } else {
                    uuids.add(uuid);
                    player.sendMessage(ChatColor.GREEN + "Glide On");
                }
                config.set("glide.uuids",uuids);
                Main.getPlugin().saveConfig();
            }
        }
        return false;
    }
}
