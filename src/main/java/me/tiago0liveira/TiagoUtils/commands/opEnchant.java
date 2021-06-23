package me.tiago0liveira.TiagoUtils.commands;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.configs.Default;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class opEnchant implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (TiagoUtils.options.getConfigurationSection(Default.SectionCommands).getBoolean(Default.commands.opEnchant)) {
                ItemStack tool = player.getInventory().getItemInMainHand();
                if (tool.getType().name().equalsIgnoreCase("STICK")) {
                    tool.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 20);
                    tool.addUnsafeEnchantment(Enchantment.KNOCKBACK, 20);
                }
            } else {
                player.sendMessage(ChatColor.DARK_GRAY + "The command "+ ChatColor.WHITE + "opEnchant" + ChatColor.DARK_GRAY + " is " + ChatColor.RED + "disabled" + ChatColor.DARK_GRAY + " atm!");
            }
        }

        /*
        * MAKE MENU
        * */
        return true;
    }
}
