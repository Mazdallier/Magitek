package democretes.utils.world;

import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import democretes.utils.handlers.ConfigHandler;

public class MTWorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(ConfigHandler.altars) {
			generateAltar(random, chunkX, chunkZ, world);
		}
	}
	
	private void generateAltar(Random random, int chunkX, int chunkZ, World world) {
		int randPosX = chunkX * 16 + random.nextInt(16);
	    int randPosZ = chunkZ * 16 + random.nextInt(16);
	    int randPosY = world.getHeightValue(randPosX, randPosZ) - 9;
	    if (randPosY < world.getActualHeight()) {
	    	Chunk chunk = world.getChunkFromBlockCoords(MathHelper.floor_double(randPosX), MathHelper.floor_double(randPosZ));
	    	WorldGenAltar altar = new WorldGenAltar();
	        if (chunk.getRandomWithSeed(957234911L).nextInt(150) == 0) {
	        	altar.generate(world, random, randPosX, randPosY, randPosZ);
	        }
	    }
	}

}
