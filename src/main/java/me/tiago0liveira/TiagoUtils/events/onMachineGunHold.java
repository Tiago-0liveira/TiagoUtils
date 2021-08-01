package me.tiago0liveira.TiagoUtils.events;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.PersistentDataManager;
import me.tiago0liveira.TiagoUtils.enums.configs.Default;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.tiago0liveira.TiagoUtils.events.onHoldElementalBow.getBarColor;

public class onMachineGunHold implements Listener {
    private static BossBar MachineGunBossBar;

    @EventHandler
    public void holdMachineGun(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        if (MachineGunBossBar != null) {
            MachineGunBossBar.removeAll();
            MachineGunBossBar = null;
        }
        ItemStack itemHeld = p.getInventory().getItem(e.getNewSlot());
        if (itemHeld != null) {
            if (itemHeld.getType().equals(Material.BOW)) {
                ItemMeta meta = itemHeld.getItemMeta();
                if (PersistentDataManager.isMachineGun.has(meta)) {
                    if (TiagoUtils.options.getConfigurationSection(Default.SectionEvents).getBoolean(Default.events.machineGuns)) {
                        boolean isActive = PersistentDataManager.isMachineGunActive.get(meta);
                        String bowType = PersistentDataManager.bowType.get(meta);
                        MachineGunBossBar = Bukkit.createBossBar(meta.getDisplayName() + ChatColor.WHITE + " is " + (isActive ? ChatColor.GREEN + "ACTIVE" : ChatColor.DARK_RED + "DISABLED"), getBarColor(bowType), BarStyle.SOLID);
                        MachineGunBossBar.addPlayer(p);
                    }  else {
                        p.sendMessage("");
                        p.sendMessage("Machine Guns are " + ChatColor.RED + "disabled!");
                        p.sendMessage("");
                    }
                }
            }
        }
    }

    public static void clearMachineGunBossBar() {
        if (MachineGunBossBar != null) {
            MachineGunBossBar.removeAll();
            MachineGunBossBar = null;
        }
    }
}
