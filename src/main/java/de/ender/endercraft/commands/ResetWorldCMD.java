package de.ender.endercraft.commands;

import de.ender.core.CConfig;
import de.ender.core.guiManagers.BoolGUI;
import de.ender.endercraft.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResetWorldCMD implements @Nullable CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if(!player.hasPermission("endercraft.resetworld")) return false;
        new BoolGUI(player,"Are you sure you want to reset this/these worlds?",false,reset ->{
            if(reset) {
                CConfig cconfig = new CConfig("reset", Main.getPlugin());
                FileConfiguration config = cconfig.getCustomConfig();

                if (args.length == 1) config.set("reset." + args[0], true);
                else Bukkit.getWorlds().forEach(world -> config.set("reset." + world.getName(), true));
                cconfig.save();

                Bukkit.spigot().restart();
            }
        });
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if(args.length == 1) {
            Bukkit.getWorlds().forEach(world -> commands.add(world.getName()));
        }

        StringUtil.copyPartialMatches(args[args.length-1], commands, completions); //copy matches of first argument

        Collections.sort(completions);//sort the list
        return completions;
    }
}
