package de.ender.endercraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StatisticsCMD implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length <= 1) {
            sender.sendMessage(ChatColor.RED + "Wrong use. See: /help statistics");
            return false;
        }
        Statistic statistic = Statistic.valueOf(args[1]);
        Player target = Bukkit.getPlayer(args[0]);
        if(target == null) {
            sender.sendMessage(ChatColor.RED + "Wrong use. See: /help statistics");
            return false;
        }

        switch(statistic.getType()) {
            case ENTITY:
                if(!args[2].equals("*")) {
                    EntityType entity = EntityType.valueOf(args[2]);
                    sender.sendMessage(ChatColor.DARK_GREEN + args[0] + ": " + args[1] + ": " + args[2] + ": " + target.getStatistic(statistic, entity));
                } else {
                    List<EntityType> entities = Arrays.asList(EntityType.values());
                    for(int i = 0; i<=entities.size()-1;i++){
                        EntityType entity = entities.get(i);
                        if(entity != EntityType.UNKNOWN) {
                            sender.sendMessage(ChatColor.DARK_GREEN + args[0] + ": " + args[1] + ": " + entity.toString() + ": " + target.getStatistic(statistic, entity));
                        }
                    }
                }
                break;
            case ITEM:
            case BLOCK:
                Material material = Material.valueOf(args[2]);
                sender.sendMessage(ChatColor.DARK_GREEN+ args[0] + ": " +args[1] + ": " +args[2] + ": " + target.getStatistic(statistic,material));
                break;
            default:
                sender.sendMessage(ChatColor.DARK_GREEN+ args[0] + ": " +args[1] + ": " + target.getStatistic(statistic));
                break;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if(args.length == 2) {
            List<Statistic> stats = Arrays.asList(Statistic.values());
            for(int i = 0; i<=stats.size()-1;i++){
                commands.add(stats.get(i).toString());
            }
        } else if (args.length == 1) {
            List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
            for(int i = 0; i<=players.size()-1;i++){
                commands.add(players.get(i).getName());
            }
        } else if (args.length == 3) {
            switch(Statistic.valueOf(args[1]).getType()) {
                case ENTITY:
                    List<EntityType> entities = Arrays.asList(EntityType.values());
                    for(int i = 0; i<=entities.size()-1;i++){
                        commands.add(entities.get(i).toString());
                    }
                    break;
                case ITEM:
                case BLOCK:
                    List<Material> materials = Arrays.asList(Material.values());
                    for(int i = 0; i<=materials.size()-1;i++){
                        commands.add(materials.get(i).toString());
                    }
                    break;
            }
        }

        StringUtil.copyPartialMatches(args[args.length-1], commands, completions); //copy matches of first argument

        Collections.sort(completions);//sort the list
        return completions;
    }
}
