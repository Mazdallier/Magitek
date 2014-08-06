package democretes.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import democretes.block.generators.TileSubTerraGenerator;
import democretes.lib.RenderIds;
import democretes.render.blocks.RenderBlockGenerator;
import democretes.render.tile.RenderSubTerraGenerator;

public class ClientProxy extends CommonProxy{

	public void initSounds() {

    }

    public void initRenderers() {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileSubTerraGenerator.class, new RenderSubTerraGenerator());
		RenderIds.idGENERATOR = RenderingRegistry.getNextAvailableRenderId();
    	
		RenderingRegistry.registerBlockHandler(new RenderBlockGenerator());

    }
    
}
