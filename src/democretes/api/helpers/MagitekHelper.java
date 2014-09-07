package democretes.api.helpers;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MagitekHelper {
	
	public static void registerBlockForHorizontalEnergyTransfer(Block block) {
		if(block != null) {
			horizontal.add(block);	
		}
	}
	
	public static void registerBlockForVerticalEnergyTransfer(Block block) {
		if(block != null) {
			vertical.add(block);	
		}
	}
	
	public static void registerBlockForEnergyTransfer(Block block) {
		if(block != null) {
			vertical.add(block);
			horizontal.add(block);		
		}
	}
	
	public static ArrayList<Block> horizontal = new ArrayList<Block>();
	public static ArrayList<Block> vertical = new ArrayList<Block>();

}
