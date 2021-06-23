package me.tiago0liveira.TiagoUtils.events;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.configs.Default;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class onTreeCapitatorTrigger implements Listener {

    @EventHandler
    public void onCapitatorAxeBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();

        if (TiagoUtils.options.getConfigurationSection(Default.SectionEvents).getBoolean(Default.events.onTreeCapitatorTrigger)) {
            if (e.getBlock().getType().name().endsWith("_LOG")) {
                ItemStack tool = player.getInventory().getItemInMainHand();

                if (tool.getType().name().endsWith("_AXE")) {
                    Material blockType = e.getBlock().getType();
                    Location blockOrigin = e.getBlock().getLocation();

                    List<Block> logs = this.getLogs(blockOrigin, blockType.toString());
                    for (Block log : logs) {
                        Damageable meta = (Damageable) tool.getItemMeta();
                        assert meta != null;
                        int durability = tool.getType().getMaxDurability() - meta.getDamage() - 1;
                        if (logs.size() > durability) {
                            logs = logs.subList(0, durability);
                        }
                        meta.setDamage(meta.getDamage() + logs.size());
                        tool.setItemMeta((ItemMeta) meta);
                        log.breakNaturally(tool);
                    }
                }
            }
        }
    }

    public List<Block> getLogs(Location origin, String type) {
        List<Block> logs = new ArrayList<>();
        List<Block> next = getSurrounding(origin, type);
        while (!next.isEmpty()) {
            List<Block> nextNext = new ArrayList<>();
            for (Block log : next) {
                if (!logs.contains(log)) {
                    logs.add(log);
                    nextNext.addAll(getSurrounding(log.getLocation(), type));
                }
            }
            next = nextNext;
        }
        return logs;
    }

    public List<Block> getSurrounding(Location origin, String type) {
        List<Block> blocks = new ArrayList<>();
        for (int x = -1; x < 2; x++)
            for (int y = -1; y < 2; y++)
                for (int z = -1; z < 2; z++) {
                    Location loc = origin.clone().add(x, y, z);
                    Block block = loc.getBlock();
                    if (!origin.equals(loc) && block.getType().name().endsWith(type)) {
                        blocks.add(block);
                    }
                }
        return blocks;
    }

}
