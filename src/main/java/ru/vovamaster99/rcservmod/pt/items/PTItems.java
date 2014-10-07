package ru.vovamaster99.rcservmod.pt.items;

import net.minecraft.item.Item;

import ru.vovamaster99.rcservmod.ModBase;

import cpw.mods.fml.common.registry.GameRegistry;

public class PTItems {

	public static Item palladiumChunk025;
	
	public static void items()
	{
		palladiumChunk025 = new Item().setCreativeTab(ModBase.materials).setUnlocalizedName("palladiumChunk025").setTextureName(ModBase.MODID + ":" + "palladiumChunk025");
		
		GameRegistry.registerItem(palladiumChunk025, "palladiumChunk025");
	}
}
