package de.ender.endercraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.ender.endercraft.Main;

public class DelSpawn implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("EnderCraft.delspawn")) {
                if(args.length == 0) {

                    FileConfiguration configset = Main.getPlugin().getConfig();
                    configset.set("utils.spawn", null);
                    player.sendMessage("§cYou Deleted the Spawn!");
                    Main.getPlugin().saveConfig();

                } else
                    player.sendMessage("§cPlease use §6/delspawn§c!");
            }else
                player.sendMessage("§cYou have no permission for that command!");
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
