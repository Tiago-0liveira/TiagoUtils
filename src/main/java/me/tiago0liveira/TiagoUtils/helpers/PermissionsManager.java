package me.tiago0liveira.TiagoUtils.helpers;

import me.tiago0liveira.TiagoUtils.enums.Permissions;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PermissionsManager {

    private static File file;
    private static FileConfiguration config;

    public PermissionsManager() {
        file = FileManager.getYMLFile("Permissions.yml");
        if (file != null) {
            config = YamlConfiguration.loadConfiguration(file);
        }
    }

    public boolean hasPermission(Player player, String permissionString) {
        return config.getBoolean(player.getUniqueId().toString() + "." + permissionString);
    }
    public void givePermission(Player player, String permissionString) {
        config.set(player.getUniqueId().toString() + "." + permissionString, true);
        saveFile();
    }
    public void removePermission(Player player, String permissionString) {
        config.set(player.getUniqueId().toString() + "." + permissionString, false);
        saveFile();
    }
    public HashMap<String, Boolean> getAllAssignedPermissions(Player player) {
        ConfigurationSection commandSection = config.getConfigurationSection(player.getUniqueId().toString() + "." + Permissions.SectionCommands);
        ConfigurationSection eventSection = config.getConfigurationSection(player.getUniqueId().toString() + "." + Permissions.SectionEvents);

        HashMap<String, Boolean> permList = new HashMap<>();
        if (commandSection != null) {
            Set<String> commandPerms = commandSection.getKeys(false);
            for(String s : commandPerms) { permList.put(Permissions.SectionCommands + "." + s, commandSection.getBoolean(s)); };
        }
        if (eventSection != null) {
            Set<String> eventPerms = eventSection.getKeys(false);
            for(String s : eventPerms) { permList.put(Permissions.SectionEvents + "." + s, eventSection.getBoolean(s)); };
        }
        return permList;
    }

    public TreeMap<String, Boolean> getAllPermissions(Player player) {
        ConfigurationSection commandSection = config.getConfigurationSection(player.getUniqueId().toString() + "." + Permissions.SectionCommands);
        ConfigurationSection eventSection = config.getConfigurationSection(player.getUniqueId().toString() + "." + Permissions.SectionEvents);

        HashMap<String, Boolean> permList = new HashMap<>();

        for(String s : Permissions.Commands.commandsList) {
            permList.put(s, commandSection != null && commandSection.getBoolean(player.getUniqueId().toString() + "." + s));
        }
        for(String s : Permissions.Events.eventsList) {
            permList.put(s, eventSection != null && eventSection.getBoolean(player.getUniqueId().toString() + "." + s));
        }

        return new TreeMap<>(permList);
    }
    public void saveFile() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
