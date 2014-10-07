package ru.vovamaster99.rcservmod.ob.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import ru.vovamaster99.rcservmod.ob.tileenity.TileEntityMatterHandler;

public class MatterHandler extends Container{

    private TileEntityMatterHandler tile;
    private EntityPlayer player;
    public MatterHandler(EntityPlayer p, TileEntityMatterHandler te){
        tile = te; player = p;
        addSlotToContainer(new SlotROM(tile, 0, 26, 35));
        for(int i = 0; i < 9; i++)
            addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 9; j++)
                addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slotID){
        return null;
    }

    public boolean canInteractWith(EntityPlayer player){
        return true;
    }

    private class SlotROM extends Slot {
        public SlotROM(IInventory par1iInventory, int par2, int par3, int par4) {
            super(par1iInventory, par2, par3, par4);

        }
    }
}
