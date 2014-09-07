package democretes.utils.handlers;

import democretes.api.MagitekHelper;
import democretes.block.MTBlocks;
import net.minecraft.init.Blocks;

public class BlockTransferHandler {
	
	public static void registerBlocks() {

		MagitekHelper.registerBlockForHorizontalEnergyTransfer(Blocks.sandstone);
		MagitekHelper.registerBlockForHorizontalEnergyTransfer(Blocks.stonebrick);
		MagitekHelper.registerBlockForHorizontalEnergyTransfer(Blocks.nether_brick);
		MagitekHelper.registerBlockForHorizontalEnergyTransfer(Blocks.brick_block);
		MagitekHelper.registerBlockForHorizontalEnergyTransfer(Blocks.obsidian);
		MagitekHelper.registerBlockForHorizontalEnergyTransfer(Blocks.iron_block);
		MagitekHelper.registerBlockForHorizontalEnergyTransfer(Blocks.gold_block);
		
		MagitekHelper.registerBlockForVerticalEnergyTransfer(Blocks.coal_block);
		MagitekHelper.registerBlockForVerticalEnergyTransfer(Blocks.quartz_block);
		MagitekHelper.registerBlockForVerticalEnergyTransfer(Blocks.lapis_block);

		MagitekHelper.registerBlockForEnergyTransfer(Blocks.diamond_block);
		MagitekHelper.registerBlockForEnergyTransfer(Blocks.emerald_block);
		MagitekHelper.registerBlockForEnergyTransfer(MTBlocks.simple);
	}

}
