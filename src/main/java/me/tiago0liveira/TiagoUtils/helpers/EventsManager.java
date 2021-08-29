package me.tiago0liveira.TiagoUtils.helpers;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.events.Gui.*;
import me.tiago0liveira.TiagoUtils.events.*;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.List;

public class EventsManager {

    public static final List<Listener> Events = Arrays.asList(
        new onClickAdminOptionsMenu(),
        new onClickCommandsSettingsMenu(),
        new onClickCustomEnchantmentsMenu(),
        new onClickEventsSettingsMenu(),
        new onClickPlayerMenu(),
        new onArrowCollides(),
        new onBadWeather(),
        new onHoldElementalBow(),
        new onMachineGunHold(),
        new onRightClickForMachineGunBow()
    );
    public static void registerEvents(){
        for(Listener e : Events) {
            TiagoUtils.getPlugin().getServer().getPluginManager().registerEvents(e, TiagoUtils.getPlugin());
        }
    }
}
