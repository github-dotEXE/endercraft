package de.ender.endercraft;

import de.ender.core.MCore;
import de.ender.endercraft.commands.*;
import de.ender.endercraft.listeners.OnDeathListener;
import de.ender.endercraft.listeners.OnPlayerJoinListener;
import de.ender.endercraft.listeners.OnPlayerLeaveListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Date;

public class Main extends JavaPlugin {
    static int TaskID;
    private static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        FileConfiguration config = Main.getPlugin().getConfig();

        new MCore().log(ChatColor.AQUA + "Starting EnderCraft...");

        getCommand("heal").setExecutor(new HealCommand());
        getCommand("sudo").setExecutor(new Sudo());
        getCommand("flyspeed").setExecutor(new FlySpeed());
        getCommand("fly").setExecutor(new Fly());
        getCommand("godmode").setExecutor(new Godmode());
        getCommand("ec").setExecutor(new Enderchest());
        getCommand("craft").setExecutor(new Craft());
        getCommand("invsee").setExecutor(new Invsee());
        getCommand("vanish").setExecutor(new Vanish());
        getCommand("chat").setExecutor(new Chat());
        getCommand("enchantitem").setExecutor(new Enchant());
        getCommand("position").setExecutor(new Position());
        getCommand("setspawn").setExecutor(new SetSpawn());
        getCommand("delspawn").setExecutor(new DelSpawn());
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("realtime").setExecutor(new RealTime());
        getCommand("rename").setExecutor(new Rename());
        getCommand("itemlock").setExecutor(new ItemLock());
        getCommand("repair").setExecutor(new Repair());
        getCommand("floatsign").setExecutor(new InvisSign());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new OnPlayerJoinListener(), this);
        pluginManager.registerEvents(new OnPlayerLeaveListener(), this);
        pluginManager.registerEvents(new OnDeathListener(), this);

        //realtime


        if(config.getBoolean("realtime")) {
            TaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {

                @Deprecated
                @Override
                public void run() {

                    long time = 6000;
                    Date date = new Date();

                    int dateticksH = date.getHours() * 1000;
                    int dateticksM = date.getMinutes() + date.getMinutes() / 3 * 2;
                    int dateticksM2 = dateticksM * 10;
                    int dateticks = dateticksH + dateticksM2;

                    time = dateticks  - 8000;

                    Bukkit.getWorld("world").setTime(time);


                }
            }, 0, 10*20);
        }

        //configs und so
        if(config.getString("messages.joinEXTRA") != null ) {
        } else {
            config.set("messages.joinEXTRA", "");
            Main.getPlugin().saveConfig();
        }

    }

    public void onDisable() {
        new MCore().log(ChatColor.AQUA + "Stopping EnderCraft...");
    }

    public static Main getPlugin() {
        return plugin;

    }

}
