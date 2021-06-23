package me.tiago0liveira.TiagoUtils.Gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryFactory {
    public static Inventory InvBorder54(Player player, String MenuTitle) {
        Inventory inventory = Bukkit.createInventory(player, 54, MenuTitle);
        ItemStack border = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, border);
        }
        for (int i = 54-9; i < 54; i++) {
            inventory.setItem(i, border);
        }
        for (int i = 1; i <= 4; i++) {
            inventory.setItem(i*9, border);
            inventory.setItem((i*9)+8, border);
        }

        return inventory;
    }
}
