package democretes.utils.handlers;

import democretes.api.MagitekApi;
import democretes.block.MTBlocks;
import net.minecraft.init.Blocks;

public class BlockTransferHandler {
	
	public static void registerBlocks() {

		MagitekApi.registerBlockForHorizontalEnergyTransfer(Blocks.sandstone);
		MagitekApi.registerBlockForHorizontalEnergyTransfer(Blocks.stonebrick);
		MagitekApi.registerBlockForHorizontalEnergyTransfer(Blocks.nether_brick);
		MagitekApi.registerBlockForHorizontalEnergyTransfer(Blocks.brick_block);
		MagitekApi.registerBlockForHorizontalEnergyTransfer(Blocks.obsidian);
		MagitekApi.registerBlockForHorizontalEnergyTransfer(Blocks.iron_block);
		MagitekApi.registerBlockForHorizontalEnergyTransfer(Blocks.gold_block);
		
		MagitekApi.registerBlockForVerticalEnergyTransfer(Blocks.coal_block);
		MagitekApi.registerBlockForVerticalEnergyTransfer(Blocks.quartz_block);
		MagitekApi.registerBlockForVerticalEnergyTransfer(Blocks.lapis_block);

		MagitekApi.registerBlockForEnergyTransfer(Blocks.diamond_block);
		MagitekApi.registerBlockForEnergyTransfer(Blocks.emerald_block);
		MagitekApi.registerBlockForEnergyTransfer(MTBlocks.simple);
	}

}
