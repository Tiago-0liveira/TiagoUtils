package me.tiago0liveira.TiagoUtils.events;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class onBadWeather implements Listener {
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        ConfigurationSection eventsSection = TiagoUtils.options.getConfigurationSection("events");
        if (eventsSection != null && eventsSection.getBoolean("onBadWeather")) {
            e.setCancelled(true);
        }
    }
}
