package com.ashawsolutions.waterspile.Items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack spile;

    public static void Init(){
        createSpile();
    }

    private static void createSpile(){
        ItemStack item = new ItemStack(Material.STICK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Water Spile");
        List<String> loreStrings = new ArrayList<String>();
        loreStrings.add("§7Used for gather water out of logs");
        meta.setLore(loreStrings);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        spile = item;
    }
}
