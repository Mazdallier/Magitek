package democretes.block.circuits;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import democretes.block.BlockMTBase;
import democretes.lib.Reference;

public class BlockCircuit extends BlockMTBase {
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < 3; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	IIcon[] icons = new IIcon[4];
	@Override
	public void registerBlockIcons(IIconRegister ir) {
		String pre = Reference.TEXTURE_PREFIX + "circuit/circuit_";
		icons[0] = ir.registerIcon(pre + "master");
		icons[1] = ir.registerIcon(pre + "wire");
		icons[2] = ir.registerIcon(pre + "redstone_inactive");
		icons[3] = ir.registerIcon(pre + "redstone_active");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return icons[meta];
	}
	
	@Override
	public IIcon getIcon(IBlockAccess world, int x,	int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == 2 && ((TileRedstoneCircuit)world.getTileEntity(x, y, z)).isPowered()) {
			return icons[3];
		}
		return icons[meta];
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch(meta) {
		case 0: return new TileMasterCircuit();
		case 1: return new TileCircuitWire();
		case 2: return new TileRedstoneCircuit();
		}
		return null;
	}
	
	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}
	
	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
		if(world.getBlockMetadata(x, y, z) == 0) {
			TileMasterCircuit tile = (TileMasterCircuit)world.getTileEntity(x, y, z);
			int output = tile.getMachtStored()/tile.getCapacity()*15;
			if(tile.getMachtStored()>0) {
				output = Math.max(output, 1);
			}
			return output;
		}
		return 0;
	}
	
	@Override
	public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
		return world.getBlockMetadata(x, y, z) == 2;
	}
	
	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
		TileCircuitBase tile = (TileCircuitBase)world.getTileEntity(x,y,z);
		tile.closeCircuit();
	}

}
