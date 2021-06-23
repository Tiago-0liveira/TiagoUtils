package me.tiago0liveira.TiagoUtils.commands;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.BowType;
import static me.tiago0liveira.TiagoUtils.helpers.ExtraStringMethods.someEqualsIgnore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class opBow implements TabExecutor {
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
                    } else if (args[0].equalsIgnoreCase("lightning")) {
                        giveBow(player, BowType.LIGHTNING);
                    } else {
                        player.sendMessage(ChatColor.RED + args[0] + ChatColor.WHITE + " does not exist!");
                        player.sendMessage("  Available arrow types: ");
                        player.sendMessage(ChatColor.RED + " - EXPLOSION");
                        player.sendMessage(ChatColor.DARK_PURPLE + " - TELEPORT");
                        player.sendMessage(ChatColor.AQUA + " - LIGHTNING");
                    }
                } else {
                    player.sendMessage("  Available arrow types: ");
                    player.sendMessage(ChatColor.RED + " - EXPLOSION");
                    player.sendMessage(ChatColor.DARK_PURPLE + " - TELEPORT");
                    player.sendMessage(ChatColor.AQUA + " - LIGHTNING");
                }
            } else {
                player.sendMessage(ChatColor.DARK_GRAY + "The command "+ ChatColor.WHITE + "opBow" + ChatColor.DARK_GRAY + " is " + ChatColor.RED + "disabled" + ChatColor.DARK_GRAY + " atm!");
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            List<String> theArgs = Arrays.asList(args);
            boolean someEqualExplosion = someEqualsIgnore(theArgs, "explosion");
            boolean someEqualLightning = someEqualsIgnore(theArgs, "lightning");
            boolean someEqualTeleport = someEqualsIgnore(theArgs, "teleport");
            if (someEqualExplosion || someEqualLightning || someEqualTeleport) {
                return null;
            } else {
                return Arrays.asList("Explosion","Lightning","Teleport");
            }
        }
        return null;
    }
    private static void giveBow(Player player, BowType bowType) {
        ItemStack Bow = new ItemStack(Material.BOW);
        ItemMeta itemMeta = Bow.getItemMeta();
        PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
        List<String> Lore = new ArrayList<>();
        dataContainer.set(
                new NamespacedKey(TiagoUtils.getPlugin(), "bowType"),
                PersistentDataType.STRING,
                bowType.toString()
        );
        if (bowType.equals(BowType.EXPLOSION)) {
            itemMeta.setDisplayName(getBowName(bowType) + ChatColor.GRAY + " BOW");
            Lore.add(ChatColor.DARK_GRAY + "When it lands the arrow makes an " + ChatColor.RED + "explosion" + ChatColor.DARK_GRAY + "!");
        } else if (bowType.equals(BowType.TELEPORT)) {
            itemMeta.setDisplayName(getBowName(bowType) + ChatColor.GRAY + " BOW");
            Lore.add(ChatColor.DARK_GRAY + "You " + ChatColor.DARK_PURPLE + "teleport" + ChatColor.DARK_GRAY + " wherever the arrow lands!");
        } else if(bowType.equals(BowType.LIGHTNING)) {
            itemMeta.setDisplayName(getBowName(bowType) + ChatColor.GRAY + " BOW");
            Lore.add(ChatColor.DARK_GRAY + "A " + ChatColor.AQUA + "lightning" + ChatColor.DARK_GRAY + " strikes wherever the arrow lands!");
        }
        itemMeta.setLore(Lore);
        Bow.setItemMeta(itemMeta);

        player.getInventory().addItem(Bow);
    }
    public static String getBowName(BowType bowType) {
        if (bowType.equals(BowType.EXPLOSION)) {
            return ChatColor.RED + "EXPLOSION";
        } else if (bowType.equals(BowType.TELEPORT)) {
            return ChatColor.DARK_PURPLE + "TELEPORT";
        } else if(bowType.equals(BowType.LIGHTNING)) {
            return ChatColor.AQUA + "LIGHTNING";
        } else {
            System.err.println("ERROR|UNKNOWN bowType->" + bowType);
            return null;
        }
    }
    public static String getBowName(String bowType) {
        if (bowType.equals(BowType.EXPLOSION.toString())) {
            return ChatColor.RED + "EXPLOSION";
        } else if (bowType.equals(BowType.TELEPORT.toString())) {
            return ChatColor.DARK_PURPLE + "TELEPORT";
        } else if(bowType.equals(BowType.LIGHTNING.toString())) {
            return ChatColor.AQUA + "LIGHTNING";
        } else if(bowType.equals("default")) {
            return ChatColor.ITALIC + "" + ChatColor.WHITE + "DEFAULT";
        } else {
            System.err.println("ERROR|UNKNOWN bowType->" + bowType);
            return null;
        }
    }

}
