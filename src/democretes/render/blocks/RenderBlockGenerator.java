package democretes.render.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import democretes.block.generators.TileBounceGenerator;
import democretes.block.generators.TileDestructionGenerator;
import democretes.block.generators.TileMiniSpreader;
import democretes.block.generators.TilePurityGenerator;
import democretes.block.generators.TileRunicGenerator;
import democretes.block.generators.TileSolarGenerator;
import democretes.block.generators.TileSpreader;
import democretes.block.generators.TileSubTerraGenerator;
import democretes.block.generators.TileThermalGenerator;
import democretes.lib.RenderIds;

public class RenderBlockGenerator implements ISimpleBlockRenderingHandler{

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		TileEntityRendererDispatcher dispatch = TileEntityRendererDispatcher.instance;
		switch(meta) {
		case 0:	
			dispatch.renderTileEntityAt(new TileSolarGenerator(), 0.0D, 0.0D, 0.0D, 0.0F);break;
		case 1:
			GL11.glScalef(0.75F, 0.7F, 0.75F);
			GL11.glTranslatef(0, -0.3F, 0);
			dispatch.renderTileEntityAt(new TileSubTerraGenerator(), 0.0D, 0.0D, 0.0D, 0.0F);break;
		case 2:
			dispatch.renderTileEntityAt(new TilePurityGenerator(), 0.0D, 0.0D, 0.0D, 0.0F);break;
		case 3:
			dispatch.renderTileEntityAt(new TileSpreader(), 0.0D, 0.0D, 0.0D, 0.0F);break;
		case 4:
			dispatch.renderTileEntityAt(new TileRunicGenerator(), 0.0D, 0.0D, 0.0D, 0.0F);break;
		case 5:
			dispatch.renderTileEntityAt(new TileThermalGenerator(), 0.0D, 0.0D, 0.0D, 0.0F);break;
		case 6:
			dispatch.renderTileEntityAt(new TileDestructionGenerator(), 0.0D, 0.0D, 0.0D, 0.0F);break;
		case 7:
			dispatch.renderTileEntityAt(new TileMiniSpreader(), 0.0D, 0.0D, 0.0D, 0.0F);break;
		case 9:
			dispatch.renderTileEntityAt(new TileBounceGenerator(), 0.0D, 0.0D, 0.0D, 0.0F);
		}
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public int getRenderId() {
		return RenderIds.idGENERATOR;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}
}
