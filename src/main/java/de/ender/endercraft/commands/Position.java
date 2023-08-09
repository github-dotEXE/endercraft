package de.ender.endercraft.commands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import de.ender.endercraft.Main;



public class Position implements CommandExecutor {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    static String Dir = "";

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("endercraft.position")) {
                if(args.length == 2) {

                    if(args[0].equals("set")) {
                        FileConfiguration configset = Main.getPlugin().getConfig();
                        Location loc = player.getLocation();
                        configset.set("Position." + args[1], loc);
                        Main.getPlugin().saveConfig();
                        player.sendMessage(miniMessage.deserialize("<green>You set §6POSITION <reset>" + args[1] +
                                " <green>at <aqua>" + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + "<green>!"));
                    } else if(args[0].equals("get")) {
                        FileConfiguration configget = Main.getPlugin().getConfig();
                        Location loc = configget.getLocation("Position." + args[1]);
                        Vector v = loc.toVector().subtract(player.getLocation().toVector());
                        Location loc2 = player.getLocation();
                        loc2.setDirection(v);
                        double rotation = (player.getLocation().getYaw() - 90) % 360;
                        if (rotation < 0) {
                            rotation += 360.0;
                        }
                        if (0 <= rotation && rotation < 22.5) {
                            Dir = "WEST";
                        } else if (22.5 <= rotation && rotation < 67.5) {
                            Dir = "NORTHWEST";
                        } else if (67.5 <= rotation && rotation < 112.5) {
                            Dir = "NORTH";
                        } else if (112.5 <= rotation && rotation < 157.5) {
                            Dir = "NORTHEAST";
                        } else if (157.5 <= rotation && rotation < 202.5) {
                            Dir = "EAST";
                        } else if (202.5 <= rotation && rotation < 247.5) {
                            Dir = "SOUTHEAST";
                        } else if (247.5 <= rotation && rotation < 292.5) {
                            Dir = "SOUTH";
                        } else if (292.5 <= rotation && rotation < 337.5) {
                            Dir = "SOUTHWEST";
                        } else if (337.5 <= rotation && rotation < 360.0) {
                            Dir = "WEST";
                        } else {
                            Dir = "";
                        }
                        player.teleport(loc2);
                        player.sendMessage(miniMessage.deserialize("<green>The <gold>POSITION <reset>" + args[1] +
                                " <green>is at <aqua>" + loc.getBlockX() + " " +
                                loc.getBlockY() + " " + loc.getBlockZ() + "<dark_gray> (" + Dir + ")<green>!"));
                    }else if(args[0].equals("del")) {

                        FileConfiguration configdel = Main.getPlugin().getConfig();
                        configdel.set("Position." + args[1], null);
                        Main.getPlugin().saveConfig();
                        player.sendMessage(miniMessage.deserialize("<red>You've Deleted the Position <gold>" + args[1]));

                    } else {
                        player.sendMessage(miniMessage.deserialize("<red>Please use <gold>/position set|get|del|list|pos <aqua>"
                                + player.getName() + "<red>!!!"));
                    }
                }else if(args.length == 1) {

                    if(args[0].equals("list")) {

                        FileConfiguration configset = Main.getPlugin().getConfig();
                        player.sendMessage(miniMessage.deserialize("<dark_green>" +
                                configset.getConfigurationSection("Position").getKeys(false)));

                    }

                }else if(args.length == 4) {

                    if(args[0].equals("pos")) {

                        Location loc = new Location(player.getWorld(), Double.parseDouble(args[1]), Double.parseDouble(args[2]),
                                Double.parseDouble(args[3]));
                        Vector v = loc.toVector().subtract(player.getLocation().toVector());
                        Location loc2 = player.getLocation();
                        loc2.setDirection(v);
                        double rotation = (player.getLocation().getYaw() - 90) % 360;
                        if (rotation < 0) {
                            rotation += 360.0;
                        }
                        if (0 <= rotation && rotation < 22.5) {
                            Dir = "WEST";
                        } else if (22.5 <= rotation && rotation < 67.5) {
                            Dir = "NORTHWEST";
                        } else if (67.5 <= rotation && rotation < 112.5) {
                            Dir = "NORTH";
                        } else if (112.5 <= rotation && rotation < 157.5) {
                            Dir = "NORTHEAST";
                        } else if (157.5 <= rotation && rotation < 202.5) {
                            Dir = "EAST";
                        } else if (202.5 <= rotation && rotation < 247.5) {
                            Dir = "SOUTHEAST";
                        } else if (247.5 <= rotation && rotation < 292.5) {
                            Dir = "SOUTH";
                        } else if (292.5 <= rotation && rotation < 337.5) {
                            Dir = "SOUTHWEST";
                        } else if (337.5 <= rotation && rotation < 360.0) {
                            Dir = "WEST";
                        } else {
                            Dir = "";
                        }
                        player.teleport(loc2);
                        player.sendMessage(miniMessage.deserialize("<green> The Positon is<gray> (" + Dir + ")<green> from here!"));


                    }

                }else
                    player.sendMessage(miniMessage.deserialize("<red>Please use <gold>/position set|get|del|list|pos <aqua>"
                            + player.getName() + "<red>!!!"));
            }
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
