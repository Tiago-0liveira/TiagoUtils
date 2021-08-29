package me.tiago0liveira.TiagoUtils.enums.configs;

public class ClickInventoryItemAction {
    /* Menus */
    public enum Prefix {
        menu,
        commandsMenu,
        eventsMenu,
        playerMenu,
        customEnchantsMenu
    }
    public enum Menus {
        MenuButton,
        CommandsMenu,
        EventsMenu,
        PlayerMenu,
        CustomEnchants
    }
    /* Commands Menu */
    public enum CommandsMenu {
        ElementalBow,
        Fly,
        God,
        Heal,
        Home,
        opEnchant,
        setMachineGun
    }
    /* Events Menu */
    public enum EventsMenu {
        onArrowCollides,
        onBadWeather,
        machineGuns
    }
    /* Player Menu */
    public enum PlayerMenu {

    }
    /* Custom Enchants */
    public enum CustomEnchantsMenu {

    }

    @SuppressWarnings("unchecked")
    public static <T> T getItemAction(String stringifiedItemAction) {
        String[] splitString = stringifiedItemAction.split(":");
        String thePrefix = splitString[0];
        String theAction = splitString[1];
        if (thePrefix.equals(Prefix.menu.toString())) {
            return (T) Menus.valueOf(theAction);
        } else if (thePrefix.equals(Prefix.commandsMenu.toString())) {
            return (T) CommandsMenu.valueOf(theAction);
        } else if (thePrefix.equals(Prefix.eventsMenu.toString())) {
            return (T) EventsMenu.valueOf(theAction);
        } else if (thePrefix.equals(Prefix.playerMenu.toString())) {
            return (T) PlayerMenu.valueOf(theAction);
        } else if (thePrefix.equals(Prefix.customEnchantsMenu.toString())) {
            return (T) CustomEnchantsMenu.valueOf(theAction);
        } else return null;
    }
}
