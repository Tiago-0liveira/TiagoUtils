package me.tiago0liveira.TiagoUtils.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class onBadWeather implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        e.getWorld().setClearWeatherDuration(1000000000);
    }
}
