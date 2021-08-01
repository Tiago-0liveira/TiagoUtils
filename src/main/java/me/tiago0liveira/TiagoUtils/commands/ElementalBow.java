package me.tiago0liveira.TiagoUtils.commands;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.BowType;
import me.tiago0liveira.TiagoUtils.enums.Permissions;
import me.tiago0liveira.TiagoUtils.enums.PersistentDataManager;
import me.tiago0liveira.TiagoUtils.enums.configs.Default;
import me.tiago0liveira.TiagoUtils.helpers.ChatCommand;
import me.tiago0liveira.TiagoUtils.helpers.ItemFactory;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.tiago0liveira.TiagoUtils.helpers.ExtraStringMethods.someEqualsIgnore;

public class ElementalBow extends ChatCommand {
    public static final String commandName = "ElementalBow";
    public static final ItemStack EXPLOSION_BOW = craftBow(BowType.EXPLOSION);
    public static final ItemStack TELEPORT_BOW = craftBow(BowType.TELEPORT);
    public static final ItemStack LIGHTNING_BOW = craftBow(BowType.LIGHTNING);


    public ElementalBow() {
        super(commandName);
    }

    @Override
    public String getName() {
        return commandName;
    }

    @Override
    public String getUsage() {
        return "<lightning|explosive|teleport>";
    }

    @Override
    public String getDescription() {
        return "Get a Elemental Bow";
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("ebow", "specialbow");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (TiagoUtils.PermManager.hasPermission(player, Permissions.Commands.ElementalBow) || player.isOp()) {
                if (TiagoUtils.options.getConfigurationSection(Default.SectionCommands).getBoolean(Default.commands.ElementalBow)) {
                    if (args.length > 0) {
                        if (args[0].equalsIgnoreCase("explosion")) {
                            player.getInventory().addItem(EXPLOSION_BOW);
                        } else if (args[0].equalsIgnoreCase("teleport")) {
                            player.getInventory().addItem(TELEPORT_BOW);
                        } else if (args[0].equalsIgnoreCase("lightning")) {
                            player.getInventory().addItem(LIGHTNING_BOW);
                        } else {
                            player.sendMessage(ChatColor.RED + args[0] + ChatColor.WHITE + " does not exist!");
                            showPossibleElementalbows(player);
                        }
                    } else {
                        showPossibleElementalbows(player);
                    }
                } else {
                    player.sendMessage(ChatColor.DARK_GRAY + "The command "+ ChatColor.WHITE + "ElementalBow" + ChatColor.DARK_GRAY + " is " + ChatColor.RED + "disabled" + ChatColor.DARK_GRAY + " atm!");
                }
            } else {
                player.sendMessage(ChatColor.DARK_GRAY + "You need " + ChatColor.RED + "permission" + ChatColor.DARK_GRAY + " to use this command!");
            }
        }
        return true;
    }
    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        if (sender instanceof Player) {
            List<String> theArgs = Arrays.asList(args);
            boolean someEqualExplosion = someEqualsIgnore(theArgs, "explosion");
            boolean someEqualLightning = someEqualsIgnore(theArgs, "lightning");
            boolean someEqualTeleport = someEqualsIgnore(theArgs, "teleport");
            if (someEqualExplosion || someEqualLightning || someEqualTeleport) {
                return new ArrayList<>();
            } else {
                return Arrays.asList("Explosion","Lightning","Teleport");
            }
        }
        return new ArrayList<>();
    }

    private static ItemStack craftBow(BowType bowType) {
        ItemFactory Item = new ItemFactory(Material.BOW)
                .setDisplayName(getBowName(bowType) + ChatColor.GRAY + " BOW");
        if (bowType.equals(BowType.EXPLOSION)) {
            Item.addLore(ChatColor.DARK_GRAY + "When it lands the arrow makes an " + ChatColor.RED + "explosion" + ChatColor.DARK_GRAY + "!");
        } else if (bowType.equals(BowType.TELEPORT)) {
            Item.addLore(ChatColor.DARK_GRAY + "You " + ChatColor.DARK_PURPLE + "teleport" + ChatColor.DARK_GRAY + " wherever the arrow lands!");
        } else if(bowType.equals(BowType.LIGHTNING)) {
            Item.addLore(ChatColor.DARK_GRAY + "A " + ChatColor.AQUA + "lightning" + ChatColor.DARK_GRAY + " strikes wherever the arrow lands!");
        }

        return Item.Build((item, meta) -> {
            PersistentDataManager.bowType.set(meta, bowType.toString());
            item.setItemMeta(meta);
            return item;
        });
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
    private static void showPossibleElementalbows(Player player) {
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
}
