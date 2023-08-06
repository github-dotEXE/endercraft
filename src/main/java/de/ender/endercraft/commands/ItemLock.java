package de.ender.endercraft.commands;

import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemLock implements CommandExecutor {

    ArrayList<String> Lore = new ArrayList<>();

    @Deprecated
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.itemlock")) {
                if(args.length == 1) {

                    ItemStack item = player.getItemInHand();
                    ItemMeta itemMeta = item.getItemMeta();
                    Lore.add("§c" + player.getName() + "§6 | §4" + args[0]);
                    itemMeta.setLore(Lore);
                    item.setItemMeta(itemMeta);
                    Lore.remove("§c" + player.getName() + "§6 | §4" + args[0]);

                } else
                    player.sendMessage("§cPlease use §6/lore <Code>§c!");
            }else
                player.sendMessage("§cYou have no permission for that command!");
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
