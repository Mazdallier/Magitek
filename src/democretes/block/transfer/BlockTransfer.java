package democretes.block.transfer;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.block.BlockMTBase;
import democretes.block.MTBlocks;
import democretes.block.simple.BlockSimple;
import democretes.lib.Reference;
import democretes.lib.RenderIds;

public class BlockTransfer extends BlockMTBase {
	
	public BlockTransfer() {
		setBlockName(Reference.MOD_PREFIX + ".transfer");
	}
	
	private int facing;
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack){
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TileItemTransfer) {
			((TileItemTransfer)tile).facing = this.facing;
		}
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta){
		this.facing = ForgeDirection.OPPOSITES[side];
		return side + meta;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TileItemTransfer) {
			switch(((TileItemTransfer)tile).facing) {
			case 0:
				setBlockBounds(0.1875F, 0.0F, 0.1875F, 0.8125F, 0.4F, 0.8125F);break;
			case 1:
				setBlockBounds(0.1875F, 1.0F, 0.1875F, 0.8125F, 0.6F, 0.8125F);break;
			case 2:
				setBlockBounds(0.1875F, 0.1875F, 0.0F, 0.8125F, 0.8125F, 0.4F);break;
			case 3:
				setBlockBounds(0.1875F, 0.1875F, 1.0F, 0.8125F, 0.8125F, 0.6F);break;
			case 4:
				setBlockBounds(0.0F, 0.1875F, 0.1875F, 0.4F, 0.8125F, 0.8125F);break;
			case 5:
				setBlockBounds(1.0F, 0.1875F, 0.1875F, 0.6F, 0.8125F, 0.8125F);
			}
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileItemTransfer();
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return RenderIds.idTRANSFER;
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return BlockSimple.icons[1];
	}

}
