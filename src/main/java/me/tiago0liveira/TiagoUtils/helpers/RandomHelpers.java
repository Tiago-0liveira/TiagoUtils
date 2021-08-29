package me.tiago0liveira.TiagoUtils.helpers;

import me.tiago0liveira.TiagoUtils.Gui.InventoryFactory;
import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.PersistentDataManager;
import me.tiago0liveira.TiagoUtils.enums.configs.ClickInventoryItemAction;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

public class RandomHelpers {
    public static void setGlobalPermission(String section, String string, boolean bool) {
        TiagoUtils.options.getConfigurationSection(section).set(string, bool);
    }
    public static boolean mainMenuClicked(ItemMeta meta, Player player) {
        String unparsedClickAction = PersistentDataManager.clickAction.get(meta);
        if (unparsedClickAction != null) {
            if (ClickInventoryItemAction.getItemAction(unparsedClickAction).equals(ClickInventoryItemAction.Menus.MenuButton)) {
                player.openInventory(InventoryFactory.mainMenuMenu(player));
                return true;
            } else {
                return false;
            }
        } return false;
    }
}
