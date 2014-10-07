package ru.vovamaster99.rcservmod.ob.crafts;


import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.vovamaster99.rcservmod.ob.items.OBItems;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MHRecipes {
    private static final MHRecipes handlingBase = new MHRecipes();
    private Map handlingList = new HashMap();
    private Map experienceList = new HashMap();

    public static MHRecipes handling() {
        return handlingBase;
    }

    public MHRecipes() {

        handleMatter(OBItems.rawMatter, new ItemStack(OBItems.matter), 1.0F);

    }

    public void handleMatter(Item item, ItemStack itemstack, float exp) {
        handleMatter_a(new ItemStack(item, 1, 32767), itemstack, exp);
    }

    public void handleMatter_a(ItemStack itemstack, ItemStack itemStack, float exp) {
        handlingList.put(itemstack, itemStack);
        experienceList.put(itemStack, Float.valueOf(exp));
    }

    public ItemStack getHandlingResult(ItemStack itemstack) {
        Iterator iterator  = handlingList.entrySet().iterator();
        Entry entry;

        do {
            if(!iterator.hasNext())
                return null;

            entry = (Entry)iterator.next();
        }

        while (func_1_a(itemstack, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean func_1_a(ItemStack itemstack, ItemStack itemStack) {
        return itemStack.getItem() == itemstack.getItem() && (itemStack.getItemDamage() == 32767 || itemStack.getItemDamage() == itemstack.getItemDamage());
    }

    public Map getHandlingList() {
        return handlingList;
    }

    public float func_2_a(ItemStack itemStack) {
        float ret = itemStack.getItem().getSmeltingExperience(itemStack);

        if (ret != -1) return ret;

        Iterator iterator = experienceList.entrySet().iterator();
        Entry entry;

        do {
            if (!iterator.hasNext())
                return 0.0F;

            entry = (Entry)iterator.next();
        }
        while (!func_1_a(itemStack, (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }
}
