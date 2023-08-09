package de.ender.endercraft.commands;

import de.ender.core.CConfig;
import de.ender.endercraft.Main;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class RealTime implements CommandExecutor {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Deprecated
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.realtime")) {
                if(args.length == 0) {
                    Main plugin = Main.getPlugin();
                    CConfig cconfig = new CConfig(plugin.DEFAULTCONFIG, plugin);
                    FileConfiguration config = cconfig.getCustomConfig();
                    if(config.getBoolean("realtime.enabled")) {
                        config.set("realtime.enabled", false);
                        cconfig.save();
                        Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE,true);
                        Bukkit.broadcast(miniMessage.deserialize("<green>RealTime <dark_red>OFF"));
                    } else {
                        config.set("realtime.enabled", true);
                        cconfig.save();
                        init();
                        Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE,false);
                        Bukkit.broadcast(miniMessage.deserialize("<green>RealTime <dark_green>ON"));
                    }

                } else
                    player.sendMessage(miniMessage.deserialize("<red>Please use <gold>/realtime<red>!"));
            }else
                player.sendMessage(miniMessage.deserialize("<red>You have no permission for that command!"));
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

    public static void init(){
        Main plugin = Main.getPlugin();
        CConfig cconfig = new CConfig(plugin.DEFAULTCONFIG,plugin);
        FileConfiguration config = cconfig.getCustomConfig();
        double multiplier = config.getDouble("realtime.multiplier");
        long offset = config.getInt("realtime.offset");
        new BukkitRunnable() {
            @Override
            public void run() {
                Main plugin = Main.getPlugin();
                CConfig cconfig = new CConfig(plugin.DEFAULTCONFIG,plugin);
                FileConfiguration config = cconfig.getCustomConfig();
                if(config.getBoolean("realtime.enabled")) {
                    World world = Bukkit.getWorld("world");
                    long time = System.currentTimeMillis();
                    float tTime = time % 86400000;
                    float mcTime = tTime/3600;
                    long finalTime = (long) (((mcTime+offset)*multiplier)%24000);
                    if (world != null && world.getTime() != finalTime) world.setTime(finalTime);
                } else cancel();
            }
        }.runTaskTimer(plugin,0, (long) (72/multiplier));
    }
}
