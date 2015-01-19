package democretes.api;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class MagitekApi {
	
	public static ArmorMaterial pureArmorMaterial = EnumHelper.addArmorMaterial("PURE", 15, new int[] { 2, 6, 5, 2 }, 18);
	public static ToolMaterial pureToolMaterial = EnumHelper.addToolMaterial("PURE", 3, 900, 7.0F, 2.0F, 20);
	
	public static ArmorMaterial darkArmorMaterial = EnumHelper.addArmorMaterial("DARK", 18, new int[] { 3, 7, 5, 3 }, 12);
	public static ToolMaterial darkToolMaterial = EnumHelper.addToolMaterial("DARK", 3, 700, 6.0F, 3.0F, 10);
	
	/*
	 * Armor
	 *  CLOTH(5, new int[]{1, 3, 2, 1}, 15),
        CHAIN(15, new int[]{2, 5, 4, 1}, 12),
        IRON(15, new int[]{2, 6, 5, 2}, 9),
        GOLD(7, new int[]{2, 5, 3, 1}, 25),
        DIAMOND(33, new int[]{3, 8, 6, 3}, 10);
        
        Tools
        WOOD(0, 59, 2.0F, 0.0F, 15),
        STONE(1, 131, 4.0F, 1.0F, 5),
        IRON(2, 250, 6.0F, 2.0F, 14),
        DIAMOND(3, 1561, 8.0F, 3.0F, 10),
        GOLD(0, 32, 12.0F, 0.0F, 22);
	 */
	
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
