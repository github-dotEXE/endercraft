package de.ender.endercraft;

import de.ender.core.Log;
import de.ender.core.UpdateChecker;
import de.ender.endercraft.commands.*;
import de.ender.endercraft.customItems.Sandwich;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main plugin;

    public String SPAWNCONFIG = "spawn_location";
    public String DEFAULTCONFIG = "configuration";

    @Override
    public void onEnable() {
        plugin = this;
        FileConfiguration config = Main.getPlugin().getConfig();
        Log.enable(this);
        new UpdateChecker(this,"github-dotEXE","endercraft","main").check().downloadLatestMeins();

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
        getCommand("repair").setExecutor(new Repair());
        getCommand("glide").setExecutor(new Glide());
        getCommand("statistics").setExecutor(new StatisticsCMD());
        getCommand("statistics").setTabCompleter(new StatisticsCMD());
        getCommand("advancementprogress").setExecutor(new AdvancementCMD());
        getCommand("advancementprogress").setTabCompleter(new AdvancementCMD());
        getCommand("nick").setExecutor(new NickCMD());
        getCommand("clr").setExecutor(new ClrCommand());
        getCommand("i").setExecutor(new GiveCMD());
        getCommand("demomode").setExecutor(new DemoModeCMD());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new Glide(), this);
        pluginManager.registerEvents(new Vanish(), this);
        pluginManager.registerEvents(new Fly(), this);

        //realtime
        RealTime.init();

        //configs und so
        if (config.getString("messages.joinEXTRA") == null) {
            config.set("messages.joinEXTRA", "");
            Main.getPlugin().saveConfig();
        }

        new Sandwich().init();
    }

    public void onDisable() {
        FileConfiguration config = Main.getPlugin().getConfig();
        config.set("glide.uuids",null);
        Main.getPlugin().saveConfig();
        Log.disable(this);
    }

    public static Main getPlugin() {
        return plugin;

    }

}
