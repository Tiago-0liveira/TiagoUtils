package me.tiago0liveira.TiagoUtils;

import me.tiago0liveira.TiagoUtils.commands.*;
import me.tiago0liveira.TiagoUtils.events.onArrowCollides;
import me.tiago0liveira.TiagoUtils.events.onTreeCapitatorTrigger;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class TiagoUtils extends JavaPlugin {


    public static TiagoUtils getPlugin() {
        return plugin;
    }
    private static TiagoUtils plugin;

    public static FileConfiguration options;

    @Override
    public void onEnable() {
        plugin = this;
        System.out.println("MyFirstPlugin has started!");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        options = getConfig().options().configuration();
        for (Player player : getServer().getOnlinePlayers()) {
            player.sendMessage(ChatColor.AQUA + "MyFirstPlugin" + ChatColor.WHITE +" has " + ChatColor.GREEN + "started!");
        }
        getCommand("fly").setExecutor(new Fly());
        getCommand("god").setExecutor(new God());
        getCommand("heal").setExecutor(new Heal());
        getCommand("openchant").setExecutor(new opEnchant());
        getCommand("opBow").setExecutor(new opBow());
        getServer().getPluginManager().registerEvents(new onTreeCapitatorTrigger(), this);
        getServer().getPluginManager().registerEvents(new onArrowCollides(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("MyFirstPlugin has stopped!");
        for (Player player : getServer().getOnlinePlayers()) {
            player.sendMessage(ChatColor.AQUA + "MyFirstPlugin" + ChatColor.WHITE +" has " + ChatColor.RED + "stopped!");
        }
    }
}
