package democretes.api;

import java.util.ArrayList;

import net.minecraft.block.Block;

public class MagitekHelper {
	
	public static void registerBlockForHorizontalEnergyTransfer(Block block) {
		horizontal.add(block);
	}
	
	public static void registerBlockForVerticalEnergyTransfer(Block block) {
		vertical.add(block);
	}
	
	public static void registerBlockForEnergyTransfer(Block block) {
		vertical.add(block);
		horizontal.add(block);		
	}
	
	public static ArrayList<Block> horizontal = new ArrayList<Block>();
	public static ArrayList<Block> vertical = new ArrayList<Block>();

}
