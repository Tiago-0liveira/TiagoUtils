package me.tiago0liveira.TiagoUtils.commands;

import me.tiago0liveira.TiagoUtils.helpers.CommandsManager;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class TiagoUtils extends Command {


    public TiagoUtils() {
        super(me.tiago0liveira.TiagoUtils.TiagoUtils.pluginName);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        sender.sendMessage("Plugin developed by " + ChatColor.AQUA + "tiagooo__");
        sender.sendMessage("All available commands" + ChatColor.DARK_GRAY + ":");
        for(Command c : CommandsManager.commands) {
            TextComponent tc = new TextComponent(ChatColor.DARK_GRAY + " -> " + ChatColor.YELLOW + c.getName());
            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{
                    new TextComponent("Click me")
            }));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + c.getName()));
            sender.spigot().sendMessage(tc);
        }
        return true;
    }
}
