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

public class onClickCommandsSettingsMenu implements Listener {

    @EventHandler()
    public void onClickCommandSettingsMenu(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(InventoryFactory.TitleMenuCommands)) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null) {
                ItemMeta clickedItemMeta = clickedItem.getItemMeta();
                if (clickedItemMeta != null && PersistentDataManager.clickAction.has(clickedItemMeta)) {
                    ClickInventoryItemAction clickAction = PersistentDataManager.clickAction.get(clickedItemMeta);
                    player.sendMessage("click action -> " + clickAction);
                    switch (clickAction) {
                        case ElementalBow:

                            break;
                        case Fly:

                            break;
                        case God:

                            break;
                        case Heal:

                            break;
                        case Home:

                            break;
                        case opEnchant:

                            break;
                        case setMachineGun:

                            break;
                        default:
                            player.sendMessage("default");
                            break;
                    }
                }
            } else {
                player.sendMessage("onClickAdminOptionsMenu.java|clicked Item null");
            }
        }
    }
}
