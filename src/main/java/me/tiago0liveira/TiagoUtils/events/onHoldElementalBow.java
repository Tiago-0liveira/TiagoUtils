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

public class onHoldElementalBow implements Listener {
    @EventHandler
    public void onHoldBow(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        ItemStack itemHeld = p.getInventory().getItem(e.getNewSlot());
        if (!TiagoUtils.options.getConfigurationSection(Default.SectionEvents).getBoolean(Default.events.onArrowCollides) && itemHeld != null) {
            if (itemHeld.getType().equals(Material.BOW)) {
                ItemMeta meta = itemHeld.getItemMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                if (container.has(new NamespacedKey(TiagoUtils.getPlugin(), "bowType"), PersistentDataType.STRING)) {
                    p.sendMessage("");
                    p.sendMessage(ChatColor.DARK_GRAY + "Elemental Bows are " + ChatColor.RED + "disabled" + ChatColor.DARK_GRAY + "!");
                    p.sendMessage("");
                }
            }
        }
    }

    public static BarColor getBarColor(String bowType) {
        if (bowType.equals(BowType.EXPLOSION.toString())) {
            return BarColor.RED;
        } else if (bowType.equals(BowType.TELEPORT.toString())) {
            return BarColor.PURPLE;
        } else if(bowType.equals(BowType.LIGHTNING.toString())) {
            return BarColor.BLUE;
        } else if(bowType.equals("DEFAULT")) {
            return BarColor.WHITE;
        } else {
            System.err.println("ERROR|UNKNOWN bowType->" + bowType);
            return null;
        }
    }
}
