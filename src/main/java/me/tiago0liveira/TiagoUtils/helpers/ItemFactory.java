package me.tiago0liveira.TiagoUtils.helpers;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;

public class ItemFactory {
    private Material mat;
    private Integer Amount = 1;
    private String DisplayName;
    private List<String> Lore = new ArrayList<>();
    private HashMap<Enchantment, Integer> Enchantments = new HashMap<>();
    private Player OwningPlayer;

    public ItemFactory(Material material) {
        this.mat = material;
    }
    public ItemFactory(Material material, Integer Amount) {
        this(material);
        if (Amount != 1 && Amount <= 64 && Amount > 0) {
            this.Amount = Amount;
        } else {
            throw new Error("ItemStack can only have from 1-64 items!");
        }
    }
    public ItemFactory(boolean PlayerHead, Player player) {
        if (PlayerHead) {
            this.mat = Material.PLAYER_HEAD;
            this.OwningPlayer = player;
        } else {
            throw new Error("Dumb boolean with FALSE value!!\nYou want the skull or u don't ?");
        }
    }
    public ItemFactory(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        this.mat = item.getType();
        this.Amount = item.getAmount();
        try {
            this.DisplayName = meta.getDisplayName();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        this.Lore = meta.getLore();
        this.Enchantments = (HashMap<Enchantment, Integer>) item.getEnchantments();
        if (this.mat == Material.PLAYER_HEAD) this.OwningPlayer = (Player) ((SkullMeta) meta).getOwningPlayer();
    }

    public ItemFactory setAmount(Integer Amount) {
        if (Amount != 1 && Amount <= 64 && Amount > 0) {
            this.Amount = Amount;
        } else {
            throw new Error("ItemStack can only have from 1-64 items!");
        }
        return this;
    }

    public ItemFactory setMaterial(Material material) {
        this.mat = material;
        return this;
    }
    public ItemFactory setDisplayName(String displayName) {
        DisplayName = displayName;
        return this;
    }

    public ItemFactory setLore(List<String> lore) {
        Lore = lore;
        return this;
    }
    public ItemFactory addLore(String lore) {
        this.Lore.add(lore);
        return this;
    }
    public ItemFactory removeLore(String lore) {
        this.Lore.remove(lore);
        return this;
    }
    public ItemFactory clearLore() {
        this.Lore.clear();
        return this;
    }

    public ItemFactory setEnchantment(HashMap<Enchantment, Integer> enchantments) {
        this.Enchantments = enchantments;
        return this;
    }
    public ItemFactory addEnchantment(Enchantment Enchantment, Integer EnchantLevel) {
        this.Enchantments.put(Enchantment, EnchantLevel);
        return this;
    }
    public ItemFactory removeEnchantment(Enchantment Enchantment) {
        this.Enchantments.remove(Enchantment);
        return this;
    }
    public ItemFactory clearEnchantments() {
        this.Enchantments.clear();
        return this;
    }

    public ItemStack Build() {
        ItemStack Item = new ItemStack(this.mat, this.Amount);
        ItemMeta meta = Item.getItemMeta();
        assert meta != null;
        if (this.DisplayName != null) meta.setDisplayName(this.DisplayName);
        if (this.Lore != null && this.Lore.size() > 0) meta.setLore(this.Lore);
        if (this.Enchantments != null && this.Enchantments.size() > 0) Item.addEnchantments(this.Enchantments);
        if (this.OwningPlayer != null) ((SkullMeta) meta).setOwningPlayer(this.OwningPlayer);

        Item.setItemMeta(meta);
        return Item;
    }
    public ItemStack Build(BiFunction<ItemStack,ItemMeta,ItemStack> func) {
        ItemStack preBuiltItem = Build();
        ItemStack FinalItem = func.apply(preBuiltItem, preBuiltItem.getItemMeta());
        if (FinalItem != null) {
            return FinalItem;
        } else {
            throw new Error("The lambda Function HAS to return an ItemStack!!");
        }
    }
}
