package democretes.block.totems;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.block.BlockMTBase;
import democretes.block.transfer.TileItemTransfer;
import democretes.lib.Reference;

public class BlockTotem extends BlockMTBase {
	
	public void registerBlockIcons(IIconRegister ir) {
		facingActive = ir.registerIcon(Reference.TEXTURE_PREFIX + "totem_Active");
		facingInactive = ir.registerIcon(Reference.TEXTURE_PREFIX + "totem_Inactive");
		vertical = ir.registerIcon(Reference.TEXTURE_PREFIX + "totem_vertical");;
		sides = ir.registerIcon(Reference.TEXTURE_PREFIX + "totem_side");;
	};
	
	IIcon facingInactive;
	IIcon facingActive;
	IIcon vertical;
	IIcon sides;
	@Override
	public IIcon getIcon(IBlockAccess world, int x,	int y, int z, int side) {
		int facing = ((TileTotem)world.getTileEntity(x, y, z)).facing;
		if(side == facing) {
			if(world.getBlockMetadata(x, y, z) == 15) {
				return facingActive;
			}
			return facingInactive;
		}
		if(side <= 1) {
			return vertical;
		}
		return sides;
	}

	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(side <= 1) {
			return vertical;
		}
		if(side == 2) {
			return facingInactive;
		}
		return sides;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack){
		int face = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;
		TileEntity tile = world.getTileEntity(x, y, z);
		if ((tile instanceof TileTotem)) {
			TileTotem totem = (TileTotem)tile;
			switch(face) {
			case 0:
				totem.facing = 2;break;
			case 1:
				totem.facing = 5;break;
			case 2:
				totem.facing = 3;break;
			case 3:
				totem.facing = 4;
			}
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileTotem();
	}

	@Override
	public boolean canProvidePower() {
		return true;
	}
	
	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int meta) {		
		return world.getBlockMetadata(x, y, z);
	}

}
