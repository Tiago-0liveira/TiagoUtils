package me.tiago0liveira.TiagoUtils.commands;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Heal implements CommandExecutor {

    List<String> healAliases = Arrays.asList("heal", "h", "healme", "iNeedLife", "givemelife");
    List<String> rPotionEffectsAliases = Arrays.asList("rpe", "cleame", "milk");
    List<String> foodAliases = Arrays.asList("food", "f", "feedme", "gimmefood");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (TiagoUtils.options.getConfigurationSection("commands").getBoolean("Heal")) {
                if (args.length == 0) {
                    setMaxHealth(player);
                    removePotionEffects(player);
                    setMaxFood(player);
                } else {
                    List<String> argslist = Arrays.asList(args);
                    if (!Collections.disjoint(argslist, healAliases)) {
                        player.sendMessage("heal triggered");
                        setMaxHealth(player);
                    }
                    if (!Collections.disjoint(argslist, foodAliases)) {
                        player.sendMessage("rpe triggered");
                        removePotionEffects(player);
                    }
                    if (!Collections.disjoint(argslist, rPotionEffectsAliases)) {
                        player.sendMessage("food triggered");
                        setMaxFood(player);
                    }
                }
            } else {
                player.sendMessage(ChatColor.DARK_GRAY + "The command "+ ChatColor.WHITE + "Heal" + ChatColor.DARK_GRAY + " is " + ChatColor.RED + "disabled" + ChatColor.DARK_GRAY + " atm!");
            }
        }
        return true;
    }

    public static void setMaxHealth(Player player) {
        if (player.getHealth() != player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
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
