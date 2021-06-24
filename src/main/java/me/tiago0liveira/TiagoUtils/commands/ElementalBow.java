package me.tiago0liveira.TiagoUtils.commands;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.BowType;
import static me.tiago0liveira.TiagoUtils.helpers.ExtraStringMethods.someEqualsIgnore;

import me.tiago0liveira.TiagoUtils.enums.PersistentData;
import me.tiago0liveira.TiagoUtils.enums.configs.Default;
import net.md_5.bungee.api.chat.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElementalBow implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (TiagoUtils.options.getConfigurationSection(Default.SectionCommands).getBoolean(Default.commands.ElementalBow)) {
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("explosion")) {
                        player.getInventory().addItem(giveBow(BowType.EXPLOSION));
                    } else if (args[0].equalsIgnoreCase("teleport")) {
                        player.getInventory().addItem(giveBow(BowType.TELEPORT));
                    } else if (args[0].equalsIgnoreCase("lightning")) {
                        player.getInventory().addItem(giveBow(BowType.LIGHTNING));
                    } else {
                        player.sendMessage(ChatColor.RED + args[0] + ChatColor.WHITE + " does not exist!");
                        for (BowType type : BowType.values()) {
                            TextComponent em = new TextComponent();
                            em.setText("  - " + getBowName(type) + ChatColor.GRAY + " BOW");
                            em.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ElementalBow " + type.toString()));
                            em.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{
                                    new TextComponent(getBowName(type) + ChatColor.GRAY + " BOW\n"),
                                    new TextComponent(ChatColor.GRAY + "[ " + ChatColor.YELLOW + "CLICK ME TO GET ONE" + ChatColor.GRAY + " ]")
                            }));
                            player.spigot().sendMessage(em);
                        }
                    }
                } else {
                    player.sendMessage("Available bow types: ");
                    for (BowType type : BowType.values()) {
                        TextComponent em = new TextComponent();
                        em.setText("  - " + getBowName(type) + ChatColor.GRAY + " BOW");
                        em.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ElementalBow " + type.toString()));
                        em.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{
                                new TextComponent(getBowName(type) + ChatColor.GRAY + " BOW\n"),
                                new TextComponent(ChatColor.GRAY + "[" + ChatColor.YELLOW + "CLICK ME TO GET ONE" + ChatColor.GRAY + "]")
                        }));
                        player.spigot().sendMessage(em);
                    }
                }
            } else {
                player.sendMessage(ChatColor.DARK_GRAY + "The command "+ ChatColor.WHITE + "ElementalBow" + ChatColor.DARK_GRAY + " is " + ChatColor.RED + "disabled" + ChatColor.DARK_GRAY + " atm!");
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
    private static ItemStack giveBow(BowType bowType) {
        ItemStack Bow = new ItemStack(Material.BOW);
        ItemMeta itemMeta = Bow.getItemMeta();
        List<String> Lore = new ArrayList<>();
        PersistentData.bowType.set(itemMeta, bowType.toString());
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

        return Bow;
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
