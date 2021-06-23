package me.tiago0liveira.TiagoUtils.commands;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.configs.Default;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (TiagoUtils.options.getConfigurationSection(Default.SectionCommands).getBoolean(Default.commands.Fly)) {
                if (!player.getAllowFlight()) {
                    player.setAllowFlight(true);
                }
                player.setFlying(true);
            } else {
                player.sendMessage(ChatColor.DARK_GRAY + "The command "+ ChatColor.WHITE + "Fly" + ChatColor.DARK_GRAY + " is " + ChatColor.RED + "disabled" + ChatColor.DARK_GRAY + " atm!");
            }
        }
        return true;
    }
}
