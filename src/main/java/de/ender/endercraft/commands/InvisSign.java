package de.ender.endercraft.commands;

import de.ender.endercraft.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class InvisSign implements CommandExecutor {
    @Deprecated
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.floatsign")) {
                if(args.length >= 1) {
                    if(args[0].equals("kill")) {
                        Entity a = getNearestArmorstand(player, 3);
                        if (a == null) {
                            player.sendMessage(ChatColor.RED + "No FloatSigns in reach!");
                            return false;
                        }
                        if ((args.length >= 2)) {
                            if(args[1].equals("a")) {
                                for(Entity e : player.getNearbyEntities(3, 3, 3)) {
                                    if(e instanceof ArmorStand && ((ArmorStand) e).isInvisible()) {
                                        e.remove();
                                    }
                                }
                            } else if (Boolean.parseBoolean(args[1])) {
                                player.sendMessage(ChatColor.GREEN + "FloatSign " + a.getCustomName() + ChatColor.GREEN + " is now killable!");
                                ArmorStand as = ((ArmorStand) a);
                                as.setInvisible(false);
                                Component before = as.customName();
                                as.customName(before.color(NamedTextColor.DARK_RED));
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        as.setInvisible(true);
                                        as.customName(before);
                                    }
                                }.runTaskLater(Main.getPlugin(), 20 * 3);
                                return false;
                            }
                            player.sendMessage(ChatColor.GOLD + "Killed Floatsign " + a.getCustomName());
                            a.remove();
                            return true;
                        }
                    }
                    World world = player.getWorld();

                    String full = String.join(" ", Arrays.copyOfRange(args, 0, args.length)).replace("&", "§");
                    String[] array = full.split("//");
                    for (int i = 0; i <= array.length-1; i++) {
                        String line = array[i];
                        Entity entity = world.spawnEntity(player.getLocation().add(0,-0.25*i,0), EntityType.ARMOR_STAND);
                        ArmorStand a = (ArmorStand) entity;
                        a.setCustomName(line);
                        a.setInvisible(true);
                        a.setGravity(false);
                        a.setCustomNameVisible(true);
                    }
                }else
                    player.sendMessage("§ePlease use '/floatsign <..TEXT..>|kill (true|a)'");
            }
        }
        return false;
    }

    private Entity getNearestArmorstand(Player player,double radius){
        Entity result = null;
        double lastDistance = Double.MAX_VALUE;
        for(Entity e : player.getNearbyEntities(radius, radius, radius)) {
            if(!(e instanceof ArmorStand&& e.customName() != null)){
                continue;
            }

            double distance = player.getLocation().distance(e.getLocation());
            if(distance < lastDistance) {
                lastDistance = distance;
                result = e;
            }
        }

        return result;
    }
}
