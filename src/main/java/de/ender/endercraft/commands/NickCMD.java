package de.ender.endercraft.commands;

import de.ender.core.NickManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NickCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("endercraft.nick")) return false;

        Player player = null;
        if(sender instanceof Player) player = (Player) sender;
        switch (args.length) {
            case 1:
                if (sender instanceof Player) {
                    NickManager.changeSkin(player, args[0]);
                    NickManager.changeName(player, args[0]);
                }
                break;
            case 2:
                player = Bukkit.getPlayer(args[0]);
                if (player == null) return false;
                NickManager.changeSkin(player, args[1]);
                NickManager.changeName(player, args[1]);
                break;
            default:
                if (sender instanceof Player) {
                    NickManager.resetSkin(player);
                    NickManager.resetName(player);
                }
                break;
        }

        return true;
    }
}
