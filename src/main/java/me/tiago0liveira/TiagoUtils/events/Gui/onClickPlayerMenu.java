package me.tiago0liveira.TiagoUtils.events.Gui;

import me.tiago0liveira.TiagoUtils.Gui.InventoryFactory;
import me.tiago0liveira.TiagoUtils.enums.PersistentDataManager;
import me.tiago0liveira.TiagoUtils.enums.configs.ClickInventoryItemAction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class onClickPlayerMenu implements Listener {

    @EventHandler
    public void playerMenuClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(InventoryFactory.TitleMenuPlayer)) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null) {
                ItemMeta clickedItemMeta = clickedItem.getItemMeta();
                if (clickedItemMeta != null) {
                    String unparsedClickAction = PersistentDataManager.clickAction.get(clickedItemMeta);
                    if (ClickInventoryItemAction.getItemAction(unparsedClickAction).equals(ClickInventoryItemAction.Menus.MenuButton)) {
                        player.openInventory(InventoryFactory.mainMenuMenu(player));
                    } else {

                    }
                }
            } else {
                player.sendMessage("onClickPlayerMenu.java|clicked Item null");
            }
        }
    }
}
