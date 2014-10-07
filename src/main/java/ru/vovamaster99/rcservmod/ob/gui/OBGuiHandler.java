package ru.vovamaster99.rcservmod.ob.gui;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import ru.vovamaster99.rcservmod.ob.tileenity.TileEntityMatterHandler;

public class OBGuiHandler implements IGuiHandler {

	public Object getServerGuiElement(int id, EntityPlayer player, World wrd, int x, int y, int z){
        TileEntity te  = wrd.getTileEntity(x, y, z);
        switch(id){
            case 1://
                return new MatterHandler(player, (TileEntityMatterHandler)te);
            default:
                return null;
        }
    }

    public Object getClientGuiElement(int id, EntityPlayer player, World wrd, int x, int y, int z){
        TileEntity te  = wrd.getTileEntity(x, y, z);
        switch(id){
            case 1:
                return new MHGui(player, (TileEntityMatterHandler)te);
            default:
                return null;
        }
    }
}
