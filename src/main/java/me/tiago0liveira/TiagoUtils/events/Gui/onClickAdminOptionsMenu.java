package me.tiago0liveira.TiagoUtils.events.Gui;

import me.tiago0liveira.TiagoUtils.Gui.InventoryFactory;
import me.tiago0liveira.TiagoUtils.commands.AdminOptions;
import me.tiago0liveira.TiagoUtils.enums.PersistentData;
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
        if (e.getView().getTitle().equals(AdminOptions.MenuTitle)) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem instanceof ItemStack) {

                ItemMeta clickedItemMeta = clickedItem.getItemMeta();
                if (clickedItemMeta instanceof ItemMeta) {
                    player.sendMessage("onClickAdminOptionsMenu.java|ItemMeta not null");
                    switch (PersistentData.clickAction.get(clickedItemMeta)) {
                        case "commandsMenu":
                            Inventory inv = InventoryFactory.InvBorder54(player, "Commands Menu");

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
