package me.tiago0liveira.TiagoUtils.commands;

import me.tiago0liveira.TiagoUtils.Gui.InventoryFactory;

import me.tiago0liveira.TiagoUtils.helpers.ChatCommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.List;


public class AdminOptions extends ChatCommand {
    public static final String commandName = "AdminOption";

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
    public List<String> getAliases() {
        return Arrays.asList("ao");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Inventory inventory = InventoryFactory.mainMenuMenu(player);
            player.openInventory(inventory);
        }
        return true;
    }
}
