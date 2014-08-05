package democretes.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import democretes.block.generators.BlockGenerator;
import democretes.block.generators.ItemBlockGenerator;
import democretes.lib.BlockNames;

public class BlocksEM {
	
	public static Block generator;
	
	public static void init() {
		generator = new BlockGenerator();
		
		GameRegistry.registerBlock(generator, ItemBlockGenerator.class, BlockNames.GENERATOR_NAME);
	}
}
