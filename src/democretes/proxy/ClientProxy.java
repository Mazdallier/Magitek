package democretes.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import democretes.block.altar.TileAltar;
import democretes.block.coils.TileMachtCoil;
import democretes.block.generators.TilePurityGenerator;
import democretes.block.generators.TileSolarGenerator;
import democretes.block.generators.TileSubTerraGenerator;
import democretes.block.machines.TileRuneConstructor;
import democretes.lib.RenderIds;
import democretes.render.blocks.RenderBlockAltar;
import democretes.render.blocks.RenderBlockCoil;
import democretes.render.blocks.RenderBlockGenerator;
import democretes.render.tile.RenderAltar;
import democretes.render.tile.RenderEnergyCoil;
import democretes.render.tile.RenderPurityGenerator;
import democretes.render.tile.RenderRuneConstructor;
import democretes.render.tile.RenderSolarGenerator;
import democretes.render.tile.RenderSubTerraGenerator;
import democretes.utils.handlers.KeyHandler;

public class ClientProxy extends CommonProxy{

	@Override
	public void initSounds() {

    }

	@Override
    public void initRenderers() {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileSubTerraGenerator.class, new RenderSubTerraGenerator());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileSolarGenerator.class, new RenderSolarGenerator());
    	ClientRegistry.bindTileEntitySpecialRenderer(TilePurityGenerator.class, new RenderPurityGenerator());
    	RenderIds.idGENERATOR = RenderingRegistry.getNextAvailableRenderId();
    	
    	ClientRegistry.bindTileEntitySpecialRenderer(TileMachtCoil.class, new RenderEnergyCoil());
    	RenderIds.idNODE = RenderingRegistry.getNextAvailableRenderId();
    	
    	ClientRegistry.bindTileEntitySpecialRenderer(TileAltar.class, new RenderAltar());
    	RenderIds.idALTAR = RenderingRegistry.getNextAvailableRenderId();
    	
    	ClientRegistry.bindTileEntitySpecialRenderer(TileRuneConstructor.class, new RenderRuneConstructor());
    	
		RenderingRegistry.registerBlockHandler(new RenderBlockGenerator());
		RenderingRegistry.registerBlockHandler(new RenderBlockCoil());
		RenderingRegistry.registerBlockHandler(new RenderBlockAltar());

    }
    
	@Override
	public void registerKeyBindings() {
		keyHandler = new KeyHandler();
		FMLCommonHandler.instance().bus().register(keyHandler);
	}
}
