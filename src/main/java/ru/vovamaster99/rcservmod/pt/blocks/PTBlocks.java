package ru.vovamaster99.rcservmod.pt.blocks;

import ru.vovamaster99.rcservmod.ModBase;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class PTBlocks {

	public static Block palladiumOre;
	
	public static void blocks()
	{
		palladiumOre = new PalladiumOre().setBlockName("palladiumOre").setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ModBase.buildingBlocks).setBlockTextureName(ModBase.MODID + ":" + "palladiumOre");
		
		GameRegistry.registerBlock(palladiumOre, "palladiumOre");
	}
}
