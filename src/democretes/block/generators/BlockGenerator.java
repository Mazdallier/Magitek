package democretes.block.generators;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import democretes.block.BlockMTBase;
import democretes.block.BlocksMT;
import democretes.block.dummy.BlockSubTerraDummy;
import democretes.lib.RenderIds;
import democretes.render.fx.SmokeFX;

public class BlockGenerator extends BlockMTBase {
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if(!world.isRemote && world.getBlock(x, y+1, z) != BlocksMT.dummy && world.getBlockMetadata(x, y, z) == 1) {
			this.dropBlockAsItem(world, x, y, z, new ItemStack(this, 1, 1));
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
		if(access.getBlockMetadata(x, y, z) == 1) {
			setBlockBounds(0F, 0F, 0F, 1F, 2F, 1F);
		}else{
			setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		}
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		if(world.getBlockMetadata(x, y, z) == 1 && !world.isAirBlock(x, y + 1, z)) {
			return false;
		}		
		return super.canPlaceBlockAt(world, x, y, z);
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		if(stack.getItemDamage() == 1) {
			world.setBlock(x, y+1, z, BlocksMT.dummy);
			((BlockSubTerraDummy)world.getBlock(x, y+1, z)).block = this;
			world.getBlock(x, y+1, z).onPostBlockPlaced(world, x, y, z, stack.getItemDamage());
		}
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < 3; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
		
	@Override
	public IIcon getIcon(int side, int meta) {
		return Blocks.stone.getIcon(0, 0);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch(meta) {
		case 0:
			return new TileSolarGenerator();
		case 1:
			return new TileSubTerraGenerator();
		case 2:
			return new TilePurityGenerator();
		}
		return null;
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
