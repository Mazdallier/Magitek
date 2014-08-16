package democretes.block.machines;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import democretes.block.BlockMTBase;

public class BlockMachine extends BlockMTBase {
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch(meta) {
		case 0:
			return new TilePurityInverter();
		case 1:
			return new TileRuneConstructor();
		}
		return null;
	}
	

}
