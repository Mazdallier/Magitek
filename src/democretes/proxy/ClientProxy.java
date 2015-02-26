package democretes.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import democretes.block.altar.TilePurityAltar;
import democretes.block.coils.TileMachtCoil;
import democretes.block.generators.TileBounceGenerator;
import democretes.block.generators.TileSolarGenerator;
import democretes.block.generators.TileSubTerraGenerator;
import democretes.block.generators.TileThermalGenerator;
import democretes.block.machines.TileReconstructor;
import democretes.block.transfer.TileItemTransfer;
import democretes.lib.RenderIds;
import democretes.render.blocks.RenderBlockCoil;
import democretes.render.blocks.RenderBlockGenerator;
import democretes.render.blocks.RenderBlockItemTransfer;
import democretes.render.fx.FXOrb;
import democretes.render.tile.RenderItemTransfer;
import democretes.render.tile.RenderMachtCoil;
import democretes.render.tile.RenderPurityAltar;
import democretes.render.tile.RenderReconstructor;
import democretes.render.tile.RenderSolarGenerator;
import democretes.render.tile.RenderSubTerraGenerator;
import democretes.render.tile.RenderThermalGenerator;
import democretes.render.tile.RenderTileBounceGenerator;
import democretes.utils.handlers.KeyHandler;

public class ClientProxy extends CommonProxy{

	@Override
	public void initSounds() {

    }

	@Override
    public void initRenderers() {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileSubTerraGenerator.class, new RenderSubTerraGenerator());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileSolarGenerator.class, new RenderSolarGenerator());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileThermalGenerator.class, new RenderThermalGenerator()); 
    	ClientRegistry.bindTileEntitySpecialRenderer(TileBounceGenerator.class, new RenderTileBounceGenerator());
    	RenderIds.idGENERATOR = RenderingRegistry.getNextAvailableRenderId();
    	
    	ClientRegistry.bindTileEntitySpecialRenderer(TileMachtCoil.class, new RenderMachtCoil());
    	RenderIds.idCOIL = RenderingRegistry.getNextAvailableRenderId();
    	
    	
    	ClientRegistry.bindTileEntitySpecialRenderer(TileReconstructor.class, new RenderReconstructor());
    	
    	
    	ClientRegistry.bindTileEntitySpecialRenderer(TileItemTransfer.class, new RenderItemTransfer());
    	RenderIds.idTRANSFER = RenderingRegistry.getNextAvailableRenderId();
    	
		RenderingRegistry.registerBlockHandler(new RenderBlockGenerator());
		RenderingRegistry.registerBlockHandler(new RenderBlockCoil());
		RenderingRegistry.registerBlockHandler(new RenderBlockItemTransfer());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TilePurityAltar.class, new RenderPurityAltar());
    	RenderIds.idPURITY = RenderingRegistry.getNextAvailableRenderId();
    	

    }
    
	@Override
	public void registerKeyBindings() {
		keyHandler = new KeyHandler();
		FMLCommonHandler.instance().bus().register(keyHandler);
	}
	
	private boolean doParticle() {
		float chance = 1F;
		if(Minecraft.getMinecraft().gameSettings.particleSetting == 1) {
			chance = 0.6F;
		}else if(Minecraft.getMinecraft().gameSettings.particleSetting == 2) {
			chance = 0.2F;
		}
		return Math.random() < chance;
	}
	
	@Override
	public void orbFX(World world, double x, double y, double z, float r, float g, float b, float size, int maxAge, boolean shrink) {
		if(!doParticle()) {
			return;
		}
		FXOrb sparkle = new FXOrb(world, x, y, z, 0.0D, 0.0D, 0.0D, r, g, b, size, maxAge, shrink);
		Minecraft.getMinecraft().effectRenderer.addEffect(sparkle);
	}
	
	@Override
	public void orbFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ, float r, float g, float b, float size, int maxAge, boolean shrink) {
		if(!doParticle()) {
			return;
		}
		FXOrb sparkle = new FXOrb(world, x, y, z, motionX, motionY, motionZ, r, g, b, size, maxAge, shrink);
		Minecraft.getMinecraft().effectRenderer.addEffect(sparkle);
	}
}
