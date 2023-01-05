package de.ender.endercraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    @Deprecated
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label,String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.heal")) {
                if(args.length == 0) {
                    player.setHealth(player.getMaxHealth());
                    player.setFoodLevel(20);
                    player.setSaturation(20);
                    player.sendMessage("§aYou have been healed!");
                } else if(args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target != null) {
                        target.setHealth(target.getMaxHealth());
                        target.setFoodLevel(20);
                        player.setSaturation(20);
                        target.sendMessage("§aYou have been healed!");
                        player.sendMessage("§aYou've healed the Player §6" + target.getName());
                    } else
                        player.sendMessage("§cThe Player §6" + args[0] + " §c isn't online!");
                } else
                    player.sendMessage("§cPlease use §6/heal <Player>§c!");

            } else
                player.sendMessage("§cYou have no permission for that command!");

        } else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
