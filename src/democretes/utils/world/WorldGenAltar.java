package democretes.utils.world;

import java.util.Random;

import democretes.Magitek;
import democretes.block.MTBlocks;
import democretes.block.altar.TilePurityAltar;
import democretes.utils.helper.DirectionHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenAltar extends WorldGenerator {

	@Override
	public boolean generate(World world, Random r, int x, int y, int z) {
		y = setY(world, x, z);
		if(y == 0) {
			return false;
		}
		createShell(world, r, x, y, z);
		
		Block stairs;
		Block base;
		Block pillar;
		if(r.nextBoolean()) {
			stairs = Blocks.nether_brick_stairs;
			base = Blocks.nether_brick;
			pillar = Blocks.nether_brick_fence;
		}else{
			stairs = Blocks.quartz_stairs;
			base = Blocks.quartz_block;
			pillar = base;
		}
		
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				for(int k = 0; k < 4; k++) {
					world.setBlockToAir(x+i, y+k, z+j);					
				}
			}
		}
		
		world.setBlock(x, y, z, MTBlocks.purityAltar, 1, 2);
		((TilePurityAltar)world.getTileEntity(x, y, z)).creative = true;
		world.markBlockForUpdate(x, y, z);
		
		world.setBlock(x+1, y, z, stairs, 0, 1);
		world.setBlock(x-1, y, z, stairs, 1, 1);
		world.setBlock(x, y, z+1, stairs, 2, 1);
		world.setBlock(x, y, z-1, stairs, 3, 1);
		
		world.setBlock(x+1, y, z+1, base);
		world.setBlock(x+1, y, z-1, base);
		world.setBlock(x-1, y, z+1, base);
		world.setBlock(x-1, y, z-1, base);
		
		world.setBlock(x+1, y+1, z+1, pillar, 2, 1);
		world.setBlock(x+1, y+1, z-1, pillar, 2, 1);
		world.setBlock(x-1, y+1, z+1, pillar, 2, 1);
		world.setBlock(x-1, y+1, z-1, pillar, 2, 1);
		world.setBlock(x+1, y+2, z+1, pillar, 2, 4);
		world.setBlock(x+1, y+2, z-1, pillar, 2, 4);
		world.setBlock(x-1, y+2, z+1, pillar, 2, 4);
		world.setBlock(x-1, y+2, z-1, pillar, 2, 4);
		
		world.setBlock(x, y+3, z, base);

		world.setBlock(x-1, y+3, z-1, stairs, 0, 1);
		world.setBlock(x-1, y+3, z, stairs, 0, 1);
		world.setBlock(x-1, y+3, z+1, stairs, 0, 1);

		world.setBlock(x+1, y+3, z-1, stairs, 1, 1);
		world.setBlock(x+1, y+3, z, stairs, 1, 1);
		world.setBlock(x+1, y+3, z+1, stairs, 1, 1);
		
		world.setBlock(x, y+3, z+1, stairs, 3, 1);
		world.setBlock(x, y+3, z-1, stairs, 2, 1);
		return true;
	}
	
	private void createShell(World world, Random r, int x, int y, int z) {
		Block block = world.getBiomeGenForCoords(x, z).biomeID == 2 ? Blocks.sandstone : Blocks.grass;
		world.setBlock(x, y+5, z, block);
		int flipX = 1;
		int flipZ = 1;
		for(ForgeDirection dir : DirectionHelper.horizontal) {
			flipZ = dir.offsetZ;
			if(dir == ForgeDirection.WEST) {
				flipZ = -1;
			}
			if(dir == ForgeDirection.EAST) {
				flipZ = 1;
			}
			if(dir == ForgeDirection.WEST || dir ==  ForgeDirection.EAST) {
				flipX =-1;
			}
			world.setBlock(x, y+5, z + flipZ, block);
			world.setBlock(x, y+5, z + flipZ*2, block);
			world.setBlock(x, y+4, z + flipZ*3, block);
			world.setBlock(x, y+3, z + flipZ*4, block);
			world.setBlock(x, y+3, z + flipZ*5, block);

			world.setBlock(x + 1*flipX, y+5, z, block);
			world.setBlock(x + 1*flipX, y+5, z + flipZ, block);
			world.setBlock(x + 1*flipX, y+5, z + flipZ*2, block);
			world.setBlock(x + 1*flipX, y+4, z + flipZ*3, block);
			world.setBlock(x + 1*flipX, y+3, z + flipZ*4, block);
			world.setBlock(x + 1*flipX, y+2, z + flipZ*5, block);
			world.setBlock(x + 1*flipX, y+1, z + flipZ*5, block);

			world.setBlock(x + 2*flipX, y+5, z, block);
			world.setBlock(x + 2*flipX, y+5, z + flipZ, block);
			world.setBlock(x + 2*flipX, y+5, z + flipZ*2, block);
			world.setBlock(x + 2*flipX, y+4, z + flipZ*3, block);
			world.setBlock(x + 2*flipX, y+3, z + flipZ*4, block);
			world.setBlock(x + 2*flipX, y+2, z + flipZ*5, block);
			world.setBlock(x + 2*flipX, y+1, z + flipZ*5, block);

			world.setBlock(x + 3*flipX, y+4, z, block);
			world.setBlock(x + 3*flipX, y+4, z + flipZ, block);
			world.setBlock(x + 3*flipX, y+4, z + flipZ*2, block);
			world.setBlock(x + 3*flipX, y+3, z + flipZ*3, block);
			world.setBlock(x + 3*flipX, y+2, z + flipZ*4, block);
			world.setBlock(x + 3*flipX, y+1, z + flipZ*4, block);

			world.setBlock(x + 4*flipX, y+3, z, block);
			world.setBlock(x + 4*flipX, y+3, z + flipZ, block);
			world.setBlock(x + 4*flipX, y+3, z + flipZ*2, block);
			world.setBlock(x + 4*flipX, y+2, z + flipZ*3, block);
			world.setBlock(x + 4*flipX, y+1, z + flipZ*3, block);

			world.setBlock(x + 5*flipX, y+3, z, block);
			world.setBlock(x + 5*flipX, y+2, z + flipZ, block);
			world.setBlock(x + 5*flipX, y+1, z + flipZ, block);
			world.setBlock(x + 5*flipX, y+2, z + flipZ*2, block);
			world.setBlock(x + 5*flipX, y+1, z + flipZ*2, block);
			
			//Floor
			world.setBlock(x, y, z + flipZ, block);
			world.setBlock(x, y, z + flipZ*2, block);
			world.setBlock(x, y, z + flipZ*3, block);
			world.setBlock(x, y, z + flipZ*4, block);
			world.setBlock(x, 3, z + flipZ*5, block);

			world.setBlock(x + 1*flipX, y, z, block);
			world.setBlock(x + 1*flipX, y, z + flipZ, block);
			world.setBlock(x + 1*flipX, y, z + flipZ*2, block);
			world.setBlock(x + 1*flipX, y, z + flipZ*3, block);
			world.setBlock(x + 1*flipX, y, z + flipZ*4, block);
			world.setBlock(x + 1*flipX, y, z + flipZ*5, block);

			world.setBlock(x + 2*flipX, y, z, block);
			world.setBlock(x + 2*flipX, y, z + flipZ, block);
			world.setBlock(x + 2*flipX, y, z + flipZ*2, block);
			world.setBlock(x + 2*flipX, y, z + flipZ*3, block);
			world.setBlock(x + 2*flipX, y, z + flipZ*4, block);
			world.setBlock(x + 2*flipX, y, z + flipZ*5, block);

			world.setBlock(x + 3*flipX, y, z, block);
			world.setBlock(x + 3*flipX, y, z + flipZ, block);
			world.setBlock(x + 3*flipX, y, z + flipZ*2, block);
			world.setBlock(x + 3*flipX, y, z + flipZ*3, block);
			world.setBlock(x + 3*flipX, y, z + flipZ*4, block);

			world.setBlock(x + 4*flipX, y, z, block);
			world.setBlock(x + 4*flipX, y, z + flipZ, block);
			world.setBlock(x + 4*flipX, y, z + flipZ*2, block);
			world.setBlock(x + 4*flipX, y, z + flipZ*3, block);

			world.setBlock(x + 5*flipX, y, z, block);
			world.setBlock(x + 5*flipX, y, z + flipZ, block);
			world.setBlock(x + 5*flipX, y, z + flipZ, block);
			world.setBlock(x + 5*flipX, y, z + flipZ*2, block);
		}
		
		
	}

	private int setY(World world, int x, int z) {
		int y = 50;
		Block block;
		do{
			y++;
			block = world.getBlock(x, y, z);
			if(y > world.getActualHeight()) {
				return 0;
			}
			if(block == Blocks.grass || block == Blocks.sand) {
				break;
			}
		}while(true);
		if(world.getBlock(x, y+z, z) == Blocks.water || world.getBlock(x, y+z, z) == Blocks.lava) {
			return 0;
		}
		return y;
	}
}
