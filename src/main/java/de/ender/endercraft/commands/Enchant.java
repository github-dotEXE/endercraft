package de.ender.endercraft.commands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Enchant implements CommandExecutor {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public boolean onCommand(CommandSender sender, Command command, String label,String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.enchantitem")) {
                if(args.length == 2) {

                    ItemStack item = player.getItemInHand();
                    ItemMeta itemMeta = item.getItemMeta();
                    itemMeta.addEnchant(Enchantment.getByKey(NamespacedKey.minecraft(args[0])), Integer.parseInt(args[1]), true);
                    item.setItemMeta(itemMeta);

                } else
                    player.sendMessage(miniMessage.deserialize("<red>Please use <gold>/enchantitem <enchantment> <level><red>!"));
            }else
                player.sendMessage(miniMessage.deserialize("<red>You have no permission for that command!"));
        }else
            sender.sendMessage("That Command is only for Players");

        return false;
    }

}
