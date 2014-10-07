package ru.vovamaster99.rcservmod.ob.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import ru.vovamaster99.rcservmod.ModBase;

import cpw.mods.fml.common.registry.GameRegistry;

public class OBBlocks
{
	public static Block airCobblestone;
	public static Block airGlowstone;
    public static Block airStone;
    public static Block airDirt;
    public static Block airGrass;
	public static Block airOre;
	public static Block matterOre;
	public static Block polymerOre;
	public static Block matterHandler;
	
	public static void blocks()
	{
		airCobblestone = new AirBase(Material.rock).setBlockName("airCobblestone").setCreativeTab(ModBase.buildingBlocks).setBlockTextureName("minecraft:cobblestone");
		airGlowstone =  new AirBase(Material.glass).setBlockName("airGlowstone").setCreativeTab(ModBase.buildingBlocks).setBlockTextureName("minecraft:glowstone").setLightLevel(1.0F).setStepSound(Block.soundTypeGlass);
        airStone = new AirBase(Material.rock).setBlockName("airStone").setCreativeTab(ModBase.buildingBlocks).setBlockTextureName("minecraft:stone");
		airDirt = new AirBase(Material.ground).setBlockName("airDirt").setCreativeTab(ModBase.buildingBlocks).setBlockTextureName("minecraft:dirt").setStepSound(Block.soundTypeGrass);
        airGrass = new AirBase(Material.grass).setBlockName("airGrass").setCreativeTab(ModBase.buildingBlocks).setBlockTextureName("minecraft:grass");
		airOre = new AirOre().setBlockName("airOre").setHardness(1.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ModBase.buildingBlocks).setBlockTextureName(ModBase.MODID + ":" + "airOre");
		matterOre = new MatterOre().setBlockName("matterOre").setHardness(2.0F).setResistance(6.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ModBase.buildingBlocks).setBlockTextureName(ModBase.MODID + ":" + "matterOre");
		polymerOre = new PolymerOre().setBlockName("polymerOre").setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone).setCreativeTab(ModBase.buildingBlocks).setBlockTextureName(ModBase.MODID + ":" + "polymerOre");
		matterHandler = new MatterHandler().setBlockName("matterHandler").setCreativeTab(ModBase.buildingBlocks).setStepSound(Block.soundTypeMetal);
		
		GameRegistry.registerBlock(airCobblestone, "airCobblestone");
	    GameRegistry.registerBlock(airGlowstone, "airGlowstone");
	    GameRegistry.registerBlock(airStone, "airStone");
		GameRegistry.registerBlock(airOre, "airOre");
		GameRegistry.registerBlock(matterOre, "matterOre");
		GameRegistry.registerBlock(polymerOre, "polymerOre");
		GameRegistry.registerBlock(matterHandler, "matterHandler");
	}
}
