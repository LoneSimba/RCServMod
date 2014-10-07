package ru.vovamaster99.rcservmod.ob.items;

import ru.vovamaster99.rcservmod.ModBase;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class OBItems {

	public static Item rawMatter;
	public static Item rawPolymer;
	public static Item matter;
	
	public static void items()
	{
		rawMatter = new Matter().setCreativeTab(ModBase.materials).setUnlocalizedName("rawMatter").setTextureName(ModBase.MODID + ":" + "rawMatter");
		rawPolymer = new Item().setCreativeTab(ModBase.materials).setUnlocalizedName("rawPolymer").setTextureName(ModBase.MODID + ":" + "rawPolymer");
		matter = new Matter().setCreativeTab(ModBase.materials).setUnlocalizedName("matter").setTextureName(ModBase.MODID + ":" + "matter");
		
		
		GameRegistry.registerItem(rawMatter, "rawMatter");
		GameRegistry.registerItem(rawPolymer, "rawPolymer");
		GameRegistry.registerItem(matter, "matter");
	}
}
