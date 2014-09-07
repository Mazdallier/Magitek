package democretes.block.transfer;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.block.BlockMTBase;
import democretes.lib.Reference;

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
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileItemTransfer();
	}

}
