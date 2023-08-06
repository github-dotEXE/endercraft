package de.ender.endercraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdvancementCMD implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("endercraft.advancement")) return false;
        if(args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Wrong use. See: /help advancementprogress");
            return false;
        }
        NamespacedKey namespacedKey = NamespacedKey.fromString(args[1]);
        if(namespacedKey ==null) {
            sender.sendMessage(ChatColor.RED + "Wrong use. See: /help advancementprogress");
            return false;
        }
        Advancement advancement = Bukkit.getAdvancement(namespacedKey);
        Player target = Bukkit.getPlayer(args[0]);
        if(advancement ==  null || target ==  null) {
            sender.sendMessage(ChatColor.RED + "Wrong use. See: /help advancementprogress");
            return false;
        }
        AdvancementProgress progress = target.getAdvancementProgress(advancement);
        if(progress.isDone()) {
            sender.sendMessage(ChatColor.GOLD+ args[0] + " has already finished " +args[1] + "!");
            return true;
        }
        sender.sendMessage(ChatColor.DARK_GREEN+ args[0] + ": " +args[1] + ": Missing: "+ChatColor.GREEN + progress.getRemainingCriteria());
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if(args.length == 2) {
            Bukkit.advancementIterator().forEachRemaining(advancement -> commands.add(advancement.getKey().toString()));
        } else if (args.length == 1) {
            List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
            for(int i = 0; i<=players.size()-1;i++){
                commands.add(players.get(i).getName());
            }
        }

        StringUtil.copyPartialMatches(args[args.length-1], commands, completions); //copy matches of first argument

        Collections.sort(completions);//sort the list
        return completions;
    }
}
