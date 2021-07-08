package me.tiago0liveira.TiagoUtils.commands;

import me.tiago0liveira.TiagoUtils.Gui.InventoryFactory;
import me.tiago0liveira.TiagoUtils.enums.PersistentData;
import me.tiago0liveira.TiagoUtils.helpers.ChatCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;


public class AdminOptions extends ChatCommand {
    public static final String commandName = "AdminOption";
    public static final  String MenuTitle = "Tiago Utils Options Menu";

    public AdminOptions(){
        super(commandName);
    }

    @Override
    public String getName() {
        return commandName;
    }

    @Override
    public String getUsage() {
        return commandName.toLowerCase();
    }

    @Override
    public String getDescription() {
        return "Admin Options Menu";
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Inventory inventory = InventoryFactory.InvBorder54(player, MenuTitle);
            /*
            * Commands
            * */
            ItemStack commands = new ItemStack(Material.LECTERN);
            ItemMeta commandsMetaData = commands.getItemMeta();
            commandsMetaData.setDisplayName(ChatColor.AQUA + "Commands");
            List<String> commandsLore = new ArrayList<>();
            commandsLore.add(ChatColor.DARK_GRAY + "Change Commands Settings");
            commandsMetaData.setLore(commandsLore);
            PersistentData.clickAction.set(commandsMetaData, "commandsMenu");
            commands.setItemMeta(commandsMetaData);
            /*
            * Events
            * */
            ItemStack events = new ItemStack(Material.OBSERVER);
            ItemMeta eventsMetaData = events.getItemMeta();
            eventsMetaData.setDisplayName(ChatColor.AQUA + "Events");
            List<String> eventsLore = new ArrayList<>();
            eventsLore.add(ChatColor.DARK_GRAY + "Change Events Settings");
            eventsMetaData.setLore(eventsLore);
            PersistentData.clickAction.set(eventsMetaData, "eventsMenu");
            events.setItemMeta(eventsMetaData);
            /*
            * Player Head
            * */
            ItemStack PlayerHead = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta playerHeadItemMeta = (SkullMeta) PlayerHead.getItemMeta();
            playerHeadItemMeta.setDisplayName(ChatColor.DARK_BLUE + player.getDisplayName());
            playerHeadItemMeta.setLore(new ArrayList<>());
            PersistentData.clickAction.set(playerHeadItemMeta, "playerMenu");
            playerHeadItemMeta.setOwningPlayer(player);
            PlayerHead.setItemMeta(playerHeadItemMeta);


            inventory.setItem(20, commands);
            inventory.setItem(24, events);
            inventory.setItem(53-4 /*bottom middle*/, PlayerHead);

            player.openInventory(inventory);
            
        }
        return true;
    }
}
