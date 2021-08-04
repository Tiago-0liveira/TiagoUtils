package me.tiago0liveira.TiagoUtils.enums;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.configs.ClickInventoryItemAction;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class PersistentDataManager {
    public static class Keys {
        public static final String isMachineGun = "isMachineGun";
        public static final String isMachineGunActive = "isMachineGunActive";
        public static final String BowType = "bowType";
        public static final String ClickAction = "clickAction";
        public static final String ActionValue = "ActionValue";
    }
    public static class isMachineGun {
        public static boolean get(ItemMeta meta) {
            return has(meta);
        }
        public static void set(ItemMeta meta, boolean val) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(new NamespacedKey(TiagoUtils.getPlugin(), Keys.isMachineGun), PersistentDataType.BYTE, val ? (byte) 1 : (byte) 0);
        }
        public static boolean has(ItemMeta meta) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            return container.has(new NamespacedKey(TiagoUtils.getPlugin(), Keys.isMachineGun), PersistentDataType.BYTE);
        }
    }
    public static class isMachineGunActive {
        public static boolean get(ItemMeta meta) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            return container.get(new NamespacedKey(TiagoUtils.getPlugin(), Keys.isMachineGunActive), PersistentDataType.BYTE) == (byte) 1;
        }
        public static void set(ItemMeta meta, boolean val) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(new NamespacedKey(TiagoUtils.getPlugin(), Keys.isMachineGunActive), PersistentDataType.BYTE, val ? (byte) 1 : (byte) 0);
        }
        public static boolean has(ItemMeta meta) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            return container.has(new NamespacedKey(TiagoUtils.getPlugin(), Keys.isMachineGunActive), PersistentDataType.BYTE);
        }
    }
    public static class bowType {
        public static String get(ItemMeta meta) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            return container.get(new NamespacedKey(TiagoUtils.getPlugin(), Keys.BowType), PersistentDataType.STRING);
        }
        public static void set(ItemMeta meta, String val) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(new NamespacedKey(TiagoUtils.getPlugin(), Keys.BowType), PersistentDataType.STRING, val);
        }
        public static boolean has(ItemMeta meta) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            return container.has(new NamespacedKey(TiagoUtils.getPlugin(), Keys.BowType), PersistentDataType.STRING);
        }
    }
    public static class clickAction {
        public static ClickInventoryItemAction get(ItemMeta meta) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            return ClickInventoryItemAction.valueOf(container.get(new NamespacedKey(TiagoUtils.getPlugin(), Keys.ClickAction), PersistentDataType.STRING));
        }
        public static void set(ItemMeta meta, ClickInventoryItemAction val) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(new NamespacedKey(TiagoUtils.getPlugin(), Keys.ClickAction), PersistentDataType.STRING, val.toString());
        }
        public static boolean has(ItemMeta meta) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            return container.has(new NamespacedKey(TiagoUtils.getPlugin(), Keys.ClickAction), PersistentDataType.STRING);
        }
    }
    public static class ActionValue {
        public static boolean get(ItemMeta meta) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            return container.get(new NamespacedKey(TiagoUtils.getPlugin(), Keys.ActionValue), PersistentDataType.BYTE) == (byte) 1;
        }
        public static void set(ItemMeta meta, boolean val) {
            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(new NamespacedKey(TiagoUtils.getPlugin(), Keys.ActionValue), PersistentDataType.BYTE, val ? (byte) 1 : (byte) 0);
        }
    }
}
