package me.tiago0liveira.TiagoUtils.helpers;

import java.util.ArrayList;
import java.util.List;

public class ExtraStringMethods {
    public static boolean someEqualsIgnore(List<String> list, String arg) {
        for (String a : list) {
            boolean val = a.equalsIgnoreCase(arg);
            if (val) return true;
        }
        return false;
    }
    public static List<String> allMatchesStartWith(List<String> list, String arg) {
        List<String> returnList = new ArrayList<>();
        for (String string: list) {
            if (string.startsWith(arg)) returnList.add(string);
        }
        return returnList;
    }
}
