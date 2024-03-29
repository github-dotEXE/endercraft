package de.ender.endercraft.commands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

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
                    player.sendMessage(miniMessage.deserialize("<green>You have been healed!"));
                } else if(args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target != null) {
                        target.setHealth(target.getMaxHealth());
                        target.setFoodLevel(20);
                        player.setSaturation(20);
                        target.sendMessage(miniMessage.deserialize("<green>You have been healed!"));
                        player.sendMessage(miniMessage.deserialize("<green>You've healed the Player <gold>" + target.getName()));
                    } else
                        player.sendMessage(miniMessage.deserialize("<red>The Player <gold>" + args[0] + " <red> isn't online!"));
                } else
                    player.sendMessage(miniMessage.deserialize("<red>Please use <gold>/heal <Player><red>!"));

            } else
                player.sendMessage(miniMessage.deserialize("<red>You have no permission for that command!"));

        } else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
