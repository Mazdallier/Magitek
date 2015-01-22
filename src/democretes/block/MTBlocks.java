package democretes.block;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import democretes.block.altar.BlockAltar;
import democretes.block.altar.TileAltar;
import democretes.block.coils.BlockCoil;
import democretes.block.coils.TileMachtCoil;
import democretes.block.dummy.BlockAltarDummy;
import democretes.block.dummy.BlockSubTerraDummy;
import democretes.block.dummy.TileAltarDummy;
import democretes.block.dummy.TileSubTerraDummy;
import democretes.block.generators.BlockGenerator;
import democretes.block.generators.ItemBlockGenerator;
import democretes.block.generators.TileDestructionGenerator;
import democretes.block.generators.TileMiniSpreader;
import democretes.block.generators.TileOrganicGenerator;
import democretes.block.generators.TilePurityGenerator;
import democretes.block.generators.TileRunicGenerator;
import democretes.block.generators.TileSolarGenerator;
import democretes.block.generators.TileSpreader;
import democretes.block.generators.TileSubTerraGenerator;
import democretes.block.generators.TileThermalGenerator;
import democretes.block.generators.disposable.BlockDisposableGenerator;
import democretes.block.generators.disposable.ItemBlockDisposableGenerator;
import democretes.block.generators.disposable.TileDetonationGenerator;
import democretes.block.generators.disposable.TileLightningGenerator;
import democretes.block.machines.BlockMachine;
import democretes.block.machines.ItemBlockMachine;
import democretes.block.machines.TilePurityInverter;
import democretes.block.machines.TileReconstructor;
import democretes.block.machines.TileRuneConstructor;
import democretes.block.simple.BlockSimple;
import democretes.block.simple.ItemBlockSimple;
import democretes.block.totems.BlockEnhancingTotem;
import democretes.block.totems.BlockTotem;
import democretes.block.totems.TileTotem;
import democretes.block.transfer.BlockTransfer;
import democretes.block.transfer.TileItemTransfer;
import democretes.lib.BlockNames;
import democretes.lib.Reference;

public class MTBlocks {
	
	public static Block generator;
	public static Block machine;
	public static Block terraDummy;
	public static Block tesla;
	public static Block altar;
	public static Block altarDummy;
	public static Block disposable;
	public static Block simple;
	public static Block transfer;
	public static Block totem;
	public static Block enhancer;
	
	public static void init() {
		generator = new BlockGenerator();
		machine = new BlockMachine();
		terraDummy = new BlockSubTerraDummy();
		tesla = new BlockCoil();
		altar = new BlockAltar();
		altarDummy = new BlockAltarDummy();
		disposable = new BlockDisposableGenerator();
		simple = new BlockSimple();
		transfer = new BlockTransfer();
		totem = new BlockTotem();
		enhancer = new BlockEnhancingTotem();
		
		GameRegistry.registerBlock(generator, ItemBlockGenerator.class, BlockNames.GENERATOR_NAME);
		GameRegistry.registerBlock(machine, ItemBlockMachine.class, BlockNames.MACHINE_NAME);
		GameRegistry.registerBlock(terraDummy, BlockNames.DUMMY_NAME);
		GameRegistry.registerBlock(tesla, BlockNames.TESLA_NAME);
		GameRegistry.registerBlock(altar, BlockNames.ALTAR_NAME);
		GameRegistry.registerBlock(altarDummy, BlockNames.ALTAR_NAME + "dumbass");
		GameRegistry.registerBlock(disposable, ItemBlockDisposableGenerator.class, BlockNames.GENERATOR_NAME + "Disposable");
		GameRegistry.registerBlock(simple, ItemBlockSimple.class, BlockNames.SIMPLE_NAME);
		GameRegistry.registerBlock(transfer, BlockNames.TRANSFER_NAME);
		GameRegistry.registerBlock(totem, BlockNames.TOTEM_NAME);
		GameRegistry.registerBlock(enhancer, BlockNames.ENHANCER_NAME);
		
		GameRegistry.registerTileEntity(TileSubTerraGenerator.class, Reference.MOD_NAME + "TileSubTerra");
		GameRegistry.registerTileEntity(TileSubTerraDummy.class, Reference.MOD_NAME + "TileSubTerraDummy");		
		GameRegistry.registerTileEntity(TileSolarGenerator.class, Reference.MOD_NAME + "TileSolarGenerator");
		GameRegistry.registerTileEntity(TilePurityGenerator.class, Reference.MOD_NAME + "TilePurityGenerator");
		GameRegistry.registerTileEntity(TilePurityInverter.class, Reference.MOD_NAME + "TilePurityInverter");
		GameRegistry.registerTileEntity(TileMachtCoil.class, Reference.MOD_NAME + "TileEnergyCoil");
		GameRegistry.registerTileEntity(TileAltar.class, Reference.MOD_NAME + "TileAltar");
		GameRegistry.registerTileEntity(TileAltarDummy.class, Reference.MOD_NAME + "TileAltarDummy");
		GameRegistry.registerTileEntity(TileSpreader.class, Reference.MOD_NAME + "TileSpreader");
		GameRegistry.registerTileEntity(TileRunicGenerator.class, Reference.MOD_NAME + "TileRunicGenerator");
		GameRegistry.registerTileEntity(TileRuneConstructor.class, Reference.MOD_NAME + "TileRuneConstrctor");
		GameRegistry.registerTileEntity(TileDetonationGenerator.class, Reference.MOD_NAME + "TileDetonationGenerator");
		GameRegistry.registerTileEntity(TileThermalGenerator.class, Reference.MOD_NAME + "TileThermalGenerator");
		GameRegistry.registerTileEntity(TileDestructionGenerator.class, Reference.MOD_NAME + "TileDestructionGenerator");
		GameRegistry.registerTileEntity(TileItemTransfer.class, Reference.MOD_NAME + "TileItemTransfer");
		GameRegistry.registerTileEntity(TileReconstructor.class, Reference.MOD_NAME + "TileReconstructor");
		GameRegistry.registerTileEntity(TileMiniSpreader.class, Reference.MOD_NAME + "TileMiniSpreader");
		GameRegistry.registerTileEntity(TileLightningGenerator.class,  Reference.MOD_NAME + "TileLightningGenerator");
		GameRegistry.registerTileEntity(TileTotem.class,  Reference.MOD_NAME + "TileTotem");
		GameRegistry.registerTileEntity(TileOrganicGenerator.class,  Reference.MOD_NAME + "TileOrganicGenerator");
	}
}
