package democretes.block;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import democretes.block.generators.BlockGenerator;
import democretes.block.generators.ItemBlockGenerator;
import democretes.block.generators.TilePurityGenerator;
import democretes.block.generators.TileSolarGenerator;
import democretes.block.generators.TileSubTerraGenerator;
import democretes.block.machines.BlockMachine;
import democretes.block.machines.ItemBlockMachine;
import democretes.block.machines.TilePurityInverter;
import democretes.block.nodes.BlockNode;
import democretes.block.nodes.ItemBlockNode;
import democretes.block.nodes.TileEnergyNode;
import democretes.block.nodes.TilePurityNode;
import democretes.lib.BlockNames;
import democretes.lib.Reference;

public class BlocksMT {
	
	public static Block generator;
	public static Block machine;
	public static Block dummy;
	public static Block node;
	
	public static void init() {
		generator = new BlockGenerator();
		machine = new BlockMachine();
		dummy = new BlockDummy();
		node = new BlockNode();
		
		GameRegistry.registerBlock(generator, ItemBlockGenerator.class, BlockNames.GENERATOR_NAME);
		GameRegistry.registerBlock(machine, ItemBlockMachine.class, BlockNames.MACHINE_NAME);
		GameRegistry.registerBlock(dummy, BlockNames.DUMMY_NAME);
		GameRegistry.registerBlock(node, ItemBlockNode.class, BlockNames.NODE_NAME);
		
		GameRegistry.registerTileEntity(TileSubTerraGenerator.class, Reference.MOD_NAME + "TileSubTerra");
		GameRegistry.registerTileEntity(TileSolarGenerator.class, Reference.MOD_NAME + "TileSolarGenerator");
		GameRegistry.registerTileEntity(TilePurityGenerator.class, Reference.MOD_NAME + "TilePurityGenerator");
		GameRegistry.registerTileEntity(TilePurityInverter.class, Reference.MOD_NAME + "TilePurityInverter");
		GameRegistry.registerTileEntity(TileEnergyNode.class, Reference.MOD_NAME + "TileEnergyNode");
		GameRegistry.registerTileEntity(TilePurityNode.class, Reference.MOD_NAME + "TilePurityNode");
	}
}
