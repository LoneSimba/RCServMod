package ru.vovamaster99.rcservmod;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.internal.NetworkModHolder;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import ru.vovamaster99.rcservmod.ob.blocks.OBBlocks;
import ru.vovamaster99.rcservmod.ob.gui.OBGuiHandler;
import ru.vovamaster99.rcservmod.ob.items.OBItems;
import ru.vovamaster99.rcservmod.ob.tileenity.TileEntityMatterHandler;
import ru.vovamaster99.rcservmod.proxy.CommonProxy;
import ru.vovamaster99.rcservmod.pt.blocks.PTBlocks;
import ru.vovamaster99.rcservmod.pt.craft.PTCrafts;
import ru.vovamaster99.rcservmod.pt.items.PTItems;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = ModBase.MODID, version = ModBase.VERSION)
public class ModBase
{
	public static final String MODID = "RCServMod";
	public static final String VERSION = "#2";
	
	public static CreativeTabs buildingBlocks = new CreativeTabs("buildingBlocks")
	{
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(Blocks.cobblestone);
		}
	};
	
	public static CreativeTabs materials = new CreativeTabs("materials")
	{
		public Item getTabIconItem()
		{
			return OBItems.rawMatter;

		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
			//OtherBlocks
			OBBlocks.blocks();
			OBItems.items();
			//PlasmaTech
			PTBlocks.blocks();
			PTItems.items();
			PTCrafts.crafts();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(MODID, new OBGuiHandler());
        GameRegistry.registerTileEntity(TileEntityMatterHandler.class, MODID + ".MatterHandler");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		
	}
}
