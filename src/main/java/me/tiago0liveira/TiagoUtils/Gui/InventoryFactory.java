package me.tiago0liveira.TiagoUtils.Gui;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.PersistentDataManager;
import me.tiago0liveira.TiagoUtils.enums.configs.ClickInventoryItemAction;
import me.tiago0liveira.TiagoUtils.enums.configs.Default;
import me.tiago0liveira.TiagoUtils.helpers.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class InventoryFactory {
    public static final String TitleMainMenu;
    public static final String TitleMenuCommands;
    public static final String TitleMenuEvents;
    public static final String TitleMenuPlayer;
    public static final String TitleMenuCustomEnchants;
    public static final List<String> PreventItemMoveMenuTitles;
    public static final ItemStack border = new ItemFactory(Material.BLACK_STAINED_GLASS_PANE)
            .setDisplayName(ChatColor.BLACK + " BORDER ")
            .Build();

    static {
        TitleMainMenu = "Tiago Utils Menu";
        TitleMenuCommands = "Commands Menu Options";
        TitleMenuEvents = "Events Menu Options";
        TitleMenuPlayer = "Player Menu Options";
        TitleMenuCustomEnchants = "Custom Enchantments";
        PreventItemMoveMenuTitles = Arrays.asList(
                TitleMainMenu,
                TitleMenuCommands,
                TitleMenuEvents,
                TitleMenuPlayer,
                TitleMenuCustomEnchants
        );

    }

    private static Inventory CraftInvBorder54(Player player, String MenuTitle, boolean isMainMenu) {
        Inventory inventory = Bukkit.createInventory(player, 54, MenuTitle);
        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, border);
        }
        for (int i = 54-9; i < 54; i++) {
            inventory.setItem(i, border);
        }
        for (int i = 1; i <= 4; i++) {
            inventory.setItem(i*9, border);
            inventory.setItem((i*9)+8, border);
        }
        if (!isMainMenu) {
            setMenuButton(inventory);
        }
        return inventory;
    }
    public static Inventory InvBorder54(Player player, String MenuTitle, boolean isMainMenu) {
        return CraftInvBorder54(player, MenuTitle, isMainMenu);
    }
    public static Inventory InvBorder54(Player player, String MenuTitle, boolean isMainMenu, boolean playerHead) {
        Inventory inventory = CraftInvBorder54(player, MenuTitle, isMainMenu);
        if (playerHead) {
            ItemStack PlayerHead = new ItemFactory(true, player)
                .setDisplayName(ChatColor.DARK_BLUE + player.getDisplayName())
                .Build((item, meta) -> {
                    PersistentDataManager.clickAction.set(meta, ClickInventoryItemAction.PlayerMenu);
                    item.setItemMeta(meta);
                    return item;
                });

            inventory.setItem(53-4 /*bottom middle*/, PlayerHead);
        }
        return inventory;
    }
    private static void setMenuButton(Inventory inventory) {
        ItemStack is = new ItemStack(Material.BARRIER);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "MENU");
        PersistentDataManager.clickAction.set(meta, ClickInventoryItemAction.MenuButton);
        is.setItemMeta(meta);
        inventory.setItem(54-9 /*bottom left*/,is);
    }

    public static Inventory mainMenuMenu(Player player) {
        Inventory inventory = InvBorder54(player, TitleMainMenu, true, true);
        /* Commands */
        ItemStack commands = new ItemFactory(Material.LECTERN)
            .setDisplayName(ChatColor.AQUA + "Commands")
            .addLore(ChatColor.DARK_GRAY + "Change Commands Settings")
            .Build((item, meta) -> {
                PersistentDataManager.clickAction.set(meta, ClickInventoryItemAction.CommandsMenu);
                item.setItemMeta(meta);
                return item;
            });
        /*
         * Events
         * */

        ItemStack events = new ItemFactory(Material.OBSERVER)
            .setDisplayName(ChatColor.AQUA + "Events")
            .addLore(ChatColor.DARK_GRAY + "Change Events Settings")
            .Build((item, meta) -> {
                PersistentDataManager.clickAction.set(meta, ClickInventoryItemAction.EventsMenu);
                item.setItemMeta(meta);
                return item;
            });
        /*
         * Enchants
         * */

        ItemStack enchants = new ItemFactory(Material.ENCHANTED_BOOK)
            .setDisplayName(ChatColor.AQUA + "Custom Enchantments")
            .addLore(ChatColor.DARK_GRAY + "Add Custom Enchantments")
            .Build((item, meta) -> {
                PersistentDataManager.clickAction.set(meta, ClickInventoryItemAction.CustomEnchants);
                item.setItemMeta(meta);
                return item;
            });


        inventory.setItem(20, commands);
        inventory.setItem(24, events);
        inventory.setItem(53-4-9-9 /* 3 from bottom middle */, enchants);

        return inventory;
    }
    public static Inventory commandsMenu(Player player) {
        Inventory inventory = InvBorder54(player, TitleMenuCommands, false, true);
        ConfigurationSection section = TiagoUtils.options.getConfigurationSection(Default.SectionCommands);
        for(String s : section.getKeys(false)) {
            boolean bool = section.getBoolean(s);
            ItemStack is = new ItemFactory(bool ? Material.LIME_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE)
                .setDisplayName(ChatColor.AQUA + s)
                .addLore(bool ? ChatColor.GREEN + "TRUE" : ChatColor.RED + "FALSE")
                .Build((item, meta) -> {
                    /*PersistentDataManager.clickAction.set(meta, ClickInventoryItemAction.CommandsMenu);
                    item.setItemMeta(meta);*/
                    return item;
                });

            inventory.addItem(is);
        }
        return inventory;
    }
    public static Inventory eventsMenu(Player player) {
        Inventory inventory = InvBorder54(player, TitleMenuEvents, false);
        ConfigurationSection section = TiagoUtils.options.getConfigurationSection(Default.SectionEvents);
        for(String s : section.getKeys(false)) {
            boolean bool = section.getBoolean(s);
            ItemStack is = new ItemFactory(bool ? Material.LIME_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE)
                .setDisplayName(ChatColor.AQUA + s)
                .addLore(bool ? ChatColor.GREEN + "TRUE" : ChatColor.RED + "FALSE")
                .Build((item, meta) -> {
                    /*PersistentDataManager.clickAction.set(meta, ClickInventoryItemAction.EventsMenu);
                    item.setItemMeta(meta);*/
                    return item;
                });

            inventory.addItem(is);
        }
        return inventory;
    }
    public static Inventory playerMenu(Player player) {
        /* AND MAYBE SKILLS ?? OR STATS ?? */
        Inventory inventory = InvBorder54(player, TitleMenuPlayer, false);
        for(Map.Entry<String, Boolean> e : TiagoUtils.PermManager.getAllPermissions(player).entrySet()) {
            ItemStack is = new ItemFactory(e.getValue() ? Material.LIME_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE)
                .setDisplayName(ChatColor.AQUA + e.getKey())
                .addLore(e.getValue() ? ChatColor.GREEN + "TRUE" : ChatColor.RED + "FALSE")
                .Build((item, meta) -> {
                    PersistentDataManager.clickAction.set(meta, ClickInventoryItemAction.CommandsMenu);
                    item.setItemMeta(meta);
                    return item;
                });

            inventory.addItem(is);
        }

        return inventory;
    }
    public static Inventory customEnchants(Player player) {
        Inventory inventory = InvBorder54(player, TitleMenuCustomEnchants, false);

        return inventory;
    }
}
