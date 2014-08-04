package democretes.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import democretes.Electromancy;
import democretes.api.block.BlockInfo;
import democretes.api.block.IBlockDebug;
import democretes.api.macht.IMachtStorage;

public class BlockTRBase extends BlockContainer implements IBlockDebug {

	public BlockTRBase() {
		super(Material.iron);
		setCreativeTab(Electromancy.tabEM);
		setHardness(2F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	@Override
	public BlockInfo getInfo(EntityPlayer player, int x, int y, int z) {
		TileEntity tile = player.worldObj.getTileEntity(x, y, z);
		if(tile instanceof IMachtStorage) {
			return new BlockInfo(player, x, y, z, ((IMachtStorage)tile).getMachtStored());
		}
		return new BlockInfo(player, x, y, z);
	}

	

}
