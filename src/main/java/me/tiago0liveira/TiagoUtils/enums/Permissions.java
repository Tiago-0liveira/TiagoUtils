package me.tiago0liveira.TiagoUtils.enums;

import java.util.Arrays;

import static me.tiago0liveira.TiagoUtils.TiagoUtils.pluginName;

public class Permissions {

    public static final String SectionCommands = "commands";
    public static final String SectionEvents = "events";
    public static String getPermissionString (String... strings) {
        String res = ".";
        for (String el: strings) { res += el + (strings.length-1 == Arrays.asList(strings).indexOf(el) ? "" : "."); }
        return String.join(".", pluginName) + res;
    }

    public static final class Commands {
        public static final String Fly = getPermissionString(SectionCommands, "Fly");
        public static final String God = getPermissionString(SectionCommands, "God");
        public static final String Heal = getPermissionString(SectionCommands, "Heal");
        public static final String Home = getPermissionString(SectionCommands, "Home");
        public static final String ElementalBow = getPermissionString(SectionCommands, "ElementalBow");
        public static final String opEnchant = getPermissionString(SectionCommands, "opEnchant");
        public static final String setMachineGun = getPermissionString(SectionCommands, "setMachineGun");
    }
    public static final class Events {
        public static final String onArrowCollides = getPermissionString(SectionEvents, "onArrowCollides");
        public static final String onBadWeather = getPermissionString(SectionEvents, "onBadWeather");
        public static final String machineGuns = getPermissionString(SectionEvents, "machineGuns");
    }
}
