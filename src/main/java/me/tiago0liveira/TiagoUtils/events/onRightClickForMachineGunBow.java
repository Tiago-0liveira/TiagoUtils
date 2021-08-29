package me.tiago0liveira.TiagoUtils.events;

import me.tiago0liveira.TiagoUtils.Enchantments.MachineSpeed;
import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.PersistentDataManager;
import me.tiago0liveira.TiagoUtils.enums.configs.Default;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Map;
import java.util.UUID;

public class onRightClickForMachineGunBow implements Listener {

    public static Boolean isMachineGunActive;
    private static BukkitTask machineGunLoop;
    private static UUID playerUUID;
    public static ItemStack onUseBow;

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            ItemStack bow = player.getInventory().getItemInMainHand();

            if (bow.getType().equals(Material.BOW)) {
                ItemMeta meta = bow.getItemMeta();
                if (meta != null ) {
                    if (PersistentDataManager.isMachineGun.get(meta)) {
                        if (TiagoUtils.options.getConfigurationSection(Default.SectionEvents).getBoolean(Default.events.machineGuns)) {
                            playerUUID = player.getUniqueId();
                            e.setCancelled(true);
                            if (isMachineGunActive == null) {
                                isMachineGunActive = false;
                            }
                            isMachineGunActive = !isMachineGunActive;
                            if (isMachineGunActive) {
                                startLoop(bow);
                            } else {
                                stopLoop(onUseBow);
                            }
                            player.sendMessage("Machine Gun " + (isMachineGunActive ? ChatColor.GREEN + "ACTIVE" : ChatColor.RED + "DISABLED"));
                            PersistentDataManager.isMachineGunActive.set(meta, isMachineGunActive);
                            bow.setItemMeta(meta);
                            int BowSlot = player.getInventory().getHeldItemSlot();
                            player.getServer().getPluginManager().callEvent(new PlayerItemHeldEvent(player, BowSlot, BowSlot));
                        }
                    }
                }
            }
        }
    }
    private void startLoop(ItemStack bow) {
        onUseBow = bow;
        Long gunPeriod = 5L;
        for(Map.Entry<Enchantment, Integer> e: bow.getEnchantments().entrySet()) {
            System.out.println(e.getKey().getName() + " -> " + e.getValue());
        }
        if (bow.getEnchantments().get(MachineSpeed.Key) != null) {
            Integer encLvl = bow.getEnchantments().get(MachineSpeed.Key);
            gunPeriod = (5L)/((long) encLvl *encLvl);
        }
        machineGunLoop = new BukkitRunnable() {
            @Override
            public void run() {
                Player p = TiagoUtils.getPlugin().getServer().getPlayer(playerUUID);
                if (p == null) {
                    stopLoop();
                } else {
                    if (onUseBow != null) {
                        if (p.getInventory().getItemInMainHand().equals(onUseBow)) {
                            Location eyeLoc = p.getEyeLocation();
                            Arrow arrow = p.getWorld().spawnArrow(eyeLoc, eyeLoc.getDirection().multiply(2), 2.0f, 1f);
                            arrow.setShooter(p);
                        } else {
                            stopLoop(onUseBow);
                            onUseBow = null;
                        }
                    } else {
                        stopLoop();
                        onUseBow = null;
                    }
                }
            }

        }.runTaskTimer(TiagoUtils.getPlugin(), 0L, gunPeriod);
    }
    public static void stopLoop() {
        if (machineGunLoop != null) {
            if (!machineGunLoop.isCancelled()) {
                machineGunLoop.cancel();
                machineGunLoop = null;
                isMachineGunActive = false;
                onUseBow = null;
            }
        }
    }
    public static void stopLoop(ItemStack bow) {
        if (machineGunLoop != null) {
            if (!machineGunLoop.isCancelled()) {
                machineGunLoop.cancel();
                isMachineGunActive = false;
                machineGunLoop = null;
                ItemMeta meta = bow.getItemMeta();
                if (meta != null) {
                    PersistentDataManager.isMachineGunActive.set(meta, false);
                    bow.setItemMeta(meta);
                }
            }
        }
    }
}
