package ru.vovamaster99.rcservmod.pt.craft;

import net.minecraft.item.ItemStack;

import ru.vovamaster99.rcservmod.pt.blocks.PTBlocks;
import ru.vovamaster99.rcservmod.pt.items.PTItems;

import cpw.mods.fml.common.registry.GameRegistry;

public class PTCrafts {

	public static void crafts()
	{
		GameRegistry.addSmelting(PTBlocks.palladiumOre, new ItemStack(PTItems.palladiumChunk025), 2.0F);
	}
}
