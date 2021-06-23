package me.tiago0liveira.TiagoUtils;

import me.tiago0liveira.TiagoUtils.commands.*;
import me.tiago0liveira.TiagoUtils.events.Gui.onClickAdminOptionsMenu;
import me.tiago0liveira.TiagoUtils.events.onArrowCollides;
import me.tiago0liveira.TiagoUtils.events.onMachineGunHold;
import me.tiago0liveira.TiagoUtils.events.onRightClickForMachineGunBow;
import me.tiago0liveira.TiagoUtils.events.onTreeCapitatorTrigger;
import org.bukkit.ChatColor;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Iterator;

public final class TiagoUtils extends JavaPlugin {


    public static TiagoUtils getPlugin() {
        return plugin;
    }
    private static TiagoUtils plugin;

    public static FileConfiguration options;

    @Override
    public void onEnable() {
        /*
        * DONE: lightning arrow
        * DONE: machine gun bow
        * TODO: finnish options menu
        * TODO: enchantment menu (ops included)
        * TODO: admins powers (lightning, ban, kick, ...)
        * */
        plugin = this;
        System.out.println("Tiago Utils has started!");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        options = getConfig().options().configuration();
        for (Player player : getServer().getOnlinePlayers()) {
            player.sendMessage(ChatColor.AQUA + "Tiago Utils" + ChatColor.WHITE +" has " + ChatColor.GREEN + "started!");
        }
        getCommand("fly").setExecutor(new Fly());
        getCommand("god").setExecutor(new God());
        getCommand("heal").setExecutor(new Heal());
        getCommand("openchant").setExecutor(new opEnchant());
        getCommand("opBow").setExecutor(new opBow());
        getCommand("adminOptions").setExecutor(new AdminOptions());
        getCommand("setMachineGun").setExecutor(new setMachineGun());
        getServer().getPluginManager().registerEvents(new onTreeCapitatorTrigger(), this);
        getServer().getPluginManager().registerEvents(new onArrowCollides(), this);
        getServer().getPluginManager().registerEvents(new onClickAdminOptionsMenu(), this);
        getServer().getPluginManager().registerEvents(new onRightClickForMachineGunBow(), this);
        getServer().getPluginManager().registerEvents(new onMachineGunHold(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Tiago Utils has stopped!");
        for (Player player : getServer().getOnlinePlayers()) {
            player.sendMessage(ChatColor.AQUA + "Tiago Utils" + ChatColor.WHITE +" has " + ChatColor.RED + "stopped!");
        }
    }
}
