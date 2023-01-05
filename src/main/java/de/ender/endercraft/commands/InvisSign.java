package de.ender.endercraft.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class InvisSign implements CommandExecutor {
    @Deprecated
    public boolean onCommand(CommandSender sender, Command command, String label,String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("EnderCraft.floatsign")) {
                if(args.length >= 1) {
                    World world = Bukkit.getWorld("world");
                    Entity entity = world.spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
                    entity.setCustomNameVisible(true);
                    entity.setCustomName("§" + args[0] + String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
                    entity.setInvulnerable(true);
                    entity.setGravity(false);
                    ((LivingEntity) entity).setInvisible(true);
                }else
                    player.sendMessage("§ePlease use '/floatsign <Color> <..TEXT..>'");
            }
        }
        return false;
    }
}
