package democretes.block.altar;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.MapColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import democretes.block.BlockMTBase;
import democretes.lib.Reference;

public class BlockPurityAltar extends BlockMTBase {
	
	public BlockPurityAltar() {
        this.setLightLevel(1.0F);
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		if(Minecraft.getMinecraft().thePlayer.getDisplayName().equals("Democretes")) {
			list.add(new ItemStack(item, 1, 0));
		}
	}
	
	@Override
	public String getUnlocalizedName() {
		return "tile." + Reference.MOD_PREFIX + ".purityAltar";
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TilePurityAltar();
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		if(entity instanceof EntityPlayer) {
			if(((EntityPlayer)entity).capabilities.isCreativeMode) {
				((TilePurityAltar)world.getTileEntity(x, y, z)).creative = true;
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        return side != 0 ? false : super.shouldSideBeRendered(world, x, y, z, side);
    }

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        float f = 0.0625F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
    }
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB bb, List list, Entity entity) {}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void registerBlockIcons(IIconRegister ir) {
        this.blockIcon = ir.registerIcon("portal");
    }

	@Override
    public MapColor getMapColor(int c) {
        return MapColor.redColor;
    }
	
	@Override
	public int quantityDropped(Random r) {
        return 0;
    }
	
	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
}
