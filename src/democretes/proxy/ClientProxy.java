package democretes.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import democretes.block.generators.TilePurityGenerator;
import democretes.block.generators.TileSolarGenerator;
import democretes.block.generators.TileSubTerraGenerator;
import democretes.lib.RenderIds;
import democretes.render.blocks.RenderBlockGenerator;
import democretes.render.tile.RenderPurityGenerator;
import democretes.render.tile.RenderSolarGenerator;
import democretes.render.tile.RenderSubTerraGenerator;

public class ClientProxy extends CommonProxy{

	public void initSounds() {

    }

    public void initRenderers() {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileSubTerraGenerator.class, new RenderSubTerraGenerator());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileSolarGenerator.class, new RenderSolarGenerator());
    	ClientRegistry.bindTileEntitySpecialRenderer(TilePurityGenerator.class, new RenderPurityGenerator());
    	RenderIds.idGENERATOR = RenderingRegistry.getNextAvailableRenderId();
    	
		RenderingRegistry.registerBlockHandler(new RenderBlockGenerator());

    }
    
}
