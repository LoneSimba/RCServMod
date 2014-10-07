package ru.vovamaster99.rcservmod.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.vovamaster99.rcservmod.ob.gui.MatterHandler;
import ru.vovamaster99.rcservmod.ob.tileenity.TileEntityMatterHandler;

public class CommonProxy {
    public void load() {

    }
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
         switch (id) {
             case 1:
                 if (te !=null && te instanceof TileEntityMatterHandler)
                     return new MatterHandler(player, (TileEntityMatterHandler)te);
                 else
                     return null;
             default: return null;
         }
    }

    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }
}
