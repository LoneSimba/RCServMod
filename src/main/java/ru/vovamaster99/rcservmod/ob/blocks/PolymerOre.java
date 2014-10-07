package ru.vovamaster99.rcservmod.ob.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import ru.vovamaster99.rcservmod.ob.items.OBItems;

public class PolymerOre extends Block {

	public PolymerOre()
	{
		super(Material.rock);
	}
	
	@Override
	public Item getItemDropped(int par1, Random rand, int par2)
	{
		return OBItems.rawPolymer;
	}
}
