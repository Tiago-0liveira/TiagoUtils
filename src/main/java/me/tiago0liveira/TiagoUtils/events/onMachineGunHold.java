package me.tiago0liveira.TiagoUtils.events;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.BowType;
import me.tiago0liveira.TiagoUtils.enums.configs.Default;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import static me.tiago0liveira.TiagoUtils.events.onHoldElementalBow.getBarColor;

public class onMachineGunHold implements Listener {
    private BossBar bossBar;

    @EventHandler
    public void holdMachineGun(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        if (bossBar != null) {
            bossBar.removeAll();
            bossBar = null;
        }
        ItemStack itemHeld = p.getInventory().getItem(e.getNewSlot());
        if (itemHeld != null) {
            if (itemHeld.getType().equals(Material.BOW)) {
                ItemMeta meta = itemHeld.getItemMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                if (container.has(new NamespacedKey(TiagoUtils.getPlugin(), "isMachineGun"), PersistentDataType.BYTE)) {
                    if (TiagoUtils.options.getConfigurationSection(Default.SectionEvents).getBoolean(Default.events.machineGuns)) {
                        Byte ByteMachineGunActive = container.get(new NamespacedKey(TiagoUtils.getPlugin(), "isMachineGunActive"), PersistentDataType.BYTE);
                        boolean isActive = ByteMachineGunActive.equals((byte) 1);
                        String bowType = container.get(new NamespacedKey(TiagoUtils.getPlugin(), "bowType"), PersistentDataType.STRING);
                        bossBar = Bukkit.createBossBar(meta.getDisplayName() + ChatColor.WHITE + " is " + (isActive ? ChatColor.GREEN + "ACTIVE" : ChatColor.DARK_RED + "DISABLED"), getBarColor(bowType), BarStyle.SOLID);
                        bossBar.addPlayer(p);
                    }  else {
                        p.sendMessage("");
                        p.sendMessage("Machine Guns are " + ChatColor.RED + "disabled!");
                        p.sendMessage("");
                    }
                }
            }
        }
    }
}
