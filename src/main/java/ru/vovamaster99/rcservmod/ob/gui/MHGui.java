package ru.vovamaster99.rcservmod.ob.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import ru.vovamaster99.rcservmod.ModBase;
import ru.vovamaster99.rcservmod.ob.tileenity.TileEntityMatterHandler;

public class MHGui extends GuiContainer{

    final private ResourceLocation texture = new ResourceLocation(ModBase.MODID + ":textures/gui/furnace.png");

    private TileEntityMatterHandler tile;

    public MHGui(EntityPlayer player,TileEntityMatterHandler te){
        super(new MatterHandler(player, te));
        tile = te;
        xSize = 176;
        ySize = 206;
    }
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
        int zX = (width - xSize) / 2;
        int zY = (height - ySize) / 2;
        mc.getTextureManager().bindTexture(texture);
        drawTexturedModalRect(zX, zY, 0, 0, xSize, ySize);
		
	}
}