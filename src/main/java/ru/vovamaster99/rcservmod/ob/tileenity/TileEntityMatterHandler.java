package ru.vovamaster99.rcservmod.ob.tileenity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ru.vovamaster99.rcservmod.ob.blocks.MatterHandler;
import ru.vovamaster99.rcservmod.ob.crafts.MHRecipes;
import ru.vovamaster99.rcservmod.ob.items.OBItems;

public class TileEntityMatterHandler extends TileEntity implements IInventory {

    private static final int[] slotsSides = new int[] {0, 1};

    private ItemStack[] handlerItemStacks = new ItemStack[2];

    private int handlerWorkTime;

    private int handlingTime;

    private String field_1;

    @Override
    public int getSizeInventory() {
        return handlerItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int par1) {
        return handlerItemStacks[par1];
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (handlerItemStacks[par1] != null) {
            ItemStack itemstack;

            if (handlerItemStacks[par1].stackSize <= par2) {
               itemstack = handlerItemStacks[par1];
                handlerItemStacks[par1] = null;
                return itemstack;
            }

            else {
                itemstack = handlerItemStacks[par1].splitStack(par2);

                if (handlerItemStacks[par1].stackSize == 0) {
                    handlerItemStacks[par1] = null;
                }

                return itemstack;
            }
        }
        else {
            return null;
        }


    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (handlerItemStacks[par1] != null) {
            ItemStack itemstack = handlerItemStacks[par1];
            handlerItemStacks[par1] = null;
            return itemstack;
        }
        else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int par1, ItemStack itemStack) {
        handlerItemStacks[par1] = itemStack;

        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()){
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return hasCustomInventoryName() ? field_1 : "container.handler";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return field_1 != null && field_1.length() > 0;
    }

    public void func_3_a(String str) {
        field_1 = str;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        NBTTagList nbtTagList = tagCompound.getTagList("Items", 10);
        handlerItemStacks = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbtTagList.tagCount(); ++i) {
            NBTTagCompound tagCompound1 = nbtTagList.getCompoundTagAt(i);
            byte b0 = tagCompound1.getByte("Slot");

            if (b0 >=0 && b0 < handlerItemStacks.length) {
                handlerItemStacks[b0] = ItemStack.loadItemStackFromNBT(tagCompound1);
            }

            handlerWorkTime = tagCompound.getShort("WorkTime");
            handlingTime = tagCompound.getShort("HandleTime");

            if (tagCompound.hasKey("CustomName", 8)) {
                field_1 = tagCompound.getString("CustomName");
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setShort("WorkTime", (short) handlerWorkTime);
        tagCompound.setShort("HandleTime", (short)handlingTime);
        NBTTagList nbtTagList = new NBTTagList();

        for (int i = 0; i < handlerItemStacks.length; ++i) {
            if (handlerItemStacks[i] != null) {
                NBTTagCompound tagCompound1 = new NBTTagCompound();
                tagCompound1.setByte("Slot", (byte)i);
                handlerItemStacks[i].writeToNBT(tagCompound1);
                nbtTagList.appendTag(tagCompound1);
            }
        }

        tagCompound.setTag("Items", nbtTagList);
        if (hasCustomInventoryName()) {
            tagCompound.setString("CustomName", field_1);
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }



    @SideOnly(Side.CLIENT)
    public int getTimeScaled(int time) {
      return handlingTime * time / 200;
    }



    public boolean isWorking() {
        return handlingTime > 0;
    }
//++
    @Override
    public void updateEntity() {
        if (!getWorldObj().isRemote) {
            ItemStack itemStack = getStackInSlot(0);

            if (itemStack != null) {
                for(handlingTime = 0; handlingTime < 20; ++handlingTime);
                if (handlingTime == 20) {
                    handlingTime = 0;
                    decrStackSize(0, 1);
                    if (getStackInSlot(1) != null) {
                        setInventorySlotContents(1, new ItemStack(OBItems.matter, ++getStackInSlot(1).stackSize));
                    }
                    else {
                        setInventorySlotContents(1, new ItemStack(OBItems.matter));
                        markDirty();
                    }
                } else {
                    handlingTime = 0;
                }
            }
        }
    }

    private boolean canHandle() {
        if (handlerItemStacks[0] == null) {
            return false;
        }
        else {
            ItemStack itemStack = MHRecipes.handling().getHandlingResult(handlerItemStacks[0]);
            if (itemStack == null) return  false;
            if (handlerItemStacks[1] == null) return true;
            if (!handlerItemStacks[1].isItemEqual(itemStack))return false;
            int result = handlerItemStacks[1].stackSize + itemStack.stackSize;
            return result <= getInventoryStackLimit() && result <= handlerItemStacks[1].getMaxStackSize();
        }
    }

    public void handleItem() {
        if (canHandle()) {
            ItemStack itemStack = MHRecipes.handling().getHandlingResult(handlerItemStacks[0]);

            if (handlerItemStacks[1] == null) {
                handlerItemStacks[1] = itemStack.copy();
            }
            else if (handlerItemStacks[2].getItem() == itemStack.getItem()) {
                handlerItemStacks[2].stackSize += itemStack.stackSize;
            }

            --handlerItemStacks[0].stackSize;

            if (handlerItemStacks[0].stackSize <= 0) {
                handlerItemStacks[0] = null;
            }
        }
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int par1, ItemStack itemStack) {
        return true;
    }
}

