package democretes.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import democretes.Magitek;
import democretes.block.generators.BlockGenerator;
import democretes.lib.RenderIds;
import democretes.utils.CreativeTabsMT;

public class BlockDummy extends Block {
	
	public BlockDummy() {
		super(Material.iron);
	}	
	
	public Block block;

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
		if(access.getBlockMetadata(x, y, z) == 0) {
			setBlockBounds(0F, -1F, 0F, 1F, 1F, 1F);
		}
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if(world.getBlock(x, y-1, z) instanceof BlockGenerator == false) {
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(block == null) {
			return super.getIcon(side, meta);
		}
		return block.getIcon(side, meta);
	}
	
	@Override
	public int quantityDropped(Random r) {
		return 0;
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
		return RenderIds.idGENERATOR;
	}

}
