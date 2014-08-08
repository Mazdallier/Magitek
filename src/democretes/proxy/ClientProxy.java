package democretes.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import democretes.block.generators.TilePurityGenerator;
import democretes.block.generators.TileSolarGenerator;
import democretes.block.generators.TileSubTerraGenerator;
import democretes.lib.RenderIds;
import democretes.render.blocks.RenderBlockGenerator;
import democretes.render.tile.RenderPurityGenerator;
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
    	
		RenderingRegistry.registerBlockHandler(new RenderBlockGenerator());

    }
    
	@Override
	public void registerKeyBindings() {
		keyHandler = new KeyHandler();
		FMLCommonHandler.instance().bus().register(keyHandler);
	}
}
