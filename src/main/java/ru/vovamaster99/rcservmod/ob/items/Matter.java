package ru.vovamaster99.rcservmod.ob.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Matter extends Item {

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack is)
	{
		return true;
	}
}
