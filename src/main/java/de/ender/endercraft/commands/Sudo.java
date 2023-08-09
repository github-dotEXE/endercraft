package de.ender.endercraft.commands;

import java.util.Arrays;

import javax.annotation.Nullable;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sudo implements CommandExecutor {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    public boolean onCommand(CommandSender sender, Command command, String label,String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.sudo")) {
                if(args.length == 1) {

                } else if(args.length >= 2) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target != null) {
                        target.performCommand(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
                        player.sendMessage(miniMessage.deserialize("<green>You've used sudo on <gold>" + target.getName()));
                    } else
                        player.sendMessage(miniMessage.deserialize("<red>The Player <gold>" + args[0] + " <red> isn't online!"));
                } else
                    player.sendMessage(miniMessage.deserialize("<red>Please use <gold>/sudo <Player> <Massage><red>!"));

            } else
                player.sendMessage(miniMessage.deserialize("<red>You have no permission for that command!"));

        } else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
