package democretes.block.dummy;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import democretes.block.BlockMTBase;
import democretes.block.generators.BlockGenerator;
import democretes.lib.Reference;
import democretes.lib.RenderIds;

public class BlockDummy extends BlockMTBase {
		
	public Block block;
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {}

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
	public void onPostBlockPlaced(World world, int x, int y, int z, int meta) {
		if(!world.isRemote) {
			TileEntity tile = world.getTileEntity(x, y-1, z);
			if(world.getTileEntity(x, y, z) instanceof TileDummy) {
				((TileDummy)world.getTileEntity(x, y, z)).setTile(tile);
			}
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileDummy();
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
