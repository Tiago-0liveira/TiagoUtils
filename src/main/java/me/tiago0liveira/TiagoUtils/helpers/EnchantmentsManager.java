package me.tiago0liveira.TiagoUtils.helpers;

import me.tiago0liveira.TiagoUtils.Enchantments.MachineSpeed;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EnchantmentsManager {
    public static final List<Enchantment> Enchantments = Arrays.asList(
            new MachineSpeed()
    );
    public static final MachineSpeed machineSpeed = (MachineSpeed) Enchantments.get(0);

    public static void registerEnchantments() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);/* I DO NOT KNOW WHAT THIS IS FOR !! */
            for (Enchantment e: Enchantments) {
                try {
                    Enchantment.registerEnchantment(e);
                } catch (IllegalArgumentException ignored) { }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unregisterEnchantments() {
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");
            keyField.setAccessible(true);

            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);
            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);
            for(Enchantment e: Enchantments) {
                if (byKey.containsKey(e.getKey())) {
                    byKey.remove(e.getKey());
                }
                if(byName.containsKey(e.getKey())) {
                    byName.remove(e.getKey());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
