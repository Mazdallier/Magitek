package democretes.block.coils;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import democretes.block.BlockMTBase;
import democretes.lib.Reference;
import democretes.lib.RenderIds;

public class BlockCoil extends BlockMTBase {
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileMachtCoil();
	}

	@Override
	public String getUnlocalizedName() {
		return "tile." + Reference.MOD_PREFIX + ".coil";
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
		return RenderIds.idCOIL;
	}
}
