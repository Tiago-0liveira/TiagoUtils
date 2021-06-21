package me.tiago0liveira.TiagoUtils.commands;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.BowType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class opBow implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (TiagoUtils.options.getConfigurationSection("commands").getBoolean("opBow")) {
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("explosion")) {
                        giveBow(player, BowType.EXPLOSION);
                    } else if (args[0].equalsIgnoreCase("teleport")) {
                        giveBow(player, BowType.TELEPORT);
                    } else {
                        player.sendMessage(ChatColor.RED + args[0] + ChatColor.WHITE + " does not exist!");
                        player.sendMessage("  Available arrow types: ");
                        player.sendMessage(ChatColor.RED + " - EXPLOSION");
                        player.sendMessage(ChatColor.DARK_PURPLE + " - TELEPORT");
                    }
                } else {
                    giveBow(player, BowType.EXPLOSION);
                    giveBow(player, BowType.TELEPORT);
                }
            } else {
                player.sendMessage(ChatColor.DARK_GRAY + "The command "+ ChatColor.WHITE + "opBow" + ChatColor.DARK_GRAY + " is " + ChatColor.RED + "disabled" + ChatColor.DARK_GRAY + " atm!");
            }
        }
        return true;
    }

    private static void giveBow(Player player, BowType bowType) {
        ItemStack Bow = new ItemStack(Material.BOW);
        ItemMeta itemMeta = Bow.getItemMeta();
        PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
        dataContainer.set(
                new NamespacedKey(TiagoUtils.getPlugin(), "bowType"),
                PersistentDataType.STRING,
                bowType.toString()
        );
        if (bowType.equals(BowType.EXPLOSION)) {
            itemMeta.setDisplayName(ChatColor.RED + "EXPLOSION" + ChatColor.GRAY + " BOW");
            List<String> Lore = new ArrayList<>();
            Lore.add("When it lands the arrow makes an " + ChatColor.RED + "explosion" + ChatColor.WHITE + "!");
        } else if (bowType.equals(BowType.TELEPORT)) {
            itemMeta.setDisplayName(ChatColor.DARK_PURPLE + "TELEPORT" + ChatColor.GRAY + " BOW");
            List<String> Lore = new ArrayList<>();
            Lore.add("You " + ChatColor.DARK_PURPLE + "teleport" + ChatColor.WHITE + "wherever the arrow lands!");
        }
        Bow.setItemMeta(itemMeta);

        player.getInventory().addItem(Bow);
    }
}
