package me.tiago0liveira.TiagoUtils.commands;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.Permissions;
import me.tiago0liveira.TiagoUtils.enums.configs.Default;

import me.tiago0liveira.TiagoUtils.helpers.FileManager;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Home implements TabExecutor {

    private static File file;
    private static FileConfiguration HomeConfig;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (TiagoUtils.PermManager.hasPermission(player, Permissions.Commands.Home) || player.isOp()) {
                if (TiagoUtils.options.getConfigurationSection(Default.SectionCommands).getBoolean(Default.commands.Home)) {
                    String path = me.tiago0liveira.TiagoUtils.enums.configs.Home.ListHomes + "." + player.getUniqueId().toString();
                    if (args.length < 1) {
                        if (HomeConfig.get(path) != null) {
                            try {
                                Double x = (Double) HomeConfig.get(path + ".x");
                                Double y = (Double) HomeConfig.get(path + ".y");
                                Double z = (Double) HomeConfig.get(path + ".z");
                                Double eye_x = (Double) HomeConfig.get(path + ".direction-x");
                                Double eye_y = (Double) HomeConfig.get(path + ".direction-y");
                                Double eye_z = (Double) HomeConfig.get(path + ".direction-z");
                                Vector dir = new Vector(eye_x, eye_y, eye_z);
                                player.teleport(new Location(player.getWorld() ,x,y,z).setDirection(dir));
                            } catch (NullPointerException e) {
                                System.err.println(e);
                                noHome(player);
                            }
                        } else {
                            noHome(player);
                        }
                    } else {
                        switch (args[0]) {
                            case "set":
                                HomeConfig.set(path + ".x", player.getLocation().getX());
                                HomeConfig.set(path + ".y", player.getLocation().getY());
                                HomeConfig.set(path + ".z", player.getLocation().getZ());
                                HomeConfig.set(path + ".direction-x", player.getEyeLocation().getDirection().getX());
                                HomeConfig.set(path + ".direction-y", player.getEyeLocation().getDirection().getY());
                                HomeConfig.set(path + ".direction-z", player.getEyeLocation().getDirection().getZ());
                                try {
                                    HomeConfig.save(file);
                                    player.sendMessage(ChatColor.DARK_AQUA + "INFO " + "|" + ChatColor.WHITE + " New Home " + ChatColor.DARK_AQUA + "Configured!");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "reload":
                                /* NEEDS PERMS TO RELOAD !! */
                                setup();
                                player.sendMessage(ChatColor.YELLOW + "INFO" + ChatColor.DARK_GRAY + " | " + ChatColor.GRAY + "Homes Reloaded");
                                break;
                            default:
                                player.sendMessage(ChatColor.RED + args[0] + ChatColor.WHITE + " does not exist!");
                                player.sendMessage("Available Commands: ");
                                for (String s : Arrays.asList("", "Set", "Reload")) {
                                    TextComponent em = new TextComponent();
                                    em.setText("  - " + ChatColor.GRAY + "/home " + ChatColor.YELLOW + s);
                                    em.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/home" + s));
                                    em.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{
                                            new TextComponent("/home " + s + "\n"),
                                            new TextComponent(ChatColor.GRAY + "[" + ChatColor.YELLOW + " CLICK ME " + ChatColor.GRAY + "]")
                                    }));
                                    player.spigot().sendMessage(em);
                                }
                                break;
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.DARK_GRAY + "The command "+ ChatColor.WHITE + "Home" + ChatColor.DARK_GRAY + " is " + ChatColor.RED + "disabled" + ChatColor.DARK_GRAY + " atm!");
                }
            } else {
                player.sendMessage(ChatColor.DARK_GRAY + "You need " + ChatColor.RED + "permission" + ChatColor.DARK_GRAY + " to use this command!");
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length > 1) {
            return null;
        } else {
            return Arrays.asList("reload", "set");
        }
    }

    public static void setup() {
        file = FileManager.getYMLFile("Home.yml");
        HomeConfig = YamlConfiguration.loadConfiguration(file);
    }

    private static void noHome(Player p) {
        TextComponent mainTc  = new TextComponent();
        mainTc.setText(ChatColor.GRAY + "NO " + ChatColor.RED + "HOME" + ChatColor.GRAY + " WAS FOUND IN YOU NAME!");
        TextComponent tc = new TextComponent();
        tc.setText(ChatColor.YELLOW + " [ " + ChatColor.DARK_GRAY + "CLICK ME" + ChatColor.YELLOW + " ]");
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/home set"));
        tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{
                new TextComponent(ChatColor.DARK_AQUA + "Create New Home(where you at)!")
        }));
        mainTc.addExtra(tc);
        p.spigot().sendMessage(mainTc);
    }
}
