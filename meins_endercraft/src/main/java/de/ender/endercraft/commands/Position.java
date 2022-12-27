package de.ender.endercraft.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import de.ender.endercraft.Main;



public class Position implements CommandExecutor {

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
                        player.sendMessage("§aYou set §6POSITION §r" + args[1] + " §aat §b" + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + "§a!");
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
                        player.sendMessage("§aThe §6POSITION §r" + args[1] + " §ais at §b" + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + "§9 (" + Dir + ")§a!");
                    }else if(args[0].equals("del")) {

                        FileConfiguration configdel = Main.getPlugin().getConfig();
                        configdel.set("Position." + args[1], null);
                        Main.getPlugin().saveConfig();
                        player.sendMessage("§cYou've Deleted the Position §6" + args[1]);

                    } else {
                        player.sendMessage("§cPlease use §6/position set|get|del|list|pos §b" + player.getName() + "§c!!!");
                    }
                }else if(args.length == 1) {

                    if(args[0].equals("list")) {

                        FileConfiguration configset = Main.getPlugin().getConfig();
                        player.sendMessage("§5" + configset.getConfigurationSection("Position").getKeys(false).toString());

                    }

                }else if(args.length == 4) {

                    if(args[0].equals("pos")) {

                        Location loc = new Location(player.getWorld(), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
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
                        player.sendMessage("§a The Positon is§9 (" + Dir + ")§a from here!");


                    }

                }else
                    player.sendMessage("§cPlease use §6/position set|get|del|list|pos §b" + player.getName() + "§c!!!");
            }
        }else
            sender.sendMessage("That Command is only for Players");
        return false;
    }

}
