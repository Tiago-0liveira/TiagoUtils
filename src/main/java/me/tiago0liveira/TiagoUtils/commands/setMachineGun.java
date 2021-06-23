package me.tiago0liveira.TiagoUtils.commands;

import me.tiago0liveira.TiagoUtils.TiagoUtils;

import me.tiago0liveira.TiagoUtils.enums.configs.Default;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;


public class setMachineGun implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (TiagoUtils.options.getConfigurationSection(Default.SectionCommands).getBoolean(Default.commands.setMachineGun)) {
                ItemStack bow = player.getInventory().getItemInMainHand();
                if (bow.getType().equals(Material.BOW)) {
                    ItemMeta meta = bow.getItemMeta();
                    if (meta != null) {
                        PersistentDataContainer container = meta.getPersistentDataContainer();
                        if (!container.has(new NamespacedKey(TiagoUtils.getPlugin(), "isMachineGun"), PersistentDataType.BYTE)) {
                            container.set(new NamespacedKey(TiagoUtils.getPlugin(), "isMachineGun"), PersistentDataType.BYTE, (byte) 1);
                            container.set(new NamespacedKey(TiagoUtils.getPlugin(), "isMachineGunActive"), PersistentDataType.BYTE, (byte) 0);
                            String bowType = container.get(new NamespacedKey(TiagoUtils.getPlugin(), "bowType"), PersistentDataType.STRING);
                            if (bowType == null) {
                                container.set(new NamespacedKey(TiagoUtils.getPlugin(), "bowType"), PersistentDataType.STRING, "DEFAULT");
                                bowType = "default";
                            }
                            List<String> Lore;
                            List<String> bowLore = meta.getLore();
                            if (bowLore != null) {
                                Lore = new ArrayList<>(bowLore);
                            } else {
                                Lore = new ArrayList<>();
                            }
                            Lore.add(ChatColor.RED + "MACHINE GUN");
                            Lore.add(ChatColor.DARK_GRAY + "Right click to toggle " + ChatColor.RED + "Machine Gun Mode");
                            meta.setLore(Lore);
                            meta.setDisplayName(opBow.getBowName(bowType) + ChatColor.DARK_RED + " MACHINE GUN");
                            bow.setItemMeta(meta);
                            int BowSlot = player.getInventory().getHeldItemSlot();
                            TiagoUtils.getPlugin().getServer().getPluginManager().callEvent(new PlayerItemHeldEvent(player, BowSlot, BowSlot));
                            player.sendMessage(ChatColor.DARK_GRAY + "Your bow is now a " + ChatColor.RED + "MACHINE GUN" + ChatColor.DARK_GRAY + "!");
                        } else {
                            player.sendMessage(ChatColor.DARK_GRAY + "This bow is already an " + ChatColor.RED + "MACHINE GUN" + ChatColor.DARK_GRAY + "!");
                        }
                    } else {
                        player.sendMessage(ChatColor.DARK_GRAY + "No Meta Data for this item");
                    }
                } else {
                    player.sendMessage(ChatColor.DARK_GRAY + "That's not a " + ChatColor.RED + "bow" + ChatColor.DARK_GRAY + "!");
                }
            } else {
                player.sendMessage(ChatColor.DARK_GRAY + "The command "+ ChatColor.WHITE + "setMachineGun" + ChatColor.DARK_GRAY + " is " + ChatColor.RED + "disabled" + ChatColor.DARK_GRAY + " atm!");
            }
        }
        return true;
    }
}
