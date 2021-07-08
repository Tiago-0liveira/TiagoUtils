package me.tiago0liveira.TiagoUtils.helpers;

import org.bukkit.command.Command;

import java.util.ArrayList;
import java.util.List;

public abstract class ChatCommand extends Command {
    public ChatCommand(String name) {
        super(name);
    }
    public String getName(){
        return null;
    };
    public String getUsage(){
        return "command Name";
    };
    public String getDescription(){
        return "command Desc";
    };
    public List<String> getAliases(){
        return new ArrayList<>();
    };
    public List<ChatCommand> getSubCommands(){
        return new ArrayList<>();
    };
    public boolean isSubCommand(){
        return false;
    };
}
