package ru.vovamaster99.rcservmod.ob.gui;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import ru.vovamaster99.rcservmod.ob.crafts.MHRecipes;

public class SlotMH extends Slot {

    private EntityPlayer p;
    private int number;

    public SlotMH(EntityPlayer player, IInventory inventory, int par3, int par4, int par5) {
        super(inventory, par3, par4, par5);
        p = player;
    }

    @Override
    public boolean isItemValid(ItemStack is) {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int par1) {
        if (getHasStack())
            number +=Math.min(par1, getStack().stackSize);

        return super.decrStackSize(par1);
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack is) {
        onCrafting(is);
        onPickupFromSlot(player, is);
    }

    @Override
    protected void onCrafting(ItemStack is, int par2) {
        number += par2;
        onCrafting(is);
    }

    @Override
    protected void onCrafting(ItemStack is) {
        is.onCrafting(p.worldObj, p, number);

        if (!p.worldObj.isRemote) {
            int i = number;
            float f = MHRecipes.handling().func_2_a(is);
            int j;

            if (f== 0.0F){
                i = 0;
            }
            else if (f < 1.0F){
                j = MathHelper.floor_float((float)i * f);

                if (j < MathHelper.ceiling_float_int((float)i * f) && (float)Math.random() < (float)i * f - (float)j)
                    ++j;

                i = j;
            }

            while (i > 0) {
                 j = EntityXPOrb.getXPSplit(i);
                 i -= j;
                p.worldObj.spawnEntityInWorld(new EntityXPOrb(p.worldObj, p.posX, p.posY + 0.5D, p.posZ + 0.5D, j));
            }

            number = 0;

            FMLCommonHandler.instance().firePlayerSmeltedEvent(p, is);
        }
    }


}
