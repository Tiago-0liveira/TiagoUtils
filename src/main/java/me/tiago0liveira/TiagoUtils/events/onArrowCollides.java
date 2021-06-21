package me.tiago0liveira.TiagoUtils.events;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.BowType;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class onArrowCollides implements Listener {

    @EventHandler
    public void onArrowCollides(ProjectileHitEvent e) {
        if (e.getEntity() instanceof Arrow) {
            Player player = (Player) e.getEntity().getShooter();
            if (TiagoUtils.options.getConfigurationSection("events").getBoolean("onArrowCollides")) {
                ItemStack bow = player.getInventory().getItemInMainHand();
                String bowType = bow.getItemMeta().getPersistentDataContainer().get(
                        new NamespacedKey(TiagoUtils.getPlugin(), "bowType"),
                        PersistentDataType.STRING
                );

                if (bowType == BowType.EXPLOSION.toString()) {
                    e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 4.0f);
                } else if(bowType == BowType.TELEPORT.toString()) {
                    player.teleport(e.getEntity().getLocation());
                }
            }
        }
    }

}