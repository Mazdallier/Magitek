package democretes.block;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import democretes.block.altar.BlockPurityAltar;
import democretes.block.altar.TilePurityAltar;
import democretes.block.circuits.BlockCircuit;
import democretes.block.circuits.ItemBlockCircuit;
import democretes.block.circuits.TileCircuitWire;
import democretes.block.circuits.TileMasterCircuit;
import democretes.block.circuits.TileRedstoneCircuit;
import democretes.block.coils.BlockCoil;
import democretes.block.dummy.BlockSubTerraDummy;
import democretes.block.dummy.TileSubTerraDummy;
import democretes.block.generators.BlockGenerator;
import democretes.block.generators.ItemBlockGenerator;
import democretes.block.generators.TileBounceGenerator;
import democretes.block.generators.TileSolarGenerator;
import democretes.block.generators.TileSubTerraGenerator;
import democretes.block.generators.TileThermalGenerator;
import democretes.block.generators.disposable.BlockDisposableGenerator;
import democretes.block.generators.disposable.ItemBlockDisposableGenerator;
import democretes.block.generators.disposable.TileDetonationGenerator;
import democretes.block.machines.BlockMachine;
import democretes.block.machines.ItemBlockMachine;
import democretes.block.machines.TileChipCrafter;
import democretes.block.machines.TileMachtFurnace;
import democretes.block.machines.TileReconstructor;
import democretes.block.simple.BlockSimple;
import democretes.block.simple.ItemBlockSimple;
import democretes.block.totems.BlockEnhancingTotem;
import democretes.block.totems.BlockVisionTotem;
import democretes.block.totems.TileVisionTotem;
import democretes.block.transfer.BlockTransfer;
import democretes.block.transfer.TileItemTransfer;
import democretes.lib.BlockNames;
import democretes.lib.Reference;

public class MTBlocks {
	
	public static Block generator;
	public static Block machine;
	public static Block terraDummy;
	public static Block tesla;
	public static Block disposable;
	public static Block simple;
	public static Block transfer;
	public static Block totemVision;
	public static Block totemEnhancer;
	public static Block purityAltar;
	public static Block circuit;
	
	public static void init() {
		generator = new BlockGenerator();
		machine = new BlockMachine();
		terraDummy = new BlockSubTerraDummy();
		tesla = new BlockCoil();
		disposable = new BlockDisposableGenerator();
		simple = new BlockSimple();
		transfer = new BlockTransfer();
		totemVision = new BlockVisionTotem();
		totemEnhancer = new BlockEnhancingTotem();
		purityAltar = new BlockPurityAltar();
		circuit = new BlockCircuit();
		
		GameRegistry.registerBlock(generator, ItemBlockGenerator.class, BlockNames.GENERATOR_NAME);
		GameRegistry.registerBlock(machine, ItemBlockMachine.class, BlockNames.MACHINE_NAME);
		GameRegistry.registerBlock(terraDummy, BlockNames.DUMMY_NAME);
		//GameRegistry.registerBlock(tesla, BlockNames.TESLA_NAME);
		GameRegistry.registerBlock(disposable, ItemBlockDisposableGenerator.class, BlockNames.GENERATOR_NAME + "Disposable");
		GameRegistry.registerBlock(simple, ItemBlockSimple.class, BlockNames.SIMPLE_NAME);
		GameRegistry.registerBlock(transfer, BlockNames.TRANSFER_NAME);
		GameRegistry.registerBlock(totemVision, BlockNames.VISION_NAME);
		GameRegistry.registerBlock(totemEnhancer, BlockNames.ENHANCER_NAME);
		GameRegistry.registerBlock(purityAltar, BlockNames.ALTAR_PURITY_NAME);
		GameRegistry.registerBlock(circuit, ItemBlockCircuit.class, BlockNames.CIRCUIT_NAME);
		
		//GameRegistry.registerTileEntity(TileMachtCoil.class, Reference.MOD_NAME + "TileEnergyCoil");
		GameRegistry.registerTileEntity(TileItemTransfer.class, Reference.MOD_NAME + "TileItemTransfer");
		GameRegistry.registerTileEntity(TileVisionTotem.class,  Reference.MOD_NAME + "TileTotem");
		GameRegistry.registerTileEntity(TilePurityAltar.class, Reference.MOD_NAME + "TilePurityAltar");
		
		GameRegistry.registerTileEntity(TileChipCrafter.class, Reference.MOD_NAME + "TileRuneConstrctor");
		GameRegistry.registerTileEntity(TileReconstructor.class, Reference.MOD_NAME + "TileReconstructor");
		GameRegistry.registerTileEntity(TileMachtFurnace.class, Reference.MOD_NAME + "TileMachtFurnace");
		
		GameRegistry.registerTileEntity(TileThermalGenerator.class, Reference.MOD_NAME + "TileThermalGenerator");
		GameRegistry.registerTileEntity(TileBounceGenerator.class, Reference.MOD_NAME + "TileBounceGenerator");
		GameRegistry.registerTileEntity(TileDetonationGenerator.class, Reference.MOD_NAME + "TileDetonationGenerator");
		GameRegistry.registerTileEntity(TileSubTerraGenerator.class, Reference.MOD_NAME + "TileSubTerra");
		GameRegistry.registerTileEntity(TileSubTerraDummy.class, Reference.MOD_NAME + "TileSubTerraDummy");		
		GameRegistry.registerTileEntity(TileSolarGenerator.class, Reference.MOD_NAME + "TileSolarGenerator");
		
		GameRegistry.registerTileEntity(TileMasterCircuit.class, Reference.MOD_NAME + "TileMasterCircuit");
		GameRegistry.registerTileEntity(TileCircuitWire.class, Reference.MOD_NAME + "TileCircuitWire");
		GameRegistry.registerTileEntity(TileRedstoneCircuit.class, Reference.MOD_NAME + "TileRedstoneCircuit");
	}
}
