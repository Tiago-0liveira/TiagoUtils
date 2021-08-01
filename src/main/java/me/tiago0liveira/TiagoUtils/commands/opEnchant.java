package me.tiago0liveira.TiagoUtils.commands;

import me.tiago0liveira.TiagoUtils.Gui.InventoryFactory;
import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.Permissions;
import me.tiago0liveira.TiagoUtils.enums.configs.Default;
import me.tiago0liveira.TiagoUtils.helpers.ChatCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class opEnchant extends ChatCommand {
    public static final String commandName = "opEnchant";
    public opEnchant(){
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
        return "OP ENCHANTS";
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (TiagoUtils.PermManager.hasPermission(player, Permissions.Commands.opEnchant) || player.isOp()) {
                if (TiagoUtils.options.getConfigurationSection(Default.SectionCommands).getBoolean(Default.commands.opEnchant)) {
                    Inventory inventory = InventoryFactory.customEnchants(player);
                    player.openInventory(inventory);
                } else {
                    player.sendMessage(ChatColor.DARK_GRAY + "The command "+ ChatColor.WHITE + "opEnchant" + ChatColor.DARK_GRAY + " is " + ChatColor.RED + "disabled" + ChatColor.DARK_GRAY + " atm!");
                }
            } else {
                player.sendMessage(ChatColor.DARK_GRAY + "You need " + ChatColor.RED + "permission" + ChatColor.DARK_GRAY + " to use this command!");
            }
        }

        /*
        * MAKE MENU
        * */
        return true;
    }
}
