package me.tiago0liveira.TiagoUtils.events.Gui;

import me.tiago0liveira.TiagoUtils.Gui.InventoryFactory;
import me.tiago0liveira.TiagoUtils.enums.PersistentDataManager;
import me.tiago0liveira.TiagoUtils.enums.configs.ClickInventoryItemAction;
import me.tiago0liveira.TiagoUtils.helpers.ExtraStringMethods;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class onClickAdminOptionsMenu implements Listener {

    @EventHandler
    public void onClickAdminOptionMenu(InventoryClickEvent e) {
        if (ExtraStringMethods.someEqualsIgnore(InventoryFactory.PreventItemMoveMenuTitles, e.getView().getTitle())) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            ItemStack clickedItem = e.getCurrentItem();

            if (e.getView().getTitle().equals(InventoryFactory.TitleMainMenu)) {
                if (clickedItem != null) {
                    ItemMeta clickedItemMeta = clickedItem.getItemMeta();
                    if (clickedItemMeta != null && PersistentDataManager.clickAction.has(clickedItemMeta)) {
                        String unparsedClickAction = PersistentDataManager.clickAction.get(clickedItemMeta);
                        ClickInventoryItemAction.Menus clickAction = ClickInventoryItemAction.getItemAction(unparsedClickAction);
                        switch (clickAction) {
                            case MenuButton:
                                Inventory mainMenu = InventoryFactory.mainMenuMenu(player);
                                player.openInventory(mainMenu);
                                break;
                            case CommandsMenu:
                                Inventory commandsMenu = InventoryFactory.commandsMenu(player);
                                player.openInventory(commandsMenu);
                                break;
                            case EventsMenu:
                                Inventory eventsMenu = InventoryFactory.eventsMenu(player);
                                player.openInventory(eventsMenu);
                                break;
                            case PlayerMenu:
                                Inventory playerMenu = InventoryFactory.playerMenu(player);
                                player.openInventory(playerMenu);
                                break;
                            case CustomEnchants:
                                Inventory customEnchants = InventoryFactory.customEnchants(player);
                                player.openInventory(customEnchants);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }
}
