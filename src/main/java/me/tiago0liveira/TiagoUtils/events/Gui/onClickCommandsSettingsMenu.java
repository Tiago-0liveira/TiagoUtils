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

public class onClickCommandsSettingsMenu implements Listener {

    @EventHandler()
    public void onClickCommandSettingsMenu(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(InventoryFactory.TitleMenuCommands)) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null) {
                ItemMeta clickedItemMeta = clickedItem.getItemMeta();
                if (clickedItemMeta != null) {
                    String unparsedClickAction = PersistentDataManager.clickAction.get(clickedItemMeta);
                    boolean bool = !RandomHelpers.mainMenuClicked(clickedItemMeta, player);
                    if (bool && PersistentDataManager.clickAction.has(clickedItemMeta)) {
                        ClickInventoryItemAction.CommandsMenu clickAction = ClickInventoryItemAction.getItemAction(unparsedClickAction);
                        boolean ActionValue = PersistentDataManager.ActionValue.get(clickedItemMeta);
                        switch (clickAction) {
                            case ElementalBow:
                                RandomHelpers.setGlobalPermission(Default.SectionCommands, Default.commands.ElementalBow, !ActionValue);
                                break;
                            case Fly:
                                RandomHelpers.setGlobalPermission(Default.SectionCommands, Default.commands.Fly, !ActionValue);
                                break;
                            case God:
                                RandomHelpers.setGlobalPermission(Default.SectionCommands, Default.commands.God, !ActionValue);
                                break;
                            case Heal:
                                RandomHelpers.setGlobalPermission(Default.SectionCommands, Default.commands.Heal, !ActionValue);
                                break;
                            case Home:
                                RandomHelpers.setGlobalPermission(Default.SectionCommands, Default.commands.Home, !ActionValue);
                                break;
                            case opEnchant:
                                RandomHelpers.setGlobalPermission(Default.SectionCommands, Default.commands.opEnchant, !ActionValue);
                                break;
                            case setMachineGun:
                                RandomHelpers.setGlobalPermission(Default.SectionCommands, Default.commands.setMachineGun, !ActionValue);
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
