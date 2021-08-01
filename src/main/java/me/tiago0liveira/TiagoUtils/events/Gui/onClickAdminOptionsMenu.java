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

    final public String TitleMenuCommands = "Commands Menu Options";
    final public String TitleMenuEvents = "Events Menu Options";
    final public String TitleMenuPlayer = "Player Menu Options";

    @EventHandler
    public void onClickAdminOptionMenu(InventoryClickEvent e) {
        if (ExtraStringMethods.someEqualsIgnore(InventoryFactory.PreventItemMoveMenuTitles, e.getView().getTitle())) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem != null) {

                ItemMeta clickedItemMeta = clickedItem.getItemMeta();
                if (clickedItemMeta != null && PersistentDataManager.clickAction.has(clickedItemMeta)) {
                    ClickInventoryItemAction clickAction = PersistentDataManager.clickAction.get(clickedItemMeta);
                    player.sendMessage("click action -> " + clickAction);
                    switch (clickAction) {
                        case MenuButton:
                            Inventory mainMenu = InventoryFactory.mainMenuMenu(player);
                            player.openInventory(mainMenu);
                            break;
                        case CommandsMenu:
                            Inventory commandsMenu = InventoryFactory.commandsMenu(player);
                            player.openInventory(commandsMenu);
                            break;
                        case "eventsMenu":

                            break;
                        case "playerMenu":

                            break;
                        default:
                            player.sendMessage("onClickAdminOptionsMenu.java|switch|ERRORRRRR");
                            break;
                    }
                } else {
                    player.sendMessage("onClickAdminOptionsMenu.java|ItemMeta null");
                }
            } else {
                player.sendMessage("onClickAdminOptionsMenu.java|clicked Item null");
            }
        }
    }
}
