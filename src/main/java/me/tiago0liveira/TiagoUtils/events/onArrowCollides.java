package me.tiago0liveira.TiagoUtils.events;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.BowType;
import me.tiago0liveira.TiagoUtils.enums.PersistentDataManager;
import me.tiago0liveira.TiagoUtils.enums.configs.Default;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class onArrowCollides implements Listener {

    @EventHandler
    public void onArrowCollides(ProjectileHitEvent e) {
        if (e.getEntity() instanceof Arrow) {
            Player player = (Player) e.getEntity().getShooter();
            if (TiagoUtils.options.getConfigurationSection(Default.SectionEvents).getBoolean(Default.events.onArrowCollides)) {
                ItemStack bow = player.getInventory().getItemInMainHand();
                ItemMeta meta = bow.getItemMeta();
                if (meta != null) {
                    String bowType = PersistentDataManager.bowType.get(meta);
                    if (bowType != null) {
                        if (bowType.equals(BowType.EXPLOSION.toString())) {
                            e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 3.5f);
                        } else if (bowType.equals(BowType.TELEPORT.toString())) {
                            player.teleport(e.getEntity().getLocation());
                        } else if (bowType.equals(BowType.LIGHTNING.toString())) {
                            e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation());
                        }
                        if (bowType.equals(BowType.EXPLOSION.toString()) || bowType.equals(BowType.TELEPORT.toString()) || bowType.equals(BowType.LIGHTNING.toString())) {
                            e.getEntity().remove();
                        }
                    }
                }
            }
        }
    }

}