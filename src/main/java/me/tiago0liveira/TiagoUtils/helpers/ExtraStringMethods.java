package me.tiago0liveira.TiagoUtils.helpers;

import java.util.List;

public class ExtraStringMethods {
    public static boolean someEqualsIgnore(List<String> list, String arg) {
        for (String a : list) {
            boolean val = a.equalsIgnoreCase(arg);
            if (val) return true;
        }
        return false;
    }
}
