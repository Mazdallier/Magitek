package democretes.block.generators;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import democretes.block.BlockMTBase;
import democretes.lib.Reference;
import democretes.lib.RenderIds;

public class BlockGenerator extends BlockMTBase {
		
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < 2; i++) {
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
	
	@Override
	public int getRenderBlockPass() {
		return 1;
	}

	
}
