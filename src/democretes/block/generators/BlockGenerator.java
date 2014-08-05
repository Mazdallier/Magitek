package democretes.block.generators;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import democretes.block.BlockEMBase;

public class BlockGenerator extends BlockEMBase {

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
	
}
