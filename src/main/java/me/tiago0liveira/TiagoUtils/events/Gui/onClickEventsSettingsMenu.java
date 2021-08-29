package me.tiago0liveira.TiagoUtils.events.Gui;

import me.tiago0liveira.TiagoUtils.Gui.InventoryFactory;
import me.tiago0liveira.TiagoUtils.enums.PersistentDataManager;
import me.tiago0liveira.TiagoUtils.enums.configs.ClickInventoryItemAction;
import me.tiago0liveira.TiagoUtils.enums.configs.Default;
import me.tiago0liveira.TiagoUtils.helpers.RandomHelpers;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class onClickEventsSettingsMenu implements Listener {

    @EventHandler()
    public void onClickEventSettingsMenu(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(InventoryFactory.TitleMenuEvents)) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null) {
                ItemMeta clickedItemMeta = clickedItem.getItemMeta();
                if (clickedItemMeta != null) {
                    String unparsedClickAction = PersistentDataManager.clickAction.get(clickedItemMeta);
                    if (!RandomHelpers.mainMenuClicked(clickedItemMeta, player) && PersistentDataManager.clickAction.has(clickedItemMeta)) {
                        ClickInventoryItemAction.EventsMenu clickAction = ClickInventoryItemAction.getItemAction(unparsedClickAction);
                        boolean ActionValue = PersistentDataManager.ActionValue.get(clickedItemMeta);
                        player.sendMessage("click action -> " + clickAction);
                        switch (clickAction) {
                            case machineGuns:
                                RandomHelpers.setGlobalPermission(Default.SectionEvents, Default.events.machineGuns, !ActionValue);
                                break;
                            case onArrowCollides:
                                RandomHelpers.setGlobalPermission(Default.SectionEvents, Default.events.onArrowCollides, !ActionValue);
                                break;
                            case onBadWeather:
                                RandomHelpers.setGlobalPermission(Default.SectionEvents, Default.events.onBadWeather, !ActionValue);
                                break;
                            default:
                                player.sendMessage(ChatColor.RED + "ERROR" + ChatColor.DARK_GRAY + " | " + ChatColor.WHITE + "onClickCommandsSettingsMenu, values (clickAction, ActionValue)-> " + clickAction + " -- " + ActionValue);
                                break;
                        }
                    }
                }
            }
        }
    }
}
