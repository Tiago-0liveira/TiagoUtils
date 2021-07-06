package me.tiago0liveira.TiagoUtils.enums;

import java.util.Arrays;
import java.util.List;


public final class Permissions {

    public static final String SectionCommands = "commands";
    public static final String SectionEvents = "events";
    public static String getPermissionString(String... strings) {
        String res = "";
        for (String el: strings) { res += el + (strings.length-1 == Arrays.asList(strings).indexOf(el) ? "" : "."); }
        return res;
    }

    public static final class Commands {
        private static final String base = SectionCommands;
        public static List<String> commandsList = Arrays.asList(
            getPermissionString(base, "Fly"),
            getPermissionString(base, "God"),
            getPermissionString(base, "Heal"),
            getPermissionString(base, "Home"),
            getPermissionString(base, "ElementalBow"),
            getPermissionString(base, "opEnchant"),
            getPermissionString(base, "setMachineGun")
        );

        public static final String Fly = getPermissionString(base, "Fly");
        public static final String God = getPermissionString(base, "God");
        public static final String Heal = getPermissionString(base, "Heal");
        public static final String Home = getPermissionString(base, "Home");
        public static final String ElementalBow = getPermissionString(base, "ElementalBow");
        public static final String opEnchant = getPermissionString(base, "opEnchant");
        public static final String setMachineGun = getPermissionString(base, "setMachineGun");
    }
    public static final class Events {
        private static final String base = SectionEvents;
        public static List<String> eventsList = Arrays.asList(
            getPermissionString(base, "onArrowCollides"),
            getPermissionString(base, "onBadWeather"),
            getPermissionString(base, "machineGuns")
        );

        public static final String onArrowCollides = getPermissionString(base, "onArrowCollides");
        public static final String onBadWeather = getPermissionString(base, "onBadWeather");
        public static final String machineGuns = getPermissionString(base, "machineGuns");
    }
}
