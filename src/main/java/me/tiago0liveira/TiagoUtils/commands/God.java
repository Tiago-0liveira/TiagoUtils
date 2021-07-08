package me.tiago0liveira.TiagoUtils.commands;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.Permissions;
import me.tiago0liveira.TiagoUtils.enums.configs.Default;
import me.tiago0liveira.TiagoUtils.helpers.ChatCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class God extends ChatCommand {
    public static final String commandName = "God";

    public God() {
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
        return "Set God Mode";
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (TiagoUtils.PermManager.hasPermission(player, Permissions.Commands.God) || player.isOp()) {
                if (TiagoUtils.options.getConfigurationSection(Default.SectionCommands).getBoolean(Default.commands.God)) {
                    player.setInvulnerable(!player.isInvulnerable());
                    player.sendMessage("Godmode is now " + (player.isInvulnerable() ? ChatColor.GREEN : ChatColor.RED) + (player.isInvulnerable() ? "ENABLED" : "DISABLED"));
                }else {
                    player.sendMessage(ChatColor.DARK_GRAY + "The command "+ ChatColor.WHITE + "GodMode" + ChatColor.DARK_GRAY + " is " + ChatColor.RED + "disabled" + ChatColor.DARK_GRAY + " atm!");
                }
            } else {
                player.sendMessage(ChatColor.DARK_GRAY + "You need " + ChatColor.RED + "permission" + ChatColor.DARK_GRAY + " to use this command!");
            }
        }
        return true;
    }
}
