package ru.vovamaster99.rcservmod.ob.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import net.minecraftforge.common.util.ForgeDirection;
import ru.vovamaster99.rcservmod.ModBase;
import ru.vovamaster99.rcservmod.ob.tileenity.TileEntityMatterHandler;

import java.util.ArrayList;

public class MatterHandler extends BlockContainer {

    @SideOnly(Side.CLIENT)
    private IIcon blockIcon_top;
    private IIcon blockIcon_top_work;
    private IIcon blockIcon_front;
    private IIcon blockIcon_side;
    private IIcon blockIcon_back;
    private IIcon blockIcon_bottom;

    private ArrayList<ItemStack> ist = null;

    MatterHandler() {
        super(Material.iron);
        setBlockName(ModBase.MODID + ":" + "MatterHandler");
        setHardness(3.0F);
        setCreativeTab(ModBase.buildingBlocks);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconReg) {
        TileEntityMatterHandler matterHandler = new TileEntityMatterHandler();
        blockIcon_top = iconReg.registerIcon(matterHandler.isWorking() ? ModBase.MODID + ":matterHandler_top_work" : ModBase.MODID + ":matterHandler_top");
        blockIcon_front = iconReg.registerIcon(ModBase.MODID + ":matterHandler_front");
        blockIcon_side = iconReg.registerIcon(ModBase.MODID + ":matterHandler_side");
        blockIcon_back = iconReg.registerIcon(ModBase.MODID + ":matterHandler_back");
        blockIcon_bottom = iconReg.registerIcon(ModBase.MODID + ":matterHandler_bottom");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        ForgeDirection dir = ForgeDirection.getOrientation(side);
        ForgeDirection block_dir;
        if(meta !=0)
            block_dir = ForgeDirection.getOrientation(meta);
        else
            block_dir = ForgeDirection.WEST;

        if (block_dir == dir) return blockIcon_front;
        if (block_dir.getOpposite() == dir) return blockIcon_back;
        if (dir == ForgeDirection.UP) return blockIcon_top;
        if (dir == ForgeDirection.DOWN) return blockIcon_bottom;
        return blockIcon_side;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
        super.onBlockPlacedBy(world, x, y, z, entity, is);
        int dir = (MathHelper.floor_double((double)(entity.rotationYaw * 4.0f / 360.0F) + 0.5D) & 3);
        int[] r = {2, 5, 3, 4};
        world.setBlockMetadataWithNotify(x, y, z, r[dir], 3);
        world.getTileEntity(x, y, z).markDirty();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (!world.isRemote)
            player.openGui(ModBase.MODID, 1 , world, x, y, z);
        return true;
    }

    public static void updateHandlerBlockState(boolean flag, World world, int par3, int par4, int par5) {
        int l = world.getBlockMetadata(par3, par4, par5);
        TileEntity tileEntity = world.getTileEntity(par3, par4, par5);
        flag = true;

        if (flag) {
            world.setBlock(par3, par4, par5, OBBlocks.matterHandler_work);
        }
        else {
            world.setBlock(par3, par4, par5, OBBlocks.matterHandler);
        }

        flag = false;
        world.setBlockMetadataWithNotify(par3, par4, par5, 1, 2);

        if (tileEntity != null) {
            tileEntity.validate();;
            world.setTileEntity(par3, par4, par5, tileEntity);
        }

    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityMatterHandler();
    }
}
