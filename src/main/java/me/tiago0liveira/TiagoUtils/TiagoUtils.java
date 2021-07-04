package me.tiago0liveira.TiagoUtils;

import me.tiago0liveira.TiagoUtils.commands.*;
import me.tiago0liveira.TiagoUtils.events.*;
import me.tiago0liveira.TiagoUtils.events.Gui.onClickAdminOptionsMenu;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static me.tiago0liveira.TiagoUtils.commands.Home.setup;

public final class TiagoUtils extends JavaPlugin {

    public static final String pluginName = "TiagoUtils";
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
        * DONE: always good weather
        * DONE: home/setHome
        * DONE: ADD PERMISSIONS AS SOON AS POSSIBLE !!!
        * TODO: maybe Command Factory ? for simplicity creating commands ??
        * TODO: enchantment menu (ops included)
        * TODO: admins powers (lightning, ban, kick, ...)
        * TODO: tri bow
        * TODO: machine gun mods (speed, tri bow,..)
        * TODO: warps (store with warps.yml)
        * TODO: customEffects(mine with hand, super strong hand) maybe introduce stats for every player ??
        * TODO: finnish options menu
        * */
        plugin = this;
        System.out.println("Tiago Utils has started!");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        options = getConfig().options().configuration();
        setup(); /* Home setup config file */
        for (Player player : getServer().getOnlinePlayers()) {
            player.sendMessage(ChatColor.AQUA + "Tiago Utils" + ChatColor.WHITE +" has " + ChatColor.GREEN + "started!");
        }
        getCommand("fly").setExecutor(new Fly());
        getCommand("god").setExecutor(new God());
        getCommand("heal").setExecutor(new Heal());
        getCommand("home").setExecutor(new Home());
        getCommand("openchant").setExecutor(new opEnchant());
        getCommand("ElementalBow").setExecutor(new ElementalBow());
        getCommand("adminOptions").setExecutor(new AdminOptions());
        getCommand("setMachineGun").setExecutor(new setMachineGun());
        getServer().getPluginManager().registerEvents(new onClickAdminOptionsMenu(), this);
        getServer().getPluginManager().registerEvents(new onArrowCollides(), this);
        getServer().getPluginManager().registerEvents(new onBadWeather(), this);
        getServer().getPluginManager().registerEvents(new onHoldElementalBow(), this);
        getServer().getPluginManager().registerEvents(new onMachineGunHold(), this);
        getServer().getPluginManager().registerEvents(new onRightClickForMachineGunBow(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Tiago Utils has stopped!");
        onMachineGunHold.clearMachineGunBossBar();
        for (Player player : getServer().getOnlinePlayers()) {
            player.sendMessage(ChatColor.AQUA + "Tiago Utils" + ChatColor.WHITE +" has " + ChatColor.RED + "stopped!");
        }
    }
}
