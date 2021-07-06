package me.tiago0liveira.TiagoUtils.commands;

import me.tiago0liveira.TiagoUtils.TiagoUtils;

import me.tiago0liveira.TiagoUtils.enums.Permissions;
import me.tiago0liveira.TiagoUtils.enums.configs.Default;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static me.tiago0liveira.TiagoUtils.helpers.ExtraStringMethods.someEqualsIgnore;

public class Heal implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (TiagoUtils.PermManager.hasPermission(player, Permissions.Commands.Heal) || player.isOp()) {
                if (TiagoUtils.options.getConfigurationSection(Default.SectionCommands).getBoolean(Default.commands.Heal)) {
                    if (args.length == 0) {
                        setMaxHealth(player);
                        removePotionEffects(player);
                        setMaxFood(player);
                        player.sendMessage(ChatColor.DARK_GRAY + "You just got fully " + ChatColor.GREEN + "healed" + ChatColor.DARK_GRAY + "!");
                    } else {
                        List<String> argsList = Arrays.asList(args);
                        if (someEqualsIgnore(argsList, "Heal")) {
                            setMaxHealth(player);
                        }
                        if (someEqualsIgnore(argsList, "RemovePotionEffects")) {
                            removePotionEffects(player);
                        }
                        if (someEqualsIgnore(argsList, "Food")) {
                            setMaxFood(player);
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.DARK_GRAY + "The command "+ ChatColor.WHITE + "Heal" + ChatColor.DARK_GRAY + " is " + ChatColor.RED + "disabled" + ChatColor.DARK_GRAY + " atm!");
                }
            } else {
                player.sendMessage(ChatColor.DARK_GRAY + "You need " + ChatColor.RED + "permission" + ChatColor.DARK_GRAY + " to use this command!");
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            List<String> theArgs = Arrays.asList(args);
            List<String> PossibleArgs = new ArrayList<>();
            if (!someEqualsIgnore(theArgs, "Heal")) {
                PossibleArgs.add("Heal");
            }
            if (!someEqualsIgnore(theArgs, "RemovePotionEffects")) {
                PossibleArgs.add("RemovePotionEffects");
            }
            if (!someEqualsIgnore(theArgs, "Food")) {
                PossibleArgs.add("Food");
            }
            return PossibleArgs;
        }
        return null;
    }
    public static void setMaxHealth(Player player) {
        double DefaultVal = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getDefaultValue();
        player.setHealth(DefaultVal);
    }
    public static void removePotionEffects(Player player) {
        for (PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType());
        }
    }
    public static void setMaxFood(Player player) {
        if (player.getFoodLevel() != 20) {
            player.setFoodLevel(20);
        }
    }
}
