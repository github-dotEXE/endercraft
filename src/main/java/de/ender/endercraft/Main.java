package de.ender.endercraft;

import de.ender.core.CConfig;
import de.ender.core.Log;
import de.ender.core.UpdateChecker;
import de.ender.endercraft.commands.*;
import de.ender.endercraft.listeners.OnDeathListener;
import de.ender.endercraft.listeners.OnPlayerJoinListener;
import de.ender.endercraft.listeners.OnPlayerLeaveListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {
    private static Main plugin;

    public String SPAWNCONFIG = "spawn_location";
    public String DEFAULTCONFIG = "configuration";

    @Override
    public void onLoad() {
        CConfig cconfig = new CConfig("reset", this);
        FileConfiguration config = cconfig.getCustomConfig();

        Bukkit.getWorlds().forEach(world ->{
            if(!config.getBoolean("reset."+world.getName())) return;
            File worldFile = new File(Bukkit.getWorldContainer(), world.getName());
            try {
                FileUtils.deleteDirectory(worldFile);
                config.set("reset."+world.getName(),false);
                cconfig.save();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void onEnable() {
        plugin = this;
        FileConfiguration config = Main.getPlugin().getConfig();
        Log.log(ChatColor.AQUA + "Enabling EnderCraft...");
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
        getCommand("itemlock").setExecutor(new ItemLock());
        getCommand("repair").setExecutor(new Repair());
        getCommand("floatsign").setExecutor(new InvisSign());
        getCommand("glide").setExecutor(new Glide());
        getCommand("statistics").setExecutor(new StatisticsCMD());
        getCommand("statistics").setTabCompleter(new StatisticsCMD());
        getCommand("advancementprogress").setExecutor(new AdvancementCMD());
        getCommand("advancementprogress").setTabCompleter(new AdvancementCMD());
        getCommand("nick").setExecutor(new NickCMD());
        getCommand("clr").setExecutor(new ClrCommand());
        getCommand("i").setExecutor(new GiveCMD());
        getCommand("resetworld").setExecutor(new ResetWorldCMD());
        getCommand("resetworld").setTabCompleter(new ResetWorldCMD());
        getCommand("demomode").setExecutor(new DemoModeCMD());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new OnPlayerJoinListener(), this);
        pluginManager.registerEvents(new Glide(), this);
        pluginManager.registerEvents(new OnPlayerLeaveListener(), this);
        pluginManager.registerEvents(new OnDeathListener(), this);

        //realtime
        RealTime.init();

        //configs und so
        if (config.getString("messages.joinEXTRA") == null) {
            config.set("messages.joinEXTRA", "");
            Main.getPlugin().saveConfig();
        }

    }

    public void onDisable() {
        FileConfiguration config = Main.getPlugin().getConfig();
        config.set("glide.uuids",null);
        Main.getPlugin().saveConfig();
        Log.log(ChatColor.AQUA + "Disabling EnderCraft...");
    }

    public static Main getPlugin() {
        return plugin;

    }

}
