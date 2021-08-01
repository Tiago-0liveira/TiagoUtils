package me.tiago0liveira.TiagoUtils.Enchantments;

import me.tiago0liveira.TiagoUtils.TiagoUtils;
import me.tiago0liveira.TiagoUtils.enums.PersistentDataManager;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MachineSpeed extends Enchantment {
    public static final String Key = "machineSpeed";

    public MachineSpeed() {
        super(new NamespacedKey(TiagoUtils.getPlugin(), Key));
    }

    @Override
    public String getName() {
        return "Machine Speed";
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BOW;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        return meta != null && PersistentDataManager.isMachineGun.has(meta);
    }
}
