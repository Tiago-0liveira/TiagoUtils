package me.tiago0liveira.TiagoUtils.helpers;

import me.tiago0liveira.TiagoUtils.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class CommandsManager {

    public static final List<Command> commands = Arrays.asList(
        new AdminOptions(),
        new ElementalBow(),
        new Fly(),
        new God(),
        new Heal(),
        new Home(),
        new opEnchant(),
        new Permission(),
        new setMachineGun()
    );

    public static void registerCommands() {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            commandMap.registerAll("TiagoUtils", commands);
            commandMap.register("",  new TiagoUtils());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
